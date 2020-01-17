package io.github.yuankui.easymodel;

import org.springframework.stereotype.Component;

@Component
public class HttpServerBuilder {
    public Server build(Service service) {
        return new ServerImpl(service);
    }
}
