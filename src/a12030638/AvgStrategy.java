package a12030638;

import java.util.*;

public class AvgStrategy implements Strategy {
    private List<VehicleCard> knownCards;
    private Map<VehicleCard.Category, Double> averages = new HashMap<>();

    public AvgStrategy() {
        this(new ArrayList<>());
    }

    public AvgStrategy(List<VehicleCard> knownCards) {
        this.knownCards = knownCards;
        VehicleCard.Category[] values = VehicleCard.Category.values();
        for (VehicleCard.Category c : values) {
            double v = knownCards.stream()
                    .mapToDouble(x -> c.bonus(x.getCategories().get(c)))
                    .sum() / knownCards.size();
            averages.put(c, v);
        }
    }

    @Override
    public VehicleCard.Category chooseCategory(VehicleCard vehicleCard) {
//        return vehicleCard.getCategories().entrySet().stream()
//                .max(Comparator.comparingDouble(x -> x.getKey().bonus(x.getValue())))
//                .get()
//                .getKey();

        for(var el : vehicleCard.getCategories().entrySet()) {
            VehicleCard.Category c = el.getKey();
            if(c.bonus(el.getValue()) > c.bonus(averages.get(c)))
                return c;
        }
        return new RndStrategy().chooseCategory(vehicleCard);
    }
}
