package com.hbm.render.block;

import org.lwjgl.opengl.GL11;

import com.hbm.lib.RefStrings;
import com.hbm.main.ResourceManager;
import com.hbm.render.model.ModelSteelRoof;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.obj.WavefrontObject;

public class RenderScaffoldBlock implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {

		GL11.glPushMatrix();
        Tessellator tessellator = Tessellator.instance;
        IIcon iicon = block.getIcon(0, 0);
		tessellator.setColorOpaque_F(1, 1, 1);

        if (renderer.hasOverrideBlockTexture())
        {
            iicon = renderer.overrideBlockTexture;
        }
        
        GL11.glTranslated(0, -0.5, 0);
        tessellator.startDrawingQuads();
		ObjUtil.renderWithIcon((WavefrontObject) ResourceManager.scaffold, iicon, tessellator, 0, false);
		tessellator.draw();
		
        GL11.glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		
        /*Tessellator.instance.draw();
        
		GL11.glPushMatrix();

		GL11.glTranslatef(x, y, z);
		
        ModelSteelRoof model = new ModelSteelRoof();
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(RefStrings.MODID + ":" + "textures/models/SteelRoof.png"));
        model.renderModel(0.0625F);
        
        GL11.glPopMatrix();
        
        Tessellator.instance.startDrawing(0);*/
        
        Tessellator tessellator = Tessellator.instance;
        IIcon iicon = block.getIcon(0, world.getBlockMetadata(x, y, z));
		
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
		tessellator.setColorOpaque_F(1, 1, 1);

        if (renderer.hasOverrideBlockTexture())
        {
            iicon = renderer.overrideBlockTexture;
        }
        
        float rotation = (float) -Math.PI;
        
        if(world.getBlockMetadata(x, y, z) < 4)
        	rotation = -90F / 180F * (float)Math.PI;
        
        tessellator.addTranslation(x + 0.5F, y, z + 0.5F);
		ObjUtil.renderWithIcon((WavefrontObject) ResourceManager.scaffold, iicon, tessellator, rotation, true);
        tessellator.addTranslation(-x - 0.5F, -y, -z - 0.5F);
        
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return 334078;
	}

}