package org.apache.StudentManagement;

import java.time.LocalDate;
import java.time.Period;

public class CourseEngagement {
    private final Course course;
    private final LocalDate enrollmentDate;
    private String engagementType;
    private int lastLecture;
    private LocalDate lastActivityDate;

    // Constructor
    public CourseEngagement(Course course, LocalDate enrollmentDate, String engagementType) {
        this.course = course;
        this.enrollmentDate = enrollmentDate;
        this.engagementType = engagementType;
        this.lastLecture = 0;
        this.lastActivityDate = enrollmentDate;
    }

    // ====== Getters ======
    public String getCourseCode() {
        return course.courseCode();
    }

    public int getEnrollmentYear() {
        return enrollmentDate.getYear();
    }

    public int getLastActivityYear() {
        return lastActivityDate.getYear();
    }

    public String getLastActivityMonth() {
        return lastActivityDate.getMonth().toString();
    }

    public double getPercentComplete() {
        return (lastLecture * 100.0) / course.lectureCount();
    }

    public int getMonthsSinceActive() {
        LocalDate now = LocalDate.now();
        return (int) Period.between(lastActivityDate, now).toTotalMonths();
    }

    // ====== Update progress ======
    void watchLecture(int lectureNo, LocalDate activityDate) {
        lastLecture = Math.max(lastLecture, lectureNo);
        lastActivityDate = activityDate;
        engagementType = "Active"; // tạm đặt vậy, có thể refine thêm
    }

    @Override
    public String toString() {
        return "%s | Last activity: %s %d | Progress: %.1f%% | Inactive: %d months"
                .formatted(
                        getCourseCode(),
                        getLastActivityMonth(),
                        getLastActivityYear(),
                        getPercentComplete(),
                        getMonthsSinceActive()
                );
    }

}
