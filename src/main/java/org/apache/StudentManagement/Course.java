package org.apache.StudentManagement;

public record Course(String courseCode, String title, int lectureCount ) {
    // Compact constructor để kiểm tra dữ liệu
    public Course {
        if (lectureCount <= 0) {
            lectureCount = 1; // tránh chia cho 0
        }
    }

    public Course(String courseCode, String title) {
        this(courseCode, title, 40);
    }

    @Override
    public String toString() {
        return "| " + courseCode + " - " + title + " |";
    }
}
