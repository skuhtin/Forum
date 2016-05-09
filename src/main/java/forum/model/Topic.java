package forum.model;

public class Topic {

  private String handleUser;
  private String headOfTopic;
  private String textOfTopic;

  public Topic(String headOfTopic, String textOfTopic, String handleUser) {
    this.headOfTopic = headOfTopic;
    this.textOfTopic = textOfTopic;
    this.handleUser = handleUser;
  }

  public String getHandleUser() {
    return handleUser;
  }

  public String getHead(){
    return headOfTopic;
  }

  public String getText() {
    return textOfTopic;
  }

  @Override
  public String toString() {
    return headOfTopic + " " + textOfTopic;
  }
}
