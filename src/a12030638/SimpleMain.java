package a12030638;

import java.util.Set;

import static java.util.Arrays.asList;

public class SimpleMain {

    public static void main(String[] args) {

        /**
         * NOTE: THIS ACTUALLY GUARANTEES NOTHING - WRITE YOUR OWN TESTS!
         */
        final VehicleCard ente = new VehicleCard("Ente", VehicleCard.newMap(20.4, 4., 375., 9., 1234., 21., 49));
        final FoilVehicleCard amphicar = new FoilVehicleCard("Amphicar 770", VehicleCard.newMap(21.0, 4., 1147., 38., 2314, 14., 61.), Set.of(VehicleCard.Category.DISPLACEMENT_CCM, VehicleCard.Category.WEIGHT_LBS));

        System.out.println(ente);
        System.out.println(amphicar);
        /**
         * Expected Output:
         - Ente(-798) -> {Gewicht[lbs]=1234.0, Hubraum[cc]=375.0, Leistung[hp]=9.0, Zylinder=4.0, Beschleunigung=21.0, Miles/Galon=20.4, Baujahr[19xx]=49.0}
         - Amphicar 770(2404) -> {*Gewicht[lbs]*=2314.0, *Hubraum[cc]*=1147.0, Leistung[hp]=38.0, Zylinder=4.0, Beschleunigung=14.0, Miles/Galon=21.0, Baujahr[19xx]=61.0}
         */

        Player otto = new Player("Otto");
        Player anna = new Player("Anna");

        anna.addCards(asList(ente, ente, amphicar));
        otto.addCards(asList(ente, ente, ente));

        System.out.println(otto.challengePlayer(anna));
        System.out.println(anna);
        System.out.println(otto);

        /**
         * Expected Output (order of Anna's cards may vary):
           false
           Anna(-1586):
           - Ente(-798) -> {Gewicht[lbs]=1234.0, Hubraum[cc]=375.0, Leistung[hp]=9.0, Zylinder=4.0, Beschleunigung=21.0, Miles/Galon=20.4, Baujahr[19xx]=49.0}
           - Ente(-798) -> {Gewicht[lbs]=1234.0, Hubraum[cc]=375.0, Leistung[hp]=9.0, Zylinder=4.0, Beschleunigung=21.0, Miles/Galon=20.4, Baujahr[19xx]=49.0}
           - Ente(-798) -> {Gewicht[lbs]=1234.0, Hubraum[cc]=375.0, Leistung[hp]=9.0, Zylinder=4.0, Beschleunigung=21.0, Miles/Galon=20.4, Baujahr[19xx]=49.0}
           - Ente(-798) -> {Gewicht[lbs]=1234.0, Hubraum[cc]=375.0, Leistung[hp]=9.0, Zylinder=4.0, Beschleunigung=21.0, Miles/Galon=20.4, Baujahr[19xx]=49.0}
           - Ente(-798) -> {Gewicht[lbs]=1234.0, Hubraum[cc]=375.0, Leistung[hp]=9.0, Zylinder=4.0, Beschleunigung=21.0, Miles/Galon=20.4, Baujahr[19xx]=49.0}
           - Amphicar 770(2404) -> {*Gewicht[lbs]*=2314.0, *Hubraum[cc]*=1147.0, Leistung[hp]=38.0, Zylinder=4.0, Beschleunigung=14.0, Miles/Galon=21.0, Baujahr[19xx]=61.0}
           Otto(0):
         */

    }
}
