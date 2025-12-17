package com.smartsched.crm.models;

public class Client {
    private int id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String notes;

    public Client() {}

    public Client(String name, String phone, String email, String address, String notes) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.notes = notes;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getNotes() { return notes; }
}
