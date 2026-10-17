import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("Grace", 1,
Map.of("Mathematics", 90.0, "English", 70.0));
Student s2 = new Student("Kelly", 2,
Map.of("Mathematics", 80.0, "Biology", 85.0));
Student s3 = new Student("Anne", 3,
Map.of("English", 95.0, "Biology", 75.0));



List<Student> students = List.of(s1, s2, s3);
for (Student student : students) {
    double average = student.getCourses().values()
    .stream()
    .mapToDouble(Double::doubleValue)
    .average()
    .orElse(0.0);
    System.out.println(student.getName() + "'s average: " + average);
}

Map<String, List<Student>> studentsByCourse =
students.stream()
.flatMap(student -> student.getCourses()
.keySet()
.stream()
.map(course -> Map.entry(course, student)))
.collect(Collectors.groupingBy(
   entry -> entry.getKey(),
    Collectors.mapping(
        entry -> entry.getValue(),
        Collectors.toList()
    )
));
studentsByCourse.forEach((course, studentList) -> {
    System.out.println(course + ":");

    studentList.forEach(student ->
            System.out.println("  " + student.getName()));
});
studentsByCourse.forEach((course, studentList) -> {
    Student highest = studentList.stream()
            .max((a, b) -> Double.compare(
                    a.getCourses().get(course),
                    b.getCourses().get(course)))
            .orElse(null);

    if (highest != null) {
        System.out.println(course + ": " + highest.getName()
                + " with " + highest.getCourses().get(course) + " marks");
    }
});
    }
    
} 
    