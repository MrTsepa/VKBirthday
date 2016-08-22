package com.stas.tsepa.vkbirthday.domain.interactors;

import com.stas.tsepa.vkbirthday.domain.model.User;

import java.util.List;

/**
 * Created by Tsepa Stas
 * Email tsepa.stas@gmail.com
 */
public interface GetLocalUsersInteractor {
    interface Callback {
        void onFinished(List<User> users);
        void onError(Throwable throwable);
    }
}
