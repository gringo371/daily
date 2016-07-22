package duro.imperio.angel.daily.list.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import duro.imperio.angel.daily.DailyApp;
import duro.imperio.angel.daily.R;
import duro.imperio.angel.daily.entities.Page;
import duro.imperio.angel.daily.list.ListPresenter;
import duro.imperio.angel.daily.list.ui.adapters.ListAdapter;
import duro.imperio.angel.daily.list.ui.adapters.OnItemClickListener;
import duro.imperio.angel.daily.login.ui.LoginActivity;
import duro.imperio.angel.daily.writer.ui.WriterActivity;

public class ListActivity extends AppCompatActivity implements ListView, OnItemClickListener {
    private final static String NEW_PAGE = "";

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.btnFab)
    FloatingActionButton fab;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.container)
    CoordinatorLayout container;

    @Inject
    ListPresenter presenter;
    @Inject
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        setupInject();
        setupAdapter();

        presenter.onCreate();
    }

    @Override
    protected void onStart() {
        presenter.getContent();
        super.onStart();
    }

    private void setupInject() {
        DailyApp app = (DailyApp) getApplication();
        app.getListComponent(this, this).inject(this);
    }

    private void setupAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout(){
        presenter.logout();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                |Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void setContentList(List<Page> pages) {
        adapter.setPages(pages);
    }

    @Override
    public void showRemoveMessage(String title) {
        Snackbar.make(container, String.format(getString(R.string.list_notice_message_remove), title), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showRemoveFailedMessage(String error) {
        Snackbar.make(container, String.format(getString(R.string.list_error_message_remove), error), Snackbar.LENGTH_LONG).show();
    }

    @OnClick(R.id.btnFab)
    public void onFabClick(){
        navigateToWriterActivity(NEW_PAGE);
    }

    @Override
    public void onClick(String pageId) {
        navigateToWriterActivity(pageId);
    }

    @Override
    public void onLongClick(Page page) {
        presenter.removePage(page);
    }

    private void navigateToWriterActivity(String pageId){
        Intent intent = new Intent(this, WriterActivity.class);
        intent.putExtra(DailyApp.PAGE_ID_NAME, pageId);
        startActivity(intent);
    }

}
