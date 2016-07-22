package duro.imperio.angel.daily.writer;

/**
 * Created by Angel on 18/7/2016.
 */
public class WriterSessionInteractorImpl implements WriterSessionInteractor {
    private WriterRepository repository;

    public WriterSessionInteractorImpl(WriterRepository repository) {
        this.repository = repository;
    }

    @Override
    public void logout() {
        repository.logout();
    }
}
