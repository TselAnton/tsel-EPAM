package entity;

import java.sql.Date;

public class User {
    private int id;
    private String fio;
    private String login;
    private String password;
    private String phone;
    private String email;
    private String city;
    private String street;
    private String house;
    private String apartment;
    private Date registrationDate;
    private int roleId;
    private int qtyOrders;

    public User() {
    }

    public User(int id, String fio, String login, String password, String phone, String email, String city, String street, String house, String apartment, Date registrationDate, int roleId, int qtyOrders) {
        this.id = id;
        this.fio = fio;
        this.login = login;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.city = city;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
        this.registrationDate = registrationDate;
        this.roleId = roleId;
        this.qtyOrders = qtyOrders;
    }

    /* Конструктор клонирования */
    public User(User u) {
            this(
                u.getId(),
                u.getFio(),
                u.getLogin(),
                u.getPassword(),
                u.getPhone(),
                u.getEmail(),
                u.getCity(),
                u.getStreet(),
                u.getHouse(),
                u.getApartment(),
                u.getRegistrationDate(),
                u.getRoleId(),
                u.getQtyOrders()
        );
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (roleId != user.roleId) return false;
        if (qtyOrders != user.qtyOrders) return false;
        if (fio != null ? !fio.equals(user.fio) : user.fio != null) return false;
        if (!login.equals(user.login)) return false;
        if (!password.equals(user.password)) return false;
        if (!phone.equals(user.phone)) return false;
        if (!email.equals(user.email)) return false;
        if (!city.equals(user.city)) return false;
        if (!street.equals(user.street)) return false;
        if (house != null ? !house.equals(user.house) : user.house != null) return false;
        if (apartment != null ? !apartment.equals(user.apartment) : user.apartment != null) return false;
        return registrationDate.equals(user.registrationDate);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fio != null ? fio.hashCode() : 0);
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + street.hashCode();
        result = 31 * result + (house != null ? house.hashCode() : 0);
        result = 31 * result + (apartment != null ? apartment.hashCode() : 0);
        result = 31 * result + registrationDate.hashCode();
        result = 31 * result + roleId;
        result = 31 * result + qtyOrders;
        return result;
    }
}
