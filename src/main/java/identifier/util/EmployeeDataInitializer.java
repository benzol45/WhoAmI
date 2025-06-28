package identifier.util;

import identifier.entity.Employee;
import identifier.repository.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/*@Component
public class EmployeeDataInitializer {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeDataInitializer(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostConstruct
    public void init() {
        fillEmployee();
    }


    public void fillEmployee() {
        List<Employee> employeeList = List.of(
                new Employee(11, "John Doe"),
                new Employee(12, "Michael Smith"),
                new Employee(13, "David Brown"));

        employeeRepository.initData(employeeList);
    }
}*/
