package Main.Features.tableCons;

public class ChatCL {
    String username;
    int usernameID;
    String message;
    int time;
    int messageID;

    public ChatCL(String username, int usernameID, String message, int time, int messageID) {
        this.username = username;
        this.usernameID = usernameID;
        this.message = message;
        this.time = time;
        this.messageID = messageID;
    }

    public String getUsername() {
        return username;
    }

    public int getUsernameID() {
        return usernameID;
    }

    public String getMessage() {
        return message;
    }

    public int getTime() {
        return time;
    }

    public int getMessageID() {
        return messageID;
    }
}
