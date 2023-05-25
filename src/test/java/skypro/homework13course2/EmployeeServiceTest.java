package skypro.homework13course2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import skypro.homework13course2.Domane.Employee;
import skypro.homework13course2.Exception.EmployeeAlreadyAddedException;
import skypro.homework13course2.Exception.EmployeeNotFoundException;
import skypro.homework13course2.Exception.EmployeeStorageIsFullException;
import skypro.homework13course2.Service.EmployeeService;

public class EmployeeServiceTest {
    private final EmployeeService employeeService = new EmployeeService();

    @BeforeEach
    public void beforeEach() {
        employeeService.add("Михаил", "Викторович", 1, 6000);
        employeeService.add("Антонина", "Викторовна", 2, 3000);
        employeeService.add("Дмитрий", "Викторович", 3, 2000);
    }

    @AfterEach
    public void AfterEach() {
        employeeService.list()
                .forEach(employee -> employeeService.remove(employee.getFirstName(), employee.getLastName(),
                        employee.getDepartment(), employee.getWage()));
    }

    @Test
    public void addTest() {
        Employee employee = new Employee("Людмила", "Викторовна", 3, 1000);

        Assertions.assertThat(employeeService.add("Людмила", "Викторовна", 3, 1000))
                .isEqualTo(employee)
                .isIn(employeeService.list());
    }

    @Test
    public void AlreadyAddedTest() {
        Assertions.assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeService.add("Михаил", "Викторович", 1, 6000));
    }

    @Test
    public void StorageIsFullTest() {

        String[] storage = new String[4];
        int size = 0;
        if (size == storage.length)
        Assertions.assertThatExceptionOfType(EmployeeStorageIsFullException.class)
                .isThrownBy(() -> employeeService.add("Лилия", "Викторовна", 2, 6000));

    }

    @Test
    public void findTest() {
        Employee employee = new Employee("Михаил", "Викторович", 1, 6000);

        Assertions.assertThat(employeeService.find("Михаил", "Викторович", 1, 6000))
                .isEqualTo(employee)
                .isIn(employeeService.list());
    }

    @Test
    public void findTest2() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.find("Лилия", "Викторовна", 2, 6000));
    }

    @Test
    public void removeTest() {
        Employee employee = new Employee("Михаил", "Викторович", 1, 6000);

        Assertions.assertThat(employeeService.remove("Михаил", "Викторович", 1, 6000))
                .isEqualTo(employee)
                .isNotIn(employeeService.list());
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.find("Михаил", "Викторович", 1, 6000));
    }

    @Test
    public void removeTest2() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.find("Лилия", "Викторовна", 2, 6000));
    }

    @Test
    public void listTest() {
        Assertions.assertThat(employeeService.list())
                .hasSize(3)
                .containsExactlyInAnyOrder(
                        new Employee("Михаил", "Викторович", 1, 6000),
                        new Employee("Антонина", "Викторовна", 2, 3000),
                        new Employee("Дмитрий", "Викторович", 3, 2000)
                );
    }
}
