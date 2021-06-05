package iDoge;

import iDoge.User;

public class Follow_Request{
    private User sender;
    private User reciever;
    private String status; //Pending, Accepted, Rejected

    public Follow_Request(){}

    public Follow_Request(User sender, User reciever) {
        this.sender = sender;
        this.reciever = reciever;
        this.status = "Pending";
    }

    public User getSender() {
        return sender;
    }

    public User getReciever() {
        return reciever;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
