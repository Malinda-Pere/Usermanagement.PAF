package model;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/userAPI")
public class userAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	User userObj = new User();
    
    public userAPI() {
       super();
    }

	//insert
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String output = userObj.insertUser(request.getParameter("id"),
											request.getParameter("name"), 
											request.getParameter("address"), 
											request.getParameter("phone"),
											request.getParameter("nic"), 
											request.getParameter("mail"),
											request.getParameter("powerarea"));
		response.getWriter().write(output);
	}

	
	// Convert request parameters to a Map
	private static Map<String, String> getParasMap(HttpServletRequest request)
	{
	Map<String, String> map = new HashMap<String, String>();
	try
	{
	Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
	String queryString = scanner.hasNext() ?
	scanner.useDelimiter("\\A").next() : "";
	scanner.close();
	String[] params = queryString.split("&");
	for (String param : params)
	{
		String[] p = param.split("=");
		map.put(p[0], p[1]);
		}
		}
		catch (Exception e)
		{
		}
		return map;
	}
	
	//update
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		String output =userObj.updateUser(paras.get("hidItemIDSave").toString(),
		paras.get("id").toString(),
		paras.get("name").toString(),
		paras.get("address").toString(),
		paras.get("phone").toString(),
		paras.get("nic").toString(),
		paras.get("mail").toString(),
		paras.get("powerarea").toString());
		response.getWriter().write(output);
	}

	//delete
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> paras = getParasMap(request);
		String output = userObj.deleteUser(paras.get("code").toString());
		response.getWriter().write(output);
	}

}
