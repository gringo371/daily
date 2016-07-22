package duro.imperio.angel.daily.writer;

import duro.imperio.angel.daily.writer.events.WriterEvent;

/**
 * Created by Angel on 14/7/2016.
 */
public interface WriterPresenter {
    void onCreate();
    void onDestroy();

    void savePage(String title, String date, String annotation, boolean backUp);
    void getPage(String idPage);

    void logout();

    void onMainEventThread(WriterEvent event);
}
