package exter.foundry.integration;

        import exter.foundry.api.FoundryUtils;
        import exter.foundry.api.recipe.matcher.OreMatcher;
        import exter.foundry.fluid.FoundryFluids;
        import exter.foundry.recipes.manager.MoldRecipeManager;
        import exter.foundry.api.recipe.matcher.ItemStackMatcher;
        import net.minecraftforge.fml.common.Loader;
        import net.minecraftforge.fml.relauncher.Side;
        import net.minecraftforge.fml.relauncher.SideOnly;
        import ic2.api.item.IC2Items;
        import net.minecraft.init.Items;
        import net.minecraft.item.ItemStack;
        import net.minecraftforge.common.config.Configuration;
        import net.minecraftforge.fluids.Fluid;
        import net.minecraftforge.fluids.FluidStack;
        import exter.foundry.util.FoundryMiscUtils;
        import exter.foundry.api.FoundryAPI;
        import exter.foundry.config.FoundryConfig;
        import exter.foundry.item.FoundryItems;
        import exter.foundry.item.ItemMold;
        import exter.foundry.material.MaterialRegistry;
        import exter.foundry.recipes.manager.MeltingRecipeManager;

public class ModIntegrationIC2 implements IModIntegration {

    @Override
    public void onPreInit(Configuration config)
    {
    }

    @Override
    public void onInit()
    {
        MoldRecipeManager.instance.addRecipe(FoundryItems.mold(ItemMold.SubItem.CABLE_IC2), 6, 6, new int[]
                {
                        0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0,
                        2, 2, 2, 2, 2, 2,
                        2, 2, 2, 2, 2, 2,
                        0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0
                });
        MoldRecipeManager.instance.addRecipe(FoundryItems.mold(ItemMold.SubItem.INSULATED_CABLE_IC2), 6, 6, new int[]
                {
                        0, 0, 0, 0, 0, 0,
                        0, 1, 1, 1, 1, 0,
                        2, 2, 2, 2, 2, 2,
                        2, 2, 2, 2, 2, 2,
                        0, 1, 1, 1, 1, 0,
                        0, 0, 0, 0, 0, 0
                });
        MoldRecipeManager.instance.addRecipe(FoundryItems.mold(ItemMold.SubItem.CASING_IC2), 6, 6, new int[]
                {
                        2, 2, 2, 0, 2, 2,
                        2, 2, 2, 0, 2, 2,
                        2, 2, 2, 0, 2, 2,
                        0, 0, 0, 0, 0, 0,
                        2, 2, 2, 2, 2, 0,
                        2, 2, 2, 2, 2, 0
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
        MaterialRegistry.instance.registerTypeIcon("Casing", ItemStack.copyItemStack(IC2Items.getItem("casing", "copper")));
        MaterialRegistry.instance.registerTypeIcon("Cable", ItemStack.copyItemStack(IC2Items.getItem("cable","type:copper,insulation:0")));
    }

    @Override
    public void onPostInit()
    {
        if(!Loader.isModLoaded("IC2"))
        {
            return;
        }

        ItemStack bronze_pickaxe = ItemStack.copyItemStack(IC2Items.getItem("bronze_pickaxe"));
        ItemStack bronze_axe = ItemStack.copyItemStack(IC2Items.getItem("bronze_axe"));
        ItemStack bronze_shovel = ItemStack.copyItemStack(IC2Items.getItem("bronze_shovel"));
        ItemStack bronze_hoe = ItemStack.copyItemStack(IC2Items.getItem("bronze_hoe"));
        ItemStack bronze_sword = ItemStack.copyItemStack(IC2Items.getItem("bronze_sword"));
        ItemStack bronze_helmet = ItemStack.copyItemStack(IC2Items.getItem("bronze_helmet"));
        ItemStack bronze_chestplate = ItemStack.copyItemStack(IC2Items.getItem("bronze_chestplate"));
        ItemStack bronze_leggings = ItemStack.copyItemStack(IC2Items.getItem("bronze_leggings"));
        ItemStack bronze_boots = ItemStack.copyItemStack(IC2Items.getItem("bronze_boots"));

        ItemStack copper_cable = ItemStack.copyItemStack(IC2Items.getItem("cable","type:copper,insulation:0"));
        ItemStack tin_cable = ItemStack.copyItemStack(IC2Items.getItem("cable","type:tin,insulation:0"));
        ItemStack gold_cable = ItemStack.copyItemStack(IC2Items.getItem("cable","type:gold,insulation:0"));
        ItemStack iron_cable = ItemStack.copyItemStack(IC2Items.getItem("cable","type:iron,insulation:0"));

        ItemStack copper_casing = ItemStack.copyItemStack(IC2Items.getItem("casing", "copper"));
        ItemStack tin_casing = ItemStack.copyItemStack(IC2Items.getItem("casing", "tin"));
        ItemStack bronze_casing = ItemStack.copyItemStack(IC2Items.getItem("casing", "bronze"));
        ItemStack gold_casing = ItemStack.copyItemStack(IC2Items.getItem("casing", "gold"));
        ItemStack iron_casing = ItemStack.copyItemStack(IC2Items.getItem("casing", "iron"));
        ItemStack lead_casing = ItemStack.copyItemStack(IC2Items.getItem("casing", "lead"));
        ItemStack steel_casing = ItemStack.copyItemStack(IC2Items.getItem("casing", "steel"));

        ItemStack copper_cable_insulated = ItemStack.copyItemStack(IC2Items.getItem("cable", "type:copper,insulation:1"));
        ItemStack tin_cable_insulated = ItemStack.copyItemStack(IC2Items.getItem("cable", "type:tin,insulation:1"));
        ItemStack gold_cable_insulated1x = ItemStack.copyItemStack(IC2Items.getItem("cable", "type:gold,insulation:1"));
        ItemStack iron_cable_insulated1x = ItemStack.copyItemStack(IC2Items.getItem("cable", "type:iron,insulation:1"));
        ItemStack gold_cable_insulated2x = ItemStack.copyItemStack(IC2Items.getItem("cable", "type:gold,insulation:2"));
        ItemStack iron_cable_insulated2x = ItemStack.copyItemStack(IC2Items.getItem("cable", "type:iron,insulation:2"));
        ItemStack iron_cable_insulated3x = ItemStack.copyItemStack(IC2Items.getItem("cable", "type:iron,insulation:3"));

        Fluid liquid_bronze = FoundryFluids.liquid_bronze;
        Fluid liquid_copper = FoundryFluids.liquid_copper;
        Fluid liquid_tin = FoundryFluids.liquid_tin;
        Fluid liquid_gold = FoundryFluids.liquid_gold;
        Fluid liquid_iron = FoundryFluids.liquid_iron;
        Fluid liquid_lead = FoundryFluids.liquid_lead;
        Fluid liquid_steel = FoundryFluids.liquid_steel;
        Fluid liquid_rubber = FoundryFluids.liquid_rubber;

            if(FoundryConfig.recipe_equipment)
            {
                OreMatcher extra_sticks1 = new OreMatcher("stickWood",1);
                OreMatcher extra_sticks2 = new OreMatcher("stickWood",2);

                FoundryMiscUtils.registerCasting(bronze_chestplate, new FluidStack(liquid_bronze, FoundryAPI.FLUID_AMOUNT_INGOT * 8), ItemMold.SubItem.CHESTPLATE, null);
                FoundryMiscUtils.registerCasting(bronze_pickaxe, new FluidStack(liquid_bronze, FoundryAPI.FLUID_AMOUNT_INGOT * 3), ItemMold.SubItem.PICKAXE, extra_sticks2);
                FoundryMiscUtils.registerCasting(bronze_axe, new FluidStack(liquid_bronze, FoundryAPI.FLUID_AMOUNT_INGOT * 3), ItemMold.SubItem.AXE, extra_sticks2);
                FoundryMiscUtils.registerCasting(bronze_shovel, new FluidStack(liquid_bronze, FoundryAPI.FLUID_AMOUNT_INGOT), ItemMold.SubItem.SHOVEL, extra_sticks2);
                FoundryMiscUtils.registerCasting(bronze_sword, new FluidStack(liquid_bronze, FoundryAPI.FLUID_AMOUNT_INGOT * 2), ItemMold.SubItem.SWORD, extra_sticks1);
                FoundryMiscUtils.registerCasting(bronze_hoe, new FluidStack(liquid_bronze, FoundryAPI.FLUID_AMOUNT_INGOT * 2), ItemMold.SubItem.HOE, extra_sticks2);
                FoundryMiscUtils.registerCasting(bronze_leggings, new FluidStack(liquid_bronze, FoundryAPI.FLUID_AMOUNT_INGOT * 7), ItemMold.SubItem.LEGGINGS, null);
                FoundryMiscUtils.registerCasting(bronze_helmet, new FluidStack(liquid_bronze, FoundryAPI.FLUID_AMOUNT_INGOT * 5), ItemMold.SubItem.HELMET, null);
                FoundryMiscUtils.registerCasting(bronze_boots, new FluidStack(liquid_bronze, FoundryAPI.FLUID_AMOUNT_INGOT * 4), ItemMold.SubItem.BOOTS, null);

            }

            ItemStackMatcher rubber = new ItemStackMatcher(ItemStack.copyItemStack(IC2Items.getItem("crafting", "rubber")));
            ItemStackMatcher resin = new ItemStackMatcher(ItemStack.copyItemStack(IC2Items.getItem("misc_resource", "resin")));

            MeltingRecipeManager.instance.addRecipe(rubber, new FluidStack(liquid_rubber,FoundryAPI.FLUID_AMOUNT_INGOT),460,100);
        MeltingRecipeManager.instance.addRecipe(resin, new FluidStack(liquid_rubber,FoundryAPI.FLUID_AMOUNT_INGOT * 2),640,150);
            FoundryUtils.registerBasicMeltingRecipes( "resin", liquid_rubber);

            FoundryMiscUtils.registerCasting(copper_cable, new FluidStack(liquid_copper, FoundryAPI.FLUID_AMOUNT_INGOT / 3), ItemMold.SubItem.CABLE_IC2, null);
            FoundryMiscUtils.registerCasting(tin_cable, new FluidStack(liquid_tin, FoundryAPI.FLUID_AMOUNT_INGOT / 3), ItemMold.SubItem.CABLE_IC2, null);
            FoundryMiscUtils.registerCasting(gold_cable, new FluidStack(liquid_gold, FoundryAPI.FLUID_AMOUNT_INGOT / 4), ItemMold.SubItem.CABLE_IC2, null);
            FoundryMiscUtils.registerCasting(iron_cable, new FluidStack(liquid_iron, FoundryAPI.FLUID_AMOUNT_INGOT / 4), ItemMold.SubItem.CABLE_IC2, null);

            FoundryMiscUtils.registerCasting(copper_cable_insulated, new FluidStack(liquid_rubber, FoundryAPI.FLUID_AMOUNT_INGOT), ItemMold.SubItem.INSULATED_CABLE_IC2, new ItemStackMatcher(copper_cable));
            FoundryMiscUtils.registerCasting(tin_cable_insulated, new FluidStack(liquid_rubber, FoundryAPI.FLUID_AMOUNT_INGOT), ItemMold.SubItem.INSULATED_CABLE_IC2, new ItemStackMatcher(tin_cable));
            FoundryMiscUtils.registerCasting(gold_cable_insulated2x, new FluidStack(liquid_rubber, FoundryAPI.FLUID_AMOUNT_INGOT * 2), ItemMold.SubItem.INSULATED_CABLE_IC2, new ItemStackMatcher(gold_cable));
            FoundryMiscUtils.registerCasting(iron_cable_insulated3x, new FluidStack(liquid_rubber, FoundryAPI.FLUID_AMOUNT_INGOT * 3), ItemMold.SubItem.INSULATED_CABLE_IC2, new ItemStackMatcher(iron_cable));


            FoundryMiscUtils.registerCasting(copper_casing, new FluidStack(liquid_copper, FoundryAPI.FLUID_AMOUNT_INGOT / 2), ItemMold.SubItem.CASING_IC2, null);
            FoundryMiscUtils.registerCasting(tin_casing, new FluidStack(liquid_tin, FoundryAPI.FLUID_AMOUNT_INGOT / 2), ItemMold.SubItem.CASING_IC2, null);
            FoundryMiscUtils.registerCasting(bronze_casing, new FluidStack(liquid_bronze, FoundryAPI.FLUID_AMOUNT_INGOT / 2), ItemMold.SubItem.CASING_IC2, null);
            FoundryMiscUtils.registerCasting(gold_casing, new FluidStack(liquid_gold, FoundryAPI.FLUID_AMOUNT_INGOT / 2), ItemMold.SubItem.CASING_IC2, null);
            FoundryMiscUtils.registerCasting(iron_casing, new FluidStack(liquid_iron, FoundryAPI.FLUID_AMOUNT_INGOT / 2), ItemMold.SubItem.CASING_IC2, null);
            FoundryMiscUtils.registerCasting(lead_casing, new FluidStack(liquid_lead, FoundryAPI.FLUID_AMOUNT_INGOT / 2), ItemMold.SubItem.CASING_IC2, null);
            FoundryMiscUtils.registerCasting(steel_casing, new FluidStack(liquid_steel, FoundryAPI.FLUID_AMOUNT_INGOT / 2), ItemMold.SubItem.CASING_IC2, null);

//           FoundryMiscUtils.RegisterMoldRecipe(ItemMold.ItemMold.SubItem.CABLE_IC2_IC2_SOFT, copper_cable);
//           FoundryMiscUtils.RegisterMoldRecipe(ItemMold.ItemMold.SubItem.CABLE_IC2_IC2_SOFT, tin_cable);
//           FoundryMiscUtils.RegisterMoldRecipe(ItemMold.ItemMold.SubItem.CABLE_IC2_IC2_SOFT, gold_cable);
//           FoundryMiscUtils.RegisterMoldRecipe(ItemMold.ItemMold.SubItem.CABLE_IC2_IC2_SOFT, iron_cable);

//           FoundryMiscUtils.RegisterMoldRecipe(ItemMold.ItemMold.SubItem.INSULATED_CABLE_IC2_IC2_SOFT, copper_cable_insulated);
//           FoundryMiscUtils.RegisterMoldRecipe(ItemMold.ItemMold.SubItem.INSULATED_CABLE_IC2_IC2_SOFT, tin_cable_insulated);
//           FoundryMiscUtils.RegisterMoldRecipe(ItemMold.ItemMold.SubItem.INSULATED_CABLE_IC2_IC2_SOFT, gold_cable_insulated);
//           FoundryMiscUtils.RegisterMoldRecipe(ItemMold.ItemMold.SubItem.INSULATED_CABLE_IC2_IC2_SOFT, iron_cable_insulated);

//           FoundryMiscUtils.RegisterMoldRecipe(ItemMold.ItemMold.SubItem.CASING_IC2_IC2_SOFT, copper_casing);
//           FoundryMiscUtils.RegisterMoldRecipe(ItemMold.ItemMold.SubItem.CASING_IC2_IC2_SOFT, tin_casing);
//           FoundryMiscUtils.RegisterMoldRecipe(ItemMold.ItemMold.SubItem.CASING_IC2_IC2_SOFT, bronze_casing);
//           FoundryMiscUtils.RegisterMoldRecipe(ItemMold.ItemMold.SubItem.CASING_IC2_IC2_SOFT, gold_casing);
//           FoundryMiscUtils.RegisterMoldRecipe(ItemMold.ItemMold.SubItem.CASING_IC2_IC2_SOFT, iron_casing);
//           FoundryMiscUtils.RegisterMoldRecipe(ItemMold.ItemMold.SubItem.CASING_IC2_IC2_SOFT, lead_casing);
//           FoundryMiscUtils.RegisterMoldRecipe(ItemMold.ItemMold.SubItem.CASING_IC2_IC2_SOFT, steel_casing);

//           MaterialRegistry.instance.RegisterItem(copper_casing, "Copper", "Casing");
//           MaterialRegistry.instance.RegisterItem(tin_casing, "Tin", "Casing");
//           MaterialRegistry.instance.RegisterItem(bronze_casing, "Bronze", "Casing");
//           MaterialRegistry.instance.RegisterItem(gold_casing, "Gold", "Casing");
//           MaterialRegistry.instance.RegisterItem(iron_casing, "Iron", "Casing");
//           MaterialRegistry.instance.RegisterItem(lead_casing, "Lead", "Casing");
//           MaterialRegistry.instance.RegisterItem(steel_casing, "Steel", "Casing");

//           MaterialRegistry.instance.RegisterItem(copper_cable, "Copper", "Cable");
//           MaterialRegistry.instance.RegisterItem(tin_cable, "Tin", "Cable");
//           MaterialRegistry.instance.RegisterItem(gold_cable, "Gold", "Cable");
//           MaterialRegistry.instance.RegisterItem(iron_cable, "Iron", "Cable");

    }

    @Override
    public String getName()
    {
        return "IC2";
    }

    @Override
    public void onAfterPostInit()
    {

    }
}