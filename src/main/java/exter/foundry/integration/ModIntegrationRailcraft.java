package exter.foundry.integration;

//import ic2.api.item.Items;

import exter.foundry.api.FoundryAPI;
import exter.foundry.api.FoundryUtils;
import exter.foundry.config.FoundryConfig;
import exter.foundry.fluid.FoundryFluids;
import exter.foundry.item.FoundryItems;
import exter.foundry.item.ItemMold;
import exter.foundry.material.MaterialRegistry;
import exter.foundry.recipes.manager.MoldRecipeManager;
import exter.foundry.util.FoundryMiscUtils;
import ic2.api.item.IC2Items;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
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

    @Override
    public void onPostInit()
    {
        if(!Loader.isModLoaded("Railcraft"))
        {
            return;
        }

        ItemStack steel_pickaxe = new ItemStack(GameRegistry.findItem("Railcraft", "tool.steel.pickaxe"));
        ItemStack steel_axe = new ItemStack(GameRegistry.findItem("Railcraft", "tool.steel.axe"));
        ItemStack steel_shovel = new ItemStack(GameRegistry.findItem("Railcraft", "tool.steel.shovel"));
        ItemStack steel_hoe = new ItemStack(GameRegistry.findItem("Railcraft", "tool.steel.hoe"));
        ItemStack steel_sword = new ItemStack(GameRegistry.findItem("Railcraft", "tool.steel.sword"));

        ItemStack steel_helmet = new ItemStack(GameRegistry.findItem("Railcraft", "armor.steel.helmet"));
        ItemStack steel_chestplate = new ItemStack(GameRegistry.findItem("Railcraft", "armor.steel.plate"));
        ItemStack steel_leggings = new ItemStack(GameRegistry.findItem("Railcraft", "armor.steel.legs"));
        ItemStack steel_boots = new ItemStack(GameRegistry.findItem("Railcraft", "armor.steel.boots"));

        Fluid liquid_iron = FoundryFluids.liquid_iron;
        Fluid liquid_steel = FoundryFluids.liquid_steel;

            if(FoundryConfig.recipe_equipment)
            {
                ItemStack extra_sticks1 = new ItemStack(Items.STICK, 1);
                ItemStack extra_sticks2 = new ItemStack(Items.STICK, 2);

                FoundryMiscUtils.registerCasting(steel_chestplate, new FluidStack(liquid_steel, FoundryAPI.FLUID_AMOUNT_INGOT * 8), ItemMold.SubItem.CHESTPLATE, null);
                FoundryMiscUtils.registerCasting(steel_pickaxe, new FluidStack(liquid_steel, FoundryAPI.FLUID_AMOUNT_INGOT * 3), ItemMold.SubItem.PICKAXE, extra_sticks2);
                FoundryMiscUtils.registerCasting(steel_axe, new FluidStack(liquid_steel, FoundryAPI.FLUID_AMOUNT_INGOT * 3), ItemMold.SubItem.AXE, extra_sticks2);
                FoundryMiscUtils.registerCasting(steel_shovel, new FluidStack(liquid_steel, FoundryAPI.FLUID_AMOUNT_INGOT), ItemMold.SubItem.SHOVEL, extra_sticks2);
                FoundryMiscUtils.registerCasting(steel_sword, new FluidStack(liquid_steel, FoundryAPI.FLUID_AMOUNT_INGOT * 2), ItemMold.SubItem.SWORD, extra_sticks1);
                FoundryMiscUtils.registerCasting(steel_hoe, new FluidStack(liquid_steel, FoundryAPI.FLUID_AMOUNT_INGOT * 2), ItemMold.SubItem.HOE, extra_sticks2);
                FoundryMiscUtils.registerCasting(steel_leggings, new FluidStack(liquid_steel, FoundryAPI.FLUID_AMOUNT_INGOT * 7), ItemMold.SubItem.LEGGINGS, null);
                FoundryMiscUtils.registerCasting(steel_helmet, new FluidStack(liquid_steel, FoundryAPI.FLUID_AMOUNT_INGOT * 5), ItemMold.SubItem.HELMET, null);
                FoundryMiscUtils.registerCasting(steel_boots, new FluidStack(liquid_steel, FoundryAPI.FLUID_AMOUNT_INGOT * 4), ItemMold.SubItem.BOOTS, null);

            }

    }

    @Override
    public String getName()
    {
        return "Railcraft";
    }

    @Override
    public void onAfterPostInit()
    {

    }
}