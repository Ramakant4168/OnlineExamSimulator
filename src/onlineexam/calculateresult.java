package onlineexam;

import java.io.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculateresult")
public class calculateresult extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	Statement st,st1;
    Connection cn;
    ArrayList <String>anslist=new ArrayList <String>();
   
    public calculateresult() 
    {
        super();
        try
        {
           Class.forName("com.mysql.jdbc.Driver");
           cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/quizz","root","ramakant");
           st=cn.createStatement();
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
	{   ResultSet rs;
	   String s1;
	   int marks=0;
	   
		String str="";
		 try 
		 {
		  
		   rs=st.executeQuery("select * from quiz");
		   
		   while(rs.next())
		   {    String ans;
			   ans=rs.getString("ans");
			   anslist.add(ans);
			  
		   }
		   
		   
			 str=str+"<html>"+

				   "<head>"+
				       "<link href=\"css/style.css\" rel=\"stylesheet\" />"+
				       
				   "</head>"+
				  
				   "<body>"+
				   
                   "<div class=\"outeruser2\"></font color=\"#036329\" size=\"20\"> "+
				   
				   "<div class=\"topper\">"+
				      "<div class=\"logo\">"+
				        "<font face=\"Old English Text MT\" size=\"5\">"+
				        "<#OnlineExam@>"+
				     "</div>"+
				   "</div>"
				  
				  ;
				   
		            
		 for(int j=0;j<anslist.size();j++)
		 {
			 s1="qu"+(j+1); 
			 
			 
			 
			if(anslist.get(j).equals(request.getParameter(s1)))
		     {
				 
		      	marks++; 
		     }
			
			
			
	     }

        }
       catch (SQLException e) 
         {
	       System.err.print(e.getMessage());
         }
		 
		 str=str+"<div class=\"resultdisplay\">"+"Your Score is -"+marks+"</div>";
		
		 
		 str=str+"<div class=\"resultdisplay\"> Check more on result tab in your account</div></font></div>";
		 
		 
		 str=str+ "<div class=\"footer\"> All Rights@Ramakant Vaishnav</div>"+ 
		           "</body>"+
		  
		            "</html>";
		 
		  
	    PrintWriter pw=response.getWriter();
		 
	    pw.println(str);
	  
	     int id=Integer.parseInt(request.getParameter("userid"));
	    
	    try
		   {
		    
	    	int tid=id-5+marks, dur=2;
	    	
	    	//java.util.Date dt1=new java.util.Date();
	        //Date dt1=new java.sql.Date(dt1.getTime());
		  
		    
	        String query = "INSERT INTO test (test_id,user_id,duration,marks) " + 
                    "VALUES (" + tid + ", " + id + ", " + dur + "," + marks + ")";
	        
	        st1.executeUpdate(query);
	        
	        
	        
	        
		    System.out.println("done !");
		   }
		   catch(Exception e)
		   {
			   System.err.println("Update : "+e.getMessage());		
		   }
	   
	
	
	
	
	
	
	
	
	
	
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
	}

}
