package com.fadineg.trainingproject.second.tasks.class_task_7;

public class Buyer {
    private String name;
    private String family;
    private Product productBuy;
    private double transferredMoney;
    private boolean blackList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public double getTransferredMoney() {
        return transferredMoney;
    }

    public void setTransferredMoney(double transferredMoney) {
        this.transferredMoney = transferredMoney;
    }

    public Product getProductBuy() {
        return productBuy;
    }

    public void setProductBuy(Product productBuy) {
        this.productBuy = productBuy;
    }

    public boolean getBlackList() {
        return blackList;
    }

    public void setBlackList(boolean blackList) {
        this.blackList = blackList;
    }

    @Override
    public String toString() {
        return this.getFamily() + " " + this.getName() + '\n' + this.getProductBuy().toString() + '\n'
                + "Оплачено: " + this.getTransferredMoney() + '\n'
                + "В черном листе: " + this.getBlackList();
    }
}
