package identifier.model;


import jakarta.validation.constraints.NotBlank;
import org.springframework.lang.NonNull;

public class EmployeeModel {
    @NonNull
    private Integer id;
    @NonNull
    @NotBlank
    private String name;

    public EmployeeModel() {
    }

    public EmployeeModel(@NonNull Integer id, @NonNull String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

