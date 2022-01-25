package a11920352;

import java.util.Comparator;
import java.util.Map;

public class AvgStrategy implements Strategy {
    @Override
    public VehicleCard.Category chooseCategory(VehicleCard vehicleCard) {
        return vehicleCard.getCategories().entrySet().stream()
                .max(Comparator.comparingDouble(x -> x.getKey().bonus(x.getValue())))
                .get()
                .getKey();
    }
}
