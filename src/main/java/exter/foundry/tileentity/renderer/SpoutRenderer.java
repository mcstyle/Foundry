package exter.foundry.tileentity.renderer;

import org.lwjgl.opengl.GL11;

import exter.foundry.block.BlockRefractorySpout;
import exter.foundry.tileentity.TileEntityRefractorySpout;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SpoutRenderer extends TileEntitySpecialRenderer<TileEntityRefractorySpout>
{
  private void drawQuad(EnumFacing facing,TextureAtlasSprite texture,double x1,double y1,double x2,double y2,double z,int color)
  {
    drawQuad(facing,texture,x1,y1,x2,y2,z, 0, 0, color);
  }
  
  private void drawQuad(EnumFacing facing,TextureAtlasSprite texture,double x1,double y1,double x2,double y2,double z, double u, double v,int color)
  {
    color = 0xFFFFFFFF;
    float alpha = (color >> 24 & 255) / 255.0F;
    float red = (color >> 16 & 255) / 255.0F;
    float green = (color >> 8 & 255) / 255.0F;
    float blue = (color & 255) / 255.0F;

    double u1 = texture.getInterpolatedU(x1 + u);
    double v1 = texture.getInterpolatedV(y1 + v);
    double u2 = texture.getInterpolatedU(x2 + u);
    double v2 = texture.getInterpolatedV(y2 + v);
    
    x1 /= 16;
    y1 /= 16;
    x2 /= 16;
    y2 /= 16;
    z /= 16;

    VertexBuffer tessellator = Tessellator.getInstance().getBuffer();
    tessellator.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
    switch(facing)
    {
      case EAST:
      case WEST:
        tessellator.pos(z, y2, x1).tex(u1, v1).color(red, green, blue, alpha).endVertex();
        tessellator.pos(z, y2, x2).tex(u2, v1).color(red, green, blue, alpha).endVertex();
        tessellator.pos(z, y1, x2).tex(u2, v2).color(red, green, blue, alpha).endVertex();
        tessellator.pos(z, y1, x1).tex(u1, v2).color(red, green, blue, alpha).endVertex();
        break;
      case SOUTH:
      case NORTH:
        tessellator.pos(x2, y2, z).tex(u1, v1).color(red, green, blue, alpha).endVertex();
        tessellator.pos(x1, y2, z).tex(u2, v1).color(red, green, blue, alpha).endVertex();
        tessellator.pos(x1, y1, z).tex(u2, v2).color(red, green, blue, alpha).endVertex();
        tessellator.pos(x2, y1, z).tex(u1, v2).color(red, green, blue, alpha).endVertex();
        break;
      case UP:
      case DOWN:
        tessellator.pos(x2, z, y2).tex(u1, v2).color(red, green, blue, alpha).endVertex();
        tessellator.pos(x1, z, y2).tex(u2, v2).color(red, green, blue, alpha).endVertex();
        tessellator.pos(x1, z, y1).tex(u2, v1).color(red, green, blue, alpha).endVertex();
        tessellator.pos(x2, z, y1).tex(u1, v1).color(red, green, blue, alpha).endVertex();
        break;
    }    
    Tessellator.getInstance().draw();
  }

  
  @Override
  public void renderTileEntityAt(TileEntityRefractorySpout te, double x, double y, double z, float partialTicks, int destroyStage)
  {
    FluidStack fluid = te.getTank(1).getFluid();
    if(fluid != null)
    { 
      GL11.glPushMatrix();
      GlStateManager.disableLighting();
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
      GlStateManager.disableAlpha();
      GlStateManager.disableCull();
      bindTexture(TextureMap.locationBlocksTexture);
      GlStateManager.resetColor();
      GlStateManager.translate((float) (x + 0.5), (float) y, (float) (z + 0.5));
      switch(te.getWorld().getBlockState(te.getPos()).getValue(BlockRefractorySpout.FACING))
      {
        case EAST:
          GlStateManager.rotate(270, 0, 1, 0);
          break;
        case SOUTH:
          GlStateManager.rotate(180, 0, 1, 0);
          break;
        case WEST:
          GlStateManager.rotate(90, 0, 1, 0);
          break;
        default:
          break;        
      }
      GlStateManager.translate(-0.5f, 0, -0.5f);
      TextureAtlasSprite texture = Minecraft.getMinecraft().getTextureMapBlocks().getTextureExtry(fluid.getFluid().getFlowing(fluid).toString());
      
      double low = 0;
      BlockPos pos = te.getPos();
      if(pos.getY() > 0)
      {
        pos = pos.down();
        World world = te.getWorld();
        IBlockState state = world.getBlockState(pos.down());
        AxisAlignedBB bounds = state.getCollisionBoundingBox(world, pos);
        if(bounds != null)
        {
          low = bounds.minY * 16 - 15.75;
        }
        if(low > 0)
        {
          low = 0;
        }
        if(low < -16)
        {
          low = -16;
        }
      }

      int color = fluid.getFluid().getColor();
      drawQuad(EnumFacing.UP, texture, 7, 3, 9, 9, 8.75, color);
      drawQuad(EnumFacing.EAST, texture, 7, 0, 9, 8.75, 7, color);
      drawQuad(EnumFacing.WEST, texture, 7, 0, 9, 8.75, 9, color);
      drawQuad(EnumFacing.SOUTH, texture, 7, 0, 9, 8.75, 9, color);
      drawQuad(EnumFacing.NORTH, texture, 7, 0, 9, 5, 7, color);
      if(low < 0)
      {
        drawQuad(EnumFacing.EAST, texture, 7, low, 9, 0, 7, 0, 16, color);
        drawQuad(EnumFacing.WEST, texture, 7, low, 9, 0, 9, 0, 16, color);
        drawQuad(EnumFacing.SOUTH, texture, 7, low, 9, 0, 9, 0, 16, color);
        drawQuad(EnumFacing.NORTH, texture, 7, low, 9, 0, 7, 0, 16, color);
      }
      GlStateManager.enableCull();
      GlStateManager.enableAlpha();
      GlStateManager.enableLighting();
      GlStateManager.disableBlend();
      GL11.glPopMatrix();
    }
  }
}