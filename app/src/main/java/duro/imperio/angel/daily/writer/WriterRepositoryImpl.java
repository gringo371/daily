package duro.imperio.angel.daily.writer;

import com.raizlabs.android.dbflow.list.FlowCursorList;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import duro.imperio.angel.daily.domain.FirebaseAPI;
import duro.imperio.angel.daily.entities.FirebasePage;
import duro.imperio.angel.daily.entities.Page;
import duro.imperio.angel.daily.libs.base.EventBus;
import duro.imperio.angel.daily.writer.events.WriterEvent;

/**
 * Created by Angel on 14/7/2016.
 */
public class WriterRepositoryImpl implements WriterRepository {
    private FirebaseAPI firebase;
    private EventBus eventBus;
    private Page currentPage;

    public WriterRepositoryImpl(FirebaseAPI firebase, EventBus eventBus, Page page) {
        this.eventBus = eventBus;
        this.firebase = firebase;
        currentPage = page;
    }

    @Override
    public void savePage(String title, String date, String annotation, boolean backUp) {

        if (backUp){
            updateCurrentPage(title, date, annotation, backUp);
            currentPage.setBackup(Page.SAVED_IN_FIREBASE);
            FirebasePage firebasePage = new FirebasePage(currentPage);
            firebase.savePage(firebasePage);
            currentPage.save();
            post();

        } else
        try {
            if (!(currentPage.getTitle().equals(title)
                       && currentPage.getDate().equals(date)
                       && currentPage.getAnnotation().equals(annotation))) {
                updateCurrentPage(title, date, annotation, backUp);

                saveEraser(title, date, annotation, backUp);
            }
        } catch (NullPointerException e){
            saveEraser(title, date, annotation, backUp);
        }
    }

    private void saveEraser (String title, String date, String annotation, boolean backUp){
        updateCurrentPage(title, date, annotation, backUp);
        currentPage.save();
    }

    @Override
    public void getPage(String idPage) {

        if (idPage.equals("")){
            currentPage.setPage_id(firebase.generateKey());

            currentPage.setDate(getDate());
        } else {
            List<Page> pages = new FlowCursorList<>(false, Page.class).getAll();
            currentPage.setPage_id(idPage);
            currentPage = pages.get(pages.indexOf(currentPage));
        }
        post(currentPage);
    }

    private String getDate(){
        return SimpleDateFormat.getDateInstance().format(new Date());
    }

    @Override
    public void logout() {
        firebase.logout();
    }

    private void updateCurrentPage(String title, String date, String annotation, boolean backUp){

        if (date.equals("")){
            date = getDate();
        }

        if (title.equals("")){
            title = date;
        }

        currentPage.setAnnotation(annotation);
        currentPage.setTitle(title);
        currentPage.setSaved(backUp);
        currentPage.setDate(date);
    }

    private void post(Page page){
        post(page, WriterEvent.ON_UPDATE_PAGE);
    }

    private void post(){
        post(null, WriterEvent.ON_SEND_MESSAGE);
    }

    private void post(Page page, int type){
        WriterEvent event = new WriterEvent();
        event.setPage(page);
        event.setType(type);
        eventBus.post(event);
    }

}