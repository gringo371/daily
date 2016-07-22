package duro.imperio.angel.daily.writer.events;

import duro.imperio.angel.daily.entities.Page;

/**
 * Created by Angel on 14/7/2016.
 */
public class WriterEvent {
    public final static int ON_UPDATE_PAGE = 1;
    public final static int ON_SEND_MESSAGE = 2;

    private Page page;
    private int type;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}