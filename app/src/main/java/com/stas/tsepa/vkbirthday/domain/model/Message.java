package com.stas.tsepa.vkbirthday.domain.model;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

/**
 * Created by Tsepa Stas
 * Email tsepa.stas@gmail.com
 */
public class Message {
    public LocalDate date;
    public LocalTime time;
    public Account reciever;
    public String value;
    public boolean isCompleted;
}
