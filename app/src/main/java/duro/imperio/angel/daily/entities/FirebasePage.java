package duro.imperio.angel.daily.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by Angel on 17/7/2016.
 */
public class FirebasePage {

    @JsonIgnore
    private String pageId;
    private String title;
    private String date;
    private String annotation;

    public FirebasePage() {}

    public FirebasePage(Page page) {
        annotation = page.getAnnotation();
        pageId = page.getPage_id();
        title = page.getTitle();
        date = page.getDate();
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean equals(Object object){
        boolean equal = false;
        if (object instanceof FirebasePage){
            FirebasePage page = (FirebasePage) object;
            equal = page.getPageId().equals(this.pageId);
        }
        return equal;
    }
}
