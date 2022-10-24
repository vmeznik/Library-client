package library.view.viewModel;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import library.service.IBookService;
import library.utility.Logger;

public class BorrowViewModel {
    private final StringProperty id;
    private final IBookService iBookService;

    public BorrowViewModel(IBookService iBookService) {
        this.iBookService = iBookService;
        this.id = new SimpleStringProperty();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId() {
        iBookService.setId(Integer.parseInt(this.id.getValue()));
    }

    public void borrowBook() {
        if (this.id.getValue() != null) {
            iBookService.borrowBook();
            this.id.set(null);
        } else Logger.getInstance().log("BorrowViewModel - Error while changing book status  ,something is not set");
    }

    public void returnBook() {
        if (this.id.getValue() != null) {
            iBookService.returnBook();
            this.id.set(null);
        } else Logger.getInstance().log("BorrowViewModel - Error while changing book status  ,something is not set");
    }
}
