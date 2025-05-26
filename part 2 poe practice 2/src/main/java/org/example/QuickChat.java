package org.example;
import javax.swing.JOptionPane;


class Message {
    public static void main(String[] args) {
        Login loginSystem = new Login();
        loginSystem.registerAndLoginUser();

        while (true) {
            String choice = JOptionPane.showInputDialog(
                    "=== QuickChat Menu ===\n" +
                            "1. Send Messages\n" +
                            "2. Show Recently Sent Messages\n" +
                            "3. Quit"
            );

            switch (choice) {
                case "1":
                    sendMessages();
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null, "Coming Soon");
                    break;
                case "3":
                    JOptionPane.showMessageDialog(null, "Goodbye!");
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice, please try again.");
            }
        }
    }

    private static void sendMessages() {
        String input = JOptionPane.showInputDialog("How many messages would you like to send?");
        int numMessages;

        try {
            numMessages = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number. Returning to menu.");
            return;
        }

        for (int i = 0; i < numMessages; i++) {
            String recipient = JOptionPane.showInputDialog("Enter recipient number:");
            String content = JOptionPane.showInputDialog("Enter your message (max 250 characters):");

            if (content.length() > 250) {
                JOptionPane.showMessageDialog(null, "Message too long. Try again.");
                i--;
                continue;
            }

            message message = new message(recipient, content);

            if (!message.checkRecipientCell()) {
                JOptionPane.showMessageDialog(null, "Invalid recipient number format. Try again.");
                i--;
                continue;
            }

            String[] options = {"Send", "Disregard", "Store"};
            int action = JOptionPane.showOptionDialog(
                    null,
                    "What would you like to do with this message?",
                    "Message Options",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            JOptionPane.showMessageDialog(null, message.sentMessage(action + 1));

            if (action == 2) { // Store
                message.storeMessage();
            }
        }

        JOptionPane.showMessageDialog(null, "Total messages processed: " + message.returnTotalMessages());
    }
}