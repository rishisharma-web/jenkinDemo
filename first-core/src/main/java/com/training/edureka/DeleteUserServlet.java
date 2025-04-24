package com.training.edureka;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteUserServlet
 */
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		ServletContext context=getServletContext();
		ServletConfig config=getServletConfig();
		
		String dbUrl=context.getInitParameter("dburl");
		String dbUser=context.getInitParameter("dbuser");
		String dbPwd=context.getInitParameter("dbpwd");
		System.out.println(dbUrl+" "+dbUser+" "+dbPwd);
		
		try {
		     Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(dbUrl, dbUser, dbPwd);
			PreparedStatement ps=con.prepareStatement("DELETE FROM appuser WHERE username=? AND password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			int count=ps.executeUpdate();
			if(count>=1)
			{
				out.println("User Deleted Successfully!");
			}
			else
			{
				out.println("User with such credentials does not exist");
			}
			
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			out.println("Something went wrong during deletion");
			e.printStackTrace();
		}
	}

}
