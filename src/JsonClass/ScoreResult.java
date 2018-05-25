package JsonClass;

import javax.servlet.jsp.el.ScopedAttributeELResolver;

public class ScoreResult {
    //获取学生成绩的json对象
    private String sub_name;
    private float score;
    private String sub_teacher;

    public ScoreResult(String name,String teacher ,int score){
        this.sub_name=name;
        this.sub_teacher=teacher;
        this.score=score;
    }
    public ScoreResult(){}

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public void setSub_teacher(String sub_teacher) {
        this.sub_teacher = sub_teacher;
    }


    public String getSub_name() {

        return sub_name;
    }

    public float getScore() {
        return score;
    }

    public String getSub_teacher() {
        return sub_teacher;
    }

}
