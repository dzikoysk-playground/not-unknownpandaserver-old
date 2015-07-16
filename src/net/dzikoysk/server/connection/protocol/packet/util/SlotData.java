package net.dzikoysk.server.connection.protocol.packet.util;

import net.dzikoysk.server.world.ItemStack;

public class SlotData {

	private short id;
	private byte amount;
	private short damage;

	public SlotData(short id, byte amount, short damage){
		this.id = id;
		this.amount = amount;
		this.damage = damage;
	}

	public ItemStack getItemStack(){
		return new ItemStack(id, damage);
	}

	public short getId(){
		return this.id;
	}

	public byte getAmount(){
		return this.amount;
	}

	public short getDamage(){
		return this.damage;
	}

}
