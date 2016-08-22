package com.stas.tsepa.vkbirthday;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.stas.tsepa.vkbirthday.domain.interactors.RefreshUsersInteractor;
import com.stas.tsepa.vkbirthday.domain.interactors.base.Interactor;
import com.stas.tsepa.vkbirthday.domain.interactors.impl.RefreshVKUsersInteractor;
import com.stas.tsepa.vkbirthday.domain.model.User;
import com.stas.tsepa.vkbirthday.domain.repository.UserRepository;
import com.stas.tsepa.vkbirthday.domain.storage.UserCollection;
import com.vk.sdk.VKAccessToken;

import java.util.List;

/**
 * Created by Tsepa Stas
 * Email tsepa.stas@gmail.com
 */
public class MainActivity extends AppCompatActivity implements RefreshUsersInteractor.Callback{

    private TextView mTextView;
    private UserRepository mRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.main_text_view);

        mRepository = new UserCollection();

        Interactor interactor = new RefreshVKUsersInteractor(mRepository, this,
                getApplicationVKAccessToken());
        interactor.execute();
    }

    @Override
    public void onAdded(List<User> addedUsers) {
        String text = "";
        if (addedUsers == null)
            return;
        for (User user : addedUsers) {
            text += user.firstName + " " + user.lastName + "\n";
        }
        mTextView.setText(text);
    }

    @Override
    public void onDeleted(List<User> deletedUsers) {

    }

    private VKAccessToken getApplicationVKAccessToken() {
        return ((MyApplication) getApplication()).mVKAccessToken;
    }
}
