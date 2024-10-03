package org.nak.systembanker.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/simulator")
public class ServletSimulator extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException , ServletException {

        resp.setContentType("text/html");
        req.getRequestDispatcher("/views/simulator.jsp").include(req , resp);

    }
}
