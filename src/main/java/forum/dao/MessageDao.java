package forum.dao;




import forum.model.Message;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MessageDao {

  public void insertMessage(Message message) {
    try {
      Connection connection = getConnection();
      handleInsert(message, connection);
      connection.close();
    }catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private void handleInsert(Message message, Connection connection) throws SQLException {
    PreparedStatement statement = connection.prepareStatement("INSERT INTO message (fromUser, toUser, text) VALUES (?,?,?)");
    statement.setString(1, message.getFromUser());
    statement.setString(2, message.getToUser());
    statement.setString(3, message.getText());
    statement.execute();
  }

  private Connection getConnection() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    }catch(ClassNotFoundException e){
      throw new RuntimeException(e);
    }
    try {
      return DriverManager.getConnection("jdbc:mysql://localhost:3306/forum", "forum", "forum");
    } catch (SQLException e) {
      System.out.println("Connection Failed! Check output console");
      throw new RuntimeException(e);
    }
  }
}
