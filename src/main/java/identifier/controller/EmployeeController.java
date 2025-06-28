package identifier.controller;

import identifier.model.EmployeeModel;
import identifier.model.UserInputModel;
import identifier.service.IdentifyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class EmployeeController {

    private final IdentifyService identifyService;

    @Autowired
    public EmployeeController(IdentifyService identifyService) {
        this.identifyService = identifyService;
    }

    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("userInput", new UserInputModel());
        return "main";
    }

    @PostMapping
    public String processForm(@ModelAttribute("userInput") @Valid UserInputModel input,
                              BindingResult result,
                              Model model) {
        if (result.hasErrors()) {
            return "main";
        }
        Optional<EmployeeModel> employee = identifyService.getName(input.getId());
        if (employee.isPresent()) {
            model.addAttribute("employee", employee.get());
            return "result";
        } else {
            model.addAttribute("error", "Employee with id " + input.getId() + " not found");
            return "main";
        }
    }
}

