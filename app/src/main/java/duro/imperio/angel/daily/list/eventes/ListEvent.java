package duro.imperio.angel.daily.list.eventes;

import java.util.List;

import duro.imperio.angel.daily.entities.Page;

/**
 * Created by Angel on 18/7/2016.
 */
public class ListEvent {
    public final static int ON_REMOVE_FAILED = 1;
    public final static int ON_REMOVE_SUCCESSFULLY = 2;
    public final static int CONTENT_UPDATE = 4;

    private List<Page> pages;
    private String message;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }
}
