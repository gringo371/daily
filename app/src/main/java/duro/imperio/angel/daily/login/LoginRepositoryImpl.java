package duro.imperio.angel.daily.login;

import android.content.SharedPreferences;

import com.firebase.client.FirebaseError;

import duro.imperio.angel.daily.DailyApp;
import duro.imperio.angel.daily.domain.FirebaseAPI;
import duro.imperio.angel.daily.domain.FirebaseActionListenerCallback;
import duro.imperio.angel.daily.libs.base.EventBus;
import duro.imperio.angel.daily.login.events.LoginEvent;

/**
 * Created by Angel on 17/7/2016.
 */
public class LoginRepositoryImpl implements LoginRepository {
    private final static String NO_EMAIL = "";

    private SharedPreferences preferences;
    private FirebaseAPI firebase;
    private EventBus eventBus;

    public LoginRepositoryImpl(SharedPreferences preferences, FirebaseAPI firebase, EventBus eventBus) {
        this.preferences = preferences;
        this.firebase = firebase;
        this.eventBus = eventBus;
    }

    @Override
    public void getEmail() {
        String email = preferences.getString(DailyApp.getUserEmailName(), NO_EMAIL);
        post(LoginEvent.UPDATE_EMAIL, email);
    }

    @Override
    public void signUp(final String email, final String password) {
        firebase.singUp(email, password, new FirebaseActionListenerCallback() {
            @Override
            public void onSuccess() {
                post(LoginEvent.ON_SIGN_UP_SUCCESS);
                singIn(email, password);
            }

            @Override
            public void onError(FirebaseError error) {
                post(LoginEvent.ON_SIGN_UP_ERROR, error.getMessage());
            }
        });
    }

    @Override
    public void singIn(final String email, final String password) {
        firebase.login(email, password, new FirebaseActionListenerCallback() {
            @Override
            public void onSuccess() {
                preferences.edit().putString(DailyApp.getUserEmailName(), email).commit();
                post(LoginEvent.ON_SIGN_IN_SUCCESS, email);
            }

            @Override
            public void onError(FirebaseError error) {
                post(LoginEvent.ON_SIGN_IN_ERROR, error.getMessage());
            }
        });
    }

    void post (int type){
        post(type, null);
    }

    void post(int type, String message){
        LoginEvent event = new LoginEvent();
        event.setMessage(message);
        event.setType(type);
        eventBus.post(event);
    }
}
