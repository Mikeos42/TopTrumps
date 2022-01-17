package a12030638;

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
        cards.forEach(g::addCard);
        Strategy rnd = new RndStrategy();
        Strategy avg = new AvgStrategy();
        g.addPlayer(new Player("Miki", avg));
        g.addPlayer(new Player("Jessica"));
        g.addPlayer(new Player("Nik"));
        for (int i = 0; i < 10; i++) {
            g.play();
        }

        g.writeStatistics(System.out);
    }
}
