package exter.foundry.block;

import java.util.List;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import exter.foundry.ModFoundry;
import exter.foundry.creativetab.FoundryTabMachines;
import exter.foundry.proxy.CommonFoundryProxy;
import exter.foundry.tileentity.TileEntityFoundry;
import exter.foundry.tileentity.TileEntityRefractoryTankBasic;
import exter.foundry.tileentity.renderer.ISpoutPourDepth;
import exter.foundry.util.FoundryMiscUtils;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRefractoryTankBasic extends BlockContainer implements ISpoutPourDepth
{
  public BlockRefractoryTankBasic()
  {
    super(Material.IRON);
    setSoundType(SoundType.STONE);
    setCreativeTab(FoundryTabMachines.tab);
    setHardness(1.0F);
    setResistance(8.0F);
    setUnlocalizedName("foundry.refractory_tank_basic");
    setRegistryName("refractory_tank_basic");
  }
  

  @Override
  public TileEntity createNewTileEntity(World world, int meta)
  {
    return new TileEntityRefractoryTankBasic();
  }

  @Override
  public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos from)
  {
    TileEntityFoundry te = (TileEntityFoundry) world.getTileEntity(pos);

    if(te != null)
    {
      te.updateRedstone();
    }
  }

  @Override
  public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitx, float hity, float hitz)
  {
    if(world.isRemote)
    {
      return true;
    } else
    {
      player.openGui(ModFoundry.instance, CommonFoundryProxy.GUI_REFRACTORYTANK, world, pos.getX(), pos.getY(), pos.getZ());
      return true;
    }
  }
  
  @Override
  public void breakBlock(World world, BlockPos pos, IBlockState state)
  {
    FoundryMiscUtils.breakTileEntityBlock(world, pos, state);
    super.breakBlock(world, pos, state);
  }

  @Override
  public EnumBlockRenderType getRenderType(IBlockState state)
  {
    return EnumBlockRenderType.MODEL;
  }

  @Override
  public boolean isFullCube(IBlockState state)
  {
    return false;
  }

  @Override
  public boolean isOpaqueCube(IBlockState state)
  {
    return false;
  }
  
  @Override
  public boolean isSideSolid(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side)
  {
    return true;
  }
  
  @SideOnly(Side.CLIENT)
  public BlockRenderLayer getBlockLayer()
  {
      return BlockRenderLayer.CUTOUT;
  }

  @SideOnly(Side.CLIENT)
  public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side)
  {
    return true;
  }

  @SideOnly(Side.CLIENT)
  @Override
  public int getSpoutPourDepth(World world, BlockPos pos, IBlockState state)
  {
    return 2;
  }
  
  @SideOnly(Side.CLIENT)
  @Override
  public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced)
  {
    FoundryMiscUtils.localizeTooltip("tooltip.foundry.refractory_tank_basic", tooltip);
  }
}
