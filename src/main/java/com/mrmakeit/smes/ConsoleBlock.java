package com.mrmakeit.smes;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ConsoleBlock extends BlockContainer {
	ConsoleTileEntity inventory;
	public ConsoleBlock(Material p_i45394_1_) {
		super(p_i45394_1_);
		// TODO Auto-generated constructor stub
		setHardness(0.5F);
		setStepSound(Block.soundTypeMetal);
		setBlockName("consoleBlock");
		setCreativeTab(CreativeTabs.tabMisc);
		setBlockTextureName("smesconsole:consoleBlock");
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		// TODO Auto-generated method stub
		inventory = new ConsoleTileEntity();
		return inventory;
	}
	@Override
	public boolean onBlockActivated(World world, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
		if(world.isRemote){
			return true;
		}
		player.displayGUIChest((IInventory)inventory);
		return true;
    }
}
