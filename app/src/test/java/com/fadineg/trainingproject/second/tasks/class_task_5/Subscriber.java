package com.fadineg.trainingproject.second.tasks.class_task_5;

class Subscriber {

    private String name;
    private String secondName;
    private String family;
    private String address;
    private Integer numberCreditCard;
    private Integer debit;
    private Integer credit;
    private Integer longDistanceCall;
    private Integer cityCall;
    private Integer number;

    public Subscriber(Integer number, String name, String secondName, String family, String address,
                      Integer numberCreditCard, Integer debit, Integer credit,
                      Integer longDistanceCall, Integer cityCall) {

        this.number = number;
        this.name = name;
        this.secondName = secondName;
        this.family = family;
        this.address = address;
        this.numberCreditCard = numberCreditCard;
        this.debit = debit;
        this.credit = credit;
        this.longDistanceCall = longDistanceCall;
        this.cityCall = cityCall;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getNumberCreditCard() {
        return numberCreditCard;
    }

    public void setNumberCreditCard(Integer numberCreditCard) {
        this.numberCreditCard = numberCreditCard;
    }

    public Integer getDebit() {
        return debit;
    }

    public void setDebit(Integer debit) {
        this.debit = debit;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getLongDistanceCall() {
        return longDistanceCall;
    }

    public void setLongDistanceCall(Integer longDistanceCall) {
        this.longDistanceCall = longDistanceCall;
    }

    public Integer getCityCall() {
        return cityCall;
    }

    public void setCityCall(Integer cityCall) {
        this.cityCall = cityCall;
    }


    @Override
    public String toString() {
        return "Номер.тел:" + this.getNumber() + ", Имя : " + this.getName()
                + ", Отчество : " + this.getSecondName() + ",  Фамилия : "
                + this.getFamily() + ",  Адрес : " + this.getAddress()
                + ",  Номер карты : " + this.getNumberCreditCard() + ",  Дебет : " + this.getDebit()
                + ",  Кредит: " + this.getCredit() + ",  Междугор. вызовы: " + this.getLongDistanceCall()
                + ",  Гор. вызовы: " + this.getCityCall();
    }

}
