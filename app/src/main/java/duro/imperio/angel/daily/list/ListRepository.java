package duro.imperio.angel.daily.list;

import duro.imperio.angel.daily.entities.Page;

/**
 * Created by Angel on 18/7/2016.
 */
public interface ListRepository{
    void getContent();
    void removePage(Page page);

    void logout();
}
