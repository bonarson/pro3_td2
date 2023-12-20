package model;

public class Customer {
    private int id_customer;
    private String name_customer;

    public Customer(int id_customer, String name_customer) {
        this.id_customer = id_customer;
        this.name_customer = name_customer;
    }

    public int getId_customer() {
        return id_customer;
    }

    public void setId_customer(int id_customer) {
        this.id_customer = id_customer;
    }

    public String getName_customer() {
        return name_customer;
    }

    public void setName_customer(String name_customer) {
        this.name_customer = name_customer;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id_customer=" + id_customer +
                ", name_customer='" + name_customer + '\'' +
                '}';
    }
}
