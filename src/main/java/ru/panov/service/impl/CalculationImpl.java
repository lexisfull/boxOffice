package ru.panov.service.impl;

import ru.panov.model.Discount;
import ru.panov.model.Product;
import ru.panov.model.Receipt;
import ru.panov.service.Calculation;
import ru.panov.service.WareHouse;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class CalculationImpl implements Calculation {

    private final Receipt receipt = new Receipt.Builder("CASH RECEIPT")
            .date(LocalDate.now())
            .time(LocalTime.now())
            .build();

    private final Discount disc = new Discount();

    private final Scanner scanner = new Scanner(System.in);

    private final WareHouse wareHouse = new WarehouseImpl();

    public void getReceipt(){
        List<Product> products = wareHouse.addProducts();
        double sum = products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
        for (Product product : products) {
            System.out.println(product);
        }

        disc.setNumber(ThreadLocalRandom.current().nextInt(100, 999));
        int discCard = disc.getNumber();

        System.out.println("Номер вашей карты: " + discCard);
        double res = discount(products.size(), discCard);
        System.out.println();
        System.out.printf("    %10s %n", receipt.getName());
        System.out.printf(" %tT", receipt.getTime());
        System.out.printf("   %1$td.%1$tm.%1$ty %n", receipt.getDate());

        List<Product> products2 = wareHouse.getResult(products);
        for (Product product : products2) {
            System.out.printf("%n %-8s %10.2f", product.getName(), product.getPrice());
        }

        System.out.printf("%n %-8s", "Без скидки");
        System.out.printf("%9s %n", new DecimalFormat("#0.00").format(sum));
        sum *= res;
        System.out.printf("%n %-10s", "Со скидкой");
        System.out.printf("%9s %n", new DecimalFormat("#0.00").format(sum));
        double nds = sum * 0.2;
        System.out.printf("%-9s %10.2f", " НДС", nds);
    }

    public double discount(int n, int m) {
        System.out.print("Введите номер карты: ");
        int discount = scanner.nextInt();
        if(discount != m) {
            System.out.println("Скидки нет!");
            return  1.0;
        } else if (n <= 5) {
            return  0.9;
        }else if (n <= 10) {
            return  0.75;
        } else {
            return  0.5;
        }
    }
}
