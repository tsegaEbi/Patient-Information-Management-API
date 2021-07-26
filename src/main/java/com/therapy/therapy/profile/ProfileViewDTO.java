package com.therapy.therapy.profile;

import com.therapy.therapy.staff.StaffDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProfileViewDTO {

    private Long id;
    private int pendingVisits;
    private int pendingOrders;
    private int pendingTreatments;

    private int pendingStatusChanges;

    private StaffDTO staff;
}
