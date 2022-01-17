package a12030638;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Map<VehicleCard.Category, Double> weakAudiMap =
                VehicleCard.newMap(10,10,10,10,20,20,10);
        Map<VehicleCard.Category, Double> strongOpelMap =
                VehicleCard.newMap(50,30,12,15,5,10,2);
        Map<VehicleCard.Category, Double> specialTeslaMap =
                VehicleCard.newMap(100, 60, 45, 22, 5, 6, 16);
        Set<VehicleCard.Category> specialTeslaSpecials =
                new HashSet<>();
        specialTeslaSpecials.add(VehicleCard.Category.WEIGHT_LBS);
        specialTeslaSpecials.add(VehicleCard.Category.ACCELERATION);

        VehicleCard WeakAudi = new VehicleCard("WeakAudi Max", weakAudiMap);
        VehicleCard StrongOpel = new VehicleCard("StrongOpel Fruit", strongOpelMap);
        FoilVehicleCard SpecialTesla = new FoilVehicleCard("Amazing Tesla", specialTeslaMap, specialTeslaSpecials);

        Player Miki = new Player("Miki");
        Player Nik = new Player("Nik");
        Player Jessica = new Player("Jessica");

        Miki.addCard(WeakAudi);
        Nik.addCard(StrongOpel);
        Jessica.addCard(SpecialTesla);

        System.out.printf("" + WeakAudi.compareByCategory(SpecialTesla, VehicleCard.Category.ACCELERATION));


    }
}
