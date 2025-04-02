package identifier.repository;

import identifier.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepository {

    @PersistenceContext
    private EntityManager em;



    public Optional<Employee> getById(int employeeId) {
        Employee foundEmployee = em.find(Employee.class, employeeId);

        if (foundEmployee != null) {
            return Optional.of(foundEmployee);
        } else {
            return Optional.empty();
        }
    }

    public List<Employee> getAll() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class)
                .getResultList();
    }

    @Transactional
    public Employee createEmployee(Employee employee) {
        em.persist(employee);
        return employee;
    }

}
