package duro.imperio.angel.daily;

import android.app.Application;

import com.firebase.client.Firebase;
import com.raizlabs.android.dbflow.config.FlowManager;

import duro.imperio.angel.daily.domain.di.DomainModule;
import duro.imperio.angel.daily.libs.di.LibsModule;
import duro.imperio.angel.daily.list.di.DaggerListComponent;
import duro.imperio.angel.daily.list.di.ListComponent;
import duro.imperio.angel.daily.list.di.ListModule;
import duro.imperio.angel.daily.list.ui.ListView;
import duro.imperio.angel.daily.list.ui.adapters.OnItemClickListener;
import duro.imperio.angel.daily.login.di.DaggerLoginComponent;
import duro.imperio.angel.daily.login.di.LoginComponent;
import duro.imperio.angel.daily.login.di.LoginModule;
import duro.imperio.angel.daily.login.ui.LoginView;
import duro.imperio.angel.daily.writer.di.DaggerWriterComponent;
import duro.imperio.angel.daily.writer.di.WriterComponent;
import duro.imperio.angel.daily.writer.di.WriterModule;
import duro.imperio.angel.daily.writer.ui.WriterView;

/**
 * Created by Angel on 14/7/2016.
 */
public class DailyApp extends Application{
    public final static String PAGE_ID_NAME = "id";

    private final static String FIREBASE_URL = "https://example-daily-angel.firebaseio.com";
    private final static String USER_PREFERENCE_NAME = "UserPreference";
    private final static String USER_EMAIL_NAME = "email";


    private DomainModule domainModule;
    private LibsModule libsModule;
    private DailyModule dailyModule;

    @Override
    public void onCreate() {
        super.onCreate();
        initFirebase();
        initDB();
        initModules();
    }

    private void initFirebase() {
        Firebase.setAndroidContext(this);
    }

    private void initDB() {
        FlowManager.init(this);
    }

    private void initModules() {
        domainModule = new DomainModule(FIREBASE_URL);
        dailyModule = new DailyModule(this);
        libsModule = new LibsModule();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        DBTearDown();
    }

    private void DBTearDown() {
        FlowManager.destroy();
    }

    public static String getUserPreferenceName() {
        return USER_PREFERENCE_NAME;
    }

    public static String getUserEmailName() {
        return USER_EMAIL_NAME;
    }

    public LoginComponent getLoginComponent(LoginView view){
        return DaggerLoginComponent
                .builder()
                .libsModule(libsModule)
                .domainModule(domainModule)
                .dailyModule(dailyModule)
                .loginModule(new LoginModule(view))
                .build();
    }

    public ListComponent getListComponent(ListView view, OnItemClickListener itemClickListener){
        return DaggerListComponent
                .builder()
                .libsModule(libsModule)
                .domainModule(domainModule)
                .listModule(new ListModule(view, itemClickListener))
                .build();
    }

    public WriterComponent getWriterComponent(WriterView view){
        return DaggerWriterComponent
                .builder()
                .libsModule(libsModule)
                .domainModule(domainModule)
                .writerModule(new WriterModule(view))
                .build();
    }

}
