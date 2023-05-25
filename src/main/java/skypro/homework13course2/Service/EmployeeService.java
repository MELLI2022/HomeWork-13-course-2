package skypro.homework13course2.Service;

import org.springframework.stereotype.Service;
import skypro.homework13course2.Domane.Employee;
import skypro.homework13course2.Exception.EmployeeAlreadyAddedException;
import skypro.homework13course2.Exception.EmployeeNotFoundException;
import skypro.homework13course2.Exception.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private static final int SIZE = 10;
    private final List<Employee> employees = new ArrayList<>(SIZE);


    public Employee add(String firstName, String lastName, int department, int wage) {
        Employee employee = new Employee(firstName, lastName, department, wage);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() < SIZE) {
            employees.add(employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException();
    }

    public Employee find(String firstName, String lastName, int department, int wage) {
        Employee employee = new Employee(firstName, lastName, department, wage);
        if (employees.contains(employee)) {
            return employee;
        }

        throw new EmployeeNotFoundException();
    }

    public Employee remove(String firstName, String lastName, int department, int wage) {
        Employee employee = new Employee(firstName, lastName, department, wage);
        if (employees.remove(employee)) {
            return employee;
        }

        throw new EmployeeNotFoundException();
    }

    public List<Employee> list() {
        return new ArrayList<>(employees);
    }

}



