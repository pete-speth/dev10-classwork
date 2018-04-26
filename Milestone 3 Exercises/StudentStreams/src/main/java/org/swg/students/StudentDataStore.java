package org.swg.students;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StudentDataStore extends FileDataStore {

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("M/d/yyyy");

    public StudentDataStore() {
        super("students.csv", 8, true);
    }

    public List<Student> all() throws IOException {

        List<Student> result = new ArrayList<>();

        runPerLine(tokens -> {

            Student student = new Student();

            student.setId(Integer.parseInt(tokens[0]));
            student.setFirstName(tokens[1]);
            student.setLastName(tokens[2]);
            student.setEmailAddress(tokens[3]);
            student.setBirthDate(LocalDate.parse(tokens[4], dtf));
            student.setCountry(tokens[5]);
            student.setGpa(new BigDecimal(tokens[6]));
            student.setMajor(getMajor(tokens[7]));

            int hashCode = Math.abs(tokens[1].concat(tokens[2]).hashCode());
            student.setIq(80.0 + (hashCode % 50));

            setRegistrations(student, hashCode);

            result.add(student);
        });

        return result;
    }

    private String getMajor(String majorId) {

        int value = Integer.parseInt(majorId);
        switch (value) {
            case 1:
                return "American Studies";
            case 2:
                return "Sociology";
            case 3:
                return "Philosophy";
            case 4:
                return "Political Science";
            case 5:
                return "Economics";
            case 6:
                return "Business";
            case 7:
                return "Antropology";
            case 8:
                return "Education";
            case 9:
                return "Communications";
            case 10:
                return "Pre-Med";
            case 11:
                return "Education";
            default:
                return "Computer Science";
        }
    }

    private String[] courses = new String[]{
        "Research Methods: Research in Liberal Arts Disciplines",
        "Greco-Roman Tradition",
        "Ancient Philosophy",
        "Sacred Writing",
        "Intro to College English",
        "World Views",
        "Post-Classical History",
        "Modern Philosophy",
        "Principles of Mathematics and Logic",
        "Renaissance to Baroque Art",
        "Literary Genres",
        "Modern History-19th and 20th Centuries",
        "Science: History and Methodology",
        "Literary Themes",
        "Knowledge",
        "Applied Themes in English",
        "Applied Ethics in Humanities",
        "Physical Activity and Autonomy"
    };

    private GradeType[] gradeTypes = new GradeType[]{
        GradeType.A_THROUGH_F,
        GradeType.PASS_FAIL,
        GradeType.AUDIT
    };

    private void setRegistrations(Student student, int hashCode) {
        List<Registration> registrations = new ArrayList<>();
        for (int i = 0; i < hashCode % 4; i++) {
            Registration reg = new Registration();
            reg.setCourse(courses[hashCode % courses.length]);
            reg.setGradType(gradeTypes[hashCode % gradeTypes.length]);
            reg.setPointPercent(75 + (hashCode % 25));
            registrations.add(reg);
        }
        student.setRegistrations(registrations);
    }
}
