package cn.thu.info.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class NewsMysqlPipeline implements Pipeline {
    public static Connection conn = null;
    public static String sql = "insert into news ";
    public static String[] keys = {
            "summary",
            "title",
            "tag",
            "image",
            "date",
            "content",
            "sub_id",
            "source",
            "url"
    };
    static {
        try{
            Class.forName("com.mysql.jdbc.Driver") ;
            String url = "jdbc:mysql://localhost:3306/vcdb?characterEncoding=utf8" ;
            String username = "root" ;
            String password = "121212" ;
            conn = DriverManager.getConnection(url, username, password) ;

            StringBuffer keyBuffer = new StringBuffer("(");
            StringBuffer valueBuffer = new StringBuffer("(");
            for(int i=0; i<keys.length; i++){
                keyBuffer.append(keys[i]);
                valueBuffer.append("?");
                if(i!=keys.length-1) {
                    keyBuffer.append(",");
                    valueBuffer.append(",");
                }
            }
            keyBuffer.append(")");
            valueBuffer.append(")");
            sql  =sql + keyBuffer.toString() + " values " + valueBuffer.toString();

        }catch(Exception e){
            e.printStackTrace() ;
        }
    }
    @Override
    public void process(ResultItems resultItems, Task task) {
        try {
            int fieldNum = 0;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            for (int i=0; i<keys.length; i++){
                String key = keys[i];
                if(resultItems.getAll().containsKey(key)) {
                    fieldNum ++;
                    pstmt.setString(i + 1, resultItems.getAll().get(key).toString());
                }
            }
            if(fieldNum > 3)
                pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
