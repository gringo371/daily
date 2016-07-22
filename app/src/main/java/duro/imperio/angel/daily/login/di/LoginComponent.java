package duro.imperio.angel.daily.login.di;

import javax.inject.Singleton;

import dagger.Component;
import duro.imperio.angel.daily.DailyModule;
import duro.imperio.angel.daily.domain.di.DomainModule;
import duro.imperio.angel.daily.libs.di.LibsModule;
import duro.imperio.angel.daily.login.ui.LoginActivity;

/**
 * Created by Angel on 17/7/2016.
 */
@Singleton
@Component (modules = {DailyModule.class, DomainModule.class, LibsModule.class, LoginModule.class})
public interface LoginComponent {
    void inject(LoginActivity activity);
}