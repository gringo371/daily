package duro.imperio.angel.daily.list;

import com.firebase.client.FirebaseError;
import com.raizlabs.android.dbflow.list.FlowCursorList;

import java.util.List;

import duro.imperio.angel.daily.domain.FirebaseAPI;
import duro.imperio.angel.daily.domain.FirebaseActionListenerCallback;
import duro.imperio.angel.daily.entities.FirebasePage;
import duro.imperio.angel.daily.entities.Page;
import duro.imperio.angel.daily.libs.base.EventBus;
import duro.imperio.angel.daily.list.eventes.ListEvent;

/**
 * Created by Angel on 18/7/2016.
 */
public class ListRepositoryImpl implements ListRepository{
    private EventBus eventBus;
    private FirebaseAPI firebase;

    public ListRepositoryImpl(EventBus eventBus, FirebaseAPI firebase) {
        this.eventBus = eventBus;
        this.firebase = firebase;
    }

    @Override
    public void getContent() {
        List<Page> pages = new FlowCursorList<>(false, Page.class).getAll();
        post(pages);
    }

    @Override
    public void removePage(final Page page) {
        if (page.getBackup()){
            firebase.remove(new FirebasePage(page), new FirebaseActionListenerCallback() {
                @Override
                public void onSuccess() {
                    page.delete();
                    List<Page> pages = new FlowCursorList<>(false, Page.class).getAll();
                    post(pages, page.getTitle());
                }
                @Override
                public void onError(FirebaseError error) {
                    post(error.getMessage());
                }
            });
        } else {
            page.delete();
            List<Page> pages = new FlowCursorList<>(false, Page.class).getAll();
            post(pages, page.getTitle());
        }
    }

    @Override
    public void logout() {
        firebase.logout();
    }

    private void post(List<Page> pages){
        post(pages, null, ListEvent.CONTENT_UPDATE);
    }

    private void post(String error){
        post(null, error, ListEvent.ON_REMOVE_FAILED);
    }

    private void post(List<Page> pages, String message){
        post(pages, message, ListEvent.ON_REMOVE_SUCCESSFULLY);
    }

    private void post(List<Page> pages, String message, int type){
        ListEvent event = new ListEvent();
        event.setMessage(message);
        event.setPages(pages);
        event.setType(type);
        eventBus.post(event);
    }
}