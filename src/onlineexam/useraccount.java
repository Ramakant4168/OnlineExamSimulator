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


@WebServlet("/useraccount")
public class useraccount extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	PreparedStatement pst1,pst2;
	Statement st1;
    Connection cn;
   
    public useraccount() 
    {
        super();
        try
        {
           Class.forName("com.mysql.jdbc.Driver");
           cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/quizz","root","ramakant");
           pst1=cn.prepareStatement("select * from user where uid=? and Password=?");
		   
           
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
	   if(request.getParameter("logout")!=null)
	   {
		   HttpSession ses=request.getSession(false);
		   if(ses!=null)
			   ses.invalidate();
		   response.sendRedirect("loginpage.html");
		   return;
	   }
	  
	     ResultSet rs;
	     String str="";
	  
	     int lid=Integer.parseInt(request.getParameter("uid"));
		 String pswd=request.getParameter("pwd");
		 
		 try 
		 {
		   pst1.setInt(1,lid);
		   pst1.setString(2,pswd);
		   
		   rs=pst1.executeQuery();
		   
		   if(rs.next())
		   {
			   HttpSession ses=request.getSession(true);
			  ses.setAttribute("id", lid);
			  String fname=rs.getString("fname") ;
			  String lname=rs.getString("lname") ;
			  String email=rs.getString("email") ;
			  
			         str=str+"<html><head><link href=\"css/style.css\" rel=\"stylesheet\" />"+
			                   "<title>welcome</title></head><body>"+
			        		 
			            
	         "<div class=\"outeruser2\"></font color=\"#000000\" size=\"20\">"+
			                 
			                    "<div class=\"topper\">"+
			                        "<div class=\"logo\">"+
			                            "<font face=\"Old English Text MT\" size=\"5\">"+
			                            "<#OnlineExam@>"+
			                       "</font></div>"+
			                    "</div>"+
			              
			           "<div class=\"topuser2\"> "+
			                    
			                    "<div class=\"topusercontent2\"></div>"+
			            
			                    "<div class=\"topusercontent2\">Name-"+fname+" "+lname+"</div>"+
			            
			                    "<div class=\"topusercontent2\">UserId-"+lid+"</div><div class=\"topusercontent2\"> Email-"+email+"</div>"+
			           "</div>"+       
			            
			                    
			                       "<div class=\"miduser2\">"+
			            
			                             "<div class=\"midusercontent2\">"+
			            
			                               "<a href=\"editaccountinfo?id1="+lid+"&id2="+fname+"&id3="+lname+"&id4="+email+"\">"+
			            
			                              "<font color=\"black\" size=\"5\">Edit info</font> </a></div>"+
			            
			                               "<form action=\"Questionpaper\">"+
			                              
                                          "<input type=\"hidden\" name=\"userid\" value=\""+lid+"\">"+
			                               
			                             "<div class=\"midusercontent2\"><input type=\"submit\" value=\"Take Exam\" name=\"\" size=\"30\"class=\"sout\" ><font color=\"black\"></font> </a></div>"+
			           
                                          "</form>"+
                        
                                        "<form action=\"Resultpage\">"+
                                        
                                        "<input type=\"hidden\" name=\"userid\" value=\""+lid+"\">"+
			            
                                        "<div class=\"midusercontent2\"><input type=\"submit\" value=\" Result\" name=\"\" size=\"30\"class=\"sout\"><font color=\"black\"></font> </div>"+
			            
                                        "</form>"+
			            
			                            "<form action=\"useraccount\">"+
			             
			                            "<div class=\"midusercontent2\"><input type=\"submit\" value=\" Logout\" name=\"logout\" size=\"30\"class=\"sout\"></div>"+
			            
			                            "</form>"+
			            
			                    "</div>"+
			                    
			            
			     "</div><div class=\"footer\"> All Rights@Ramakant Vaishnav</div></body></html>";
			   
			   
		   
		   }
		   
		   else
		   {
			   
			   
			  str=str+"<html>"+

			   "<head>"+
			       "<link href=\"css/style.css\" rel=\"stylesheet\" />"+
			       "<title>Sign In</title>"+
			   "</head>"+

			   "<body>"+
			   "<div class=\"topper\">"+
			   "<div class=\"logo\">"+
			   "<font face=\"Old English Text MT\" size=\"5\">"+
			   "<#OnlineExam@>"+
			   "</div>"+
			   "<div class=\"admin\">"+
			   "<input type=\"submit\" value=\"Admin Login\" name=\"\" size=\"30\"  class=\"adminlogin\">"+
			   "</div>"+
			  " </div>"+
			   
			   
			   
			   "<font face=\"Old English Text MT\" size=\"5\">"+

			   "<div class=\"outer\">"+
			        
			        "<div class=\"left\">"+
			   	 "</div>"+
			   	
			   	" <div class=\"mid\">"+
			   	 
			   	 "<font size=\"+3\" color=\"#660099\" >Welcome!!!</font>"+
			   	 "<br><br>Enter your Exam Hall<br>"+
			   	 "<font color=\"red\">Invalid Identity</font><br>"+
			   	 "<form action=\"useraccount\">"+
			   	 
			   	 "<div class=\"name\">"+
			   	    "<input type=\"text\" name=\"uid\" value=\"userId\" size=\"26\" height=\"50\">"+
			   	" </div>"+
			   	 
			   	" <div class=\"name\">"+
			   	    "<input type=\"password\" name=\"pwd\" value=\"password\" size=\"26\">"+
			   	 "</div>"+
			   	 
			   	 
			   	 
			   	" <div>"+
			   	     "<input type=\"submit\" name=\"ok\" value=\"Enter\" class=\"sign\">"+
			   	" </div>"+
			   	
			   	"  <br>"+
			   	"</form>"+
			   	
			   	" <font color=\"#000000\"> i can't enter my Exam Hall</font>"+
			   	" <br>"+
			   	 "<a href=\"help.html\" >help</a>"+
			   	 "<br><br>"+
			   	 
			   	 "<div class=\"create\">"+
			   	  " <a href=\"detaillogin.html\">Get Admission Access</a>"+
			   	" </div>"+
			   	" <br><br>"+
			   
			   	 

			   "</div>"+
			   "</div>"+ 
			   "<div class=\"footer\"> All Rights@Ramakant Vaishnav</div>"+	
			   "</body>"+
			   "</html>"; 
			   
			   
			   
			   
		   }
		
		 }
		 
		 catch (SQLException e) 
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
