package duro.imperio.angel.daily.list;

import duro.imperio.angel.daily.entities.Page;

/**
 * Created by Angel on 18/7/2016.
 */
public class ListContentInteractorImpl implements ListContentInteractor {
    private ListRepository repository;

    public ListContentInteractorImpl(ListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getContent() {
        repository.getContent();
    }

    @Override
    public void removePage(Page page) {
        repository.removePage(page);
    }
}
