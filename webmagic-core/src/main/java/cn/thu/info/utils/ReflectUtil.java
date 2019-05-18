package cn.thu.info.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by zhangbo on 2016/6/16.
 */
public class ReflectUtil {
    private static Logger logger = LoggerFactory.getLogger(ReflectUtil.class);
    public static void setValueFromMap(Object instance, Map<String, Object> map){
        if(instance == null || map == null){
            return;
        }
        for (Map.Entry<String, Object> pair : map.entrySet()){
            try {
                if(pair.getValue() == null){
                    continue;
                }
                //logger.info("Current function " + Util.underlineToCamel("set_" + pair.getKey()));
                Method method = instance.getClass().getMethod(Util.underlineToCamel("set_" + pair.getKey()), pair.getValue().getClass());
                method.invoke(instance, pair.getValue());
            } catch (Exception e) {
                //logger.error("No such method " + Util.underlineToCamel("set_" + pair.getKey()));
                e.printStackTrace();
            }
        }
    }
}
