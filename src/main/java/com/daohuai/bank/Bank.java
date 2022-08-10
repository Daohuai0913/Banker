package com.daohuai.bank;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author huai
 * @date 2022/8/10
 */
public class Bank {

    //    注册方法
    public void register(int cardNo, int password, String userName, int ID) throws SQLException {
        if(Db.use().findAll(Entity.create("BankCard").set("cardNo", cardNo).set("password", password).set("userName", userName).set("ID", ID)).size() > 0){
            System.out.println("该卡号已存在，请重新输入！");
            return;
        }
        Db.use().insert(Entity.create("BankCard").set("cardNo", cardNo).set("password", password).set("userName", userName).set("ID", ID));
        System.out.println("注册成功！");
    }

    //    登陆
    public Boolean login(int cardNo, int password) throws SQLException {
        if(Db.use().findAll(Entity.create("BankCard").set("cardNo", cardNo).set("password", password)).size() == 0){
            System.out.println("当前不存在任何银行卡信息，建议及时注册！");
            return false;
        }
        if (Db.use().findAll(Entity.create("BankCard").set("cardNo", cardNo)).size() > 0) {
            if (Db.use().findAll(Entity.create("BankCard").set("cardNo", cardNo).set("password", password)).size() > 0) {
                System.out.println("登录成功！");
                return true;
            } else {
                System.out.println("登录失败，该密码错误！");
                return false;
            }
        } else {
            System.out.println("登录失败，该卡号不存在！");
        }
        return false;
    }

    //进入银行界面
    public void show(User user) throws SQLException {
        while (true) {
            System.out.println("***欢迎来到本银行，请选择您的业务：***");
            System.out.println("1.登陆账户");
            System.out.println("2.注册账户");
            System.out.println("3.退出");
            System.out.println("请输入您的选择：");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            int cardNo;
            int password;
            switch (choice) {
                case 1:
                    System.out.println("请输入您的银行卡账号：");
                    cardNo = scanner.nextInt();
                    System.out.println("请输入您的银行卡密码：");
                    password = scanner.nextInt();
//                    login(cardNo, password);
                    if (login(cardNo, password) != null) {
                        System.out.printf("***卡号：%d登陆成功***", cardNo);
                        while (true) {
                            System.out.println("");
                            System.out.println("***请输入您需要的业务***");
                            System.out.println("1.存钱");
                            System.out.println("2.取钱");
                            System.out.println("3.转账");
                            System.out.println("4.查询余额");
                            System.out.println("5.修改密码");
                            System.out.println("6.退卡");
                            int num = scanner.nextInt();
                            if (num == 6) {
                                break;
                            };
                            operate(user, num, cardNo);
                        }
                    }
                    break;
                case 2:
                    cardNo = (int) (Math.random() * 1000000000);
                    System.out.println("请输入您的用户名：");
                    String userName = scanner.next();
                    System.out.println("请输入您的银行卡密码：");
                    password = scanner.nextInt();
                    System.out.println("请输入您的身份证号：");
                    int ID = scanner.nextInt();
                    register(cardNo,password, userName, ID);
                    System.out.println("注册成功！");
                    System.out.println("您的卡号为：" + cardNo);
                    break;
                case 3:
                    System.out.println("退出成功！");
                    break;
                default:
                    break;

            }
        }


    }

    public void operate(User user, int num,int cardNo) throws SQLException {
        switch (num) {
            case 1:
                user.savingMoney(cardNo);
                break;
            case 2:
                user.withdrawMoney(cardNo);
                break;
            case 3:
                user.transfer(cardNo);
                break;
            case 4:
                user.select(cardNo);
                break;
            case 5:
                user.changePassword(cardNo);
                break;
            default:
                break;
        }
    }

}
