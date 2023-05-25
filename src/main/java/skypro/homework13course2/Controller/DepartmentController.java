package skypro.homework13course2.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import skypro.homework13course2.Domane.Employee;
import skypro.homework13course2.Service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-wage")
    public Employee getMaxWage(@RequestParam("departmentId") Integer id) {
        return departmentService.getMaxWage(id);
    }

    @GetMapping("/min-wage")
    public Employee getMinWage(@RequestParam("departmentId") Integer id) {
        return DepartmentService.getMinWage(id);
    }

    @GetMapping("/all")
    public List<Employee> getAllInDepart(@RequestParam("departmentId") Integer id) {
        return departmentService.getAllInDepart(id);
    }

    @GetMapping("/departments/all")
    public Map<Integer, List<Employee>> getAllByDepart() {
        return departmentService.getAllByDepart();
    }

}
