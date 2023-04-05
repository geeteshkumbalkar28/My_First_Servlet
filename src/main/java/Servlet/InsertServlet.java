package Servlet;

import Utility.RegexUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Servlet.InsertServlet")
public class InsertServlet extends HttpServlet {
    public static String userName;
    public static String passWord;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        RegexUtil regexUtil = new RegexUtil();
        String username = request.getParameter("user");
        String password = request.getParameter("pass");

        if (regexUtil.isNameValid(username)) {


            if (regexUtil.isPasswordValid(password)) {


                InsertServlet.userName = username;
                InsertServlet.passWord = password;


                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                response.sendRedirect("Success.jsp");
            } else {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Insert.html");
                PrintWriter out = response.getWriter();
                out.println("<font color=red>You want to add This Password that pattern is incorrect(EX : Geetesh@11)</font>");
                requestDispatcher.include(request, response);
            }
        } else {
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Insert.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>  Name starts with Cap and has minimum 3 characters (EX : Geet)</font>");
            requestDispatcher.include(request, response);
        }
    }
}