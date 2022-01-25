package a11920352;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExtensionMain {
    public static void main(String[] args) throws IOException {
        final List<String> allLines = SimpleCsvParser.readAllLinesFrom("src/a12030638/csv/cars.csv");
        final List<VehicleCard> cards = allLines.stream().map(SimpleCsvParser::parseLine).filter(Objects::nonNull).collect(Collectors.toList());
        Game g = new Game();
        Game f = new Game();

        cards.forEach(g::addCard);
        cards.forEach(f::addCard);

        Strategy rnd = new RndStrategy();
        Strategy avg = new AvgStrategy();
        Player Miki = new Player("Miki");
        Player Jessica = new Player("Jessica");
        Player Nik = new Player("Nik");

        g.addPlayer(Miki);
        g.addPlayer(Jessica);

        f.addPlayer(Miki);
        f.addPlayer(Nik);
        for (int i = 0; i < 100; i++) {
            g.play();
            f.play();
        }

        g.writeStatistics(System.out);
        f.writeStatistics(System.out);
    }
}
