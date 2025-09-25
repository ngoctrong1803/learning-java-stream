package org.apache.filterdata;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        IntStream.iterate((int) 'A', i -> i <= (int) 'Z', i -> i + 1)
                .mapToObj(c -> String.valueOf((char) c))
                .filter(ch -> ch.compareTo("E") < 0)
                .forEach(System.out::println);

        IntStream.iterate((int) 'A', i -> i <= (int) 'Z', i -> i + 1)
                .skip(5)
                .forEach(c -> System.out.print((char) c));

        IntStream.iterate((int) 'A', i -> i <= (int) 'Z', i -> i + 1)
                .dropWhile(c -> c <= 'E') // bỏ tới khi gặp > E
                .takeWhile(c -> c < 'U') // lấy tới trước U
                .forEach(c -> System.out.print((char) c));

        Random random = new Random();
        Stream.generate(() -> random.nextInt(26) + 'A') // random chữ A–Z
                .limit(50)
                .distinct()
                .sorted()
                .forEach(c -> System.out.print(":" + (char)(int)c));
    }
}
