package io.github.yuankui.easymodel;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Result {
    private boolean success;
    private String msg;

    public static Result success() {
        return new Result(true, null);
    }
    
    public static Result fail(String msg) {
        return new Result(false, msg);
    }
}
