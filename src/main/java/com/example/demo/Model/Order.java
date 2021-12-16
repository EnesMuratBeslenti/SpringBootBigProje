package com.example.demo.Model;

public class Order<customerId> {
    private  Long id;
    private  Long customerId;
    private  Long productId;
    private String productName;
    private String customerName;

    public Order() {
    }

    public Order(Long customerId, Long productId, String productName, String customerName) {
        this.customerId = customerId;
        this.productId = productId;
        this.productName = productName;
        this.customerName = customerName;
    }

    public Order(Long id, Long customerId, Long productId, String productName, String customerName) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.productName = productName;
        this.customerName = customerName;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
