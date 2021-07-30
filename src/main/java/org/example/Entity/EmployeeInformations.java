package org.example.entity;

public class EmployeeInformations {

    private String name;
    private String street;
    private String city;
    private String country;
    private String phone;
    private String email;

    public EmployeeInformations(){

    }

    public EmployeeInformations(String name, String street, String city, String country, String phone, String email) {
        setName(name);
        setStreet(street);
        setCity(city);
        setCountry(country);
        setPhone(phone);
        setEmail(email);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

}
