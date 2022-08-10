import cn.hutool.db.Db;
import com.daohuai.bank.Bank;
import com.daohuai.bank.User;

import java.sql.SQLException;

/**
 * @author huai
 * @date 2022/8/10
 */
public class TestDemo {
    public static void main(String[] args) throws SQLException {
//        Db.use().getConnection();
////        Db.use().findAll("BankCard");
//        System.out.println(Db.use().findAll("BankCard"));
        User user = new User();
        Bank bank = new Bank();
        bank.show(user);
    }
}
