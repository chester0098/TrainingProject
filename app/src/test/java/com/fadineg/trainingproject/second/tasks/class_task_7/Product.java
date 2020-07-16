package com.fadineg.trainingproject.second.tasks.class_task_7;


public class Product {
    private String prodName;
    private double prodPrice;

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public double getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(double prodPrice) {
        this.prodPrice = prodPrice;
    }

    @Override
    public String toString() {
        return "Наименование: " + this.prodName + " |  Цена: " + this.prodPrice;
    }
}
