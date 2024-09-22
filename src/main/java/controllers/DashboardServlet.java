package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        boolean hasUsernameCookie = false;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    hasUsernameCookie = true;
                    break;
                }
            }
        }

        if (hasUsernameCookie) {
            req.getRequestDispatcher("view/dashboard.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("view/error-page.jsp").forward(req, resp);
        }
    }

}
