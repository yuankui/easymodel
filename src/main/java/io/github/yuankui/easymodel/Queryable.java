package io.github.yuankui.easymodel;

import java.util.List;

public interface Queryable<T extends BaseObj> {
    List<T> list();
    
    Queryable<T> filter(String fieldName, String value);
    
    Queryable<T> sort(String fieldName, boolean asc);
    
    int size();
    
    Queryable<T> limit(int limit, int offset);
}