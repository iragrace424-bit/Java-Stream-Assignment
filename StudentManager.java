import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.HashSet;
import java.util.Set;
public class StudentManager {

    private List<Student> students = new ArrayList<>();
private Set<Integer> bonusProcessedIds = new HashSet<>();
public void addStudent(Student student) {
    students.add(student);
}

public boolean studentExists(int id) {
    return students.stream()
            .anyMatch(student -> student.getId() == id);
}

public Student findStudentById(int id) {
    return students.stream()
            .filter(student -> student.getId() == id)
            .findFirst()
            .orElse(null);
}

public void displayAllStudents() {
    if (students.isEmpty()) {
        System.out.println("No students registered yet.");
    } else {
        students.stream()
                .forEach(student -> System.out.println(student));
    }
}

public double calculateAverage(Student student) {
    return student.getCourses()
            .values()
            .stream()
            .mapToDouble(mark -> mark)
            .average()
            .orElse(0.0);
}

public void displayPassingStudents() {
    students.stream()
            .filter(student -> calculateAverage(student) >= 65)
            .forEach(student -> System.out.println(student));
}

public void displayFailingStudents() {
    students.stream()
            .filter(student -> calculateAverage(student) < 65)
            .forEach(student -> System.out.println(student));
}

public List<Student> findStudentsByName(String name) {
    return students.stream()
            .filter(student -> student.getStudentName()
                    .equalsIgnoreCase(name))
            .collect(Collectors.toList());
}

public void sortStudentsByName() {
    students.stream()
            .sorted((s1, s2) -> s1.getStudentName()
                    .compareToIgnoreCase(s2.getStudentName()))
            .forEach(student -> System.out.println(student));
}

public void sortStudentsByAverage() {
    students.stream()
            .sorted((s1, s2) -> Double.compare(
                    calculateAverage(s2),
                    calculateAverage(s1)))
            .forEach(student -> System.out.println(
                    student.getStudentName() + " - Average: "
                    + calculateAverage(student)));
}

public void displayAllCourses() {
    students.stream()
            .flatMap(student -> student.getCourses()
                    .keySet().stream())
            .forEach(course -> System.out.println(course));
}

public void displayUniqueCourses() {
    students.stream()
            .flatMap(student -> student.getCourses()
                    .keySet().stream())
            .distinct()
            .sorted()
            .forEach(course -> System.out.println(course));
}

public void findStudentsByCourse(String courseName) {
    students.stream()
            .filter(student -> student.getCourses()
                    .keySet().stream()
                    .anyMatch(course -> course.equalsIgnoreCase(courseName)))
            .forEach(student -> System.out.println(student));
}

public void displayUniqueSkills() {
    students.stream()
            .flatMap(student -> student.getSkills().stream())
            .distinct()
            .sorted()
            .forEach(skill -> System.out.println(skill));
}

public void groupStudentsByCourse() {
    Map<String, List<Student>> groups = students.stream()
            .flatMap(student -> student.getCourses()
                    .keySet().stream()
                    .map(course -> Map.entry(course, student)))
            .collect(Collectors.groupingBy(
                    Map.Entry::getKey,
                    Collectors.mapping(
                            Map.Entry::getValue,
                            Collectors.toList()
                    )
            ));

    groups.forEach((course, studentList) -> {
        System.out.println("\nCourse: " + course);
        studentList.forEach(student ->
                System.out.println(student.getStudentName()));
    });
}

public void displayPerformanceReport() {
    students.forEach(student -> {
        if (student.getCourses().isEmpty()) {
            System.out.println(student.getStudentName()
                    + " has no registered courses.");
            return;
        }

        var stats = student.getCourses().values().stream()
                .mapToDouble(Double::doubleValue)
                .summaryStatistics();

        System.out.println("\nStudent: "
                + student.getStudentName());
        System.out.println("Average: " + stats.getAverage());
        System.out.println("Highest mark: " + stats.getMax());
        System.out.println("Lowest mark: " + stats.getMin());
        System.out.println("Total marks: " + stats.getSum());
        System.out.println("Number of courses: "
                + stats.getCount());
    });
}

public void displayBestStudent() {
    students.stream()
            .max((s1, s2) -> Double.compare(
                    calculateAverage(s1),
                    calculateAverage(s2)))
            .ifPresentOrElse(
                    student -> System.out.println(
                            "Best student: "
                            + student.getStudentName()
                            + " - Average: "
                            + calculateAverage(student)),
                    () -> System.out.println(
                            "No students registered.")
            );
}

public void displayTopThreeStudents() {
    students.stream()
            .sorted((s1, s2) -> Double.compare(
                    calculateAverage(s2),
                    calculateAverage(s1)))
            .limit(3)
            .forEach(student -> System.out.println(
                    student.getStudentName()
                    + " - Average: "
                    + calculateAverage(student)));
}

public void applyBonusMarks() {
    students.forEach(student -> {

        if (bonusProcessedIds.contains(student.getId())) {
            System.out.println(student.getStudentName()
                    + " has already been processed.");
            return;
        }

        double originalAverage = calculateAverage(student);

        if (originalAverage > 50) {
            student.getCourses().replaceAll(
                    (course, mark) -> Math.min(mark + 10, 100)
            );

            double finalAverage = student.getCourses()
                    .values()
                    .stream()
                    .reduce(0.0, Double::sum)
                    / student.getCourses().size();

            System.out.println("\nStudent: "
                    + student.getStudentName());
            System.out.println("Original average: "
                    + originalAverage);
            System.out.println("Final average: "
                    + finalAverage);
            System.out.println("Bonus applied successfully!");

        } else {
            System.out.println("\nStudent: "
                    + student.getStudentName());
            System.out.println("Original average: "
                    + originalAverage);
            System.out.println("Not eligible for bonus.");
        }

        bonusProcessedIds.add(student.getId());
    });
}

public void displayFinalRanking() {
    students.stream()
            .sorted((s1, s2) -> Double.compare(
                    calculateAverage(s2),
                    calculateAverage(s1)))
            .forEach(student -> System.out.println(
                    student.getStudentName()
                    + " - Final Average: "
                    + calculateAverage(student)));
}

}
