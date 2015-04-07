package com.basi;
public class Item{
	abstract class ItemData {
		private int id;
		private String name;
		private String description;	
		private int quantity;
		private String Sprite;


		public ItemData(int id, String name, String description) {
			super();
			this.id = id;
			this.name = name;
			this.description = description;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
		
		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public String getSprite() {
			return Sprite;
		}

		public void setSprite(String sprite) {
			Sprite = sprite;
		}


	}

	public class ConsumableItemData extends ItemData{

		private Skill itemSkill;
		

		public ConsumableItemData(int id, String name, String description) {
			super(id, name, description);

		}

		public void setItemSkill(Skill skill){
			this.itemSkill = skill;
		}

		public Skill getItemSkill(){
			return itemSkill;
		}
		
		
		public void useOn(CharacterData objective){
			itemSkill.use(objective);
			setQuantity(getQuantity()-1);
		}

		@Override
		public String toString() {
			return "ConsumableItemData [itemSkill=" + itemSkill + ", quantity="
					+ getQuantity() + ", getId()=" + getId() + ", getName()="
					+ getName() + ", getDescription()=" + getDescription()
					+ "]";
		}
	}
	
	public class EquippableItemData extends ItemData{
		
		private int i_hp;
		private int i_mp;
		private int i_atk;
		private int i_def;
		private int i_agi;
		private int i_int;
		private int type;
		
		/**
		 * @param id
		 * @param name
		 * @param description
		 */
		public EquippableItemData(int id, String name, String description) {
			super(id, name, description);
			
		}
		
		
		public int getI_atk() {
			return i_atk;
		}
		public void setI_atk(int i_atk) {
			this.i_atk = i_atk;
		}
		public int getI_def() {
			return i_def;
		}
		public void setI_def(int i_def) {
			this.i_def = i_def;
		}
		public int getI_agi() {
			return i_agi;
		}
		public void setI_agi(int i_agi) {
			this.i_agi = i_agi;
		}
		public int getI_int() {
			return i_int;
		}
		public void setI_int(int i_int) {
			this.i_int = i_int;
		}
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}


		public int getI_hp() {
			return i_hp;
		}


		public void setI_hp(int i_hp) {
			this.i_hp = i_hp;
		}


		public int getI_mp() {
			return i_mp;
		}


		public void setI_mp(int i_mp) {
			this.i_mp = i_mp;
		}	
	}
	
	public class KeyItemData extends ItemData{

		/**
		 * @param id
		 * @param name
		 * @param description
		 */
		public KeyItemData(int id, String name, String description) {
			super(id, name, description);
			
		}

	}
	
}

