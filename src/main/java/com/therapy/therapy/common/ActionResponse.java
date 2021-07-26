package com.therapy.therapy.common;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter


public class ActionResponse<T> {

    private Boolean result;
    private String  message;
    private Object T;
}
