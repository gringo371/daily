package duro.imperio.angel.daily.list;

/**
 * Created by Angel on 18/7/2016.
 */
public class ListSessionInteractorImpl implements ListSessionInteractor {
    private ListRepository repository;

    public ListSessionInteractorImpl(ListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void logout() {
        repository.logout();
    }
}
