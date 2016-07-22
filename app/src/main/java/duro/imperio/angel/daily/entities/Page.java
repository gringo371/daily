package duro.imperio.angel.daily.entities;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import duro.imperio.angel.daily.db.PageDatabase;

/**
 * Created by Angel on 14/7/2016.
 */
@Table(database = PageDatabase.class)
public class Page extends BaseModel {

    public final static boolean SAVED_IN_FIREBASE = true;
    public final static boolean NO_SAVED_IN_FIREBASE = false;

    public final static boolean SAVED_FOR_USER = true;
    public final static boolean NO_SAVED_FOR_USER = false;

    @PrimaryKey
    @Column private String page_id;

    @Column private String title;
    @Column private String date;
    @Column private String annotation;

    @Column private boolean backup;
    @Column private boolean saved;

    public Page() {
        backup = NO_SAVED_IN_FIREBASE;
        saved = NO_SAVED_FOR_USER;
    }

    public Page(FirebasePage firebasePage) {
        annotation = firebasePage.getAnnotation();
        page_id = firebasePage.getPageId();
        title = firebasePage.getTitle();
        date = firebasePage.getDate();
        backup = SAVED_IN_FIREBASE;
        saved = SAVED_FOR_USER;
    }

    public String getPage_id() {
        return page_id;
    }

    public void setPage_id(String page_id) {
        this.page_id = page_id;
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

    public boolean getBackup() {
        return backup;
    }

    public void setBackup(boolean backup) {
        this.backup = backup;
    }

    public boolean getSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    @Override
    public boolean equals(Object object){
        boolean equal = false;
        if (object instanceof Page){
            Page page = (Page) object;
            equal = page.getPage_id().equals(this.page_id);
        }
        return equal;
    }

}