package forum.dao;

import forum.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UsersDao {

  public static void main(String[] args) throws SQLException{
   // User user = new User("login", "password");
    UsersDao usersDao = new UsersDao();
    //usersDao.insertUser(user);
    List<User> users = usersDao.loadUsers();
    for (User user : users) {
      System.out.println(user.getId() + " " + user.getLogin() + " " + user.getPassword() + " " + user.isBan() + " " + user.isAdmin() );
    }
  }
  public boolean userIsAbsent(String login, String password) {
    List <User> users = loadUsers();
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
    return  users;
  }

  private void getUsers(ResultSet resultSet, List<User> users) throws SQLException {
    String login = resultSet.getString("login");
    String passw = resultSet.getString("password");
    User user = new User(login,passw);
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
    }catch(SQLException e) {
      System.out.println("Connection Failed! Check output console");
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }
}
