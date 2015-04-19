package com.mrmakeit.smes;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = SMESConsole.MODID, version = SMESConsole.VERSION)
public class SMESConsole
{
    public static final String MODID = "smesconsole";
    public static final String VERSION = "1.0";
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	Block console = new ConsoleBlock(Material.iron);
		RecipeSorter.register(this.MODID+":shaped", WrapShapedRecipe.class, Category.SHAPED, "after:minecraft:shaped");
		RecipeSorter.register(this.MODID+":shapless", WrapShapelessRecipe.class, Category.SHAPED, "after:minecraft:shapeless");
		GameRegistry.registerBlock(console, "consoleBlock");
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
    	CraftingManager instance = CraftingManager.getInstance();
    	ArrayList recipes = (ArrayList) CraftingManager.getInstance().getRecipeList();
    	ArrayList newRecipes = new ArrayList();
    	for (int scan = 0; scan < recipes.size(); scan++)
        {
    		IRecipe tmpRecipe = (IRecipe) recipes.get(scan);
    		IRecipe wrappedRecipe = null;
    		if (tmpRecipe instanceof ShapedRecipes)
            {
    			String itemId = tmpRecipe.getRecipeOutput().getUnlocalizedName();
                ShapedRecipes recipe = (ShapedRecipes)tmpRecipe;
        		wrappedRecipe = new WrapShapedRecipe(recipe);
    			System.out.println("Wrapped Recipe for "+itemId);
        		recipes.remove(scan);
        		newRecipes.add(wrappedRecipe);
            }
    		if (tmpRecipe instanceof ShapelessRecipes)
            {
    			String itemId = tmpRecipe.getRecipeOutput().getUnlocalizedName();
                ShapelessRecipes recipe = (ShapelessRecipes)tmpRecipe;
        		wrappedRecipe = new WrapShapelessRecipe(recipe);
    			System.out.println("Wrapped Recipe for "+itemId);
        		recipes.remove(scan);
        		newRecipes.add(wrappedRecipe);
            }
    	}
    	for (int scan = 0; scan < newRecipes.size(); scan++){
    		GameRegistry.addRecipe((IRecipe) newRecipes.get(scan));
    	}
    }
    
}
