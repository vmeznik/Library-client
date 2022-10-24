package library.view.viewModel;

import library.service.IBookService;

public class ViewModelFactory {
    private final AddViewModel addViewModel;
    private final BorrowViewModel borrowViewModel;
    private final RemoveViewModel removeViewModel;
    private final SearchViewModel searchViewModel;

    public ViewModelFactory(IBookService iBookService) {
        this.addViewModel = new AddViewModel(iBookService);
        this.borrowViewModel = new BorrowViewModel(iBookService);
        this.removeViewModel = new RemoveViewModel(iBookService);
        this.searchViewModel = new SearchViewModel(iBookService);
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
