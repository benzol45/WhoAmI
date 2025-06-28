package identifier.repository;

import identifier.entity.Employee;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepository {

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void init() {
        //fillEmployee();
    }

    private void fillEmployee() {
        EntityTransaction transaction = em.getTransaction();
        //transaction.begin();
        if (em.find(Employee.class, 11) == null) {
            em.persist(new Employee(11, "John Doe"));
        }
        if (em.find(Employee.class, 12) == null) {
            em.persist(new Employee(12, "Michael Smith"));
        }
        if (em.find(Employee.class, 13) == null) {
            em.persist(new Employee(13, "David Brown"));
        }
        transaction.commit();
    }


    public Optional<String> getNameById(int employeeId) {
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

/*    @Transactional
    public void initData(List<Employee> employees) {
        employees.forEach(employee -> {
            if (em.find(Employee.class, employee.getId()) == null) {
                em.persist(employee);
            }
        });
    }*/
}
