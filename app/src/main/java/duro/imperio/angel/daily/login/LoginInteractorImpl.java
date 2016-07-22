package duro.imperio.angel.daily.login;

/**
 * Created by Angel on 16/7/2016.
 */
public class LoginInteractorImpl implements LoginInteractor{
    private LoginRepository loginRepository;

    public LoginInteractorImpl(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public void getEmail() {
        loginRepository.getEmail();
    }

    @Override
    public void doSingUp(String email, String password) {
        loginRepository.signUp(email, password);
    }

    @Override
    public void singIn(String email, String password) {
        loginRepository.singIn(email, password);
    }
}
