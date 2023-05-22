package skypro.homework13course2.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import skypro.homework13course2.Domane.Employee;
import skypro.homework13course2.Service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/add")
    public Employee add(@RequestParam String firstName,
                        @RequestParam  String lastName,
                        @RequestParam  int department,
                        @RequestParam  int wage){
        return employeeService.add(firstName, lastName, department, wage );
    }
    @GetMapping("/find")
    public Employee find(@RequestParam  String firstName,
                         @RequestParam  String lastName,
                         @RequestParam  int department,
                         @RequestParam  int wage){
        return employeeService.find(firstName, lastName, department, wage);
    }
    @GetMapping("/remove")
    public Employee remove(@RequestParam  String firstName,
                           @RequestParam  String lastName,
                           @RequestParam  int department,
                           @RequestParam  int wage
    ){
        return employeeService.remove(firstName, lastName, department, wage);
    }
    @GetMapping()
    public List<Employee> list (){
        return employeeService.list();
    }


}
