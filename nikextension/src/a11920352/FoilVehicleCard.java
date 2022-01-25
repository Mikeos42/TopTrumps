package a11920352;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


public class FoilVehicleCard extends VehicleCard{
	
	private Set<Category> specials;
	
	public FoilVehicleCard(final String name, final Map<Category, Double> categories, final Set<Category> specials) {
		super(name,categories);
		if (specials == null || specials.isEmpty() || specials.size() > 3)
			throw new IllegalArgumentException("FoilVehicleCard: specials is empty, null or size > 3.");
		if (specials.stream().anyMatch(Objects::isNull))
			throw new IllegalArgumentException("special contains null");
		Set<Category> temp = specials;
		this.specials = temp;
	}
	
	public Set<Category> getSpecials(){ // returns shallow copy (!) of this.specials
		//Set<Category> copy = new HashSet<>(specials);
		return new HashSet<>(specials);
	}
	
	@Override
	public int totalBonus() {//for each special category add the (*absolute*) value of the corresponding category to the previously calculated totalBonus.
		int bonus = super.totalBonus();
		for (var entry : this.getCategories().entrySet()) {
			boolean contains = specials.contains(entry.getKey());
			if(contains)
				bonus += entry.getValue();
		}
		return bonus;
	}
	
	public String toString() {
		String result = "- " + this.getName() + "(" + this.totalBonus() + ") -> {";
		boolean first = true;
		Map<Category, Double> categories = new HashMap<>(this.getCategories());
		
		for (Category cat : Category.values()) {
			if(first)
				first = false;
			else
				result += ", ";

			if(specials.contains(cat))
				result += "*" + cat.toString() + "*=" + categories.get(cat);
			else
				result += cat.toString() + "=" + categories.get(cat);
		}
		
		return result + "}";
	}
}
