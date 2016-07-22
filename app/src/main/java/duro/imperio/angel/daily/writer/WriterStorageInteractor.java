package duro.imperio.angel.daily.writer;

/**
 * Created by Angel on 14/7/2016.
 */
public interface WriterStorageInteractor {
    void savePage(String title, String date, String annotation, boolean backUp);
    void getPage(String idPage);
}
