package io.github.yuankui.easymodel;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface Queryable {
    List<JSONObject> list();
    
    Queryable filter(String fieldName, String value);
    
    Queryable sort(String fieldName, boolean asc);
    
    int size();
    
    Queryable limit(int limit, int offset);
}