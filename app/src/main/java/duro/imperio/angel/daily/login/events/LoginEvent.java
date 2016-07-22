package duro.imperio.angel.daily.login.events;

/**
 * Created by Angel on 16/7/2016.
 */
public class LoginEvent {
    public final static int ON_SIGN_IN_ERROR = 0;
    public final static int ON_SIGN_UP_ERROR = 1;
    public final static int ON_SIGN_IN_SUCCESS = 2;
    public final static int ON_SIGN_UP_SUCCESS = 3;
    public final static int ON_FAIL_TO_RECOVER_SESSION = 4;
    public final static int UPDATE_EMAIL = 4;

    private int type;
    private String message;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

