package com.mrmakeit.smes;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class WrapShapedRecipe implements IRecipe {

	private IRecipe recipe;
	WrapShapedRecipe(IRecipe oldRecipe){
		this.recipe = oldRecipe;
		String modId = GameRegistry.findUniqueIdentifierFor(this.recipe.getRecipeOutput().getItem()).modId;
		String itemId = this.recipe.getRecipeOutput().getItem().getUnlocalizedName();
		System.out.println("Replacing Recipe: "+modId+":"+itemId);
	}
	@Override
	public boolean matches(InventoryCrafting p_77569_1_, World p_77569_2_) {
		// TODO Auto-generated method stub
		String modId = GameRegistry.findUniqueIdentifierFor(this.recipe.getRecipeOutput().getItem()).modId;
		if(modId.contains("minecraft")){
			return this.recipe.matches(p_77569_1_, p_77569_2_);
		}
		return false;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting p_77572_1_) {
		// TODO Auto-generated method stub
		return this.recipe.getCraftingResult(p_77572_1_);
	}

	@Override
	public int getRecipeSize() {
		// TODO Auto-generated method stub
		return this.recipe.getRecipeSize();
	}

	@Override
	public ItemStack getRecipeOutput() {
		// TODO Auto-generated method stub
		return this.recipe.getRecipeOutput();
	}
	
}
