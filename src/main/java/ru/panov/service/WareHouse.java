package ru.panov.service;

import ru.panov.model.Product;

import java.util.List;

public interface WareHouse {
    List<Product> addProducts();
    List<Product> getResult(List<Product> products);
}
