package duro.imperio.angel.daily.domain;

import com.firebase.client.FirebaseError;

/**
 * Created by Angel on 15/7/2016.
 */
public interface FirebaseActionListenerCallback {
    void onSuccess();
    void onError(FirebaseError error);
}
