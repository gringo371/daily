package duro.imperio.angel.daily;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Angel on 17/7/2016.
 */
@Module
public class DailyModule {
    private Application application;

    public DailyModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    Application providesApplication(){
        return this.application;
    }

    @Singleton
    @Provides
    SharedPreferences providesSharedPreferences(Application application){
        return application.getSharedPreferences(DailyApp.getUserPreferenceName(), Context.MODE_PRIVATE);
    }

}
