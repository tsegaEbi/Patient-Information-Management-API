package com.therapy.therapy.examination.labOrder;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LabOrderUpdate {
    private String cmd;
    private Long id;
    private Long staffId;
    private String value;

}
