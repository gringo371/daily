package duro.imperio.angel.daily.writer;

/**
 * Created by Angel on 14/7/2016.
 */
public class WriterInteractorImpl implements WriterStorageInteractor {
    private WriterRepository repository;

    public WriterInteractorImpl(WriterRepository repository) {
        this.repository = repository;
    }

    @Override
    public void savePage(String title, String date, String annotation, boolean backUp) {
        repository.savePage(title, date, annotation, backUp);
    }

    @Override
    public void getPage(String idPage) {
        repository.getPage(idPage);
    }
}
