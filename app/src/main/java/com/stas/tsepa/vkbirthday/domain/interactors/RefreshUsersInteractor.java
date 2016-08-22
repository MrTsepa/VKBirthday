package com.stas.tsepa.vkbirthday.domain.interactors;

import com.stas.tsepa.vkbirthday.domain.model.User;

import java.util.List;

/**
 * Created by Tsepa Stas
 * Email tsepa.stas@gmail.com
 */
public interface RefreshUsersInteractor {
    public interface Callback {
        void onAdded(List<User> addedUsers);
        void onDeleted(List<User> deletedUsers);
    }
}
