package com.daohuai.bank;

/**
 * @author huai
 * @date 2022/8/10
 */
public class BankCard {
    //卡号
    private int cardNo;
    //密码
    private int password;
    private String userName;
    private int ID;
    //余额
    private double money;

    @Override
    public String toString() {
        return "BankCard{" +
                "cardNo=" + cardNo +
                ", password=" + password +
                ", userName='" + userName + '\'' +
                ", ID=" + ID +
                ", money=" + money +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public BankCard() {
    }

    public BankCard(int cardNo, int password, String userName, int ID) {
        this.cardNo = cardNo;
        this.password = password;
        this.userName = userName;
        this.ID = ID;
    }

    //初始化
    public BankCard(int cardNo, int password) {
        this.cardNo = cardNo;
        this.password = password;
        this.money = 0;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public void setMoney(double money) {

        this.money = money;
    }

    public int getPassword() {
        return password;
    }

    public double getMoney() {
        return money;
    }

    public int getCardNo() {
        return cardNo;
    }

    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }
}
