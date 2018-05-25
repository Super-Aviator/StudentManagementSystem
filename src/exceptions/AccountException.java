package exceptions;

public class AccountException extends  Exception{
    public AccountException(String name){
        super(name);
    }

    public String toString(){
        return super.getMessage();
    }
}
