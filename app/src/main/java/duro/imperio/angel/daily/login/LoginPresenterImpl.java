package duro.imperio.angel.daily.login;

import org.greenrobot.eventbus.Subscribe;

import duro.imperio.angel.daily.libs.base.EventBus;
import duro.imperio.angel.daily.login.events.LoginEvent;
import duro.imperio.angel.daily.login.ui.LoginView;

/**
 * Created by Angel on 16/7/2016.
 */
public class LoginPresenterImpl implements LoginPresenter {

    private EventBus eventBus;
    private LoginView view;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(EventBus eventBus, LoginView view, LoginInteractor loginInteractor) {
        this.eventBus = eventBus;
        this.view = view;
        this.loginInteractor = loginInteractor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        view = null;
        eventBus.unRegister(this);
    }

    @Override
    public void getEmail() {
        loginInteractor.getEmail();
    }

    @Override
    public void validateLogin(String email, String password) {
        if (view != null) {
            view.disableInput();
            view.showProgress();
        }

        loginInteractor.singIn(email, password);
    }

    @Override
    public void registerNewUser(String email, String password) {
        if (view != null) {
            view.disableInput();
            view.showProgress();
        }

        loginInteractor.doSingUp(email, password);
    }

    private void onSingInSuccess() {
        if (view != null) {
            view.navigateToListScreen();
        }
    }

    private void onSingUpSuccess() {
        if (view != null) {
            view.newUserSuccess();
        }
    }

    private void onSingInError(String error) {
        if (view != null) {
            view.hideProgress();
            view.enableInput();
            view.loginError(error);
        }
    }

    private void onSingUpError(String error) {
        if (view != null) {
            view.hideProgress();
            view.enableInput();
            view.newUserError(error);
        }
    }

    private void updateEmail(String email) {
        if (view != null) {
            view.updateEmail(email);
        }
    }

    @Override
    @Subscribe
    public void onEventMainThread(LoginEvent event) {

        switch(event.getType()){
            case LoginEvent.ON_SIGN_IN_ERROR:
                onSingInError(event.getMessage());
                break;
            case LoginEvent.ON_SIGN_UP_ERROR:
                onSingUpError(event.getMessage());
                break;
            case LoginEvent.ON_SIGN_IN_SUCCESS:
                onSingInSuccess();
                break;
            case LoginEvent.ON_SIGN_UP_SUCCESS:
                onSingUpSuccess();
                break;
            case LoginEvent.UPDATE_EMAIL:
                updateEmail(event.getMessage());
                break;
        }
    }

}
