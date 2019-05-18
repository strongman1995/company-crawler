package us.codecraft.webmagic.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

import java.util.List;
import java.util.Map;

/**
 * Write results in console.<br>
 * Usually used in test.
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.1.0
 */
public class ConsolePipeline implements Pipeline {
    public void outputMap(Map<String, Object> map){
        for (Map.Entry<String, Object> subEntry : map.entrySet()) {
            System.out.println(subEntry.getKey() + ":\t" + subEntry.getValue());
        }
    }
    public void outputList(List<Map<String, Object>> list){
        for (Map<String, Object> m : list){
            outputMap(m);
        }
    }
    @Override
    public void process(ResultItems resultItems, Task task) {
        System.out.println("get page: " + resultItems.getRequest().getUrl());
        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
            if(entry.getValue() instanceof Map){
                outputMap((Map<String, Object>)entry.getValue());
            }else if(entry.getValue() instanceof List) {
                outputList((List<Map<String, Object>>) entry.getValue());
            } else {
                System.out.println(entry.getKey() + ":\t" + entry.getValue());
            }
        }
    }
}
