package cn.thu.info.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;


public class CompanyMysqlPipeline implements Pipeline {
    public static Connection conn = null;
    public static String sql = "insert into company ";
    public static String[] keys = {
            "name",
            "website",
            "company_name",
            "industry",
            "setup_time",
            "city",
            "capital",
            "profile",
            "reg_address",
            "financing_round",
            "financing_time",
            "financing_amount",
            "financing_financer",
            "founder"
    };
    static {
        try{
            Class.forName("com.mysql.jdbc.Driver") ;
            String url = "jdbc:mysql://localhost:3306/infocrawler?characterEncoding=utf8" ;
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
                    Object value = resultItems.getAll().get(key);
                    pstmt.setString(i + 1, value == null ? "" : value.toString());
                }else {
                    pstmt.setString(i + 1 , "");
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
