package skypro.homework13course2.Service;

import org.springframework.stereotype.Service;
import skypro.homework13course2.Domane.Employee;
import skypro.homework13course2.Exception.EmployeeNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
   private static  EmployeeService employeeService = new EmployeeService();

    public DepartmentService(EmployeeService employeeService) {
        DepartmentService.employeeService = employeeService;
    }
        public static final Employee[] employees = new Employee[10];

        public Employee getMaxWage(int department) {
            return employeeService.list().stream()
                .filter(Objects::nonNull)
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingDouble(Employee::getWage))
                .orElseThrow(EmployeeNotFoundException::new);
    }


    public static Employee getMinWage(Integer department) {
        return employeeService.list().stream()
                .filter(Objects::nonNull)
                .filter(employee -> employee.getDepartment() == department )
                .min(Comparator.comparingInt(Employee::getWage))
                .orElseThrow(EmployeeNotFoundException::new);
    }
    public List<Employee> getAllInDepart(int id){
        return employeeService.list().stream()
                .filter(employee -> employee.getDepartment() == id )
                .collect(Collectors.toList());

    }

    public Map<Integer, List<Employee>> getAllByDepart(){
        return employeeService.list().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

    }


}
