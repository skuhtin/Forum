package forum.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CommentsDao {

  public void insertComment(int idTopic, String comment)  {
    try {
      Connection connection = getConnection();
      handleInsert(connection, idTopic, comment);
      connection.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private void handleInsert(Connection connection, int idTopic, String comment) throws SQLException {
    PreparedStatement statement = connection.prepareStatement("INSERT INTO comments (TOPIC_ID, TEXT) VALUES (?,?)");
    statement.setInt(1, idTopic);
    statement.setString(2, comment);
    statement.execute();
    connection.close();
  }

  public List<String> loadComment(int idTopic) {
    try {
      Connection connection = getConnection();
      List<String> result = handleComment(connection, idTopic);
      connection.close();
      return result;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private List<String> handleComment(Connection connection, int idTopic) throws SQLException {
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("select * from comments where TOPIC_ID =" + idTopic);
    List<String> result = new ArrayList<String>();
    while (resultSet.next()) {
      String comment = resultSet.getString("TEXT");
      result.add(comment);
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
