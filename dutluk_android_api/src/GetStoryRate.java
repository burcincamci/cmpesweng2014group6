

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class GetStoryRate
 */
@WebServlet("/GetStoryRate")
public class GetStoryRate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetStoryRate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int storyId = Integer.parseInt(request.getParameter("storyId"));
		DatabaseService db = new DatabaseService();
		String sql = "SELECT ROUND(AVG(Rate),2) FROM Rate WHERE StoryID='"+storyId+"';";
		ResultSet rs = null;
		Connection con;
		RegisterResult registerResult  = new RegisterResult();
		try {
			con = db.getConnection();
			Statement statement = con.createStatement();
			rs =statement.executeQuery(sql);
			double averageRate = 0.0;
			if(rs.next())
				averageRate = rs.getDouble(1);
			response.reset();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			registerResult.setMessage("" + averageRate);
			registerResult.setResult(true);
			Gson gson = new Gson();
			PrintWriter pw = response.getWriter();
			pw.print(gson.toJson(registerResult));
			pw.flush();
			pw.close();
			statement.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			response.reset();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			registerResult.setMessage("error occured");
			registerResult.setResult(false);
			Gson gson = new Gson();
			PrintWriter pw = response.getWriter();
			pw.print(gson.toJson(registerResult));
			pw.flush();
			pw.close();
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}