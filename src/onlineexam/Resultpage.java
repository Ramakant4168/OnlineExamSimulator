package onlineexam;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Resultpage")
public class Resultpage extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	PreparedStatement pst1,pst2;
	Statement st1;
    Connection cn;
    
    public Resultpage() 
    {
        super();
        try
        {
           Class.forName("com.mysql.jdbc.Driver");
           cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/quizz","root","ramakant");
           pst1=cn.prepareStatement("select * from test where user_id=?");
		   
           
        }
        catch(ClassNotFoundException e)
        {
           System.err.println("Driver Not Found....aborting"); 
        } 
        catch(SQLException ae)
        {
        	System.err.println("sql error"); 
        }
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession ses=request.getSession(false);
		if(ses==null)
			{
			
			 response.sendRedirect("loginpage.html");
			 return;
			}
		 
		
		 String str= "<html>"+

			        "<head>"+
			        "<link href=\"css/style.css\" rel=\"stylesheet\" />"+
			        
			         "</head>"+
			  
			         "<body>"+
			   
                   "<div class=\"outeruser2\"></font color=\"#036329\" size=\"20\">"+
			   
			           "<div class=\"topper\">"+
			                "<div class=\"logo\">"+
			                       "<font face=\"Old English Text MT\" size=\"5\">"+
			                        "<#OnlineExam@></font>"+
			                 "</div>"+
			                        
                        "</div>"; 
		
		
         int lid=Integer.parseInt(request.getParameter("userid"));
	
		 ResultSet rs;
	 	 
		 try 
		 {
		   pst1.setInt(1,lid);
		  
		   
		   rs=pst1.executeQuery();
		   
		   str=str+ "<div class=\"resultdisplay21\">"
		   		      + "<div class=\"resultdisplay23\">test-id</div> "
             		  + "<div class=\"resultdisplay23\">Userid</div>"
             		  + "<div class=\"resultdisplay23\">duration</div>"
             		  + "<div class=\"resultdisplay23\">marks</div>"
             		
             		+ "</div>";
		   
		  
			   
		   if(rs.next())  
		   {     
			     while(rs.next())
			      { int testid=rs.getInt("test_id") ;
			        int uid=rs.getInt("user_id") ;
			        int dur=rs.getInt("duration") ;
			        int marks=rs.getInt("marks") ;
			        System.out.println("duration is"+dur);
					
                 
		          
                       str=str+"<div class=\"resultdisplay21\"> <div class=\"resultdisplay2\">"+testid+"</div>"+
                		  "<div class=\"resultdisplay2\">"+uid+"</div>"+
                		  "<div class=\"resultdisplay2\">"+dur+ "min"+"</div>"+
                		  "<div class=\"resultdisplay2\">"+marks+"</div></div>";
			      }   
		    }
		   
		   else
		   {
			   
			 str="<html><body bgcolor=\"#CCCCCC\"><center>No exam Attempted</center></body></html>";  
			   
		   }
			
		 }
		 
		 catch (SQLException e) 
		   {
			System.err.print(e.getMessage());
	       }
		 
		 
			   
		      str=str+"</div>";
		
		      str=str+ "<div class=\"footer\"> All Rights@Ramakant Vaishnav</div>"+ 
		           
		        "</body>"+
		  
		            "</html>";
		
		 
		 
		 
		 PrintWriter pw=response.getWriter();
		 pw.println(str);
		 
		 System.out.println();
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

}
