package org.example;

import java.util.Random;

public class message {
    private String messageID;
    private String recipient;
    private String content;
    private String messageHash;
    private int messageNumber;
    private static int totalMessages = 0;

    public message(String recipient, String content) {
        this.recipient = recipient;
        this.content = content;
        this.messageNumber = ++totalMessages;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    // Validation methods
    public boolean checkMessageID() {
        return messageID.length() == 10;
    }

    public boolean checkRecipientCell() {
        return recipient.startsWith("+") && recipient.length() <= 10;
    }

    public String createMessageHash() {
        String[] words = content.split(" ");
        String first = words.length > 0 ? words[0] : "";
        String last = words.length > 1 ? words[words.length-1] : first;
        return messageID.substring(0, 2) + ":" + messageNumber + ":" + (first + last).toUpperCase();
    }

    public String sentMessage(int choice) {
        switch(choice) {
            case 1: return "Message successfully sent.";
            case 2: return "Press 0 to delete message.";
            case 3: return "Message successfully stored.";
            default: return "Invalid choice";
        }
    }

    public static int returnTotalMessages() {
        return totalMessages;
    }

    // Helper methods
    private String generateMessageID() {
        Random rand = new Random();
        return String.format("%010d", rand.nextInt(1000000000));
    }

    public void storeMessage() {
        // Implementation for storing to JSON would go here
    }
}