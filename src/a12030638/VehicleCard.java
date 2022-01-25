package a12030638;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class VehicleCard implements Comparable<VehicleCard> {
	public enum Category {
		ECONOMY_MPG("Miles/Galon"), DISPLACEMENT_CCM("Hubraum[cc]"), WEIGHT_LBS("Gewicht[lbs]") {
			@Override
			public boolean isInverted() {
				return true;
			}
		},
		ACCELERATION("Beschleunigung") {
			@Override
			public boolean isInverted() {
				return true;
			}
		},
		CYLINDERS_CNT("Zylinder"), YEAR("Baujahr[19xx]"), POWER_HP("Leistung[hp]");

		private final String categoryName;

		private Category(final String categoryName) {
			if (categoryName == null || categoryName.isEmpty())
				throw new IllegalArgumentException("Category: name invalid");
			this.categoryName = categoryName;
		}

		public boolean isInverted() {
			return false;
		}

		public int bonus(final Double value) {
			return (int) (isInverted() ? -value : value);
		}

		@Override
		public String toString() {
			return categoryName;
		}
	}

	private String name;
	private Map<Category, Double> categories;

	public VehicleCard(final String name, final Map<Category, Double> categories) {
		if (name == null || name.isEmpty() || categories == null || categories.isEmpty())
			throw new IllegalArgumentException("VehicleCard: name or categories null or empty");

		for (Category cat : Category.values()) {
			if (!categories.containsKey(cat))
				throw new IllegalArgumentException("VehicleCard: not all values exist");
			if (categories.get(cat) == null || categories.get(cat) < 0)
				throw new IllegalArgumentException("VehicleCard: categories has illegal value");
		}

		this.name = name;
		this.categories = new HashMap<>(categories);

	}

	public String getName() {
		return name;
	}

	public Map<Category, Double> getCategories() {
		return new HashMap<>(categories);
	}

	public static Map<Category, Double> newMap(double economy, double cylinders, double displacement, double power,
			double weight, double acceleration, double year) {
		return new HashMap<>(
				Map.of(Category.ECONOMY_MPG, economy, Category.CYLINDERS_CNT, cylinders, Category.DISPLACEMENT_CCM,
				displacement, Category.POWER_HP, power, Category.WEIGHT_LBS, weight, Category.ACCELERATION,
				acceleration, Category.YEAR, year)
		);
	}

	@Override
	public int compareTo(final VehicleCard other) {
		return Integer.compare(totalBonus(), other.totalBonus());
	}

	public int totalBonus() {
		return categories.entrySet().stream().mapToInt(x -> x.getKey().bonus(x.getValue())).sum();
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, totalBonus());
	}

	@Override
	public boolean equals(Object obj) {
		return obj.getClass() == this.getClass() && Objects.equals(name, ((VehicleCard) obj).getName())
				&& ((VehicleCard) obj).totalBonus() == this.totalBonus();
	}

	@Override
	public String toString() {
		return "- " + getName() + "(" + totalBonus() + ") -> {" + Arrays.stream(Category.values())
				.map(x -> x.toString() + "=" + getCategories().get(x)).collect(Collectors.joining(", ")) + "}";
	}

	public int compareByCategory(VehicleCard other, Category category) {
//		return categories.get(category).compareTo(other.categories.get(category));
		var ret = Double.compare(
				category.bonus(getCategories().get(category)),
				category.bonus(other.getCategories().get(category))
		);
		//System.out.println("ret = " + ret);
		return ret;
	}
}
