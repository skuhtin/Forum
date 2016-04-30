package forum.dao;

import forum.model.User;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class UsersDao {

  public static void main(String[] args) throws SQLException{
   // User user = new User("login", "password");
    UsersDao usersDao = new UsersDao();
    //usersDao.insertUser(user);
    Map<Integer, User> users = usersDao.loadUsers();
    for (Map.Entry<Integer,User> out : users.entrySet()) {
      User user = out.getValue();
      System.out.println(out.getKey() + " " + user.getLogin() + " " + user.getPassword() + " " + user.isBan() + " " + user.isAdmin() );
    }
  }

  public Map<Integer, User> loadUsers() throws SQLException{
    Connection connection = getConnection();
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
    Map<Integer, User> users = new HashMap<Integer, User>();
    while (resultSet.next()) {
      getUsers(resultSet, users);
    }
    return  users;
  }

  private void getUsers(ResultSet resultSet, Map<Integer, User> users) throws SQLException {
    int idUser = resultSet.getInt("id");
    String login = resultSet.getString("login");
    String passw = resultSet.getString("password");
    User user = new User(login,passw);
    boolean banInfo = resultSet.getBoolean("ban");
    boolean adminInfo = resultSet.getBoolean("admin");
    user.setBan(banInfo);
    user.setAdmin(adminInfo);
    users.put(idUser, user);
  }

  public void insertUser(User user) throws SQLException{
    Connection connection = getConnection();
    PreparedStatement statement = connection.prepareStatement("INSERT INTO users (login, password) VALUES (?,?)");
    statement.setString(1, user.getLogin());
    statement.setString(2, user.getPassword());
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
    }catch(SQLException e) {
      System.out.println("Connection Failed! Check output console");
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }
}
