package org.apache.StreamIntermediate;

import java.util.Comparator;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        int maxSeats = 100;
        int seatsInRow = 10;

        // 1️⃣ Tạo Stream<Seat> từ Stream.iterate
        var seats = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
                .map(i -> {
                    char row = (char) ('A' + (i / seatsInRow));    // Tính row
                    int seatNo = (i % seatsInRow) + 1;            // Tính seat number
                    return new Seat(row, seatNo);                 // Trả về Seat
                });

        // 2️⃣ In ra toàn bộ Seat
        System.out.println("=== Danh sách ghế ===");
        seats.forEach(System.out::println);

        // Phải tạo lại stream vì stream đã bị consume
        seats = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
                .map(i -> {
                    char row = (char) ('A' + (i / seatsInRow));
                    int seatNo = (i % seatsInRow) + 1;
                    return new Seat(row, seatNo);
                });

        System.out.println("\n=== Ghế sorted theo giá, rồi theo tên ===");
        seats.sorted(
                Comparator.comparingDouble(Seat::price)
                        .thenComparing(Seat::toString)
        ).forEach(System.out::println);

        // 4️⃣ mapToDouble và boxed
        seats = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
                .map(i -> {
                    char row = (char) ('A' + (i / seatsInRow));
                    int seatNo = (i % seatsInRow) + 1;
                    return new Seat(row, seatNo);
                });


        double total = seats.mapToDouble(Seat::price).sum();
        System.out.println("\n=== Tổng tiền toàn bộ ghế: " + total);

        // 5️⃣ peek để debug
        seats = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
                .map(i -> {
                    char row = (char) ('A' + (i / seatsInRow));
                    int seatNo = (i % seatsInRow) + 1;
                    return new Seat(row, seatNo);
                });

        System.out.println("\n=== Debug bằng peek (skip 5, limit 10) ===");
        seats.skip(5).limit(10)
                .peek(s -> System.out.println("Peek: " + s))
                .forEach(s -> {}); // terminal operation để chạy được peek
    }
}
