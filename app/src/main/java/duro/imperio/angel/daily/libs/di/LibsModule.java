package duro.imperio.angel.daily.libs.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import duro.imperio.angel.daily.libs.GreenRobotEventBus;
import duro.imperio.angel.daily.libs.base.EventBus;

/**
 * Created by Angel on 14/7/2016.
 */
@Module
public class LibsModule {

    @Provides
    @Singleton
    EventBus providesEventBus(org.greenrobot.eventbus.EventBus eventBus){
        return new GreenRobotEventBus(eventBus);
    }

    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesLibraryEventBud(){
        return org.greenrobot.eventbus.EventBus.getDefault();
    }
}
