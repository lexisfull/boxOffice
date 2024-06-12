package ru.panov.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.text.DecimalFormat;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Product {

    String name;
    Double price;
    boolean discount;

    private Product(Builder builder){
        this.name = builder.name;
        this.price = builder.price;
        this.discount = builder.discount;
    }

    public static class Builder{
        String name;
        Double price;
        boolean discount;


        public Builder(String name){
            this.name = name;
        }

        public Builder setPrice(Double price) {
            this.price = price;
            return this;
        }

        public Builder setDiscount(boolean discount) {
            this.discount = discount;
            return this;
        }

        public Product build(){
            return new Product(this);
        }
    }

    @Override
    public String toString() {
        return  "name =' " + name + '\'' +
                ", price = " + new DecimalFormat("#0.00").format(price) +
                ", discount = " + discount;
    }
}
