package a12030638;

import java.util.Random;

public class RndStrategy implements Strategy{
    @Override
    public VehicleCard.Category chooseCategory(VehicleCard vehicleCard) {
        Random random = new Random();
        VehicleCard.Category[] values =
                vehicleCard.getCategories().keySet().toArray(new VehicleCard.Category[0]);
        return values[random.nextInt(values.length)];
    }
}
