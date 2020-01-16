package io.github.yuankui.easymodel;

public interface Model<T extends BaseObj> {
    Queryable<T> query();
    
    Result insert(T obj);
    
    Result update(Long id, T obj);
    
    Result delete(Long id);
}
