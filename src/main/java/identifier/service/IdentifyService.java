package identifier.service;

import identifier.entity.Employee;
import identifier.model.EmployeeModel;
import identifier.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class IdentifyService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public IdentifyService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeModel> getAllEmployee() {
        return employeeRepository.findAll()
                .stream()
                .map(this::mapEmployeeEntityToModel)
                .toList();
    }

    @Cacheable(value = "employee", key = "#root.args[0]") //employeeId
    public Optional<EmployeeModel> findEmployee(int employeeId) {
        System.out.println("Fetch data from DB");
        return checkIsAnEternalConsultant(employeeId)
                ? Optional.of(new EmployeeModel(employeeId, "External consultant"))
                : employeeRepository.findById(employeeId).map(this::mapEmployeeEntityToModel);
    }

    @Transactional
    public Integer createEmployee(EmployeeModel employeeModel) {
        Employee employee = new Employee();
        employee.setId(employeeModel.getId());
        employee.setName(employeeModel.getName());

        return employeeRepository.save(employee).getId();
    }

    @Transactional
    @CacheEvict(value = "employee", key = "#root.args[0]") //employeeId
    public Integer editEmployee(int employeeId, EmployeeModel employeeModel) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(EntityNotFoundException::new);
        employee.setId(employeeModel.getId());
        employee.setName(employeeModel.getName());

        return employeeRepository.save(employee).getId();
    }

    private EmployeeModel mapEmployeeEntityToModel(Employee entity) {
        return new EmployeeModel(entity.getId(), entity.getName());
    }

    private boolean checkIsAnEternalConsultant(int employeeId) {
        return employeeId >= 100 && employeeId <= 200;
    }
}


