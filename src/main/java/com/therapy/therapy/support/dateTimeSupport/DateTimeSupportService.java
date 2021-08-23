package com.therapy.therapy.support.dateTimeSupport;

import java.util.Date;

public interface DateTimeSupportService {

    Date getNextDay(Date date);
    Date getDayBefore(Date date);

    Date getWeekBefore(Date date);
    Date getWeekAfter(Date date);

    Date getMonthBefore(Date date);
    Date getMonthAfter(Date date);
}
