package forum.model;

public class Message {
  private String fromUser;
  private String toUser;
  private String text;
  private boolean notRead;

  public Message(String fromUser, String toUser, String text) {
    this.fromUser = fromUser;
    this.toUser = toUser;
    this.text = text;
  }

  public Message(String fromUser, String text) {
    this.fromUser = fromUser;
    this.text = text;
  }

  public boolean isNotRead() {
    return notRead;
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
