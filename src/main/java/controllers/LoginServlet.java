package controllers;

import config.Database;
import helper.HashUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/login.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        password = HashUtil.hashPassword(password); // Mã hóa mật khẩu
        boolean rememberMe = "on".equals(request.getParameter("rememberMe"));

        try (Connection conn = Database.getConnection()) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);

                if (rememberMe) {
                    Cookie userCookie = new Cookie("username", username);
                    userCookie.setMaxAge(60 * 60 * 24 * 7);
                    response.addCookie(userCookie);
                }

                response.sendRedirect("dashboard");
            } else {
                request.setAttribute("errorMessage", "Invalid credentials!");
                request.getRequestDispatcher("view/login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred. Please try again.");
            request.getRequestDispatcher("view/login.jsp").forward(request, response);
        }
    }
}
