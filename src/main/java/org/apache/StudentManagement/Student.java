package org.apache.StudentManagement;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Student {
    private static long lastStudentId = 1;
    private static final Random random = new Random();

    private final long studentId;
    private final String countryCode;
    private final int yearEnrolled;
    private final int ageEnrolled;
    private final String gender;
    private final boolean programmingExperience;
    private final Map<String, CourseEngagement> engagementMap = new HashMap<>();

    public Student(String countryCode, int yearEnrolled, int ageEnrolled, String gender, boolean programmingExperience, Course... courses) {
        this.studentId = Student.lastStudentId + 1;
        this.countryCode = countryCode;
        this.yearEnrolled = yearEnrolled;
        this.ageEnrolled = ageEnrolled;
        this.gender = gender;
        this.programmingExperience = programmingExperience;

        for (Course course : courses) {
            addCourse(course, LocalDate.of(yearEnrolled, 1, 1));
        }
        Student.lastStudentId++;
    }

    public void addCourse(Course newCourse) {
        addCourse(newCourse, LocalDate.now());
    }

    public void addCourse(Course newCourse, LocalDate enrollDate) {
        engagementMap.put(newCourse.courseCode(),
                new CourseEngagement(newCourse, enrollDate, "ENROLLED"));
    }

    public static long getLastStudentId() {
        return lastStudentId;
    }

    public long getStudentId() {
        return studentId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public int getYearEnrolled() {
        return yearEnrolled;
    }

    public int getAgeEnrolled() {
        return ageEnrolled;
    }

    public String getGender() {
        return gender;
    }

    public boolean isProgrammingExperience() {
        return programmingExperience;
    }

    public Map<String, CourseEngagement> getEngagementMap() {
        return engagementMap;
    }

    public int getYearsSinceEnrolled() {
        return LocalDate.now().getYear() - yearEnrolled;
    }

    public int getAge() {
        return ageEnrolled + getYearsSinceEnrolled();
    }

    public int getMonthsSinceActive(String courseCode) {
        CourseEngagement ce = engagementMap.get(courseCode);
        return (ce == null) ? 0 : ce.getMonthsSinceActive();
    }

    public int getMonthsSinceActive() {
        int inactiveMonths = (LocalDate.now().getYear() - 2014) * 12;
        for (String courseCode : engagementMap.keySet()) {
            inactiveMonths = Math.min(inactiveMonths,
                    engagementMap.get(courseCode).getMonthsSinceActive());
        }
        return inactiveMonths;
    }

    public double getPercentComplete(String courseCode) {
        CourseEngagement ce = engagementMap.get(courseCode);
        return (ce == null) ? 0.0 : ce.getPercentComplete();
    }

    public void watchLecture(String courseCode, int lectureNumber, int year, int month) {
        CourseEngagement ce = engagementMap.get(courseCode);
        if (ce != null) {
            ce.watchLecture(lectureNumber, LocalDate.of(year, month, 1));
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + studentId +
                ", country='" + countryCode + '\'' +
                ", yearEnrolled=" + yearEnrolled +
                ", ageEnrolled=" + ageEnrolled +
                ", gender='" + gender + '\'' +
                ", programmingExperience=" + programmingExperience +
                ", engagement=" + engagementMap +
                '}';
    }

    private static String getRandomVal(String... values) {
        return values[random.nextInt(values.length)];
    }

    public static Student getRandomStudent(Course... courses) {
        int maxYear = LocalDate.now().getYear();
        String country = getRandomVal("AU", "US", "UK", "IN", "VN");
        int year = 2015 + random.nextInt(maxYear - 2015 + 1);
        int age = 18 + random.nextInt(72);
        String gender = getRandomVal("M", "F", "U");
        boolean hasExp = random.nextBoolean();

        Student student = new Student(country, year, age, gender, hasExp, courses);

        // tạo random activity
        for (Course c : courses) {
            int lec = 1 + random.nextInt(50);
            int randomYear = year + random.nextInt(maxYear - year + 1);
            int month = 1 + random.nextInt(12);
            if (randomYear == LocalDate.now().getYear()) {
                month = Math.min(month, LocalDate.now().getMonthValue());
            }
            student.watchLecture(c.courseCode(), lec, randomYear, month);
        }

        return student;
    }
}
