package a11920352;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleCsvParser {

    private SimpleCsvParser() {
    }

    public static List<String> readAllLinesFrom(final String path) throws IOException {
        return Files.lines(Paths.get(path)).skip(1).collect(Collectors.toList());
    }


    public static VehicleCard parseLine(final String line) {
//        name,economy (mpg),cylinders,displacement (cc),power (hp),weight (lb),0-60 mph (s),year
//        AMC Ambassador Brougham,13,8,360,175,3821,11,73
        String[] parts = line.split(",");
        if (Arrays.stream(parts).anyMatch(String::isEmpty))
            return null;
        return new VehicleCard(parts[0], VehicleCard.newMap(
                Double.parseDouble(parts[1]),
                Double.parseDouble(parts[2]),
                Double.parseDouble(parts[3]),
                Double.parseDouble(parts[4]),
                Double.parseDouble(parts[5]),
                Double.parseDouble(parts[6]),
                Double.parseDouble(parts[7])
        ));
    }
}

