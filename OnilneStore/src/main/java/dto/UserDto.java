package dto;

import model.Role;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Objects;

public class UserDto {

    private int id;
    private String fio;
    private String login;
    private String phone;
    private String email;
    private String city;
    private String street;
    private String house;
    private String apartment;
    private Date registrationDate;
    private int roleId;
    private int qtyOrders;
    private OrderDto orderDto;

    public UserDto() {
    }

    public UserDto(int id, String fio, String login, String phone, String email, String city, String street, String house, String apartment, Date registrationDate, int roleId, int qtyOrders, OrderDto orderDto) {
        this.id = id;
        this.fio = fio;
        this.login = login;
        this.phone = phone;
        this.email = email;
        this.city = city;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
        this.registrationDate = registrationDate;
        this.roleId = roleId;
        this.qtyOrders = qtyOrders;
        this.orderDto = orderDto;
    }

    public String toString(HashMap<Integer, Role> roles) {
        String result = "Логин: " + login;
        result += !fio.equals("") ? "\nФИО: " + fio : "";
        result += "\nТелефон: " + phone;
        result += "\nE-mail: " + email;
        result += "\nГород: " + city;

        if (!street.equals("")) {
            result += "\nУлица: " + street;

            if (house != null && !house.equals("")) {
                result += ", Дом: " + house;
            }
            if (apartment != null && !apartment.equals("")) {
                result += ", Квартира: " + apartment;
            }

        }

        result += "\nКоличество заказов: " + qtyOrders;
        result += "\nСтатус: " + roles.get(roleId).getName();
        result += "\nДата регистрации: " + new SimpleDateFormat("dd MMMM yyyy").
                format(registrationDate);

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDto userDto = (UserDto) o;

        if (id != userDto.id) return false;
        if (roleId != userDto.roleId) return false;
        if (qtyOrders != userDto.qtyOrders) return false;
        if (!Objects.equals(fio, userDto.fio)) return false;
        if (!login.equals(userDto.login)) return false;
        if (!phone.equals(userDto.phone)) return false;
        if (!email.equals(userDto.email)) return false;
        if (!city.equals(userDto.city)) return false;
        if (!Objects.equals(street, userDto.street)) return false;
        if (!Objects.equals(house, userDto.house)) return false;
        if (!Objects.equals(apartment, userDto.apartment)) return false;
        if (!registrationDate.equals(userDto.registrationDate)) return false;
        return Objects.equals(orderDto, userDto.orderDto);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fio != null ? fio.hashCode() : 0);
        result = 31 * result + login.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (house != null ? house.hashCode() : 0);
        result = 31 * result + (apartment != null ? apartment.hashCode() : 0);
        result = 31 * result + registrationDate.hashCode();
        result = 31 * result + roleId;
        result = 31 * result + qtyOrders;
        result = 31 * result + (orderDto != null ? orderDto.hashCode() : 0);
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getQtyOrders() {
        return qtyOrders;
    }

    public void setQtyOrders(int qtyOrders) {
        this.qtyOrders = qtyOrders;
    }

    public OrderDto getOrderDto() {
        return orderDto;
    }

    public void setOrderDto(OrderDto orderDto) {
        this.orderDto = orderDto;
    }
}
