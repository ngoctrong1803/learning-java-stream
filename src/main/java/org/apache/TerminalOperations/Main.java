package org.apache.TerminalOperations;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        /*
        Group of Operations:
        1. Statistical reduction: count, sum, min, max, average, summaryStatistics.
        2. Matching: anyMatch, allMatch, noneMatch.
        3. forEach, forEachOrdered.
        * */

        // 1. Statistical reduction: count, sum, min, max, average, summaryStatistics.
        IntSummaryStatistics stats = IntStream.iterate(0, i -> i <= 1000, i -> i + 10)
                .summaryStatistics();
        System.out.println(stats);

        // 2. Matching: anyMatch, allMatch, noneMatch.
        int[] arr = {1, 2 ,3, 4 ,5};

        boolean anyMatch = Arrays.stream(arr).anyMatch(i -> i == 1);
        boolean fullyMatch = Arrays.stream(arr).allMatch(i -> i % 1 == 0);
        boolean noneMatch = Arrays.stream(arr).noneMatch(i -> i == 1);

        System.out.println("|anyMatch: " + anyMatch + " |fullyMatch: " + fullyMatch + " |noneMatch: " + noneMatch);
    }
}
