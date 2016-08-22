package com.stas.tsepa.vkbirthday.domain.interactors.impl;

import com.stas.tsepa.vkbirthday.domain.interactors.GetLocalUsersInteractor;
import com.stas.tsepa.vkbirthday.domain.interactors.base.AbstractThreadInteractor;
import com.stas.tsepa.vkbirthday.domain.model.User;
import com.stas.tsepa.vkbirthday.domain.repository.UserRepository;

import java.util.List;

/**
 * Created by Tsepa Stas
 * Email tsepa.stas@gmail.com
 */
public class GetLocalUsersInteractorImpl extends AbstractThreadInteractor
        implements GetLocalUsersInteractor {

    private UserRepository mRepository;
    private Callback mCallback;

    GetLocalUsersInteractorImpl(UserRepository mRepository, Callback mCallback) {
        this.mRepository = mRepository;
        this.mCallback = mCallback;
    }

    @Override
    public void run() {
        try {
            List<User> list = mRepository.getAll();
            mCallback.onFinished(list);
        } catch (Throwable throwable) {
            mCallback.onError(throwable);
        }
    }
}
