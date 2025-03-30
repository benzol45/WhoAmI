package identifier;

import identifier.config.SpringConfig;
import identifier.ui.UI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        UI ui = context.getBean(UI.class);
        while (true) {
            ui.displayUi();
        }
    }
}
