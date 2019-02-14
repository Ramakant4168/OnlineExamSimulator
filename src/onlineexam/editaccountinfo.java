package onlineexam;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/editaccountinfo")
public class editaccountinfo extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
   
    public editaccountinfo() 
    {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession ses=request.getSession(false);
		if(ses==null)
			{response.sendRedirect("loginpage.html");
			 return;
			}
		String str="";
		
		int s1=Integer.parseInt(request.getParameter("id1"));
		String s2=request.getParameter("id2");
		String s3=request.getParameter("id3");
		String s4=request.getParameter("id4");
		

	   str=str+"<html><head><link href=\"css/style.css\" rel=\"stylesheet\" />"+
		       
			       "</head>"+
			       "<body><form action=\"editaccountupdate\">"+
              
		           "<div class=\"outeruser2\"></font color=\"#036329\" size=\"20\">"+
			   
			           "<div class=\"topper\">"+
			                "<div class=\"logo\">"+
			                       "<font face=\"Old English Text MT\" size=\"5\">"+
			                        "<#OnlineExam@></font>"+
			                 "</div>"+
			             "</div>"+
		       
		       "<div class=\"outerlogindetail\">"+
		       
		                  "<div class=\"logindetailcolum\">"+
		       
		                     "<div class=\"columinside\">Login ID</div>"+
		      
		                      "<div class=\"columinside\"> <input type=\"text\" value="+s1+" name=\"loid\" size=\"30\" readonly></div>"+
		                  "</div>"+
		   
       			   
		                  "<div class=\"logindetailcolum\"><div class=\"columinside\"> old Password</div>"+
		  
		                  "<div class=\"columinside\"><input type=\"password\" value=\"\" name=\"pwd1\" size=\"30\"></div></div>"+
		   
		   
		  
		                  "<div class=\"logindetailcolum\"><div class=\"columinside\">New Password</div>"+
		                  "<div class=\"columinside\"><input type=\"password\" value=\"\" name=\"pwd\" size=\"30\"></div></div>"+
		   
		                  "<div class=\"logindetailcolum\"><div class=\"columinside\">First Name</div>"+
		                  "<div class=\"columinside\"><input type=\"text\" value="+s2+" name=\"fname\" size=\"30\"></div></div>"+
		   
		                  "<div class=\"logindetailcolum\"><div class=\"columinside\">Last Name</div>"+
		                  "<div class=\"columinside\"><input type=\"text\" value="+s3+" name=\"lname\" size=\"30\"></div></div>"+
		   
		                  "<div class=\"logindetailcolum\"><div class=\"columinside\">Email </div>"+
		                  "<div class=\"columinside\"><input type=\"text\" value="+s4+" name=\"email\" size=\"30\"></div></div>"+
		   
		                  "<div class=\"logindetailcolum\"><div class=\"columinside\">Contact no </div>"+
		                  "<div class=\"columinside\"><input type=\"text\" value=\"\" name=\"phon\" size=\"30\"></div></div>"+
		   
		                  "<div class=\"logindetailcolum\"><div class=\"columinside\">Branch </div>"+
		                  "<div class=\"columinside\"><input type=\"text\" value=\"\" name=\"brch\" size=\"30\"></div></div>"+
		   
		                  "<div class=\"logindetailcolum\"><input type=\"submit\" value=\"Edit Account\" name=\"\" size=\"30\" class=\"signup2\"></div>"+
		                 
		            "</div></form>"+
		            
		         "<div class=\"footer\"> All Rights@Ramakant Vaishnav</div></div>"+
		    "</body></html>";

		
	    PrintWriter pw=response.getWriter();
		pw.println(str);
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

}
