package com.therapy.therapy.support.dateTimeSupport;

import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class DateTimeSupportServiceImp implements DateTimeSupportService {
    @Override
    public Date getNextDay(Date date) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        Date next= c.getTime();
        return next;
    }

    @Override
    public Date getDayBefore(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -1);
        Date next= c.getTime();
        return next;
    }

    @Override
    public Date getWeekBefore(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -7);
        Date next= c.getTime();
        return next;
    }

    @Override
    public Date getWeekAfter(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 7);
        Date next= c.getTime();
        return next;
    }

    @Override
    public Date getMonthBefore(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -1);
        Date next= c.getTime();
        return next;
    }

    @Override
    public Date getMonthAfter(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, 1);
        Date next= c.getTime();
        return next;
    }
}
