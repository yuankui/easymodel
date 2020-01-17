package io.github.yuankui.easymodel.request;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class Request {
    /**
     * insert
     * update
     * delete
     * query
     */
    private String type;

    /**
     * 表名
     */
    private String table;

    /**
     * 参数
     */
    private JSONObject param;
}
