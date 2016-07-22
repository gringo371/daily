package duro.imperio.angel.daily.login.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import duro.imperio.angel.daily.DailyApp;
import duro.imperio.angel.daily.R;
import duro.imperio.angel.daily.list.ui.ListActivity;
import duro.imperio.angel.daily.login.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @Bind(R.id.editTextEmail)
    EditText inputEmail;
    @Bind(R.id.editTxtPassword)
    EditText inputPassword;
    @Bind(R.id.btnSignin)
    Button btnSignin;
    @Bind(R.id.btnSignup)
    Button btnSignup;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.container)
    RelativeLayout container;

    @Inject
    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        setupInject();

        presenter.onCreate();
        presenter.getEmail();
    }

    private void setupInject() {
        DailyApp app = (DailyApp) getApplication();
        app.getLoginComponent(this).inject(this);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }


    @Override
    public void updateEmail(String email) {
        if (!email.equals("")) {
            inputEmail.setText(email);
            inputPassword.requestFocus();
        }
    }

    @Override
    public void enableInput() {
        setInput(true);
    }

    @Override
    public void disableInput() {
        setInput(false);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.btnSignup)
    @Override
    public void handleSingUp() {
        presenter.registerNewUser(
                inputEmail.getText().toString(),
                inputPassword.getText().toString()
        );
    }

    @OnClick(R.id.btnSignin)
    @Override
    public void handleSingIn() {
        presenter.validateLogin(
                inputEmail.getText().toString(),
                inputPassword.getText().toString()
        );
    }

    public void navigateToListScreen() {
        startActivity(new Intent(this, ListActivity.class));
    }


    @Override
    public void loginError(String error) {
        inputPassword.setText("");
        String msgError = String.format(getString(R.string.login_error_message_signin), error);
        inputPassword.setError(msgError);
    }

    @Override
    public void newUserSuccess() {
        Snackbar.make(container, R.string.login_notice_message_signup, Snackbar.LENGTH_SHORT);
    }

    @Override
    public void newUserError(String error) {
        inputPassword.setText("");
        String msgError = String.format(getString(R.string.login_error_message_signup), error);
        inputPassword.setError(msgError);
    }

    private void setInput(boolean enabled) {
        inputEmail.setEnabled(enabled);
        inputPassword.setEnabled(enabled);
        btnSignin.setEnabled(enabled);
        btnSignup.setEnabled(enabled);
    }


}
