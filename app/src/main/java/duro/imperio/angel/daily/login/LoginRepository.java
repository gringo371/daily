package duro.imperio.angel.daily.login;

/**
 * Created by Angel on 16/7/2016.
 */
public interface LoginRepository {
    void getEmail();
    void signUp(final String email, final String password);
    void singIn(String email, String password);
}
