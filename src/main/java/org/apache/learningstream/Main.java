package org.apache.learningstream;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // I. simple example
        // Without Stream
        List<String> names = Arrays.asList("John", "Alice", "Bob", "Charlie");

        List<String> filtered = new ArrayList<>();
        for (String name : names) {
            if (name.startsWith("C")) {
                filtered.add(name.toUpperCase());
            }
        }
        System.out.println(filtered); // [CHARLIE]

        // Use Stream
        List<String> filteredStream = names.stream()
                                            .filter(n -> {return n.startsWith("C");})
                                            .map(n -> n.toLowerCase())
                                            .toList();
        System.out.println(filtered); // [CHARLIE]
    }
}