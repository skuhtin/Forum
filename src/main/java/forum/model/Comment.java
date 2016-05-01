package forum.model;


public class Comment {
  private int topicId;
  private String userHandler;
  private String text;

  public Comment(String text, String userHandler) {
    this.userHandler = userHandler;
    this.text = text;
  }

  public void setTopicId(int topicId) {
    this.topicId = topicId;
  }

  public int getTopicId() {
    return topicId;
  }

  public String getUserHandler() {
    return userHandler;
  }

  public String getText() {
    return text;
  }
}
