package a11920352;

import java.util.HashMap;
import java.util.Map;

public class VehicleCard implements Comparable<VehicleCard>{
	public enum Category{
		WEIGHT_LBS("Gewicht[lbs]"){
			@Override
			public boolean isInverted() {return true;}
		},
		DISPLACEMENT_CCM("Hubraum[cc]"),
		POWER_HP("Leistung[hp]"),
		CYLINDERS_CNT("Zylinder"),
		ACCELERATION("Beschleunigung"){
			@Override
			public boolean isInverted() {return true;}
		}, 
		ECONOMY_MPG("Miles/Galon"), 
		YEAR("Baujahr[19xx]");
		
		private final String categoryName;
		
		private Category(final String categoryName) {
			if (categoryName == null || categoryName.isEmpty())
				throw new IllegalArgumentException("String is empty or null.");
			this.categoryName = categoryName;
		}
		
		public boolean isInverted() {
			return false;
		}
		
		public int bonus(final Double value) {/* returns -value if this.isInverted, value otherwise*/
			int ret = value.intValue();
			if(this.isInverted()) {
				return -ret;
			} else {
				return ret;
			}
		}
		
		@Override
		public String toString() {return categoryName;}
		
	
	}
	
	private String name;
	private Map<Category, Double> categories;
	
	public VehicleCard(final String name, final Map<Category, Double> categories) {
		if (name == null || name.isEmpty())
			throw new IllegalArgumentException("VehicleCard: name is empty or null.");
		if (categories == null)
			throw new IllegalArgumentException("VehicleCard: categories is null.");
		for (Category cat : Category.values()) {
		    if (!categories.containsKey(cat))
		    	throw new IllegalArgumentException("VehicleCard: does not contain all keys.");
		    if (categories.get(cat) == null || categories.get(cat) < 0)
		    	throw new IllegalArgumentException("VehicleCard: cat is null or < 0");
		} // throws IllegalArgumentException if categories contains any null value or values less than 0.
		this.categories = new HashMap<>(categories);;
		this.name = name;
	}
	
	//getters for _imutable_ class, no setters (!)
	public String getName() {return name;}
	
	public Map<Category, Double> getCategories(){/*returns shallow copy (!) of this.categories*/
		return new HashMap<>(categories);
	}
	
	public static Map<Category, Double> newMap(
			double economy, double cylinders, double displacement, double power, double weight, double acceleration, double year){
		//factory method to create a new vehicle card's categories map
		Map<Category, Double> retmap = new HashMap<>();
		retmap.put(Category.ECONOMY_MPG, economy);
		retmap.put(Category.CYLINDERS_CNT, cylinders);
		retmap.put(Category.DISPLACEMENT_CCM, displacement);
		retmap.put(Category.POWER_HP, power);
		retmap.put(Category.WEIGHT_LBS, weight);
		retmap.put(Category.ACCELERATION, acceleration);
		retmap.put(Category.YEAR, year);
		
		return retmap;
	}
	
	@Override
	public int compareTo(final VehicleCard other) {
		if(this.totalBonus() > other.totalBonus())
			return 1;
		else if(this.totalBonus() < other.totalBonus())
			return -1;
		return 0;
	}
	public int totalBonus() {
		int bonus = 0;
		for (var entry : this.getCategories().entrySet()) {
			bonus += entry.getKey().bonus(entry.getValue());
		}
		return bonus;
	}
	@Override
	public int hashCode() { //hash name and total Bonus (hint: Objects-class)
		return name.hashCode() + this.totalBonus();
	}
	@Override
	public boolean equals(Object obj) {
		return obj != null && getClass() == obj.getClass() && getName().equals(((VehicleCard) obj).getName()) && totalBonus() == ((VehicleCard) obj).totalBonus();
	}
	@Override
	public String toString() {
		String result = "- " + name + "(" + this.totalBonus() + ") -> {";
		boolean first = true;
		for (Category cat : Category.values()) {
			if(first)
				first = false;
			else
				result += ", ";
			result += cat.toString() + "=" + categories.get(cat);
		}
		return result + "}";
	}

	public int compareByCategory(VehicleCard other, Category category) {
//		return categories.get(category).compareTo(other.categories.get(category));
		return Double.compare(
				category.bonus(getCategories().get(category)),
				category.bonus(other.getCategories().get(category))
		);
	}
}
