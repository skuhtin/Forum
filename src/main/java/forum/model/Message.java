package forum.model;


public class Message {
  private String fromUser;
  private String toUser;
  private String text;

  public Message(String text) {
    this.text = text;
  }

  public void setFromUser(String fromUser) {
    this.fromUser = fromUser;
  }

  public void setToUser(String toUser) {
    this.toUser = toUser;
  }

  public String getFromUser() {
    return fromUser;
  }

  public String getToUser() {
    return toUser;
  }

  public String getText() {
    return text;
  }
}
