package a12030638_basic;

import java.util.*;
import java.util.stream.Collectors;

public class FoilVehicleCard extends VehicleCard {
	private Set<Category> specials;

	public FoilVehicleCard(final String name, final Map<Category, Double> categories, final Set<Category> specials) {
		super(name, categories);
		if (specials == null || specials.isEmpty() || specials.size() > 3)
			throw new IllegalArgumentException("FoilVehicleCard: specials null, empty or < 3");
		if (specials.stream().anyMatch(Objects::isNull)) {
			throw new IllegalArgumentException("FoilVehicleCard: specials has null value");
		}
		this.specials = new HashSet<>(specials);
	}

	public Set<Category> getSpecials() {
		return new HashSet<>(specials);
	}

	@Override
	public int totalBonus() {
		return super.totalBonus() + (int) getCategories().entrySet().stream().filter(x -> specials.contains(x.getKey()))
				.mapToDouble(x -> Math.abs(x.getValue())).sum();
	}

	public String toString() {
		return "- " + getName() + "(" + totalBonus() + ") -> {" + Arrays.stream(Category.values()).map(x -> {
			if (getSpecials().contains(x))
				return "*" + x.toString() + "*=" + getCategories().get(x);
			else
				return x.toString() + "=" + getCategories().get(x);
		}).collect(Collectors.joining(", ")) + "}";
	}
}
