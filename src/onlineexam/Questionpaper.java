package onlineexam;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Questionpaper")
public class Questionpaper extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	PreparedStatement pst1,pst2;
	Statement st;
    Connection cn;
   
	
   
    public Questionpaper() 
    {
        super();
        try
        {
           Class.forName("com.mysql.jdbc.Driver");
           cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/quizz","root","ramakant");
           st=cn.createStatement();
		   
           
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

	
	public void init(ServletConfig config) throws ServletException
	{
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		HttpSession ses=request.getSession(false);
		if(ses==null)
			{response.sendRedirect("loginpage.html");
			 return;
			}
	
		 ResultSet rs;
		 String str="";
		 str=str+"<html>"+

			   "<head>"+
			       "<link href=\"css/style.css\" rel=\"stylesheet\" />"+
			       
			   "</head>"+
			  
			   "<body>"+
			   "<div class=\"qouter\">"+
			  
			         "<div class=\"topper\">"+
			               "<div class=\"logo\">"+
			                   "<font face=\"Old English Text MT\" size=\"5\">"+
			                   "<#OnlineExam@></font>"+
			               "</div>"+
			         "</div>"+
			         "<form action=\"calculateresult\">"+ 
			      			   
			             "<div class=\"quemid\"> ";
			             
		                String id=request.getParameter("userid");
		 
			             try 
			    		 {
			    		  
			    		   rs=st.executeQuery("select * from quiz");
			    		   
			    		   while(rs.next())
			    		   {
			    			   int q;
			    			   String que,op1,op2,op3,op4,ans;
			    			   q=rs.getInt("quid");
			    			   que=rs.getString("que");
			    			   op1=rs.getString("op1");
			    			   op2=rs.getString("op2");
			    			   op3=rs.getString("op3");
			    			   op4=rs.getString("op4");
			    			 
			    			   str=str+"Q"+q+que+"<br><br>"+
			    			   "A."+"<input type=\"Checkbox\" name=\"qu"+q+"\" value=\""+op1+"\">"+op1+"<br>"+
			    			   "B."+"<input type=\"Checkbox\" name=\"qu"+q+"\" value=\""+op2+"\">"+op2+"<br>"+
			    			   "C."+"<input type=\"Checkbox\" name=\"qu"+q+"\" value=\""+op3+"\">"+op3+"<br>"+
			    			   "D."+"<input type=\"Checkbox\" name=\"qu"+q+"\" value=\""+op4+"\">"+op4+"<br><br>";
			    					
			    		   }
			    		   
			    		   
		   
				       }
				        catch (SQLException e) 
				         {
					       System.err.print(e.getMessage());
			             }
			             
			             
			             
			        
			        str=str+"</div>"+
			     
			        "</div><input type=\"hidden\" name=\"userid\" value=\""+id+"\">";
		
		            str=str+"<div class=\"qsubmit\"><input type=\"submit\" name=\"submitans\" value=\"submit answers\"></div><div class=\"footer\"></div></body>"+
		   
		                    "</form></html>";
		
		 
		 
		 
		 PrintWriter pw=response.getWriter();
		 pw.println(str);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

}
