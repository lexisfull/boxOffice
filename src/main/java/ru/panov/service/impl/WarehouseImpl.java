package ru.panov.service.impl;

import ru.panov.model.Product;
import ru.panov.service.WareHouse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class WarehouseImpl implements WareHouse {

    private List<Product> products;

    @Override
    public List<Product> addProducts() {
        products = new ArrayList<>();
        for (int i = 0; i < ThreadLocalRandom.current().nextInt(5,35); i++) {
            products.add(new Product.Builder("Товар-" + (i + 1))
                            .setPrice(ThreadLocalRandom.current().nextDouble(100, 1000))
                            .setDiscount(ThreadLocalRandom.current().nextBoolean())
                    .build());
        }
        return products;
    }

    @Override
    public List<Product> getResult(List<Product> products){

        int count = 0;

        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).isDiscount()){
                count ++;
            }
        }

        if(count >= 5){
            for (Product product : products) {
                if (product.isDiscount()) {
                    product.setPrice(product.getPrice() * 0.9);
                }
            }
        }
        return products;
    }
}
