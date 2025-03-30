package identifier.service;

import identifier.model.EmployeeModel;
import identifier.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IdentifyService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public IdentifyService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Optional<EmployeeModel> getName(int employeeId) {
        return checkIsAnEternalConsultant(employeeId)
                ? Optional.of(new EmployeeModel("External consultant"))
                : employeeRepository.getNameById(employeeId).map(EmployeeModel::new);
    }

    private boolean checkIsAnEternalConsultant(int employeeId) {
        return employeeId>=100;
    }

}
