package library.view.viewModel;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import library.service.IBookService;
import library.utility.Logger;

public class BorrowViewModel {
    private final StringProperty id;
    private final IBookService IBookService;

    public BorrowViewModel(IBookService IBookService) {
        this.IBookService = IBookService;
        this.id = new SimpleStringProperty();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId() {
        IBookService.setId(Integer.parseInt(this.id.getValue()));
    }

    public void borrowBook() {
        if (this.id.getValue() != null) {
            IBookService.borrowBook();
            this.id.set(null);
        } else Logger.getInstance().log("BorrowViewModel - Error while changing book status  ,something is not set");
    }

    public void returnBook() {
        if (this.id.getValue() != null) {
            IBookService.returnBook();
            this.id.set(null);
        } else Logger.getInstance().log("BorrowViewModel - Error while changing book status  ,something is not set");
    }
}
