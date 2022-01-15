package a12030638;

import java.util.*;
import a12030638.VehicleCard.Category;

public class Main {

    public static void main(String[] args) {
//        Map<VehicleCard.Category, Double> weakAudiMap =
//                VehicleCard.newMap(10,10,10,10,20,20,10);
//        Map<VehicleCard.Category, Double> strongOpelMap =
//                VehicleCard.newMap(50,30,12,15,5,10,2);
//        Map<VehicleCard.Category, Double> specialTeslaMap =
//                VehicleCard.newMap(100, 60, 45, 22, 5, 6, 16);
//        Set<VehicleCard.Category> specialTeslaSpecials =
//                new HashSet<>();
//        specialTeslaSpecials.add(VehicleCard.Category.WEIGHT_LBS);
//        specialTeslaSpecials.add(VehicleCard.Category.ACCELERATION);
//
//        VehicleCard WeakAudi = new VehicleCard("WeakAudi Max", weakAudiMap);
//        VehicleCard StrongOpel = new VehicleCard("StrongOpel Fruit", strongOpelMap);
//        FoilVehicleCard SpecialTesla = new FoilVehicleCard("Amazing Tesla", specialTeslaMap, specialTeslaSpecials);
//
//        Player Miki = new Player("Miki");
//        Player Nik = new Player("Nik");
//        Player Jessica = new Player("Jessica");
//
//        Miki.addCard(WeakAudi);
//        Nik.addCard(StrongOpel);
//        Jessica.addCard(SpecialTesla);
//
//        System.out.println("\nRunde 1");
//        System.out.println(Miki);
//        System.out.println(Nik);
//        System.out.println(Jessica);
//
//        Miki.challengePlayer(Jessica);
//
//        System.out.println("\nRunde 2");
//        System.out.println(Miki);
//        System.out.println(Nik);
//        System.out.println(Jessica);

//        // TODO Auto-generated method stub
//        final VehicleCard ente = new VehicleCard("Ente", VehicleCard.newMap(20.4, 4., 375., 9., 1234., 21., 49));
//        final FoilVehicleCard amphicar = new FoilVehicleCard("Amphicar 770", VehicleCard.newMap(21.0, 4., 1147., 38., 2314, 14., 61.),
//                Set.of(VehicleCard.Category.DISPLACEMENT_CCM, VehicleCard.Category.WEIGHT_LBS));
//        final VehicleCard hund = new VehicleCard("Hund", VehicleCard.newMap(13.3, 2., 480., 14., 130., 12., 50));
//
//
//        Player otto = new Player("Otto");
//        Player anna = new Player("Anna");
//        Player hans = new Player("Hans");
//
//        hans.addCards(Arrays.asList(ente, ente, ente, hund, amphicar, amphicar, amphicar));
//        anna.addCards(Arrays.asList(ente, ente, amphicar));
//        otto.addCards(Arrays.asList(ente, ente, ente));
//
//        System.out.println("hans.hashCode() = " + hans.hashCode());
//        System.out.println("Objects.hashCode(hans) = " + Objects.hashCode(hans));
//        System.out.println("Objects.hash(hans, hans.getName()) = " + Objects.hash(hans.getScore(), hans.getName()));
//        System.out.println("Objects.hashCode(hans.getScore()) + Objects.hashCode(hans.getName()) = " + Objects.hashCode(hans.getScore()) + Objects.hashCode(hans.getName()));
        try {
            VehicleCard brum = new VehicleCard(null, VehicleCard.newMap(20.4, 4., 375., 9., 1234., 21., 49));
        } catch (IllegalArgumentException i) {
            System.out.println("Error 1");
        }

        try {
            VehicleCard wroom = new VehicleCard("", VehicleCard.newMap(20.4, 4., 375., 9., 1234., 21., 49));
        } catch (IllegalArgumentException i) {
            System.out.println("Error 2");
        }

        try {
            VehicleCard tut = new VehicleCard("Auto", VehicleCard.newMap(20.4, 4., 375., -9., 1234., 21., 49));
        } catch (IllegalArgumentException i) {
            System.out.println("Error 3");
        }

        try {
            Map<Category, Double> help = new HashMap<Category, Double>();
            help.put(Category.ACCELERATION, 2.);
            help.put(Category.POWER_HP, 2.);
            help.put(Category.WEIGHT_LBS, 2.);
            help.put(Category.DISPLACEMENT_CCM, 2.);

            VehicleCard car = new VehicleCard("Auto", help);
        } catch (IllegalArgumentException i) {
            System.out.println("Error 4");
        }

        try {
            Map<Category, Double> help = new HashMap<Category, Double>();
            help.put(Category.ACCELERATION, 2.);
            help.put(Category.POWER_HP, 2.);
            help.put(Category.WEIGHT_LBS, 2.);
            help.put(Category.DISPLACEMENT_CCM, 2.);
            help.put(Category.CYLINDERS_CNT, 2.);
            help.put(Category.ECONOMY_MPG, 2.);
            help.put(Category.YEAR, null);


            VehicleCard car = new VehicleCard("Auto", help);
        } catch (IllegalArgumentException i) {
            System.out.println("Error 5");
        }

        try {
            VehicleCard tut = new VehicleCard("Auto", null);
        } catch (IllegalArgumentException i) {
            System.out.println("Error 6");
        }

        try {
            FoilVehicleCard hehehe = new FoilVehicleCard("Amphicar 770", VehicleCard.newMap(21.0, 4., 1147., 38., 2314, 14., 61.), Set.of(VehicleCard.Category.DISPLACEMENT_CCM, VehicleCard.Category.WEIGHT_LBS, VehicleCard.Category.ACCELERATION, VehicleCard.Category.ECONOMY_MPG));

        } catch (IllegalArgumentException i) {
            System.out.println("Error 7");
        }

        try {
            FoilVehicleCard hehehe = new FoilVehicleCard("Amphicar 770", VehicleCard.newMap(21.0, 4., 1147., 38., 2314, 14., 61.), null);

        } catch (IllegalArgumentException i) {
            System.out.println("Error 8");
        }
        try {
            Player spieler = new Player("");
        } catch (IllegalArgumentException i) {
            System.out.println("Error 9");
        }

        try {
            Player spieler = new Player(null);
        } catch (IllegalArgumentException i) {
            System.out.println("Error 10");
        }
        try {
            Player spieler = new Player("Joe");
            spieler.challengePlayer(spieler);
        } catch (IllegalArgumentException i) {
            System.out.println("Error 11");
        }
        try {
            Player spieler = new Player("Joe");
            spieler.challengePlayer(null);
        } catch (IllegalArgumentException i) {
            System.out.println("Error 12");
        }


        //Bonus - steht nicht in der Angabe!
        try {
            Set<Category> n = new HashSet<Category>();
            n.add(VehicleCard.Category.DISPLACEMENT_CCM);
            n.add(null);
            FoilVehicleCard hehehe = new FoilVehicleCard("Amphicar 770", VehicleCard.newMap(21.0, 4., 1147., 38., 2314, 14., 61.), n);
        } catch (IllegalArgumentException i) {
            System.out.println("Error 13");
        }

    }

}
