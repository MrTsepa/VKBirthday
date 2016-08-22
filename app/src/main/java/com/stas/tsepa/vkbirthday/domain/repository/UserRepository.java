package com.stas.tsepa.vkbirthday.domain.repository;

import com.stas.tsepa.vkbirthday.domain.model.User;
import com.stas.tsepa.vkbirthday.domain.model.VKAccount;

import java.util.List;

/**
 * Created by Tsepa Stas
 * Email tsepa.stas@gmail.com
 */
public interface UserRepository {
    List<User> getAll();
    List<VKAccount> getAllVKAccounts();
    void add(User user);
}
