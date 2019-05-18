package cn.thu.info.utils;

import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 * Created by zhangbo on 2016/6/3.
 */
public class DataQueryUtil {
    public static Connection conn = null;
    public static String sqlT = "SELECT title, date, url, source FROM news WHERE title like \"%query%\" ";
    public static String sqlD = "SELECT title, date, url, source FROM news WHERE content like \"%query%\" ";
    static {
        try{
            Class.forName("com.mysql.jdbc.Driver") ;
            String url = "jdbc:mysql://localhost:3306/infocrawler?characterEncoding=utf8" ;
            String username = "root" ;
            String password = "121212" ;
            conn = DriverManager.getConnection(url, username, password) ;


        }catch(Exception e){
            e.printStackTrace() ;
        }
    }
    public static void query(String query){
        try {
            PreparedStatement  pstmt = (PreparedStatement)conn.prepareStatement(sqlT.replace("query", query));
            ResultSet rs = pstmt.executeQuery();
            int cnt = 0;
            while (rs.next()){
                StringBuffer buffer = new StringBuffer();
                for (int i=1; i<=4; i++){
                    buffer.append(rs.getString(i));
                    buffer.append("\t");
                }
                System.out.println(buffer.toString());
                cnt++;
                if(cnt >=3){
                    break;
                }
            }
            if(cnt <3){
                pstmt = (PreparedStatement)conn.prepareStatement(sqlD.replace("query", query));
                rs = pstmt.executeQuery();
                while (rs.next()){
                    StringBuffer buffer = new StringBuffer();
                    for (int i=1; i<=4; i++){
                        buffer.append(rs.getString(i));
                        buffer.append("\t");
                    }
                    System.out.println(buffer.toString());
                    cnt++;
                    if(cnt >=3){
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
