package io.github.yuankui.easymodel;

import com.alibaba.fastjson.JSONObject;

public interface Table {
    Queryable query();
    
    Result insert(JSONObject obj);
    
    Result update(Long id, JSONObject obj);
    
    Result delete(Long id);
    
    String name();
}
