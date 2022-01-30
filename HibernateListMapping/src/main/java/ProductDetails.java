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
import com.ecommerce.OS;
import com.ecommerce.ScreenSizes;

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
                         out.println("<b>Product Listing</b><br>");
                         for(EProduct p: list) {
                                 out.println("ID: " + String.valueOf(p.getID()) + ", Name: " + p.getName() +
                                                 ", Price: " + String.valueOf(p.getPrice()) + ", Date Added: " + p.getDateAdded().toString());
                                 
                                 List<Color> colors = p.getColors();
                                 out.println("Colors: ");
                                 for(Color c: colors) {
                                         out.print(c.getName() + "&nbsp;");
                                 }
                                 
                                 Collection<ScreenSizes> sizes= p.getScreensizes();
                                 out.println(", Screen Sizes: ");
                                 for(ScreenSizes s: sizes) {
                                         out.print(s.getSize() + "&nbsp;");
                                 }
                                 
                                 Set<OS> os= p.getOs();
                                 out.println(", OS : ");
                                 for(OS o: os) {
                                         out.print(o.getName() + "&nbsp;");
                                 }
                                 
                                 Map finances = p.getFinance();
                                 out.println(", Finance Options: ");
                                 if (finances.get("CREDITCARD") != null) {
                                        Finance f = (Finance) finances.get("CREDITCARD");
                                        out.println(f.getName() + " &nbsp;");
                                 }
                                 if (finances.get("BANK") != null) {
                                        Finance f = (Finance) finances.get("BANK");
                                        out.println(f.getName() + " &nbsp;");
                                 }
                                 
                                 
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
