package com.basi;
public class Item{
	abstract class ItemData {
		private int id;
		private String name;
		private String description;	


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

	}

	public class ConsumableItemData extends ItemData{

		private Skill itemSkill;
		private int quantity;

		public ConsumableItemData(int id, String name, String description) {
			super(id, name, description);

		}

		public void setItemSkill(Skill skill){
			this.itemSkill = skill;
		}

		public Skill getItemSkill(){
			return itemSkill;
		}
		
		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public void useOn(CharacterData objective){
			itemSkill.use(objective);
			setQuantity(getQuantity()-1);
		}

		@Override
		public String toString() {
			return "ConsumableItemData [itemSkill=" + itemSkill + ", quantity="
					+ quantity + ", getId()=" + getId() + ", getName()="
					+ getName() + ", getDescription()=" + getDescription()
					+ "]";
		}
	}
}

