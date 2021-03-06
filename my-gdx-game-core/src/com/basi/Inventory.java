package com.basi;

import java.util.ArrayList;
import java.util.Collection;
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
	 * Generic put method for Inventory with quantity.
	 * @param item
	 */
	public void put(ItemData item, int quantity){
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
	
	/**
	 * remove method for Inventory. If the item is present in the Inventory, it
	 * will remove that quantity of that item from the inventory. 
	 * If it isn't present, you'll get a null.
	 * 
	 * @param id
	 * @param quantity the quantity you want to remove from inventory
	 * @return an ItemData item.
	 */
	public ItemData remove(String id, int quantity){
		
		ItemData foundItem;
		if (consumables.containsKey(id)) {
			foundItem = consumables.get(id);

			
			if (foundItem.getQuantity()>quantity){
				foundItem.setQuantity(foundItem.getQuantity()-quantity);
				put(foundItem);
				return foundItem;
			}
			foundItem.setQuantity(0);
			return foundItem;
		}
		if (equips.containsKey(id)) {
			foundItem = equips.get(id);
			
			if (foundItem.getQuantity()>quantity){
				foundItem.setQuantity(foundItem.getQuantity()-quantity);
				put(foundItem);
				return foundItem;
			}
			foundItem.setQuantity(0);
			return foundItem;
		}
		if (keys.containsKey(id)) {
			foundItem = keys.get(id);
			
			if (foundItem.getQuantity()>quantity){
				foundItem.setQuantity(foundItem.getQuantity()-quantity);
				put(foundItem);
				return foundItem;
			}
			foundItem.setQuantity(0);
			return foundItem;
		}
		return null;
		
	}
	
	/**
	 * 
	 * @param itemType the desired items type array
	 * @return an arrayList of ItemData containing all the items of the specified itemType
	 */
	public ArrayList<ItemData> toArrayList(String itemType){
		ArrayList<ItemData> itemList = new ArrayList<ItemData>();
		Collection<ConsumableItemData> consumables;
		Collection<EquippableItemData> equips; 
		Collection<KeyItemData> keys; 
		
		if(itemType.equals("consumables")){
			consumables = this.consumables.values();
			for(ItemData item : consumables){
				itemList.add(item);
			}
			return itemList;
			
		} 
		if(itemType.equals("equips")){
			equips = this.equips.values();
			for(ItemData item : equips){
				itemList.add(item);
			}
			return itemList;
		}
		if(itemType.equals("keys")){
			keys = this.keys.values();
			for(ItemData item : keys){
				itemList.add(item);
			}
			return itemList;
		}
		consumables = this.consumables.values();
		for(ItemData item : consumables){
			itemList.add(item);
		}
		
		equips = this.equips.values();
		for(ItemData item : equips){
			itemList.add(item);
		}
		
		keys = this.keys.values();
		for(ItemData item : keys){
			itemList.add(item);
		}

		return itemList;
	}
	
	public ArrayList<String> getItemIds(){
		ArrayList<String> itemIds = new ArrayList<String>();
		itemIds.addAll(consumables.keySet());
		itemIds.addAll(equips.keySet());
		itemIds.addAll(keys.keySet());		
		return itemIds;
	}
	
	public int getQuantity(String id){
		ItemData foundItem;
		if (consumables.containsKey(id)) {
			foundItem = consumables.get(id);
			return foundItem.getQuantity();
		}
		if (equips.containsKey(id)) {
			foundItem = equips.get(id);
			return foundItem.getQuantity();
		}
		if (keys.containsKey(id)) {
			foundItem = keys.get(id);
			return foundItem.getQuantity();
		}
		return 0;
		
	}
}
