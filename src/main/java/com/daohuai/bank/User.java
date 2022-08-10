package com.daohuai.bank;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author huai
 * @date 2022/8/10
 */
public class User {
    Scanner scanner = new Scanner(System.in);

    public void savingMoney(int cardNo) throws SQLException {
        System.out.println("请输入存款金额：");
        double money = scanner.nextDouble();
        if (money > 0) {
            Db.use().execute("update bank_card set money = money + ? where card_no = ?", money, cardNo);

        } else {
            System.out.println("请输入正确金额");
        }
    }

    public void withdrawMoney(int cardNo) throws SQLException {
        System.out.println("请输入取款金额：");
        double money = scanner.nextDouble();
        if (money > 0 && money <= Db.use().findAll(Entity.create().set("card_no", cardNo)).get(0).getDouble("money")) {
            Db.use().execute("update bank_card set money = money - ? where card_no = ?", money, cardNo);
        } else if (money > Db.use().findAll(Entity.create().set("card_no", cardNo)).get(0).getDouble("money")) {
            System.out.println("很抱歉，您的余额不足！");
        } else {
            System.out.println("请输入正确取款金额！");
        }
//        } else if (money > bankCard.getMoney()) {
//            System.out.println("很抱歉，您的余额不足！");
//        } else {
//            System.out.println("请输入正确取款金额！");
//        }
    }

    public void select(int cardNo) throws SQLException {
        Db.use().findAll(Entity.create().set("card_no", cardNo));
    }

    public void transfer(int cardNo) throws SQLException {
        System.out.println("请输入对方的卡号：");
        //目的账号
        int destID = scanner.nextInt();
        if (Db.use().findAll(Entity.create().set("card_no", destID)).size() != 0) {
            System.out.println("请输入转账金额：");
            double money = scanner.nextDouble();
            if (money > 0 && money <= Db.use().findAll(Entity.create().set("card_no", cardNo)).get(0).getDouble("money")) {
                Db.use().execute("update bank_card set money = money - ? where card_no = ?", money, cardNo);
                Db.use().execute("update bank_card set money = money + ? where card_no = ?", money, destID);
            } else {
                System.out.println("请输入正确金额");
            }
        } else {
            System.out.println("对方卡号不存在");
        }
    }

    public void changePassword(int cardNo) throws SQLException {
        int password = scanner.nextInt();
        Db.use().execute("update bank_card set password = ? where card_no = ?", password, cardNo);
        System.out.println("Success changed password");
    }
}
