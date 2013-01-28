package charityAdminServices;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateTableServlet
 */
@WebServlet("/CreateTableServlet")
public class CreateTableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String ARGS_ARGC = "argc";
	private final String ARGS_NAME = "name_x";
	private final String ARGS_TYPE = "type_x";
	private final String ARGS_REQ = "req_x";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateTableServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int argc = Integer.parseInt(request.getParameter(ARGS_ARGC));
		PrintWriter out = response.getWriter();
		for(int i = 0; i < argc; i++)
		{
			String argName = ARGS_NAME.replace("x", Integer.toString(i));
			String argType = ARGS_TYPE.replace("x", Integer.toString(i));
			String argReq = ARGS_TYPE.replace("x",Integer.toString(i));
			String name = request.getParameter(argName);
			String type = request.getParameter(argType);
			String req = request.getParameter(argReq);
			out.print(name+":"+type);
			if(req!=null)
				out.print(" is required");
			out.println();
			
		}
	}

}
