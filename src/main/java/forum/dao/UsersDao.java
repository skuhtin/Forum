package forum.dao;

import forum.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UsersDao {

  public static void main(String[] args) throws SQLException {
    // User user = new User("login", "password");
    UsersDao usersDao = new UsersDao();
    //usersDao.insertUser(user);
    //usersDao.banProperty("login", true);
    //usersDao.kickUser("test");
  }

  public void banProperty(String userName, boolean ban) {
    Connection connection = getConnection();
    String request = "UPDATE users SET ban = ? WHERE login = ?";
    try {
      PreparedStatement statement = connection.prepareStatement(request);
      statement.setBoolean(1, ban);
      statement.setString(2, userName);
      statement.execute();
      connection.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }

  public void kickUser(String userName) {
    Connection connection = getConnection();
    String delete = "DELETE FROM users WHERE login = ?";
    try {
      PreparedStatement statement = connection.prepareStatement(delete);
      statement.setString(1,userName);
      statement.execute();
      connection.close();
    }catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public List<User> getBannedUser() {
    List<User> users;
    Connection connection = getConnection();
    try {
      users = handleBanUsers(connection);
      connection.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return users;
  }

  private List<User> handleBanUsers(Connection connection) throws SQLException {
    String banUser = "SELECT * FROM users WHERE ban = 1";
    PreparedStatement statement = connection.prepareStatement(banUser);
    ResultSet resultSet = statement.executeQuery();
    List<User> users = new ArrayList<User>();
    while (resultSet.next()) {
      String userName = resultSet.getString("login");
      String password = resultSet.getString("password");
      User user = new User(userName, password);
      users.add(user);
    }
    return users;
  }

  public User getUserbyLogin(String login) {
    User user;
    Connection connection = getConnection();
    try {
      user = handleGetUser(login, connection);
      connection.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return user;
  }

  private User handleGetUser(String login, Connection connection) throws SQLException {
    PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE login = ?");
    statement.setString(1, login);
    ResultSet resultSet = statement.executeQuery();
    User user;
    if (resultSet.next()) {
      String password = resultSet.getString("password");
      boolean ban = resultSet.getBoolean("ban");
      boolean admin = resultSet.getBoolean("admin");
      user = new User(login, password, ban, admin);
    } else {
      user = null;
      System.out.println("Login error");
    }
    return user;
  }


  public boolean userIsAbsent(String login, String password) {
    List<User> users = loadUsers();
    for (User user : users) {
      if (user.getLogin().equals(login) & user.getPassword().equals(password)) {
        return false;
      }
    }
    return true;
  }

  public boolean sameLogin(String login) {
    List<User> users = loadUsers();
    for (User user : users) {
      if (user.getLogin().equals(login)) {
        return true;
      }
    }
    return false;
  }

  public List<User> loadUsers() {
    Connection connection = getConnection();
    try {
      List<User> users = handleUsers(connection);
      connection.close();
      return users;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private List<User> handleUsers(Connection connection) throws SQLException {
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
    List<User> users = new ArrayList<User>();
    while (resultSet.next()) {
      getUsers(resultSet, users);
    }
    connection.close();
    return users;
  }

  private void getUsers(ResultSet resultSet, List<User> users) throws SQLException {
    String login = resultSet.getString("login");
    String passw = resultSet.getString("password");
    User user = new User(login, passw);
    boolean banInfo = resultSet.getBoolean("ban");
    boolean adminInfo = resultSet.getBoolean("admin");
    int idUser = resultSet.getInt("id");
    user.setId(idUser);
    user.setBan(banInfo);
    user.setAdmin(adminInfo);
    users.add(user);
  }

  public void insertUser(User user) {
    try {
      Connection connection = getConnection();
      handleInsert(user, connection);
      connection.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private void handleInsert(User user, Connection connection) throws SQLException {
    PreparedStatement statement = connection.prepareStatement("INSERT INTO users (login, password) VALUES (?,?)");
    statement.setString(1, user.getLogin());
    statement.setString(2, user.getPassword());
    statement.execute();
    connection.close();
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
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }
}
