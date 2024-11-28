package Base;

import java.util.Scanner;

public class CLIManager {
    Scanner scanner = new Scanner(System.in);

    public static class CLIOption {
        public interface Action {
            void run();
        }

        String label;
        Action action;

        public CLIOption(String label, Action action) {
            this.label = label;
            this.action = action;
        }
    }

    public void CreateOptionMenu(String title, CLIOption[] uiOptions) {
        while (true) {
            // TOD0: Clear the console
            DisplayMessage(title);
            DisplayMessage("Options: ");

            for (int i = 0; i < uiOptions.length; i++) {
                DisplayMessage("\t[ " + (i + 1) + " ]: " + uiOptions[i].label);
            }

            DisplayMessageInline("Select an option: ");

            int option = GetInputInt();
            if (option > 0 && option <= uiOptions.length) {
                uiOptions[option - 1].action.run();
                return; // Exit the loop
            } else {
                DisplayMessage("Invalid option selected");
            }
        }
    }

    public void DisplayMessage(String message) {
        System.out.println(message);
    }

    public void DisplayMessageInline(String message) {
        System.out.print(message);
    }

    public int GetInputInt() {
        return scanner.nextInt();
    }

    public String GetInputString() {
        String input = "";
        while (input.isEmpty()) {
            input = scanner.nextLine().trim();
        }
        return input;
    }

}