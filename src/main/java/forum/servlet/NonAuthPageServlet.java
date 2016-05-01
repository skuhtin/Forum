package forum.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class NonAuthPageServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();
    viewPage(out);
  }

  private void viewPage(PrintWriter out) {
    out.print("<html><body>");
    out.print("<h1>You are not authorized or were banned. Please, try again</h1>");
    out.print("<form action=\"/login\" method=\"GET\">");
    out.print("<input type=\"submit\" value=\"Try again!\">");
    out.print("</form>");
    out.print("</body></html>");
  }
}
