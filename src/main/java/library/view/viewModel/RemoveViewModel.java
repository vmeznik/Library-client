package library.view.viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import library.service.IBookService;
import library.utility.Logger;

public class RemoveViewModel {
    private final StringProperty id;
    private final IBookService IBookService;

    public RemoveViewModel(IBookService IBookService) {
        this.IBookService = IBookService;
        this.id = new SimpleStringProperty();
    }


    public StringProperty idProperty() {
        return id;
    }

    public void setId() {
        IBookService.setId(Integer.parseInt(this.id.getValue()));
    }

    // maybe later I will add error to gui instead of print method :)))
    public void removeBook() {
        if (this.id.getValue() != null) {
            IBookService.removeBook();
            this.id.set(null);
        } else Logger.getInstance().log("RemoveViewModel - Error while removing  ,something is not set");
    }
}