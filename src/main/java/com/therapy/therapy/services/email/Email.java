package com.therapy.therapy.services.email;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Email {
    private String subject;
    private String body;
    private String to;
    private String from;

}
