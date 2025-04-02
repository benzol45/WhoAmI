package identifier.service;

import identifier.entity.Employee;
import identifier.model.EmployeeModel;
import identifier.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return employeeRepository.getAll()
                .stream()
                .map(this::mapEmployeeEntityToModel)
                .toList();
    }

    public Optional<EmployeeModel> findEmployee(Integer employeeId) {
        return checkIsAnEternalConsultant(employeeId)
                ? Optional.of(new EmployeeModel(employeeId, "External consultant"))
                : employeeRepository.getById(employeeId).map(this::mapEmployeeEntityToModel);
    }

    public Integer createEmployee(EmployeeModel employeeModel) {
        Employee employee = new Employee();
        employee.setId(employeeModel.getId());
        employee.setName(employeeModel.getName());

        return employeeRepository.createEmployee(employee).getId();
    }

    private EmployeeModel mapEmployeeEntityToModel(Employee entity) {
        return new EmployeeModel(entity.getId(), entity.getName());
    }

    private boolean checkIsAnEternalConsultant(int employeeId) {
        return employeeId >= 100 && employeeId <= 200;
    }
}


