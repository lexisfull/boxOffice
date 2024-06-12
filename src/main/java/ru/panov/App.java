package ru.panov;


import ru.panov.service.Calculation;
import ru.panov.service.impl.CalculationImpl;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        Calculation receipt = new CalculationImpl();
        receipt.getReceipt();
    }
}
