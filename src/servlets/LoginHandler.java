package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import classes.User;
import utilities.DatabaseAccess;

/**
 * Servlet implementation class Login
 */
@WebServlet(name="Login", urlPatterns= {"/Login"})
public class LoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginHandler() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * Login:
	 * Takes Username, Password, and Remember Me flag. User credentials are checked against the
	 * user table in the database. If the credentials are good, a user object is populated 
	 * with the user details and then stored in the session. If the Remember Me flag was checked 
	 * then a 32 byte base-64 token is passed to the user's row in the db and a cookie is created
	 * to store that token. On credential failure, user is redirect to the login again with an 
	 * error message.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		String redirectURL = "";
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Boolean rememberMe = request.getParameter("remember") != null;
		
		
	    if (DatabaseAccess.userCredentialCheck(username, password))
	    {	    	
			redirectURL = "home.jsp";
			
			if (rememberMe == false) {
				User user = DatabaseAccess.getUser(username, password);
				user.createToken();
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
			}
			else {
				User user = DatabaseAccess.getUser(username, password);
				user.createToken();
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				DatabaseAccess.updateAuthToken(user);
				Cookie c = new Cookie("userToken", user.getToken());
				c.setMaxAge(60*60*24*30);
				response.addCookie(c);
			}			
		}
		 	else { redirectURL = "login.jsp"; 
		 	request.getSession().setAttribute("error", "Username/Password Invalid");
		}
   
		 response.sendRedirect(redirectURL);

	}

}
