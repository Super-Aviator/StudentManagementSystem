package test;

import exceptions.AccountException;
import tools.AccountOperate;

import java.security.AccessControlContext;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {
        try {
            AccountOperate.register("6504110130","6504110130xqk");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (AccountException e) {
            e.printStackTrace();
        }
    }
}