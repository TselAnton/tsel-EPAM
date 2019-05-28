package model.impl;

import model.Model;

import java.sql.Date;

public class Order implements Model {

    private int id;
    private int userId;
    private Date date;
    private int statusId;

    public Order() {
    }

    public Order(int id, int userId, Date date, int statusId) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.statusId = statusId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (userId != order.userId) return false;
        if (statusId != order.statusId) return false;
        return date.equals(order.date);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + date.hashCode();
        result = 31 * result + statusId;
        return result;
    }
}
