package test;

import java.sql.SQLException;
import java.util.Scanner;

import exceptions.AccountException;
import tools.AccountOperate;

public class LoginOrRegister {
    private static Scanner reader=new Scanner(System.in);
    public static void main(String[] args) throws Exception{
        String status=reader.nextLine();
        if (status.compareToIgnoreCase("Register")==0){
            System.out.print("注册的账号：");
            String username =reader.nextLine();
            System.out.print("注册密码：");
            String password=reader.nextLine();
            try{
                AccountOperate.register(username,password);
            }catch(SQLException e){
                //注册失败
            }catch(AccountException ra){
                //已经注册
            }

        }

        while(true){//用户登陆
            System.out.print("username:");
            String username=reader.nextLine();
            System.out.print("password:");
            String password=reader.nextLine();
            System.out.print("验证中...");
            if(AccountOperate.login(username,password))
                break;
            System.out.println("登陆失败");
        }
        System.out.println("登陆成功");
        System.out.println("------------------------------");

        String sql;
        while(true){
            sql=reader.nextLine();
            if(sql.compareToIgnoreCase("exit")==0)   {
                System.out.println("Bye");
                System.exit(1);
            }
        }
    }
}
