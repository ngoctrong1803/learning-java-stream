package org.apache.Bingo;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bingo to learning stream");

        List<String> bingoPool = new ArrayList<>(75);
        int start = 1;

        // generate all 75 labels
        for (char c : "BINGO".toCharArray()) {
            for (int i = start; i < start + 15; i++) {
                bingoPool.add("" + c + i);
            }
            start += 15;
        }

        bingoPool.forEach(s -> System.out.print(s + " "));

        // shuffle
        Collections.shuffle(bingoPool);

        // I. Use old way
        // get first 15 elements
        List<String> first15Elements = bingoPool.subList(0, 15);

        // sort 15 elements
        System.out.println("After get and sort: ");
        Collections.sort(first15Elements);
        first15Elements.forEach(e -> System.out.print(e + " "));

        // replace O and G labels
        first15Elements.replaceAll(label -> {
            if (label.startsWith("O") || label.startsWith("G")) {
                return label.charAt(0) + "-" + label.charAt(1);
            }
            return label;
        });
        System.out.println("After replace: ");
        first15Elements.forEach(e -> System.out.print(e + " "));

        // II. Use new Way
        System.out.println("");
        System.out.println("-".repeat(90));
        System.out.println("=== New Way With Stream ===");
        List<String> bingoPoolII = new ArrayList<>(75);
        int startII = 1;

        // generate all 75 labels
        for (char c : "BINGO".toCharArray()) {
            for (int i = startII; i < startII + 15; i++) {
                bingoPoolII.add("" + c + i);
            }
            startII += 15;
        }

        bingoPoolII.forEach(s -> System.out.print(s + " "));
        Collections.shuffle(bingoPoolII);

        System.out.println("");
        System.out.println("-".repeat(90));
        System.out.println("=== First 15 with Stream ===");

        bingoPoolII.stream().limit(15)
                .filter(label -> label.startsWith("G") || label.startsWith("O"))
                .map(label -> label.charAt(0) + "-" + label.charAt(1))
                .sorted()
                .forEach(l -> System.out.print(l + ", "));

        System.out.println("bingoPoolII After Stream: ");
        bingoPoolII.forEach(s -> System.out.print(s + " "));

        // III. Arrays Stream
        // with Collections - have .stream() method.
        // Stream with Arrays - use Arrays.stream(array) or Stream.of(... elements)
        String[] arr = {"One", "Two", "Three"};
        Stream<String> firstArray = Arrays.stream(arr)
                .sorted(Comparator.reverseOrder());
//                .forEach(System.out::println);

        Stream<String> secondArray = Stream.of("Six", "Five", "Four")
                .map(String::toLowerCase);
//                .forEach(System.out::println);

        Stream<String> sumaryStream = Stream.concat(secondArray, firstArray);
        sumaryStream.map(s -> s.charAt(0) + "-" + s)
                .forEach(System.out::println);

        // Stream from Map
        // is not have .stream() method
        // it use : map.entrySet(), map.keySet(), map.values()

        Map<String, String> map = new HashMap<>();
        map.put("1", "one");
        map.put("2", "two");
        map.put("3", "three");

        map.entrySet().stream()
                .map(entry -> entry.getKey() + ": " +
                        entry.getValue().charAt(0)+ " - " +
                        entry.getValue().charAt(entry.getValue().length() - 1))
                .forEach(System.out::println);

        // Infinite Streams (Research after)
        new Random().ints(10, 0, 2)
                .forEach(System.out::println);

        IntStream.iterate(1, n -> n + 1)
                .limit(20)
                .forEach(System.out::print);

        // Finite Stream (Research after)
    }
}
