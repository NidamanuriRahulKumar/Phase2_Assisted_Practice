import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.*;
import javax.xml.bind.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.ecommerce.Color;
import com.ecommerce.EProduct;
import com.ecommerce.Finance;
import com.ecommerce.HibernateUtil;


/**
* Servlet implementation class ProductDetails
*/
@WebServlet("/ProductDetails")
public class ProductDetails extends HttpServlet {
        private static final long serialVersionUID = 1L;
       
        
        
    /**
* @see HttpServlet#HttpServlet()
*/
    public ProductDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

        /**
         * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
         */
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                // TODO Auto-generated method stub
                 try {
                        SessionFactory factory = HibernateUtil.getSessionFactory();
                        
                        Session session = factory.openSession();
                        
                         
                        List<EProduct> list = session.createQuery("from EProduct").list();
                        
                         PrintWriter out = response.getWriter();
                         out.println("<html><body>");
                         
                         out.println("<b>One to One Mapping</b><br>");
                         for(EProduct p: list) {
                                 out.println("ID: " + String.valueOf(p.getID()) + ", Name: " + p.getName() +
                                                 ", Price: " + String.valueOf(p.getPrice()) + ", Date Added: " + p.getDateAdded().toString());
                                 PDescription descrip = p.getPdescrip();
                                 out.println("<br>Description:" + descrip.getDescrip());
                                 out.println("<hr>");
                         }                       
                         out.println("<b>One to Many and Many to One Mapping</b><br>");
                         for(EProduct p: list) {
                                 out.println("ID: " + String.valueOf(p.getID()) + ", Name: " + p.getName() +
                                                 ", Price: " + String.valueOf(p.getPrice()) + ", Date Added: " + p.getDateAdded().toString());                           
                                 List<Color> colors = p.getColors();
                                 out.println("<br>Colors: <ul>");
                                 for(Color c: colors) {
                                         out.print("<li>" + c.getName() + "</li>");
                                 }
                                 out.println("</ul>");
                                 out.println("<hr>");
                                 
                         }
                         out.println("<b>Many to Many Mapping</b><br>");
                         for(EProduct p: list) {
                                 out.println("ID: " + String.valueOf(p.getID()) + ", Name: " + p.getName() +
                                                 ", Price: " + String.valueOf(p.getPrice()) + ", Date Added: " + p.getDateAdded().toString());
                                 Set<Finance> finances= p.getFinance();
                                 out.println("<br>Finance Options : <ul>");
                                 for(Finance f: finances) {
                                         out.print("<li>" + f.getFtype() + "</li>");
                                 }
                                 out.println("</ul>");
                                 out.println("<hr>");
                         }
                         
                         
                                session.close();

                     out.println("</body></html>");
                     
                     
                 } catch (Exception ex) {
                         throw ex;
                 }
                    
        }

        /**
         * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
         */
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                // TODO Auto-generated method stub
                doGet(request, response);
        }

}
