package duro.imperio.angel.daily.login;

import duro.imperio.angel.daily.login.events.LoginEvent;

/**
 * Created by Angel on 16/7/2016.
 */
public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void getEmail();

    void validateLogin(String email, String password);
    void registerNewUser(String email, String password);
    void onEventMainThread(LoginEvent event);

}
