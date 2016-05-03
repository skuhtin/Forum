package forum.model;

public class Message {
  private String fromUser;
  private String toUser;
  private String text;
  private boolean notRead;

  public Message(String text) {
    this.text = text;
  }

  public boolean isNotRead() {
    return notRead;
  }

  public void setNotRead(boolean notRead) {
    this.notRead = notRead;
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
