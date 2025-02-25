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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserD user = new UserD();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        UserDao userDAO = new UserDao();
        if (userDAO.registerUser(user)) {
            response.sendRedirect("Login.html"); // Redirect to login page
        } else {
            response.sendRedirect("Register.html"); // Redirect back to registration page
        }
    }
}