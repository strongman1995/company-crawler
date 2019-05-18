package cn.thu.info.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.Reader;

/**
 * Created by zhangbo on 2016/6/12.
 */
public class SessionHelper {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;
    private static SqlSession session;

    static{
        try{
            reader  = Resources.getResourceAsReader("MyBatisConfiguration.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            session = sqlSessionFactory.openSession(true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSessionFactory(){
        return sqlSessionFactory;
    }

    public static SqlSession getSession(){
        //sqlSessionFactory.openSession(true).
        return sqlSessionFactory.openSession(true);
    }

    public static void closeSession(){
        if(session != null){
            session.close();
        }
    }
}
