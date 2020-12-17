package ru.job4j.design.lsp.foods;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Keeps food's info.
 */
public class Food {
    private String name;
    private LocalDate expiredDate;
    private LocalDate createDate;
    private int price;
    private int discount;
    private boolean activateDiscount;

    public Food(String name, LocalDate createDate, LocalDate expiredDate,
                int price, int discount) {
        this.name = name;
        this.expiredDate = expiredDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
        this.activateDiscount = false;
    }

    public String getName() {
        return name;
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public boolean isActivateDiscount() {
        return activateDiscount;
    }

    public void setActivateDiscount(boolean activateDiscount) {
        this.activateDiscount = activateDiscount;
    }

    public int getTotalPrice() {
        return activateDiscount ? price * (100 - discount) / 100 : price;
    }

    @Override
    public String toString() {
        return "Food{"
                + "name='" + name + '\''
                + ", createDate=" + createDate
                + ", expiredDate=" + expiredDate
                + ", price=" + price
                + ", discount=" + discount
                + ", totalPrice=" + getTotalPrice()
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return price == food.price
                && discount == food.discount
                && Objects.equals(name, food.name)
                && Objects.equals(expiredDate, food.expiredDate)
                && Objects.equals(createDate, food.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, expiredDate, createDate, price, discount);
    }
}
