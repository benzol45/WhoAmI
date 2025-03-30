package identifier.repository;

import identifier.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Repository
public class EmployeeRepository {

    @PersistenceContext
    private EntityManager em;

    private void fillEmployee() {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(new Employee(11, "John Doe"));
        em.persist(new Employee(12, "Michael Smith"));
        em.persist(new Employee(13, "David Brown"));
        transaction.commit();
    }


    public Optional<String> getNameById(int employeeId) {
        EntityTransaction transaction = em.getTransaction();
        Employee foundEmployee = em.find(Employee.class, employeeId);

        if (foundEmployee != null) {
            return Optional.of(foundEmployee.getName());
        } else {
            return Optional.empty();
        }
    }

    public Optional<Integer> getIdByName(String name) {
        Employee foundEmployee = em.createQuery("SELECT e FROM Employee e WHERE e.name = :name", Employee.class)
                .setParameter("name", name)
                .getSingleResult();

        if (foundEmployee != null) {
            return Optional.of(foundEmployee.getId());
        } else {
            return Optional.empty();
        }
    }
}
