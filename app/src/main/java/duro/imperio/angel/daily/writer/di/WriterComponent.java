package duro.imperio.angel.daily.writer.di;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Provides;
import duro.imperio.angel.daily.domain.di.DomainModule;
import duro.imperio.angel.daily.libs.di.LibsModule;
import duro.imperio.angel.daily.writer.ui.WriterActivity;

/**
 * Created by Angel on 14/7/2016.
 */
@Singleton
@Component(modules = {LibsModule.class, WriterModule.class, DomainModule.class})
public interface WriterComponent {
    void inject(WriterActivity activity);
}
