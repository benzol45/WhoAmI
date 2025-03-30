package identifier.ui;

import identifier.service.IdentifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class UI {
    private final IdentifyService identifyService;

    @Autowired
    public UI(IdentifyService identifyService) {
        this.identifyService = identifyService;
    }

    public void displayUi() {
        System.out.println("\n");
        System.out.println("Good morning. Input your employee ID, please");
        Scanner scanner = new Scanner(System.in);
        int employeeId = scanner.nextInt();

        Optional<String> employeeName = identifyService.getName(employeeId);
        employeeName.ifPresentOrElse(
                name -> System.out.println("You are: " + name),
                () -> System.out.println("Sorry, I don't know you. Your company is probably across the street")
        );
    }
}
