package org.apache.StreamChallenge;

import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
    private static int counter = 0;
    private static int getCounter() {
        return counter++;
    }

    public static void main(String[] args) {
        int seed = 1;

        // B1 - B15 (finite iterate)
        var streamB = Stream.iterate(seed, n -> n <= 15, n -> n + 1)
                            .map(n -> "B" + n);

        seed += 15;

        // I16 - I30 (infinite iterate + limit)
        var streamI = Stream.iterate(seed, n -> n + 1)
                .limit(15)
                .map(n -> "I" + n);

        seed += 15;

        // N31 - N45 (Arrays.stream)
        String[] nLabels = new String[15];
        int nSeed = seed;
        Arrays.setAll(nLabels, i -> "N" + (nSeed + i));
        var streamN = Arrays.stream(nLabels);

        seed += 15;

        // G46 - G60 (Stream.of)
        var streamG = Stream.of(
                "G46","G47","G48","G49","G50",
                "G51","G52","G53","G54","G55",
                "G56","G57","G58","G59","G60"
        );

        seed += 15;
        int oSeed = seed;

        // O61 - O75 (generate + counter)
        counter = 0;
        var streamO = Stream.generate(Main::getCounter)
                .limit(15)
                .map(n -> "O" + (oSeed + n));

        // Concatenate all streams
        Stream.concat(
                Stream.concat(streamB, streamI),
                Stream.concat(streamN, Stream.concat(streamG, streamO))
        ).forEach(s -> System.out.print(s + " "));


    }
}
