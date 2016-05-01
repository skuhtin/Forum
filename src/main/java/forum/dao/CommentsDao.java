package forum.dao;

import forum.model.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CommentsDao {

  public void insertComment(int topicId, Comment comment)  {
    try {
      Connection connection = getConnection();
      comment.setTopicId(topicId);
      handleInsert(connection, comment);
      connection.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private void handleInsert(Connection connection, Comment comment) throws SQLException {
    PreparedStatement statement = connection.prepareStatement("INSERT INTO comments (TOPIC_ID, TEXT, userName) VALUES (?,?,?)");
    statement.setInt(1, comment.getTopicId());
    statement.setString(2, comment.getText());
    statement.setString(3, comment.getUserHandler());
    statement.execute();
    connection.close();
  }

  public List<Comment> loadComment(int idTopic) {
    try {
      Connection connection = getConnection();
      List<Comment> result = handleComment(connection, idTopic);
      connection.close();
      return result;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private List<Comment> handleComment(Connection connection, int idTopic) throws SQLException {
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("select * from comments where TOPIC_ID =" + idTopic);
    List<Comment> result = new ArrayList<Comment>();
    while (resultSet.next()) {
      String text = resultSet.getString("TEXT");
      String userHandler = resultSet.getString("userName");
      result.add(new Comment(text, userHandler));
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
