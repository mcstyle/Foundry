package forestry.api.food;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public interface IBeverageEffect {
	int getId();

	void doEffect(World world, EntityPlayer player);

	String getDescription();
}
