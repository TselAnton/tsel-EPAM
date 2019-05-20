package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;
import service.CalculatorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AppController extends HttpServlet {

    private CalculatorService calculator = new CalculatorService();

    private final String ERR_MESSAGE = "Divide by zero is prohibited!";
    private final String DEFOLT_MESSAGE = "One or more fields are empty!";
    private final Logger logger = LoggerFactory.getLogger(AppController.class.getName());

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        double num1 = 0, num2 = 0;
        int operation = 1;
        String resultMessage = null;

        try {
            num1 = Float.parseFloat(request.getParameter("num1"));
            num2 = Float.parseFloat(request.getParameter("num2"));
            operation = Integer.parseInt(request.getParameter("operation"));
            logger.debug("Num1 = " + num1 + ", Num2 = " + num2 + ", OperationValue = " + operation);
        } catch (NumberFormatException e) {
            logger.error(MarkerFactory.getMarker("Exception"), "Some parameters are null", e);
            resultMessage = DEFOLT_MESSAGE;
        }

        if (resultMessage == null) {
            Double result = calculator.getResult(num1, num2, operation);
            if (result == null) {
                resultMessage = ERR_MESSAGE;
            } else {
                resultMessage = String.format("%2.2f", result);
            }
        }

        request.setAttribute("result", resultMessage);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
