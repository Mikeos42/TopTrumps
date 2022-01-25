package a11920352;

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
        int i = random.nextInt(values.length);
        System.out.println("i = " + i);
        return values[i];
    }
}
