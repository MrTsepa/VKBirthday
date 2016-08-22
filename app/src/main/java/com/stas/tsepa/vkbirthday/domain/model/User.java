package com.stas.tsepa.vkbirthday.domain.model;

import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by Tsepa Stas
 * Email tsepa.stas@gmail.com
 */
public class User {
    public String firstName;
    public String lastName;
    public LocalDate birthDate;
    public List<VKAccount> vkAccountList;
}
