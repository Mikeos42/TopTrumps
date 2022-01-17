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
        g.addPlayer(new Player("Miki"));
        g.addPlayer(new Player("Nik"));
        g.addPlayer(new Player("Jessica"));
        g.play();

        g.writeStatistics(SimpleTablePrinter.);
    }
}
