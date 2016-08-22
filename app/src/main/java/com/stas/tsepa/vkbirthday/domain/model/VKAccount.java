package com.stas.tsepa.vkbirthday.domain.model;

import org.joda.time.LocalDate;

/**
 * Created by Tsepa Stas
 * Email tsepa.stas@gmail.com
 */
public class VKAccount extends Account {
    public int id;
    public String vkName;
    public String firstName;
    public String lastName;
    public LocalDate birthDate;
    public int priority;
    // TODO add avatar
}
