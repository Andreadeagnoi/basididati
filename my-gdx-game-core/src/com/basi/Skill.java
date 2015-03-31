package com.basi;

public class Skill {
	int id;
	String name;
	String description;
	int cost;
	int damage;
	
	public Skill(SkillBuilder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.description = builder.description;
		this.cost = builder.cost;
		this.damage = builder.damage;
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



	public int getCost() {
		return cost;
	}



	public void setCost(int cost) {
		this.cost = cost;
	}



	public int getDamage() {
		return damage;
	}



	public void setDamage(int damage) {
		this.damage = damage;
	}

	public void use(CharacterData objective){
		objective.setC_hp(objective.getC_hp()-damage);
		if(objective.getC_hp()<0) {
			objective.setC_hp(0);
		}
		if(objective.getC_hp()>objective.getC_MaxHp()) {
			objective.setC_hp(objective.getC_MaxHp());
		}
	}


	public static class SkillBuilder {
		int id;
		String name;
		String description;
		int cost;
		int damage;
		
		public SkillBuilder(int id) {
			this.id = id;
		}
		
		public SkillBuilder name(String name) {
			this.name = name;
			return this;
		}
		
		public SkillBuilder description(String description) {
			this.description = description;
			return this;
		}
		
		public SkillBuilder cost(int cost) {
			this.cost = cost;
			return this;
		}
		
		public SkillBuilder damage(int damage) {
			this.damage = damage;
			return this;
		}
		
		public Skill build() {
			return new Skill(this);
		}
	}


	@Override
	public String toString() {
		return "Skill [id=" + id + ", name=" + name + ", description="
				+ description + ", cost=" + cost + ", damage=" + damage + "]";
	}
}
