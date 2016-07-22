package duro.imperio.angel.daily.login;

/**
 * Created by Angel on 16/7/2016.
 */
public interface LoginInteractor {
    void getEmail();
    void doSingUp(String email, String password);
    void singIn(String email, String password);
}
