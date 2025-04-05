package identifier.controller;

import identifier.model.EmployeeModel;
import identifier.service.IdentifyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IdentifyService identifyService;

    @GetMapping
    //@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<EmployeeModel> getAllEmployee() {
        return identifyService.getAllEmployee();
    }

    @GetMapping("/{employeeId}")
    //@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<EmployeeModel> findEmployee(@PathVariable("employeeId") Integer employeeId) {
        return identifyService.findEmployee(employeeId)
                .map(employee -> ResponseEntity.ok(employee))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Integer createEmployee(@RequestBody @Valid EmployeeModel employeeModel) {
        return identifyService.createEmployee(employeeModel);
    }

    @PutMapping("/{employeeId}")
    //@PreAuthorize("hasRole('ADMIN')")
    public Integer editEmployee(
            @PathVariable("employeeId") Integer employeeId,
            @RequestBody @Valid EmployeeModel employeeModel) {
        return identifyService.editEmployee(employeeId, employeeModel);
    }
}

