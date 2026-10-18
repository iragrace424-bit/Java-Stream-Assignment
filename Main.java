
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        int choice;

        do {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Register Student");
            System.out.println("2. Display All Students");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter student ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                if (manager.studentExists(id)) {
                    System.out.println(
                            "A student with this ID already exists.");
                    continue;
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
                System.out.println(
                        "Student registered successfully!");

            } else if (choice == 2) {
                manager.displayAllStudents();

            } else if (choice == 0) {
                System.out.println("Goodbye!");

            } else {
                System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 0);

        scanner.close();
    }
}
