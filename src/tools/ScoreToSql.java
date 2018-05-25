package tools;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class ScoreToSql {//将成绩表转换为sql数据。
    public static void main(String[] args) throws IOException ,SQLException{
        File exc = new File("C:\\Users\\Aviator\\Desktop\\data\\term1.txt");
        Scanner reader = new Scanner(exc);
        List<Integer> l = new ArrayList<>();//保存学科号
        String[] sub = reader.nextLine().split("\\s+");
        for (int i = 0; i < sub.length; i++)
            l.add(Integer.parseInt(sub[i]));

        Map<Long,List<Byte>> map=new HashMap<>();
        while(reader.hasNextLine()){
            String[] score=reader.nextLine().split("\\s+");
            Long stu_id=Long.parseLong(score[0]);
            map.put(stu_id,new ArrayList());
            List<Byte> s=map.get(stu_id);
            for (int i = 1; i < score.length; i++)
                s.add(Byte.parseByte(score[i]));
            map.put(stu_id,s);
        }

        Connection conn = GetConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("INSERT INTO term1(stu_id,sub_id,score) VALUES (?,?,?);");
        Set<Long> s=map.keySet();
        for(long stu_id:s){//得到学号
            Iterator<Byte> score=map.get(stu_id).iterator();//得到成绩迭代器
            Iterator<Integer> sub_id=l.iterator();//得到学科号
            ps.setString(1, String.valueOf(stu_id));
            while(score.hasNext()){
                    ps.setString(2, String.valueOf(sub_id.next()));
                    ps.setString(3, String.valueOf(score.next()));
            }
        }

        ps.close();
        conn.close();
    }
}
