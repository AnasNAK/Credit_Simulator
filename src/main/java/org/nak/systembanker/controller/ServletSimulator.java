package org.nak.systembanker.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.nak.systembanker.entities.Request;
import org.nak.systembanker.services.implementations.RequestService;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(value = "/Simulator")
public class ServletSimulator extends HttpServlet {

    private RequestService requestService;

    @Override
    public void init() throws ServletException {
        requestService = new RequestService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Request newRequest = new Request();
        try {
            newRequest.setAmount(Double.parseDouble(request.getParameter("amount")));
            newRequest.setDuration(Integer.parseInt(request.getParameter("duration")));
            newRequest.setMonthly(Double.parseDouble(request.getParameter("monthly")));
            newRequest.setProject(request.getParameter("project"));
            newRequest.setProfession(request.getParameter("profession"));
            newRequest.setEmail(request.getParameter("email"));
            newRequest.setPhone(request.getParameter("phone"));
            newRequest.setGender(request.getParameter("civilite"));
            newRequest.setLastName(request.getParameter("nom"));
            newRequest.setFirstName(request.getParameter("prenom"));
            newRequest.setIdCardNumber(request.getParameter("CIN"));
            newRequest.setDateOfBirth(LocalDate.parse(request.getParameter("datenaissance"), DateTimeFormatter.ofPattern("yyyy/MM/dd")));
            newRequest.setHireDate(LocalDate.parse(request.getParameter("datedembauche"), DateTimeFormatter.ofPattern("yyyy/MM/dd")));
            newRequest.setTotalRevenue(Integer.parseInt(request.getParameter("totalrevenue")));
            newRequest.setCredits(request.getParameter("credits"));
            newRequest.setMortgageCredit(request.getParameter("creditImmo"));
            newRequest.setOtherCredits(request.getParameter("otherCredits"));

            Request savedRequest = requestService.createRequest(newRequest);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();

            if (savedRequest != null) {
                out.write("{\"status\":\"success\", \"message\":\"Request created successfully!\"}");
            } else {
                out.write("{\"status\":\"error\", \"message\":\"Error creating request. Please try again.\"}");
            }
            out.flush();

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            PrintWriter out = response.getWriter();
            out.write("{\"status\":\"error\", \"message\":\"Invalid input data. Please check your entries.\"}");
            out.flush();
        }
    }
}
