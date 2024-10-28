package com.mycompany.lab6springmvc.util;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
public class Part_9 {

    public String getMessage() {
        return "This is a message from Part_9 with request scope.";
    }
}
