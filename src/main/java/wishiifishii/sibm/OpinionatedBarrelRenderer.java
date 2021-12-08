package wishiifishii.sibm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.LivingEntity;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

@OnlyIn(Dist.CLIENT)
public class OpinionatedBarrelRenderer extends TileEntityRenderer<OpinionatedBarrelTileEntity> {
	
    private final Minecraft mc = Minecraft.getInstance();
    private final ItemRenderer itemRenderer = mc.getItemRenderer();
    private final FontRenderer fontRenderer = mc.fontRenderer;
    private Logger LOGGER = LogManager.getLogger();
    
    public OpinionatedBarrelRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
	}

	@Override
	public void render(OpinionatedBarrelTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn,
		IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
		if(!tileEntityIn.isEmpty()) {
			matrixStackIn.push();
				
				/*
				 * Orient ourselves to the block's direction
				 * North: matrixStackIn.translate(0.3D, 0.45D, -0.01D);
				 */
				double face_offset_x;
				double face_offset_y;
				double face_offset_z;
				Direction frontDirection = tileEntityIn.getBlockState().get(BlockStateProperties.FACING);
				
				if(frontDirection == Direction.EAST) {
					matrixStackIn.rotate(new Quaternion(new Vector3f(0.0F, 1.0F, 0.0F), -90, true));	
					
					face_offset_x = 0.3D;
					face_offset_y = 0.45D;
					face_offset_z = -1.01D;
				} else if (frontDirection == Direction.SOUTH) {
					matrixStackIn.rotate(new Quaternion(new Vector3f(0.0F, 1.0F, 0.0F), 180, true));
					
					face_offset_x = -0.7D;
					face_offset_y = 0.45D;
					face_offset_z = -1.01D;
				} else if (frontDirection == Direction.WEST) {
					matrixStackIn.rotate(new Quaternion(new Vector3f(0.0F, 1.0F, 0.0F), 90, true));
					
					face_offset_x = -0.7D;
					face_offset_y = 0.45D;
					face_offset_z = -0.01D;
				} else {
					//Direction == North :)
					//No rotate, MC orients towards north by default
					face_offset_x = 0.3D;
					face_offset_y = 0.45D;
					face_offset_z = -0.01D;
				}
	
				matrixStackIn.translate(face_offset_x, face_offset_y, face_offset_z);
				//Render our item
				//do NOT use renderItem() due to a lighting bug associated with it
				//manually grab the item's model and render it!
				matrixStackIn.push();
					matrixStackIn.scale(0.4F, 0.4F, 0.01F);
					
					IBakedModel ibakedmodel = this.itemRenderer.getItemModelWithOverrides(tileEntityIn.getItemStack(), mc.world, (LivingEntity)null);
					this.itemRenderer.renderModel(ibakedmodel, tileEntityIn.getItemStack(), combinedLightIn, combinedOverlayIn, matrixStackIn, bufferIn.getBuffer(RenderType.getCutoutMipped()));
				matrixStackIn.pop();
		
				//make the item count string and render it
				int itemCount = tileEntityIn.getCount();
				if(itemCount > 0 && !tileEntityIn.isEmpty()) {
					int maxCount = tileEntityIn.getMaxStackCount();
					if(maxCount > 0) {
						int stackCount = itemCount / maxCount;
						int leftoverCount = itemCount % maxCount;
						String firstPart = stackCount > 0 ? stackCount + "x" + maxCount : "";
						String secondPart = maxCount == 1 ? String.valueOf(stackCount) : String.valueOf(leftoverCount);
						String displayText = "";
						if(maxCount == 1) {
							displayText = secondPart;
						} else {
							if(stackCount > 0) {
								displayText = firstPart;
							}
							if(leftoverCount > 0) {
								if(!displayText.equals("")) {
									displayText += " + ";
								}
								displayText += secondPart;
							}
						}
						
						matrixStackIn.push();
							matrixStackIn.rotate(new Quaternion(new Vector3f(1.0f, 0.0f, 0.0f), 180, true));
							matrixStackIn.rotate(new Quaternion(new Vector3f(0.0f, 1.0f, 0.0f), 180, true));
							int strWidth = fontRenderer.getStringWidth(displayText);
							float strWidthf = (float) strWidth;
							matrixStackIn.translate(-0.2D, 0.1D, 0.005D);
							matrixStackIn.scale(0.01F, 0.01F, 0.01F);
							fontRenderer.drawString(matrixStackIn, displayText, -(strWidthf / 2f), 0f, 0);
						matrixStackIn.pop();
					}
				}
			matrixStackIn.pop();
		}
		
	}
    
    
}
