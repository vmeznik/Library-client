package library.view.viewModel;

import library.service.IBookService;

public class ViewModelFactory {
    private final AddViewModel addViewModel;
    private final BorrowViewModel borrowViewModel;
    private final RemoveViewModel removeViewModel;
    private final SearchViewModel searchViewModel;

    public ViewModelFactory(IBookService IBookService) {
        this.addViewModel = new AddViewModel(IBookService);
        this.borrowViewModel = new BorrowViewModel(IBookService);
        this.removeViewModel = new RemoveViewModel(IBookService);
        this.searchViewModel = new SearchViewModel(IBookService);
    }

    public AddViewModel getAddViewModel() {
        return addViewModel;
    }

    public BorrowViewModel getBorrowViewModel() {
        return borrowViewModel;
    }

    public RemoveViewModel getRemoveViewModel() {
        return removeViewModel;
    }

    public SearchViewModel getSearchViewModel() {
        return searchViewModel;
    }
}
