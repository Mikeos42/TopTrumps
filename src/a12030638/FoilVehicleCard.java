package a12030638;

import java.util.*;
import java.util.stream.Collectors;

public class FoilVehicleCard extends VehicleCard {
    private Set<Category> specials;

    public FoilVehicleCard(final String name, final Map<Category, Double> categories, final Set<Category> specials) {
        super(name, categories);
        if(specials == null || specials.size() > 3 || specials.isEmpty()) {
            throw new IllegalArgumentException("Specials invalid!");
        }
        this.specials = new HashSet<>(specials);
    }

    public Set<Category> getSpecials() {
        return new HashSet<>(specials);
    }

    @Override
    public int totalBonus() {
        return super.totalBonus() + (int) getCategories().entrySet().stream()
                .filter(x -> specials.contains(x.getKey()))
                .mapToDouble(Map.Entry::getValue)
                .map(Math::abs)
                .sum();
    }

    public String toString() {
        return "- " + getName() + "(" + totalBonus() + ") -> {" +
                Arrays.stream(Category.values())
                        .map(x -> {
                            if (getSpecials().contains(x))
                                return "*" + x.toString() + "*=" + getCategories().get(x);
                            else
                                return x.toString() + "=" + getCategories().get(x);
                        })
                        .collect(Collectors.joining(", ")) +
                "}";
    }
}
