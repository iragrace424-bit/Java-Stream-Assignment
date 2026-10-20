
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        int choice;

        do {
            System.out.println("\nSTUDENT MANAGEMENT SYSTEM");
            System.out.println("==========================================");
            System.out.println("1. Register New Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Filter Passing Students");
            System.out.println("5. Filter Failing Students");
            System.out.println("6. Search Students by Name");
            System.out.println("7. Sort Students by Name");
            System.out.println("8. Sort Students by Average Marks");
            System.out.println("9. Display All Courses");
            System.out.println("10. Display Unique Courses");
            System.out.println("11. Search Students by Course");
            System.out.println("12. Display Unique Skills");
            System.out.println("13. Group Students by Course");
            System.out.println("14. View Student Performance Statistics");
            System.out.println("15. View Best Performing Student");
            System.out.println("16. View Top 3 Students");
            System.out.println("17. Apply Bonus Marks");
            System.out.println("18. View Final Student Ranking");
            System.out.println("0. Exit");
            System.out.println("==========================================");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1: {
                    System.out.print("Enter student ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    if (manager.studentExists(id)) {
                        System.out.println("A student with this ID already exists.");
                        break;
                    }

                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter student age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();

                    Map<String, Double> courses = new HashMap<>();

                    System.out.print("How many courses? ");
                    int numberOfCourses = scanner.nextInt();
                    scanner.nextLine();

                    for (int i = 0; i < numberOfCourses; i++) {
                        System.out.print("Enter course name: ");
                        String course = scanner.nextLine();

                        System.out.print("Enter mark: ");
                        double mark = scanner.nextDouble();
                        scanner.nextLine();

                        courses.put(course, mark);
                    }

                    List<String> skills = new ArrayList<>();

                    System.out.print("How many skills? ");
                    int numberOfSkills = scanner.nextInt();
                    scanner.nextLine();

                    for (int i = 0; i < numberOfSkills; i++) {
                        System.out.print("Enter skill: ");
                        String skill = scanner.nextLine();
                        skills.add(skill);
                    }

                    Student student = new Student(
                            id, name, age, courses, skills);

                    manager.addStudent(student);
                    System.out.println("Student registered successfully!");
                    break;
                }

                case 2:
                    manager.displayAllStudents();
                    break;

                case 3: {
                    System.out.print("Enter student ID: ");
                    int searchId = scanner.nextInt();
                    scanner.nextLine();

                    Student foundStudent = manager.findStudentById(searchId);

                    if (foundStudent != null) {
                        System.out.println(foundStudent);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                }

                case 4:
                    manager.displayPassingStudents();
                    break;

                case 5:
                    manager.displayFailingStudents();
                    break;

                case 6: {
                    System.out.print("Enter student name: ");
                    String searchName = scanner.nextLine();

                    List<Student> foundStudents =
                            manager.findStudentsByName(searchName);

                    if (foundStudents.isEmpty()) {
                        System.out.println("No students found.");
                    } else {
                        foundStudents.forEach(
                                student -> System.out.println(student));
                    }
                    break;
                }

                case 7:
                    manager.sortStudentsByName();
                    break;

                case 8:
                    manager.sortStudentsByAverage();
                    break;

                case 9:
                    manager.displayAllCourses();
                    break;

                case 10:
                    manager.displayUniqueCourses();
                    break;

                case 11: {
                    System.out.print("Enter course name: ");
                    String courseName = scanner.nextLine();
                    manager.findStudentsByCourse(courseName);
                    break;
                }

                case 12:
                    manager.displayUniqueSkills();
                    break;

                case 13:
                    manager.groupStudentsByCourse();
                    break;

                case 14:
                    manager.displayPerformanceReport();
                    break;

                case 15:
                    manager.displayBestStudent();
                    break;

                case 16:
                    manager.displayTopThreeStudents();
                    break;

                case 17:
                    manager.applyBonusMarks();
                    break;

                case 18:
                    manager.displayFinalRanking();
                    break;

                case 0:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }

        } while (choice != 0);

        scanner.close();
    }
}
