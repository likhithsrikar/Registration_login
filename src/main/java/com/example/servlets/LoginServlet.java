package com.example.servlets;

import com.example.dao.UserDao;
import com.example.dto.UserD;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDao userDAO = new UserDao();
        UserD user = userDAO.loginUser(email, password);

        if (user != null) {
            // Login successful, create a session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("home"); // Redirect to home page
        } else {
            // Login failed, redirect back to login page with an error message
            request.setAttribute("error", "Invalid email or password");
            request.getRequestDispatcher("Login.html").forward(request, response);
        }
    }
}