package io.github.yuankui.easymodel.request;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class UpdateParam {
    private Long id;
    private JSONObject obj;
}
