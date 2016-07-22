package duro.imperio.angel.daily.list;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import duro.imperio.angel.daily.entities.Page;
import duro.imperio.angel.daily.libs.base.EventBus;
import duro.imperio.angel.daily.list.eventes.ListEvent;
import duro.imperio.angel.daily.list.ui.ListView;

/**
 * Created by Angel on 18/7/2016.
 */
public class ListPresenterImpl implements ListPresenter{
    private EventBus eventBus;
    private ListView view;
    private ListContentInteractor contentInteractor;
    private ListSessionInteractor sessionInteractor;

    public ListPresenterImpl(EventBus eventBus, ListView view, ListContentInteractor contentInteractor, ListSessionInteractor sessionInteractor) {
        this.eventBus = eventBus;
        this.view = view;
        this.contentInteractor = contentInteractor;
        this.sessionInteractor = sessionInteractor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unRegister(this);
        view = null;
    }

    @Override
    public void getContent() {
        contentInteractor.getContent();
    }

    @Override
    public void removePage(Page page) {
        contentInteractor.removePage(page);
    }

    @Override
    public void logout() {
        sessionInteractor.logout();
    }

    private void setContent(List<Page> pages){
        if (view != null){
            view.setContentList(pages);
        }
    }

    private void onRemoveSuccessfully(List<Page> page, String title){
        if (view != null){
            view.setContentList(page);
            view.showRemoveMessage(title);
        }
    }

    private void showMessageRemoveFailed(String error){
        if (view != null){
            view.showRemoveFailedMessage(error);
        }
    }

    @Subscribe
    @Override
    public void onMainThreadEvent(ListEvent event) {
        switch (event.getType()){
            case ListEvent.CONTENT_UPDATE:
                setContent(event.getPages());
                break;

            case ListEvent.ON_REMOVE_SUCCESSFULLY:
                onRemoveSuccessfully(event.getPages(), event.getMessage());
                break;

            case ListEvent.ON_REMOVE_FAILED:
                showMessageRemoveFailed(event.getMessage());
                break;
        }
    }
}
