import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        // Создание позиций
        Position manager = new Position("Менеджер", 50000);
        Position accountant = new Position("Бухгалтер", 45000);
        Position clerk = new Position("Клерк", 30000);

        // Создание отделов
        Department sales = new Department("Отдел продаж");
        Department accounting = new Department("Бухгалтерия");

        // Создание сотрудников
        Employee emp1 = new Employee("Иван", "Иванов", 30, "М", manager, sales);
        Employee emp2 = new Employee("Света", "Петрова", 28, "Ж", accountant, accounting);
        Employee emp3 = new Employee("Петр", "Сидоров", 25, "М", clerk, sales);
        Employee emp4 = new Employee("Елена", "Кузнецова", 35, "Ж", accountant, accounting);

        // Добавление сотрудников в отделы
        sales.addEmployee(emp1);
        sales.addEmployee(emp3);
        accounting.addEmployee(emp2);
        accounting.addEmployee(emp4);

        // 1. Вывод списка сотрудников с должностями, отделами и зарплатой
        writeEmployeeListToFile(Arrays.asList(emp1, emp2, emp3, emp4), "employees.txt");

        // 2. Уникальные должности сотрудников, отсортированные в алфавитном порядке
        Set<Position> uniquePositions = new HashSet<>();
        for (Employee employee : Arrays.asList(emp1, emp2, emp3, emp4)) {
            uniquePositions.add(employee.getPosition());
        }
        List<Position> sortedUniquePositions = new ArrayList<>(uniquePositions);
        Collections.sort(sortedUniquePositions, Comparator.comparing(Position::getName));
        writeUniquePositionsToFile(sortedUniquePositions, "unique_positions.txt");

        // 3. Список отделов, отсортированный по количеству сотрудников
        List<Department> departments = Arrays.asList(sales, accounting);
        departments.sort(Comparator.comparingInt(Department::getEmployeeCount));
        writeDepartmentsToFile(departments, "departments.txt");

        // 4. Лог действий сотрудников
        emp1.performAction("Подписание документов");
        emp2.performAction("Заполнение отчета по бухгалтерии");
        emp3.performAction("Подготовка клиента к сделке");
    }

    private static void writeEmployeeListToFile(List<Employee> employees, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Employee employee : employees) {
                writer.write(employee.getFullName() + " | " + employee.getPosition().getName() + " | " +
                        employee.getDepartment().getName() + " | " + employee.getSalary());
                writer.newLine();
            }
        }
    }

    private static void writeUniquePositionsToFile(List<Position> positions, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Position position : positions) {
                writer.write(position.getName());
                writer.newLine();
            }
        }
    }

    private static void writeDepartmentsToFile(List<Department> departments, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Department department : departments) {
                writer.write(department.getName() + " | " + department.getEmployeeCount() + " сотрудников");
                writer.newLine();
            }
        }
    }
}