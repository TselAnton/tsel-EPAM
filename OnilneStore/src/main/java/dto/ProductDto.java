package dto;

import entity.Category;

import java.util.HashMap;

public class ProductDto {

    private int id;
    private String name;
    private int categoryId;
    private String brand;
    private int price;
    private int qty;
    private int discount;
    private String description;
    private int count;

    public ProductDto() {
        count = 1;
    }

    public ProductDto(int id, String name, int categoryId, String brand, int price, int qty, int discount, String description) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.brand = brand;
        this.price = price;
        this.qty = qty;
        this.discount = discount;
        this.description = description;
        count = 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void sumCount(int count) {
        this.count += count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductDto that = (ProductDto) o;

        if (id != that.id) return false;
        if (categoryId != that.categoryId) return false;
        if (price != that.price) return false;
        if (qty != that.qty) return false;
        if (discount != that.discount) return false;
        if (!name.equals(that.name)) return false;
        if (!brand.equals(that.brand)) return false;
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + categoryId;
        result = 31 * result + brand.hashCode();
        result = 31 * result + price;
        result = 31 * result + qty;
        result = 31 * result + discount;
        result = 31 * result + description.hashCode();
        return result;
    }

    public String shortToString() {
        return String.format("%-20s | Количество: %-14s | Цена: %-8.2f руб  | Цена с учётом скидки: %-8.2f руб", name,
                qty == 0 ? "Нет в наличии" : String.valueOf(qty), (float)price / 100, (float)(price - discount) / 100);
    }

    public String toString(HashMap<Integer, Category> category) {
        return "Название: " + name +
                "\nКатегория: " + category.get(categoryId).getName() +
                "\nБренд: " + brand +
                "\nКоличество: " + (qty == 0 ? "Нет в наличии" : qty) +
                "\nЦена: " + String.format("%.2f руб", (price / 100.0)) +
                "\nСкидка: " + String.format("%d", (int)(((float)discount / (float)price) * 100)) + "%" +
                "\nЦена с учётом скидки: " + String.format("%.2f руб", ((price - discount) / 100.0)) +
                "\nОписание: " + description;
    }

    public String toStringShopCart() {
        return String.format("%-20s%10s%8.2f руб.    x %d шт.",
                name, "Цена: ", (float)((price - discount) / 100 * count),  count);
    }
}

