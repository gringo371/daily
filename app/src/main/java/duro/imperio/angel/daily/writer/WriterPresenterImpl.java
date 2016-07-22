package duro.imperio.angel.daily.writer;

import org.greenrobot.eventbus.Subscribe;

import duro.imperio.angel.daily.libs.base.EventBus;
import duro.imperio.angel.daily.writer.events.WriterEvent;
import duro.imperio.angel.daily.writer.ui.WriterView;

/**
 * Created by Angel on 14/7/2016.
 */
public class WriterPresenterImpl implements WriterPresenter {
    private WriterView view;
    private WriterStorageInteractor storageInteractor;
    private WriterSessionInteractor sessionInteractor;
    private EventBus eventBus;

    public WriterPresenterImpl(WriterView view, WriterStorageInteractor storageInteractor, WriterSessionInteractor sessionInteractor, EventBus eventBus) {
        this.view = view;
        this.storageInteractor = storageInteractor;
        this.sessionInteractor = sessionInteractor;
        this.eventBus = eventBus;
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
    public void savePage(String title, String date, String annotation, boolean backUp) {
        storageInteractor.savePage(title, date, annotation, backUp);
    }

    @Override
    public void getPage(String idPage) {
        storageInteractor.getPage(idPage);
    }

    @Override
    public void logout() {

    }

    @Subscribe
    @Override
    public void onMainEventThread(WriterEvent event) {
        if (view != null){
            switch (event.getType()){

                case WriterEvent.ON_SEND_MESSAGE:
                    view.showMessageSavePage();
                    break;

                case WriterEvent.ON_UPDATE_PAGE:
                    view.setPage(event.getPage());
            }
        }
    }
}
