package a12030638;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class VehicleCard implements Comparable<VehicleCard> {
    public enum Category {
        ECONOMY_MPG("Miles/Galon"),
        DISPLACEMENT_CCM("Hubraum[cc]"),
        WEIGHT_LBS("Gewicht[lbs]"),
        ACCELERATION("Beschleunigung"),
        CYLINDERS_CNT("Zylinder"),
        YEAR("Baujahr[19xx]"),
        POWER_HP("Leistung[hp]");

        private final String categoryName;

        private Category(final String categoryName) {
            if(categoryName == null || categoryName.isEmpty()) {
                throw new IllegalArgumentException("Name unacceptable");
            }
            this.categoryName = categoryName;
        }

        public boolean isInverted() {
            return this == WEIGHT_LBS || this == ACCELERATION;
        }

        public int bonus(final Double value) {
            return (int) (isInverted() ? -value : value);
        }

        @Override
        public String toString() {
            return categoryName;
        }
    }

    private String name = "";
    private Map<Category, Double> categories;

    public VehicleCard(final String name, final Map<Category, Double> categories) {
        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name invalid!");
        }
        if(categories == null) {
            throw new IllegalArgumentException("Categories invalid!");
        }
        boolean hasAll = categories.keySet().stream()
                .allMatch(x -> Arrays.stream(Category.values()).anyMatch(y -> x == y));
        boolean hasNull = categories.values().stream().anyMatch(x -> x == null || x < 0);

        if(!hasAll) {
            throw new IllegalArgumentException("Not all Categories present");
        } else if (hasNull) {
            throw new IllegalArgumentException("Has illegal value in Categories");
        }

        this.name = name;
        this.categories = new HashMap<>(categories);
    }

    public String getName() { return name; }

    public Map<Category, Double> getCategories() {
        return new HashMap<>(categories);
    }

    public static Map<Category, Double> newMap(double economy, double cylinders, double displacement,
                                               double power, double weight, double acceleration, double year) {
        Map<Category, Double> ret = new HashMap<>();
        ret.put(Category.ECONOMY_MPG, economy);
        ret.put(Category.CYLINDERS_CNT, cylinders);
        ret.put(Category.DISPLACEMENT_CCM, displacement);
        ret.put(Category.POWER_HP, power);
        ret.put(Category.WEIGHT_LBS, weight);
        ret.put(Category.ACCELERATION, acceleration);
        ret.put(Category.YEAR, year);

        return ret;
    }

    @Override
    public int compareTo(final VehicleCard other) {
        return Integer.compare(totalBonus(), other.totalBonus());
    }
//    public int totalBonusOld() { // add inverted
//        double sum = 0;
//        for(Map.Entry<Category, Double> el : categories.entrySet()) {
//            sum += el.getKey().bonus(el.getValue());
//        }
//        return (int) sum;
//    }
    public int totalBonus() { // add inverted
        return categories.entrySet().stream().mapToInt(x -> x.getKey().bonus(x.getValue())).sum();
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(name) + Objects.hashCode(totalBonus());
    }
    @Override
    public boolean equals(Object obj) {
        return obj.getClass() == this.getClass()
                && Objects.equals(name, ((VehicleCard) obj).getName())
                && ((VehicleCard) obj).totalBonus() == this.totalBonus();
    }
    @Override
    public String toString() {
        return "- " + getName() + "(" + totalBonus() + ") -> {" +
                Arrays.stream(Category.values())
                .map(x -> x.toString() + "=" + getCategories().get(x))
                .collect(Collectors.joining(", ")) +
                "}";

//        return "- " + getName() + "(" + totalBonus() + ") -> {" + String.join(", ", cat) + "}";


    }
}
