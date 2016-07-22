package duro.imperio.angel.daily.login.di;

import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import duro.imperio.angel.daily.domain.FirebaseAPI;
import duro.imperio.angel.daily.libs.base.EventBus;
import duro.imperio.angel.daily.login.LoginInteractor;
import duro.imperio.angel.daily.login.LoginInteractorImpl;
import duro.imperio.angel.daily.login.LoginPresenter;
import duro.imperio.angel.daily.login.LoginPresenterImpl;
import duro.imperio.angel.daily.login.LoginRepository;
import duro.imperio.angel.daily.login.LoginRepositoryImpl;
import duro.imperio.angel.daily.login.ui.LoginView;

/**
 * Created by Angel on 17/7/2016.
 */
@Module
public class LoginModule {
    private LoginView view;

    public LoginModule(LoginView view) {
        this.view = view;
    }

    @Singleton
    @Provides
    LoginView providesLoginView(){
        return this.view;
    }

    @Singleton
    @Provides
    LoginPresenter providesLoginPresenter(EventBus eventBus, LoginView view, LoginInteractor loginInteractor){
        return new LoginPresenterImpl(eventBus, view, loginInteractor);
    }

    @Singleton
    @Provides
    LoginInteractor providesLoginInteractor(LoginRepository repository){
        return new LoginInteractorImpl(repository);
    }

    @Singleton
    @Provides
    LoginRepository providesLoginRepository(SharedPreferences preferences, FirebaseAPI firebase, EventBus eventBus){
        return new LoginRepositoryImpl(preferences, firebase, eventBus);
    }

}
