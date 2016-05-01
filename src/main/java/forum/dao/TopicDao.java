package forum.dao;

import forum.model.Topic;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class TopicDao {

  public void insertTopic(Topic topic)  {
    try {
      Connection connection = getConnection();
      handleInsert(connection, topic);
      connection.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private void handleInsert(Connection connection, Topic topic) throws SQLException {
    PreparedStatement statement = connection.prepareStatement("INSERT INTO topics (HeadOfTopic, TextOfTopic) VALUES (?,?)");
    statement.setString(1, topic.getHead());
    statement.setString(2, topic.getText());
    statement.execute();
  }

  public Map<Integer, Topic> loadTopic() {
    try {
      Connection connection = getConnection();
      Map<Integer, Topic > result = handleTopic(connection);
      connection.close();
      return result;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private  Map<Integer, Topic> handleTopic(Connection connection) throws SQLException {
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("select * from topics");
    Map<Integer, Topic> result = new HashMap<Integer, Topic>();
    while (resultSet.next()) {
      int id = resultSet.getInt(1);
      String head = resultSet.getString(2);
      String body = resultSet.getString(3);
      result.put(id, new Topic(head,body));
    }
    connection.close();
    return result;
  }

  private Connection getConnection() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
    try {
      return DriverManager
          .getConnection("jdbc:mysql://localhost:3306/forum", "forum", "forum");
    } catch (SQLException e) {
      System.out.println("Connection Failed! Check output console");
      e.printStackTrace();
      throw new RuntimeException(e);
    }

  }
}
