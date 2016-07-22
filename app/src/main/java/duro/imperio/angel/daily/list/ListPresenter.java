package duro.imperio.angel.daily.list;

import duro.imperio.angel.daily.entities.Page;
import duro.imperio.angel.daily.list.eventes.ListEvent;

/**
 * Created by Angel on 18/7/2016.
 */
public interface ListPresenter {
    void onCreate();
    void onDestroy();

    void getContent();
    void removePage(Page page);

    void logout();

    void onMainThreadEvent(ListEvent event);
}
