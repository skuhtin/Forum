package forum.dao;

import forum.model.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDao {
  public static void main(String[] args) {
    MessageDao messageDao = new MessageDao();
    messageDao.setReadMessage("login");
  }

  public void setReadMessage(String userName) {
    try {
      Connection connection = getConnection();
      handleSetMessage(userName, connection);
      connection.close();
    }catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private void handleSetMessage(String userName, Connection connection) throws SQLException {
    PreparedStatement statement = connection.prepareStatement("UPDATE message SET notRead = 0 WHERE toUser = '" + userName + "'");
    statement.execute();
    connection.close();
  }

  public int getNewMessages(String userName){
    try {
      Connection connection = getConnection();
      int count = handleNewMessages(userName, connection);
      connection.close();
      return count;
    }catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private int handleNewMessages(String userName, Connection connection) throws SQLException {
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("SELECT COUNT(notRead) FROM message WHERE notRead = 1 and toUser = '" + userName + "'");
    int count = 0;
    while (resultSet.next()) {
      count = resultSet.getInt(1);
    }
    connection.close();
    return count;
  }

  public List<Message> loadMessage(String userName) {
    try {
      Connection connection = getConnection();
      List<Message> messages = handleMessage(userName, connection);
      connection.close();
      return messages;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private List<Message> handleMessage(String userName, Connection connection) throws SQLException {
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("SELECT fromUser, text FROM message WHERE toUser = '" + userName + "'");
    List<Message> messages = new ArrayList<Message>();
    while (resultSet.next()) {
      String fromUser = resultSet.getString("fromUser");
      String text = resultSet.getString("text");
      Message message = new Message(fromUser, text);
      messages.add(message);
    }
    return messages;
  }

  public void insertMessage(Message message) {
    try {
      Connection connection = getConnection();
      handleInsert(message, connection);
      connection.close();
    } catch (SQLException e) {
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
    } catch (ClassNotFoundException e) {
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
