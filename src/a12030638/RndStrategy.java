package a12030638;

import java.util.Random;

public class RndStrategy implements Strategy{
    @Override
    public VehicleCard.Category chooseCategory(VehicleCard vehicleCard) {
//        var el = vehicleCard.getCategories().keySet().toArray()[4];
//        System.out.println("el = " + el);
//        return (VehicleCard.Category) el;

        Random random = new Random();
        VehicleCard.Category[] values =
                vehicleCard.getCategories().keySet().toArray(new VehicleCard.Category[0]);
        return values[random.nextInt(values.length)];
    }
}
