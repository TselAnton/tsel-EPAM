package model.impl;

import model.Model;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product implements Model {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "brand")
    private String brand;

    @Column(name = "price")
    private int price;

    @Column(name = "qty")
    private int qty;

    @Column(name = "discount")
    private int discount;

    @Column(name = "description")
    private String description;

    public Product() {
    }

    public Product(int id, String name, int categoryId, String brand, int price, int qty, int discount, String description) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.brand = brand;
        this.price = price;
        this.qty = qty;
        this.discount = discount;
        this.description = description;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (categoryId != product.categoryId) return false;
        if (price != product.price) return false;
        if (qty != product.qty) return false;
        if (discount != product.discount) return false;
        if (!name.equals(product.name)) return false;
        if (!brand.equals(product.brand)) return false;
        return description.equals(product.description);
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
}
