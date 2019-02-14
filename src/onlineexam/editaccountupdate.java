package onlineexam;

import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/editaccountupdate")
public class editaccountupdate extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	PreparedStatement pst1;
    Connection cn;
    Statement pst2;

	public editaccountupdate() 
	{
        super();
        
        try
        {
           Class.forName("com.mysql.jdbc.Driver");
           cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/quizz","root","ramakant");
           //pst2=cn.prepareStatement("update user set fname=? lname=? email=? cell=? password=?  where uid=?");  
           //pst2=cn.prepareStatement("insert into user values(?,?,?,?,?,?,?,?)");
           pst2=cn.createStatement();
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
			{response.sendRedirect("loginpage.html");
			 return;
			}
		
		
		   String fname,lname,email,phn,passwd;
		
		   int lid=Integer.parseInt(request.getParameter("loid"));

		    fname=request.getParameter("fname");
		    lname=request.getParameter("lname");
		    passwd=request.getParameter("pwd");
		    phn=request.getParameter("phon");
		    email=request.getParameter("email");
           
		   try
		   {
		    
		  
		    pst2.executeUpdate("update user set fname='"+fname+"', lname='"+lname+"', email='"+email+"', cell='"+phn+"', password='"+passwd+"'  where uid="+lid);
		    System.out.println("done !");
		   }
		   catch(Exception e)
		   {
			   System.err.println("Update : "+e.getMessage());		
		   }
		   
		   String str="<html><body bgcolor=\"blue\"><center><h3>Update successful for "+fname+"<h3></body></html></center>";
		     PrintWriter pw=response.getWriter();
		     pw.println(str);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

}

