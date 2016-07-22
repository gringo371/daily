package duro.imperio.angel.daily.list.ui;

import java.util.List;

import duro.imperio.angel.daily.entities.Page;

/**
 * Created by Angel on 18/7/2016.
 */
public interface ListView {
    void setContentList(List<Page> pages);

    void showRemoveMessage(String title);
    void showRemoveFailedMessage(String error);
}
