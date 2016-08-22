package com.stas.tsepa.vkbirthday.domain.storage;

import com.stas.tsepa.vkbirthday.domain.model.User;
import com.stas.tsepa.vkbirthday.domain.model.VKAccount;
import com.stas.tsepa.vkbirthday.domain.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tsepa Stas
 * Email tsepa.stas@gmail.com
 */
public class UserCollection implements UserRepository{

    private List<User> userList = new ArrayList<>();

    @Override
    public List<User> getAll() {
        return userList;
    }

    @Override
    public void add(User user) {
        userList.add(user);
    }

    @Override
    public List<VKAccount> getAllVKAccounts() {
        List<VKAccount> accountList = new ArrayList<>();
        for (User user : userList) {
            if (user.vkAccountList != null) {
                accountList.addAll(user.vkAccountList);
            }
        }
        return accountList;
    }
}
