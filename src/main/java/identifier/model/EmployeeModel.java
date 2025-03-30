package identifier.model;

import jakarta.validation.constraints.NotBlank;

public class EmployeeModel {
    @NotBlank
    private String name;

    public EmployeeModel() {
    }

    public EmployeeModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
