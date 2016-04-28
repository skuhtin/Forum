package forum.model;

public class Topic {
  private String headOfTopic;
  private String textOfTopic;


  public Topic(String headOfTopic, String textOfTopic) {
    this.headOfTopic = headOfTopic;
    this.textOfTopic = textOfTopic;
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
