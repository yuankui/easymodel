package io.github.yuankui.easymodel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Server {
    void request(HttpServletRequest request, HttpServletResponse response);
}
