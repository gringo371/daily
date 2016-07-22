package duro.imperio.angel.daily.login.ui;

/**
 * Created by Angel on 16/7/2016.
 */
public interface LoginView {
    void enableInput();
    void disableInput();
    void showProgress();
    void hideProgress();

    void handleSingUp();
    void handleSingIn();

    void navigateToListScreen();
    void loginError(String error);

    void newUserSuccess();
    void newUserError(String error);

    void updateEmail(String email);
}
