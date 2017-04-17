package exter.foundry.integration;


import exter.foundry.api.FoundryAPI;
import exter.foundry.api.recipe.matcher.ItemStackMatcher;
import exter.foundry.api.recipe.matcher.OreMatcher;
import exter.foundry.config.FoundryConfig;
import exter.foundry.fluid.FoundryFluids;
import exter.foundry.item.FoundryItems;
import exter.foundry.item.ItemMold;
import exter.foundry.recipes.manager.InfuserRecipeManager;
import exter.foundry.recipes.manager.MoldRecipeManager;
import exter.foundry.util.FoundryMiscUtils;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModIntegrationRailcraft implements IModIntegration {

    @Override
    public void onPreInit(Configuration config)
    {
    }

    @Override
    public void onInit()
    {
        MoldRecipeManager.instance.addRecipe(FoundryItems.mold(ItemMold.SubItem.CROWBAR_RAILCRAFT), 6, 6, new int[]
                {
                        0, 0, 0, 2, 0, 0,
                        0, 0, 0, 0, 2, 0,
                        0, 0, 0, 0, 2, 0,
                        0, 0, 0, 2, 0, 0,
                        0, 0, 2, 0, 0, 0,
                        0, 2, 0, 0, 0, 0
                });
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void onClientPreInit()
    {

    }

    @SideOnly(Side.CLIENT)
    @Override
    public void onClientInit()
    {

    }

    @SideOnly(Side.CLIENT)
    @Override
    public void onClientPostInit()
    {
    }

    private ItemStack getItemStack(String name)
    {
        return getItemStack(name,0);
    }
    private ItemStack getItemStack(String name,int meta)
    {
        Item item = Item.REGISTRY.getObject(new ResourceLocation("railcraft", name));
        if(item == null)
        {
            return null;
        }
        return new ItemStack(item,1,meta);
    }

    @Override
    public void onPostInit()
    {
        if(!Loader.isModLoaded("railcraft"))
        {
            return;
        }

        ItemStack steel_pickaxe = getItemStack("tool_pickaxe_steel");
        ItemStack steel_axe = getItemStack("tool_axe_steel");
        ItemStack steel_shovel = getItemStack("tool_shovel_steel");
        ItemStack steel_hoe = getItemStack("tool_hoe_steel");
        ItemStack steel_sword = getItemStack("tool_sword_steel");

        ItemStack steel_helmet = getItemStack("armor_helmet_steel");
        ItemStack steel_chestplate = getItemStack("armor_chestplate_steel");
        ItemStack steel_leggings = getItemStack("armor_leggings_steel");
        ItemStack steel_boots = getItemStack("armor_boots_steel");

        ItemStack iron_crowbar = getItemStack("tool_crowbar_iron");
        ItemStack steel_crowbar = getItemStack("tool_crowbar_steel");

        Fluid liquid_iron = FoundryFluids.liquid_iron;
        Fluid liquid_steel = FoundryFluids.liquid_steel;

            if(FoundryConfig.recipe_equipment)
            {
                ItemStack extra_sticks1 = new ItemStack(Items.STICK, 1);
                ItemStack extra_sticks2 = new ItemStack(Items.STICK, 2);
                ItemStack crowbar_siding = FoundryMiscUtils.getModItemFromOreDictionary("dyeRed", 4);

                FoundryMiscUtils.registerCasting(steel_chestplate, new FluidStack(liquid_steel, FoundryAPI.FLUID_AMOUNT_INGOT * 8), ItemMold.SubItem.CHESTPLATE, null);
                FoundryMiscUtils.registerCasting(steel_pickaxe, new FluidStack(liquid_steel, FoundryAPI.FLUID_AMOUNT_INGOT * 3), ItemMold.SubItem.PICKAXE, extra_sticks2);
                FoundryMiscUtils.registerCasting(steel_axe, new FluidStack(liquid_steel, FoundryAPI.FLUID_AMOUNT_INGOT * 3), ItemMold.SubItem.AXE, extra_sticks2);
                FoundryMiscUtils.registerCasting(steel_shovel, new FluidStack(liquid_steel, FoundryAPI.FLUID_AMOUNT_INGOT), ItemMold.SubItem.SHOVEL, extra_sticks2);
                FoundryMiscUtils.registerCasting(steel_sword, new FluidStack(liquid_steel, FoundryAPI.FLUID_AMOUNT_INGOT * 2), ItemMold.SubItem.SWORD, extra_sticks1);
                FoundryMiscUtils.registerCasting(steel_hoe, new FluidStack(liquid_steel, FoundryAPI.FLUID_AMOUNT_INGOT * 2), ItemMold.SubItem.HOE, extra_sticks2);
                FoundryMiscUtils.registerCasting(steel_leggings, new FluidStack(liquid_steel, FoundryAPI.FLUID_AMOUNT_INGOT * 7), ItemMold.SubItem.LEGGINGS, null);
                FoundryMiscUtils.registerCasting(steel_helmet, new FluidStack(liquid_steel, FoundryAPI.FLUID_AMOUNT_INGOT * 5), ItemMold.SubItem.HELMET, null);
                FoundryMiscUtils.registerCasting(steel_boots, new FluidStack(liquid_steel, FoundryAPI.FLUID_AMOUNT_INGOT * 4), ItemMold.SubItem.BOOTS, null);
                FoundryMiscUtils.registerCasting(iron_crowbar, new FluidStack(liquid_iron, FoundryAPI.FLUID_AMOUNT_INGOT * 3), ItemMold.SubItem.CROWBAR_RAILCRAFT, crowbar_siding);
                FoundryMiscUtils.registerCasting(steel_crowbar, new FluidStack(liquid_steel, FoundryAPI.FLUID_AMOUNT_INGOT * 3), ItemMold.SubItem.CROWBAR_RAILCRAFT, crowbar_siding);

            }
        ItemStackMatcher coal_coke = new ItemStackMatcher(getItemStack("fuel_coke"));
        ItemStackMatcher coal_coke_block = new ItemStackMatcher(getItemStack("generic", 6));

        if(coal_coke != null)
        {
            InfuserRecipeManager.instance.addRecipe(new FluidStack(FoundryFluids.liquid_steel,72), new FluidStack(FoundryFluids.liquid_iron,72), coal_coke, 160000);
        }

        if(coal_coke_block != null)
        {
            InfuserRecipeManager.instance.addRecipe(new FluidStack(FoundryFluids.liquid_steel,648), new FluidStack(FoundryFluids.liquid_iron,648), coal_coke_block, 160000);


    }}

    @Override
    public String getName()
    {
        return "railcraft";
    }

    @Override
    public void onAfterPostInit()
    {

    }
}