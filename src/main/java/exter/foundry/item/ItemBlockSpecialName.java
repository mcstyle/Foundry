package exter.foundry.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

/**
 * @author Vexatos
 */
public class ItemBlockSpecialName extends ItemBlock {

	public ItemBlockSpecialName(Block block) {
		super(block);
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		return ("" + I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack))).trim();
	}
}
