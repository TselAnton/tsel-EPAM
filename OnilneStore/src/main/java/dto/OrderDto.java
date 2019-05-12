package dto;

import entity.OrderStatus;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class OrderDto {
    private int id;
    private int userId;
    private Date date;
    private int statusId;

    public OrderDto() {
    }

    public OrderDto(int id, int userId, Date date, int statusId) {
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

        OrderDto orderDto = (OrderDto) o;

        if (id != orderDto.id) return false;
        if (userId != orderDto.userId) return false;
        if (statusId != orderDto.statusId) return false;
        return date.equals(orderDto.date);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + date.hashCode();
        result = 31 * result + statusId;
        return result;
    }

    public String toString(HashMap<Integer, OrderStatus> orderStatus) {
        return "Дата оформления: " + new SimpleDateFormat("dd.MM.yyyy HH:mm").format(date) +
                "\nСтатус заказа: " + orderStatus.get(statusId).getName();
    }
}
