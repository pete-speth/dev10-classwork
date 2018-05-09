package org.swg.students;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {

        StudentDataStore ds = new StudentDataStore();
        List<Student> students = ds.all();
//        for (Student s : students) {
//            println(s);
//        }

        //TODO: Print students from Argentina
        students.stream()
                .filter(s -> s.getCountry().equalsIgnoreCase("Argentina"));
//                .forEach(System.out::println);

        //TODO: Print students from Argentina, ordered by GPA
        students.stream()
                .filter(s -> s.getCountry().equalsIgnoreCase("Argentina"))
                .sorted((s1, s2) -> -s1.getGpa().compareTo(s2.getGpa()));
//                .forEach(System.out::println);
//        System.out.println("");

        //TODO: Print the 4th - 6th ranked students by GPA from Argentina
        students.stream()
                .filter(s -> s.getCountry().equalsIgnoreCase("Argentina"))
                .sorted((s1, s2) -> -s1.getGpa().compareTo(s2.getGpa()))
                .skip(3)
                .limit(3);
//                .forEach(System.out::println);

        //TODO: Is anyone from Maldives?
        boolean hasMaldivien = students.stream()
                .anyMatch(s -> s.getCountry().equalsIgnoreCase("Maldives"));
//        System.out.println(hasMaldivien);

        //TODO: Print students who aren't currently registered for a class.
        students.stream()
                .filter(s -> s.getRegistrations().size() == 0);
//                .forEach(System.out::println);

        //TODO: Print students who are registered for the class "Literary Genres".
        students.stream()
                .filter(s -> s.getRegistrations().stream()
                .anyMatch(r -> r.getCourse().equalsIgnoreCase("Literary Genres")));
//                .forEach(System.out::println);

        //TODO: Who has the highest GPA?
        students.stream()
                .sorted((s1, s2) -> -s1.getGpa().compareTo(s2.getGpa()))
                .limit(1);
//                .forEach(System.out::println);

        //TODO: Print every class a student is registered for including repeats.
        students.stream()
                .flatMap(s -> s.getRegistrations().stream()
                .map(r -> r.getCourse()));
//                .forEach(System.out::println);

        //TODO: Print a distinct list of classes students are registered for.
        students.stream()
                .flatMap(s -> s.getRegistrations().stream()
                .map(r -> r.getCourse()))
                .distinct();
//                .forEach(System.out::println);

        //TODO: Print a distinct list of classes students are registered for, ordered by name.
        students.stream()
                .flatMap(s -> s.getRegistrations().stream()
                .map(r -> r.getCourse()))
                .distinct()
                .sorted((n1, n2) -> n1.compareTo(n2));
//                .forEach(System.out::println);

        //TODO: Create a new type, StudentSummary with fields for Country, Major, and IQ.
        //      Map Students to StudentSummary, then filter by IQ (low, high, or whatever seems fun).
        students.stream()
                .map(s -> new StudentSummary(s))
                .sorted((summary1, summary2) -> Double.compare(summary1.getIq(), summary2.getIq()));
//                .forEach(System.out::println);

        //TODO: What is the average GPA per country (remember, it's random fictional data).
        Map<String, Double> avgGpaByCountry = students.stream()
                .collect(Collectors.groupingBy(s -> s.getCountry(), 
                        Collectors.averagingDouble(s -> s.getGpa().doubleValue())));
        
//        for (String key : avgGpaByCountry.keySet()){
//            System.out.println(key + ": " + avgGpaByCountry.get(key));
//        }
        
        //TODO: What is the maximum GPA per country?
        Map<String, Optional<Student>> topStudentByCountry  = students.stream()
                .collect(Collectors.groupingBy(s -> s.getCountry(),
                        Collectors.maxBy((s1,s2) -> s1.getGpa().compareTo(s2.getGpa()))));
        
        for (String key : topStudentByCountry.keySet()){
            Optional<Student> student = topStudentByCountry.get(key);
            Student realStudent = student.get();
            if (student.isPresent()){
                System.out.println(key + ": " + realStudent.getGpa());
            }
                
        }
        
        //TODO: Print average IQ per Major ordered by IQ ascending.
        Map<String, Double> collect = students.stream()
                .collect(Collectors.groupingBy((s -> s.getMajor()), 
                        Collectors.averagingDouble(s -> s.getIq())));
                
        
        
    }

    static void println(Student s) {
        System.out.println(s);
    }

}
