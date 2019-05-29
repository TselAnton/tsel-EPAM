package model.impl;


import model.Model;

import javax.persistence.*;

@Entity
@Table(name = "order_status")
public class OrderStatus implements Model {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name")
    private String name;

    public OrderStatus() {
    }

    public OrderStatus(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderStatus status = (OrderStatus) o;

        if (id != status.id) return false;
        return name.equals(status.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }
}
