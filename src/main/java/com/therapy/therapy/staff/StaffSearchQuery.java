package com.therapy.therapy.staff;

import com.therapy.therapy.common.EMPLOYMENT;
import com.therapy.therapy.common.QUALIFICATION;
import com.therapy.therapy.common.STATUS;
import com.therapy.therapy.common.Sex;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class StaffSearchQuery {
   private int pageNumber ;
    private Long departmentId ;
    private Sex sex ;
    private EMPLOYMENT employment ;
    private QUALIFICATION qualification ;
    private STATUS status;

}
