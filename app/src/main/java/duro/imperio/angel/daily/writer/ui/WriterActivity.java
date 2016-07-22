package duro.imperio.angel.daily.writer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import duro.imperio.angel.daily.DailyApp;
import duro.imperio.angel.daily.R;
import duro.imperio.angel.daily.entities.Page;
import duro.imperio.angel.daily.login.ui.LoginActivity;
import duro.imperio.angel.daily.writer.WriterPresenter;

public class WriterActivity extends AppCompatActivity implements WriterView {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.editTextTitle)
    EditText txtTitle;
    @Bind(R.id.editTextDate)
    EditText txtDate;
    @Bind(R.id.editTextAnnotation)
    EditText txtAnnotation;
    @Bind(R.id.container)
    CoordinatorLayout container;

    @Inject
    WriterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        setupInjection();
        String id = getIntent().getStringExtra(DailyApp.PAGE_ID_NAME);

        presenter.onCreate();
        presenter.getPage(id);
    }

    private void setupInjection() {
        DailyApp app = (DailyApp) getApplication();
        app.getWriterComponent(this).inject(this);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        savePage(Page.NO_SAVED_FOR_USER);
        super.onPause();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_logout) {
            logout();
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        presenter.logout();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_writer, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void showMessageSavePage() {
        Snackbar.make(container, getString(R.string.write_notice_save), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setPage(Page page) {
        txtTitle.setText(page.getTitle());
        txtDate.setText(page.getDate());
        txtAnnotation.setText(page.getAnnotation());
    }

    @OnClick(R.id.btnFab)
    public void savePage() {
        savePage(Page.SAVED_FOR_USER);
    }

    private void savePage(boolean backup) {
        presenter.savePage(txtTitle.getText().toString(),
                txtDate.getText().toString(),
                txtAnnotation.getText().toString(),
                backup
        );
    }


}
