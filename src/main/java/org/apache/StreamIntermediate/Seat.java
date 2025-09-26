package org.apache.StreamIntermediate;

public record Seat(char rowMarker, int seatNumber, double price) {
    // Custom constructor: chỉ nhập rowMarker và seatNumber
    public Seat(char rowMarker, int seatNumber) {
        this(
                rowMarker,
                seatNumber,
                // Pricing rule: default 75, nhưng row > C và seat 1,2,9,10 thì giảm còn 60
                (rowMarker > 'C' && (seatNumber == 1 || seatNumber == 2
                        || seatNumber == 9 || seatNumber == 10)) ? 60.0 : 75.0
        );
    }

    @Override
    public String toString() {
        // Ví dụ: B001 ($75)
        return "%c%03d ($%.0f)".formatted(rowMarker, seatNumber, price);
    }
}
