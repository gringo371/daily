package duro.imperio.angel.daily.domain;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

import duro.imperio.angel.daily.entities.FirebasePage;
import duro.imperio.angel.daily.entities.Page;

/**
 * Created by Angel on 15/7/2016.
 */
public class FirebaseAPI {

    private final static String EMAIL_PATH = "email";
    private final static String USERS_PATH = "users";
    private final static String PAGE_PATH = "page";

    private Firebase firebase;

    public FirebaseAPI(Firebase firebase) {
        this.firebase = firebase;
    }

    public void logout(){
        firebase.unauth();
    }

    public void checkForData(final FirebaseActionListenerCallback listenerCallback){
        firebase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() > 0){
                    listenerCallback.onSuccess();
                } else {
                    listenerCallback.onError(null);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                listenerCallback.onError(firebaseError);
            }
        });
    }

    public void login(String email, String password, final FirebaseActionListenerCallback listenerCallback){
        firebase.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                listenerCallback.onSuccess();
            }
            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                listenerCallback.onError(firebaseError);
            }
        });
    }

    public void singUp(final String email, String password, final FirebaseActionListenerCallback listenerCallback){
        firebase.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> stringObjectMap) {
                listenerCallback.onSuccess();
            }
            @Override
            public void onError(FirebaseError firebaseError) {
                listenerCallback.onError(firebaseError);
            }
        });
    }

    private String toValidateKey(String key){
        return key.replace(".", "_");
    }

    public String generateKey(){
        return firebase.push().getKey();
    }

    private String getAuthUserEmail(){
        AuthData authData = firebase.getAuth();
        String email = null;
        if (authData != null){
            Map<String, Object> providerData = authData.getProviderData();
            email = providerData.get(EMAIL_PATH).toString();
        }
        return email;
    }


    private Firebase getUserPagesReference(){
        return this.firebase.getRoot().child(USERS_PATH).child(toValidateKey(getAuthUserEmail())).child(PAGE_PATH);
    }

    public void savePage(FirebasePage page){
        getUserPagesReference().child(page.getPageId()).setValue(page);
    }

    public void remove(FirebasePage page, FirebaseActionListenerCallback listenerCallback){
        getUserPagesReference().child(page.getPageId()).removeValue();
        listenerCallback.onSuccess();
    }



}
