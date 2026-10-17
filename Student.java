import java.util.Map;
public class Student {
   private String name;
   private int id;
   private Map<String, Double> courses;

   public Student(String name, int id, Map<String, Double> courses) {
      this.name = name;
      this.id =id;
      this.courses = courses;
   
   }
   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
   public int getId() {
      return id;
   }
   public void setId(int id) {
      this.id = id;
   }
   public Map<String, Double> getCourses() {
      return courses;
   }
   public void setCourses(Map<String, Double> courses) {
      this.courses = courses;
   }
   @Override
   public String toString() {
      return "Student{" +
              "name='" + name + '\'' +
              ", id=" + id +
              ", courses=" + courses +
              '}';
   }
   
}