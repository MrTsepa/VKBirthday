package com.stas.tsepa.vkbirthday.domain.interactors.impl;

import android.util.Log;

import com.stas.tsepa.vkbirthday.domain.interactors.RefreshUsersInteractor;
import com.stas.tsepa.vkbirthday.domain.interactors.base.AbstractThreadInteractor;
import com.stas.tsepa.vkbirthday.domain.model.User;
import com.stas.tsepa.vkbirthday.domain.model.VKAccount;
import com.stas.tsepa.vkbirthday.domain.repository.UserRepository;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKUsersArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tsepa Stas
 * Email tsepa.stas@gmail.com
 */
public class RefreshVKUsersInteractor extends AbstractThreadInteractor
        implements RefreshUsersInteractor {

    private Callback mCallback;
    private UserRepository mRepository;
    private VKAccessToken mVKAccessToken;

    private static final String TAG = "RefreshVKUsers";

    public RefreshVKUsersInteractor(UserRepository mRepository, Callback mCallback,
                             VKAccessToken mVKAccessToken) {
        this.mCallback = mCallback;
        this.mRepository = mRepository;
        this.mVKAccessToken = mVKAccessToken;
    }

    public RefreshVKUsersInteractor(UserRepository mRepository, Callback mCallback) {
        this.mCallback = mCallback;
        this.mRepository = mRepository;
        this.mVKAccessToken = null;
    }

    @Override
    public void run() {
        VKRequest request;
        if (mVKAccessToken == null) {
            Log.d(TAG, "run: mVKAccessToken is null");
        }
        if (mVKAccessToken != null) {
            request = VKApi.friends().get(VKParameters.from(
                    VKApiConst.ACCESS_TOKEN, mVKAccessToken.accessToken,
                    VKApiConst.FIELDS, "name,bdate",
                    VKApiConst.SORT, "hint"));
        }
        else {
            request = VKApi.friends().get(VKParameters.from(
                    VKApiConst.FIELDS, "name,bdate",
                    VKApiConst.SORT, "hint"));
        }
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                Log.d(TAG, "onComplete: " + response.json.toString());

                VKUsersArray vkUsersArray = (VKUsersArray) response.parsedModel;
                List<VKAccount> fetchedAccounts = new ArrayList<>();
                for (int i = 0; i < vkUsersArray.size(); i++) {
                    fetchedAccounts.add(buildVKAccount(vkUsersArray.get(i), i));
                }
                List<VKAccount> localAccounts = mRepository.getAllVKAccounts();
                List<VKAccount> addedAccounts = getAddedVKAccounts(localAccounts, fetchedAccounts);

                List<User> addedUsers = new ArrayList<>();
                for (VKAccount account : addedAccounts) {
                    addedUsers.add(buildUser(account));
                }

                mCallback.onAdded(addedUsers);
            }
        });
    }

    private VKAccount buildVKAccount(VKApiUser user, int priority) {
        VKAccount account = new VKAccount();
        account.firstName = user.first_name;
        account.lastName = user.last_name;
        account.id = user.id;
        account.birthDate = null;
        account.priority = priority;
        return account;
    }

    private User buildUser(VKAccount account) {
        User user = new User();
        user.firstName = account.firstName;
        user.lastName = account.lastName;
        user.birthDate = account.birthDate;
        user.vkAccountList = new ArrayList<>();
        user.vkAccountList.add(account);
        account.user = user;
        return user;
    }

    private List<VKAccount> getAddedVKAccounts(List<VKAccount> oldAccounts,
                                             List<VKAccount> newAccounts) {
        List<VKAccount> result = new ArrayList<>();
        if (newAccounts == null)
            return result;
        if (oldAccounts == null) {
            result.addAll(newAccounts);
            return result;
        }
        for (VKAccount newAccount : newAccounts) {
            boolean contains = false;
            for (VKAccount oldAccount : oldAccounts) {
                if (newAccount.id == oldAccount.id) {
                    contains = true;
                    break;
                }
            }
            if (!contains) {
                result.add(newAccount);
            }
        }
        return result;
    }
}
