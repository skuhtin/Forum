package forum.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet{
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();
    resp.setContentType("text/html;charset=UTF-8");

    out.print("<html><head>");
    out.print("<title>Authentication form</title>");
    out.print("</head>");
    out.print("<body>");
    out.print("<h1>Welcome to our forum!</h1><br>");
    out.print("Login:<input type=\"text\" name=\"login\">");
    out.print("<p> Psswd: <input type=\"password\" name=\"password\"><br>");
    out.print("<p><input type=\"submit\" name=\"enter\" value=\"Enter\">");
    out.print("<input type=\"submit\" name=\"registration\" value=\"Registration\"></p>");
    out.println("</body></html>");
  }
}
