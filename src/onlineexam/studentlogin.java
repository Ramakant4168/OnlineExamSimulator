package onlineexam;

import java.io.*;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

@WebServlet("/studentlogin")
public class studentlogin extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	 PreparedStatement pst1,pst2;
	 Statement st1;
	 Connection cn;
	
   
	 public studentlogin() 
    {
        super();
        
        try
        {
           Class.forName("com.mysql.jdbc.Driver");
           cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/quizz","root","ramakant");
           pst1=cn.prepareStatement("select * from user where uid=?");
		   pst2=cn.prepareStatement("insert into user values(?,?,?,?,?,?,?,?)");
           st1=cn.createStatement();
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
	     ResultSet rs;
		 int lid=Integer.parseInt(request.getParameter("loid"));
		 String str="";
		 try 
		 {
		   pst1.setInt(1,lid);
		   rs=pst1.executeQuery();
		   
		   if(rs.next())
		   {
		    str=str+" <html><head><title>personal details</title></head>"+
		           "<body bgcolor=\"#cccccc\" ><center>"+
		           "<font face=\"Old English Text MT\" size=\"5\">"+
				   "<html><head><link href=\"css/style.css\" rel=\"stylesheet\" /><title>welcome</title>"+
			       "</head><body><form action=\"studentlogin\">"+
			       "<div class=\"outerlogindetail\">Personal Details"+
			       "<br><font color=\"red\" size=\"4px\">ID already in use"+
		 
			  
			        "<div class=\"logindetailcolum\"><div class=\"columinside\">Login ID</div>"+
			   "<div class=\"columinside\"> <input type=\"text\" value=\"\" name=\"loid\" size=\"30\"></div></div>"+
			   
              			   
			   "<div class=\"logindetailcolum\"><div class=\"columinside\">Password</div>"+
			   "<div class=\"columinside\"><input type=\"password\" value=\"\" name=\"pwd\" size=\"30\"></div></div>"+
			   
			   "<div class=\"logindetailcolum\"><div class=\"columinside\">First Name</div>"+
			   "<div class=\"columinside\"><input type=\"text\" value=\"\" name=\"fname\" size=\"30\"></div></div>"+
			   
			   "<div class=\"logindetailcolum\"><div class=\"columinside\">Last Name</div>"+
			   "<div class=\"columinside\"><input type=\"text\" value=\"\" name=\"lname\" size=\"30\"></div></div>"+
			   
			   "<div class=\"logindetailcolum\"><div class=\"columinside\">Email </div>"+
			   "<div class=\"columinside\"><input type=\"text\" value=\"\" name=\"email\" size=\"30\"></div></div>"+
			   
			   "<div class=\"logindetailcolum\"><div class=\"columinside\">Contact no </div>"+
			   "<div class=\"columinside\"><input type=\"text\" value=\"\" name=\"phon\" size=\"30\"></div></div>"+
			   
			   "<div class=\"logindetailcolum\"><div class=\"columinside\">Branch </div>"+
			   "<div class=\"columinside\"><input type=\"text\" value=\"\" name=\"brch\" size=\"30\"></div></div>"+
			   
			   "<div class=\"logindetailcolum\"><input type=\"submit\" value=\"SignUp\" name=\"signup\" size=\"30\" class=\"signup2\"></div>"+
			   "</div></form></body></html>";
   
			     
	    	 }
		     else
		     {
			   String fname,lname,email,phn,passwd;

			   fname=request.getParameter("fname");
			   lname=request.getParameter("lname");
			   passwd=request.getParameter("pwd");
			   phn=request.getParameter("phon");
			   email=request.getParameter("email");

			   
			   pst2.setInt(1,lid);
			   pst2.setString(2, fname);
			   pst2.setString(3, lname);
			   pst2.setString(4,email);
			   pst2.setString(5,phn);
			   pst2.setString(6,"active");
			   pst2.setString(8,passwd);
			   pst2.setDate(7,new Date(new java.util.Date().getTime()));
			   
			   pst2.executeUpdate();
			   
			   
			   
			      
			        str=str+"<html><head><link href=\"css/style.css\" rel=\"stylesheet\" />"+
			            "<title>welcome</title></head><body>"+
			            "<font face=\"Old English Text MT\" size=\"5\">"+
			            "<div class=\"outeruser1\">welcome"+
			            "<div class=\"topuser1\"> <div class=\"topusercontent\"></div>"+
			            "<div class=\"topusercontent1\">Candidate Registered!!!!! </div>"+
			            
			            "<div class=\"miduser1\"> <div class=\"midusercontent1\">"+
			            " <a href=\"loginpage.html\"><font color=\"white\">Go to ExamHall</font></a></div>"+
			            
			            "</div>"+
			            
			            "</div></body></html>";
			   
			   
			   
			   
			   
		     }
		   
		   
		   
		   
		   
		 } catch (SQLException e) 
		   {
			System.err.print(e.getMessage());
	       }
		 
		 PrintWriter pw=response.getWriter();
		 pw.println(str);
		
	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		
	}

}
