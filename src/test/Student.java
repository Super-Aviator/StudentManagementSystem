package test;

public class Student {
    private String name;
    private long id;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student(String name, long id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    public String getName() {

        return name;
    }

    public long getId() {
        return id;
    }

    public int getAge() {
        return age;
    }
}
