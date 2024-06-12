package ru.panov.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Receipt {

    String name;
    LocalDate date;
    LocalTime time;

    private Receipt(Builder builder) {
        this.name = builder.name;
        this.date = builder.date;
        this.time = builder.time;
    }

    public static class Builder {
        private String name;
        private LocalDate date;
        private LocalTime time;

        public Builder(String name) {
            this.name = name;
        }

        public Builder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder time(LocalTime time) {
            this.time = time;
            return this;
        }
        public Receipt build() {
            return new Receipt(this);
        }
    }

}
