package com.basi;

import java.util.HashMap;

import com.basi.Item.ConsumableItemData;
import com.basi.Item.EquippableItemData;
import com.basi.Item.ItemData;
import com.basi.Item.KeyItemData;

public class Inventory {
	private static HashMap<String, ConsumableItemData> consumables;
	private static HashMap<String, EquippableItemData> equips;
	private static HashMap<String, KeyItemData> keys;
	
	public Inventory() {
		consumables = new HashMap<String, ConsumableItemData>();
		equips = new HashMap<String, EquippableItemData>();
		keys = new HashMap<String, KeyItemData>();
	}
	
	/**
	 * Generic put method for Inventory.
	 * @param item
	 */
	public void put(ItemData item){
		if (item instanceof ConsumableItemData){
			put((ConsumableItemData)item);
			return;
		}
		if(item instanceof EquippableItemData){
			put((EquippableItemData)item);
			return;
		}
		if(item instanceof KeyItemData){
			put((KeyItemData)item);
			return;
		}
	}
	
	/**
	 * Specific put method for Inventory
	 * @param consumable 
	 */
	public void put(ConsumableItemData consumable){
		consumables.put(String.valueOf(consumable.getId()), consumable);
	}
	
	/**
	 * Specific put method for Inventory
	 * @param equip
	 */
	public void put(EquippableItemData equip){
		equips.put(String.valueOf(equip.getId()), equip);
	}
	
	/**
	 * Specific put method for Inventory
	 * @param key
	 */
	public void put(KeyItemData key){
		keys.put(String.valueOf(key.getId()), key);
	}
	
	/**
	 * get method for Inventory. If the item is present in the Inventory, it
	 * will return the item. If it isn't present, you'll get a null.
	 * 
	 * @param id
	 * @return an ItemData item.
	 */
	public ItemData get(String id){
		ItemData foundItem;
		if (consumables.containsKey(id)) {
			foundItem = consumables.get(id);
			return foundItem;
		}
		if (equips.containsKey(id)) {
			foundItem = equips.get(id);
			return foundItem;
		}
		if (keys.containsKey(id)) {
			foundItem = keys.get(id);
			return foundItem;
		}
		return null;
		
	}
	
	
}