public class Employee {
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private Position position;
    private Department department;

    public Employee(String firstName, String lastName, int age, String gender, Position position, Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.position = position;
        this.department = department;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public Position getPosition() {
        return position;
    }

    public Department getDepartment() {
        return department;
    }

    public double getSalary() {
        return position.getSalary();
    }

    public void performAction(String action) {
        System.out.println(getFullName() + " выполняет действие: " + action);
    }
}
