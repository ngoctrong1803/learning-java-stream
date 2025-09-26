package org.apache.StudentManagement;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Course javaCourse = new Course("J101", "Java Fundamentals", 50);
        Course dbCourse = new Course("DB201", "Databases", 30);

        CourseEngagement e1 = new CourseEngagement(javaCourse, LocalDate.of(2023, 1, 10), "New");
        CourseEngagement e2 = new CourseEngagement(dbCourse, LocalDate.of(2024, 3, 15), "New");

        e1.watchLecture(10, LocalDate.of(2024, 5, 12));
        e2.watchLecture(5, LocalDate.of(2024, 10, 20));

        System.out.println(e1);
        System.out.println(e2);

        System.out.println("=== Student ===");
        Course pymc = new Course("PYMC", "Python Masterclass");
        Course jmc = new Course("JMC", "Java Masterclass");

        // tạo 1 student
        Student tim = new Student("AU", 2019, 30, "M", true, jmc, pymc);
        System.out.println(tim);

        tim.watchLecture("JMC", 10, 2019, 5);
        tim.watchLecture("PYMC", 7, 2020, 7);
        System.out.println(tim);

        // tạo 10 random students
        System.out.println("===== RANDOM STUDENTS =====");
        java.util.stream.Stream.generate(() -> Student.getRandomStudent(jmc, pymc))
                .limit(10)
                .forEach(System.out::println);
    }
}
