package identifier.repository;

import identifier.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    //List<Employee> findAllByNameContainsOrderById(String name);
}
