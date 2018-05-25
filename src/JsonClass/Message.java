package JsonClass;

public class Message {//发送状态码的json对象
    private String meg;

    public Message(String meg) {
        this.meg = meg;
    }

    public Message(){}

    public void setMeg(String meg) {
        this.meg = meg;
    }

    public String getMeg() {

        return meg;
    }
}
