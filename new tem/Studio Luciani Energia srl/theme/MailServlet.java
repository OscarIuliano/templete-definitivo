import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.*;
import helper.MailUtility;

public class MailServlet extends javax.servlet.http.HttpServlet
    implements javax.servlet.Servlet
{
  public MailServlet(){ super(); }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {
    String mitt = request.getParameter("mittente");
    String dest = request.getParameter("destinatario");
    String oggetto = request.getParameter("oggetto");
    String testo = request.getParameter("contenuto");

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    try
    {
      MailUtility.sendMail(dest, mitt, oggetto, testo);
      out.println("Invio messaggio OK!");
    }
    catch (MessagingException exc)
    {
      out.println("Invio non riuscito!");
      log("MessagingException: " + dest);
      log(exc.toString());
    }
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {
    this.doPost(request, response);
  }
}