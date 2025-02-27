package com.example.servlets;

import com.example.dao.UserDao;
import com.example.dto.UserD;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserD user = new UserD();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        UserDao userDAO = new UserDao();
        if (userDAO.registerUser(user)) {
            // Registration successful, redirect to login page
            response.sendRedirect("Login.html");
        } else {
            // Registration failed, redirect back to registration page with an error message
            request.setAttribute("error", "Registration failed. Email may already exist.");
            request.getRequestDispatcher("Register.html").forward(request, response);
        }
    }
}