package duro.imperio.angel.daily.list.di;

import javax.inject.Singleton;

import dagger.Component;
import duro.imperio.angel.daily.DailyModule;
import duro.imperio.angel.daily.domain.di.DomainModule;
import duro.imperio.angel.daily.libs.di.LibsModule;
import duro.imperio.angel.daily.list.ui.ListActivity;

/**
 * Created by Angel on 18/7/2016.
 */
@Singleton
@Component (modules = {LibsModule.class, DomainModule.class, ListModule.class})
public interface ListComponent {
    void inject(ListActivity activity);
}
