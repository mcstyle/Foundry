package exter.foundry.integration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import exter.foundry.api.FoundryAPI;
import exter.foundry.api.FoundryUtils;
import exter.foundry.block.BlockFoundryMachine;
import exter.foundry.block.FoundryBlocks;
import exter.foundry.item.FoundryItems;
import exter.foundry.item.ItemFoundryComponent;
import exter.foundry.item.ItemMold;
import exter.foundry.recipes.manager.AlloyMixerRecipeManager;
import exter.foundry.recipes.manager.CastingRecipeManager;
import exter.foundry.recipes.manager.MeltingRecipeManager;
import exter.foundry.registry.LiquidMetalRegistry;
import exter.foundry.util.FoundryMiscUtils;

@SuppressWarnings("unused")
public class ModIntegrationGregtech extends ModIntegration
{
  public boolean change_recipes;
  
  public ModIntegrationGregtech(String mod_name)
  {
    super(mod_name);
  }

  @Override
  public void OnPreInit(Configuration config)
  {
    Map<String,Fluid> fluids = new HashMap<String,Fluid>();
    fluids.put("StainlessSteel",LiquidMetalRegistry.instance.RegisterLiquidMetal( "StainlessSteel", 1900, 15));
    fluids.put("Kanthal",LiquidMetalRegistry.instance.RegisterLiquidMetal( "Kanthal", 1900, 15));
    fluids.put("Nichrome",LiquidMetalRegistry.instance.RegisterLiquidMetal( "Nichrome", 1950, 15));
    for(Map.Entry<String, Fluid> entry:fluids.entrySet())
    {
      FoundryUtils.RegisterBasicMeltingRecipes(entry.getKey(), entry.getValue());
    }
    change_recipes = config.get("integration", "gregtech.change_recipes", true).getBoolean(true);
  }

  @Override
  public void OnInit()
  {
  }
  
  @SuppressWarnings("unchecked")
  @Override
  public void OnPostInit()
  {
    if(!Loader.isModLoaded("gregtech"))
    {
      is_loaded = false;
      return;
    }
    ItemStack iron_stack = new ItemStack(Items.iron_ingot);
    ItemStack redstone_stack = new ItemStack(Items.redstone);
    ItemStack furnace_stack = new ItemStack(Blocks.furnace);
    ItemStack casing_stack = new ItemStack(FoundryBlocks.block_refractory_casing);
    ItemStack foundrybrick_stack = new ItemStack(FoundryItems.item_component,1,ItemFoundryComponent.COMPONENT_FOUNDRYBRICK);
    ItemStack glasspane_stack = new ItemStack(Blocks.glass_pane);
    ItemStack emptycontainer2_stack = FoundryItems.item_container.EmptyContainer(2);

    if(change_recipes)
    {
      ItemStack heating_coil = new ItemStack(FoundryItems.item_component,1,ItemFoundryComponent.COMPONENT_HEATINGCOIL);
      ItemStack heating_coil2 = new ItemStack(FoundryItems.item_component,2,ItemFoundryComponent.COMPONENT_HEATINGCOIL);
      ItemStack machine_icf = new ItemStack(FoundryBlocks.block_machine,1,BlockFoundryMachine.MACHINE_ICF);
      ItemStack machine_infuser = new ItemStack(FoundryBlocks.block_machine,1,BlockFoundryMachine.MACHINE_INFUSER);
      
      List<IRecipe> toremove = new ArrayList<IRecipe>();
      for(Object obj:CraftingManager.getInstance().getRecipeList())
      {
        if(obj instanceof IRecipe)
        {
          IRecipe res = (IRecipe)obj;
          ItemStack out = res.getRecipeOutput();
          if(out != null)
          {
            if(out.isItemEqual(heating_coil))
            {
              toremove.add(res);
            }
            if(out.isItemEqual(casing_stack))
            {
              toremove.add(res);
            }
            if(out.isItemEqual(emptycontainer2_stack))
            {
              toremove.add(res);
            }
          }
        }
      }
      CraftingManager.getInstance().getRecipeList().removeAll(toremove);
      
      GameRegistry.addRecipe(new ShapedOreRecipe(
          heating_coil2,
          "WWW",
          "WRW",
          "WWW",
          'W', "wireGt02Cupronickel",
          'R', redstone_stack));

      GameRegistry.addRecipe(new ShapedOreRecipe(
          casing_stack, 
          "IBI",
          "B B",
          "IBI",
          'I', "plateSteel",
          'B', foundrybrick_stack));

      GameRegistry.addRecipe(new ShapedOreRecipe(
          machine_infuser,
          "IRI",
          "GCG",
          "HRH",
          'I', iron_stack, 
          'R', redstone_stack, 
          'B', foundrybrick_stack,
          'C', casing_stack,
          'G', "gearStone",
          'H', "craftingHeatingCoilTier00"));

      GameRegistry.addRecipe(new ShapedOreRecipe(
          emptycontainer2_stack,
          " T ",
          "BGB",
          " T ",
          'T', "plateTin", 
          'B', foundrybrick_stack,
          'G', glasspane_stack));
    }
    
    Fluid liquid_copper = LiquidMetalRegistry.instance.GetFluid("Copper");
    Fluid liquid_nickel = LiquidMetalRegistry.instance.GetFluid("Nickel");
    Fluid liquid_iron = LiquidMetalRegistry.instance.GetFluid("Iron");
    Fluid liquid_manganese = LiquidMetalRegistry.instance.GetFluid("Manganese");
    Fluid liquid_chromium = LiquidMetalRegistry.instance.GetFluid("Chromium");
    Fluid liquid_aluminum = LiquidMetalRegistry.instance.GetFluid("Aluminum");
    
    Fluid liquid_stainless_steel = LiquidMetalRegistry.instance.GetFluid("StainlessSteel");
    Fluid liquid_cupronickel = LiquidMetalRegistry.instance.GetFluid("Cupronickel");
    Fluid liquid_kanthal = LiquidMetalRegistry.instance.GetFluid("Kanthal");
    Fluid liquid_nichrome = LiquidMetalRegistry.instance.GetFluid("Nichrome");

    /* TODO
    AlloyRecipeManager.instance.AddRecipe(new FluidStack(liquid_stainless_steel,18),
        new FluidStack[] {
          new FluidStack(liquid_iron,12),
          new FluidStack(liquid_nickel,2),
          new FluidStack(liquid_manganese,2),
          new FluidStack(liquid_chromium,2)
    });

    AlloyRecipeManager.instance.AddRecipe(new FluidStack(liquid_kanthal,12),
        new FluidStack[] {
          new FluidStack(liquid_iron,4),
          new FluidStack(liquid_aluminum,4),
          new FluidStack(liquid_chromium,4)
    });

    AlloyRecipeManager.instance.AddRecipe(new FluidStack(liquid_nichrome,15),
        new FluidStack[] {
          new FluidStack(liquid_nickel,12),
          new FluidStack(liquid_chromium,3)
    });
    */
    
    ItemStack ingot_mold = new ItemStack(FoundryItems.item_mold,1,ItemMold.MOLD_INGOT);
    ItemStack block_mold = new ItemStack(FoundryItems.item_mold,1,ItemMold.MOLD_BLOCK);
    
    CastingRecipeManager.instance.AddRecipe("ingotStainlessSteel", new FluidStack(liquid_stainless_steel,FoundryAPI.FLUID_AMOUNT_INGOT), ingot_mold, null);
    CastingRecipeManager.instance.AddRecipe("ingotCupronickel", new FluidStack(liquid_cupronickel,FoundryAPI.FLUID_AMOUNT_INGOT), ingot_mold, null);
    CastingRecipeManager.instance.AddRecipe("ingotKanthal", new FluidStack(liquid_kanthal,FoundryAPI.FLUID_AMOUNT_INGOT), ingot_mold, null);
    CastingRecipeManager.instance.AddRecipe("ingotNichrome", new FluidStack(liquid_nichrome,FoundryAPI.FLUID_AMOUNT_INGOT), ingot_mold, null);

    CastingRecipeManager.instance.AddRecipe("blockStainlessSteel", new FluidStack(liquid_stainless_steel,FoundryAPI.FLUID_AMOUNT_BLOCK), block_mold, null);
    CastingRecipeManager.instance.AddRecipe("blockCupronickel", new FluidStack(liquid_cupronickel,FoundryAPI.FLUID_AMOUNT_BLOCK), block_mold, null);
    CastingRecipeManager.instance.AddRecipe("blockKanthal", new FluidStack(liquid_kanthal,FoundryAPI.FLUID_AMOUNT_BLOCK), block_mold, null);
    CastingRecipeManager.instance.AddRecipe("blockNichrome", new FluidStack(liquid_nichrome,FoundryAPI.FLUID_AMOUNT_BLOCK), block_mold, null);

    
    for(String name:LiquidMetalRegistry.instance.GetFluidNames())
    {
      Fluid fluid = LiquidMetalRegistry.instance.GetFluid(name);
      RegisterMetalRecipes(name, fluid);
    }
    RegisterMetalRecipes("Chrome",LiquidMetalRegistry.instance.GetFluid("Chromium"));
    RegisterMetalRecipes("Aluminium",LiquidMetalRegistry.instance.GetFluid("Aluminum"));
  }

  private void RegisterMetalRecipes(String partial_name, Fluid fluid)
  {
    ItemStack plate_mold = new ItemStack(FoundryItems.item_mold,1,ItemMold.MOLD_PLATE_IC2);
    FoundryMiscUtils.RegisterMoldRecipe(ItemMold.MOLD_PLATE_IC2_SOFT, "plate" + partial_name);
    MeltingRecipeManager.instance.AddRecipe("plate" + partial_name, new FluidStack(fluid,FoundryAPI.FLUID_AMOUNT_INGOT));
    MeltingRecipeManager.instance.AddRecipe("dustSmall" + partial_name, new FluidStack(fluid,FoundryAPI.FLUID_AMOUNT_INGOT / 4));
    MeltingRecipeManager.instance.AddRecipe("dustTiny" + partial_name, new FluidStack(fluid,FoundryAPI.FLUID_AMOUNT_INGOT / 9));
    CastingRecipeManager.instance.AddRecipe("plate" + partial_name, new FluidStack(fluid,FoundryAPI.FLUID_AMOUNT_INGOT), plate_mold, null);
  }
}
