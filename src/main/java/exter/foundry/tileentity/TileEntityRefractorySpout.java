package exter.foundry.tileentity;

import exter.foundry.block.BlockRefractorySpout;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileEntityRefractorySpout extends TileEntityFoundry implements IFluidHandler
{
  private FluidTank tank;
  private FluidTank fluid_moved;
  private FluidTankInfo[] tank_info;
  private int next_drain;
  private int next_fill;

  public TileEntityRefractorySpout()
  {

    next_drain = 12;
    next_fill = 3;

    tank = new FluidTank(250);
    fluid_moved = new FluidTank(10);
    tank_info = new FluidTankInfo[2];
    tank_info[0] = new FluidTankInfo(tank);
    tank_info[1] = new FluidTankInfo(fluid_moved);
  }

  @Override
  public void readFromNBT(NBTTagCompound compund)
  {
    super.readFromNBT(compund);

    if(compund.hasKey("next_drain"))
    {
      next_drain = compund.getInteger("next_drain");
    }
    if(compund.hasKey("next_fill"))
    {
      next_fill = compund.getInteger("next_fill");
    }
  }

  @Override
  public void writeToNBT(NBTTagCompound compound)
  {
    super.writeToNBT(compound);
    compound.setInteger("next_drain", next_drain);
    compound.setInteger("next_fill", next_fill);
  }

  @Override
  public int getSizeInventory()
  {
    return 0;
  }

  @Override
  public boolean isItemValidForSlot(int i, ItemStack itemstack)
  {
    return false;
  }

  @Override
  public int fill(EnumFacing from, FluidStack resource, boolean doFill)
  {
    if(from != EnumFacing.UP)
    {
      return 0;
    }
    if(doFill && resource != null && doFill)
    {
      next_drain = 12;
    }
    return fillTank(0, resource, doFill);
  }

  @Override
  public FluidStack drain(EnumFacing from, FluidStack resource, boolean doDrain)
  {
    return null;
  }

  @Override
  public FluidStack drain(EnumFacing from, int maxDrain, boolean doDrain)
  {
    return null;
  }

  @Override
  public boolean canFill(EnumFacing from, Fluid fluid)
  {
    return from == EnumFacing.UP;
  }

  @Override
  public boolean canDrain(EnumFacing from, Fluid fluid)
  {
    return false;
  }

  @Override
  public FluidTankInfo[] getTankInfo(EnumFacing from)
  {
    return tank_info;
  }

  @Override
  protected void updateClient()
  {

  }

  static private boolean areFluidStacksEqual(FluidStack a,FluidStack b)
  {
    if(a == null)
    {
      return b == null;
    }
    if(b == null)
    {
      return false;
    }
    
    return a.isFluidStackIdentical(b);
  }
  
  @Override
  protected void updateServer()
  {
    if(--next_drain == 0)
    {
      next_drain = 12;

      // Drain from the top TileEntity
      EnumFacing side = worldObj.getBlockState(getPos()).getValue(BlockRefractorySpout.FACING).facing;
      TileEntity source = worldObj.getTileEntity(getPos().add(side.getDirectionVec()));
      side = side.getOpposite();
      if(source instanceof IFluidHandler)
      {
        IFluidHandler hsource = (IFluidHandler) source;
        FluidStack drained = hsource.drain(side, 40, false);
        if(drained != null && !drained.getFluid().isGaseous(drained) && drained.getFluid().getDensity(drained) > 0)
        {
          drained.amount = tank.fill(drained, false);
          if(drained.amount > 0)
          {
            hsource.drain(side, drained, true);
            tank.fill(drained, true);
            updateTank(0);
            markDirty();
          }
        }
      }
    }

    if(--next_fill == 0)
    {
      next_fill = 3;
      
      FluidStack last_moved = fluid_moved.getFluid();
      fluid_moved.setFluid(null);

      // Fill to the /bottom
      if(tank.getFluid() != null && tank.getFluid().amount > 0)
      {
        TileEntity dest = worldObj.getTileEntity(getPos().add(0, -1, 0));
        if(dest instanceof IFluidHandler)
        {
          IFluidHandler hdest = (IFluidHandler) dest;
          if(hdest.canFill(EnumFacing.UP, tank.getFluid().getFluid()))
          {
            FluidStack drained = tank.drain(10, false);
            if(drained != null)
            {
              drained.amount = hdest.fill(EnumFacing.UP, drained, false);
              if(drained.amount > 0)
              {
                tank.drain(drained.amount, true);
                hdest.fill(EnumFacing.UP, drained, true);
                updateTank(0);
                fluid_moved.setFluid(drained.copy());
              }
            }
          }
        }
      }
      if(!areFluidStacksEqual(fluid_moved.getFluid(),last_moved))
      {
        updateTank(1);
      }
    }
  }

  @Override
  public FluidTank getTank(int slot)
  {
    switch(slot)
    {
      case 0:
        return tank;
      case 1:
        return fluid_moved;
    }
    return null;
  }

  @Override
  public int getTankCount()
  {
    return 2;
  }

  @Override
  protected void onInitialize()
  {

  }
}
