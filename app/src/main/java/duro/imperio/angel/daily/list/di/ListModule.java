package duro.imperio.angel.daily.list.di;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import duro.imperio.angel.daily.domain.FirebaseAPI;
import duro.imperio.angel.daily.entities.Page;
import duro.imperio.angel.daily.libs.base.EventBus;
import duro.imperio.angel.daily.list.ListContentInteractor;
import duro.imperio.angel.daily.list.ListContentInteractorImpl;
import duro.imperio.angel.daily.list.ListPresenter;
import duro.imperio.angel.daily.list.ListPresenterImpl;
import duro.imperio.angel.daily.list.ListRepository;
import duro.imperio.angel.daily.list.ListRepositoryImpl;
import duro.imperio.angel.daily.list.ListSessionInteractor;
import duro.imperio.angel.daily.list.ListSessionInteractorImpl;
import duro.imperio.angel.daily.list.ui.ListView;
import duro.imperio.angel.daily.list.ui.adapters.ListAdapter;
import duro.imperio.angel.daily.list.ui.adapters.OnItemClickListener;

/**
 * Created by Angel on 18/7/2016.
 */
@Module
public class ListModule {
    private ListView view;
    private OnItemClickListener clickItemListener;

    public ListModule(ListView view, OnItemClickListener clickItemListener) {
        this.view = view;
        this.clickItemListener = clickItemListener;
    }

    @Singleton
    @Provides
    ListView providesListView(){
        return this.view;
    }

    @Singleton
    @Provides
    ListPresenter providesListPresenter (EventBus eventBus, ListView view, ListContentInteractor contentInteractor, ListSessionInteractor sessionInteractor){
        return new ListPresenterImpl(eventBus, view, contentInteractor, sessionInteractor);
    }

    @Singleton
    @Provides
    ListContentInteractor providesListContentInteractor (ListRepository repository){
        return new ListContentInteractorImpl(repository);
    }

    @Singleton
    @Provides
    ListSessionInteractor providesListSessionInteractor (ListRepository repository){
        return new ListSessionInteractorImpl(repository);
    }

    @Singleton
    @Provides
    ListRepository providesListRepository (EventBus eventBus, FirebaseAPI firebase){
        return new ListRepositoryImpl(eventBus, firebase);
    }

    @Singleton
    @Provides
    OnItemClickListener providesOnItemClickListener (){
        return this.clickItemListener;
    }

    @Singleton
    @Provides
    ListAdapter providesListAdapter (List<Page> pages, OnItemClickListener onItemClickListener){
        return new ListAdapter(pages, onItemClickListener);
    }

    @Singleton
    @Provides
    List<Page> providesEmptyList(){
        return new ArrayList<Page>();
    }

}
