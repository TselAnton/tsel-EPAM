package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculatorService {

    private final Logger logger;

    public CalculatorService() {
        logger = LoggerFactory.getLogger(CalculatorService.class.getName());
    }

    public Double getResult(double a, double b, int op) {
        Double result = null;

        switch (op) {
            case 1:
                result = getSum(a, b);
                break;
            case 2:
                result = getSubtract(a, b);
                break;
            case 3:
                result = getProduct(a, b);
                break;
            case 4:
                result = getQuot(a, b);
                break;
        }

        return result;
    }

    private Double getSum(double a, double b) {
        return a + b;
    }

    private Double getSubtract(double a, double b) {
        return a - b;
    }

    private Double getProduct(double a, double b) {
        return a * b;
    }

    private Double getQuot(double a, double b) {
        double result = a / b;
        return result == Double.POSITIVE_INFINITY ? null : result;
    }
}
