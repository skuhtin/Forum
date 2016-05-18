package forum.model;

public class User {
  private int id;
  private String login;
  private String password;
  private boolean ban = false;
  private boolean admin = false;

  public User(String login, String password){
    this.login = login;
    this.password = password;
  }

  public User(String login, String password, boolean ban, boolean admin) {
    this.login = login;
    this.password = password;
    this.ban = ban;
    this.admin = admin;
  }

  public boolean isAdmin() {
    return admin;
  }

  public void setAdmin(boolean admin) {
    this.admin = admin;
  }

  public int getId() {
    return id;
  }

  public boolean isBan() {
    return ban;
  }

  public String getLogin() {
    return login;
  }

  public String getPassword() {
    return password;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setBan(boolean ban) {
    this.ban = ban;
  }
}
