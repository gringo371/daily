package duro.imperio.angel.daily.writer.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import duro.imperio.angel.daily.domain.FirebaseAPI;
import duro.imperio.angel.daily.entities.Page;
import duro.imperio.angel.daily.libs.base.EventBus;
import duro.imperio.angel.daily.writer.WriterSessionInteractorImpl;
import duro.imperio.angel.daily.writer.WriterStorageInteractor;
import duro.imperio.angel.daily.writer.WriterInteractorImpl;
import duro.imperio.angel.daily.writer.WriterPresenter;
import duro.imperio.angel.daily.writer.WriterPresenterImpl;
import duro.imperio.angel.daily.writer.WriterRepository;
import duro.imperio.angel.daily.writer.WriterRepositoryImpl;
import duro.imperio.angel.daily.writer.WriterSessionInteractor;
import duro.imperio.angel.daily.writer.ui.WriterView;

/**
 * Created by Angel on 14/7/2016.
 */
@Module
public class WriterModule {
    private WriterView view;

    public WriterModule(WriterView view) {
        this.view = view;
    }

    @Singleton
    @Provides
    WriterView providesWriterView (){
        return this.view;
    }

    @Singleton
    @Provides
    WriterPresenter providesWriterPresenter (WriterView view, WriterStorageInteractor storageInteractor, WriterSessionInteractor sessionInteractor, EventBus eventBus){
        return new WriterPresenterImpl(view, storageInteractor, sessionInteractor, eventBus);
    }

    @Singleton
    @Provides
    WriterSessionInteractor providesWriterSessionInteractor (WriterRepository repository){
        return new WriterSessionInteractorImpl(repository);
    }

    @Singleton
    @Provides
    WriterStorageInteractor providesWriterInteractor (WriterRepository repository){
        return new WriterInteractorImpl(repository);
    }

    @Singleton
    @Provides
    WriterRepository providesWriterRepository (FirebaseAPI firebase, EventBus eventBus, Page page){
        return new WriterRepositoryImpl(firebase, eventBus, page);
    }

    @Singleton
    @Provides
    Page providesNewPage(){
        return new Page();
    }
}
