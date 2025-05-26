package org.example;

import javax.swing.JOptionPane;
import java.util.function.Predicate;

public class Login {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String cellphone;
    private boolean isLoggedIn = false;

    public void registerAndLoginUser() {
        firstName = JOptionPane.showInputDialog("Enter your name:");
        lastName = JOptionPane.showInputDialog("Enter your surname:");

        username = promptUntilValid(
                "Create a username (must include '_' and max 5 characters):",
                this::checkUsername
        );

        password = promptUntilValid(
                "Create a password (min 8 chars, must include uppercase, lowercase, number, special character):",
                this::checkPasswordComplexity
        );

        cellphone = promptUntilValid(
                "Enter your cellphone number (e.g. ‪+27831234567‬):",
                this::checkCellPhoneNumber
        );

        JOptionPane.showMessageDialog(null, "Registration successful! Welcome " + firstName + " " + lastName);

        while (!isLoggedIn) {
            String loginUser = JOptionPane.showInputDialog("Login: Enter username:");
            String loginPass = JOptionPane.showInputDialog("Enter password:");
            if (loginUser(username, loginPass)) {
                JOptionPane.showMessageDialog(null, returnLoginStatus(true));
                isLoggedIn = true;
            } else {
                JOptionPane.showMessageDialog(null, returnLoginStatus(false));
            }
        }
    }

    // Validation methods
    public boolean checkUsername(String username) {
        if (username.contains("_") && username.length() <= 5) {
            this.username = username;
            return true;
        }
        return false;
    }

    public boolean checkPasswordComplexity(String password) {
        boolean hasUpper = false, hasLower = false, hasNumber = false, hasSpecial = false;
        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) hasUpper = true;
            else if (Character.isLowerCase(ch)) hasLower = true;
            else if (Character.isDigit(ch)) hasNumber = true;
            else if (!Character.isLetterOrDigit(ch)) hasSpecial = true;
        }
        return password.length() >= 8 && hasUpper && hasLower && hasNumber && hasSpecial;
    }

    public boolean checkCellPhoneNumber(String phone) {
        return phone.startsWith("+27") && phone.length() == 12;
    }

    // Other required methods
    public boolean loginUser(String username, String password) {
        return username.equals(this.username) && password.equals(this.password);
    }

    public String returnLoginStatus(boolean status) {
        return status ? "Welcome " + firstName + ", " + lastName + " it is great to see you again."
                : "Username or password incorrect, please try again.";
    }

    // Helper method
    private String promptUntilValid(String message, Predicate<String> validator) {
        String input;
        do {
            input = JOptionPane.showInputDialog(message);
        } while (!validator.test(input));
        return input;
    }
}

