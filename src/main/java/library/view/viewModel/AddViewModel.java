package library.view.viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import library.service.IBookService;
import library.utility.Logger;

public class AddViewModel {
    private final StringProperty title;
    private final StringProperty genre;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final IBookService IBookService;

    public AddViewModel(IBookService IBookService) {
        this.IBookService = IBookService;
        this.title = new SimpleStringProperty();
        this.genre = new SimpleStringProperty();
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
    }


    public StringProperty titleProperty() {
        return title;
    }


    public StringProperty genreProperty() {
        return genre;
    }


    public StringProperty firstNameProperty() {
        return firstName;
    }


    public StringProperty lastNameProperty() {
        return lastName;
    }


    public void setTitle() {
        if (this.title.getValue() != null) {
            this.title.setValue(this.title.getValue().toLowerCase());
            IBookService.setTitle(this.title.getValue());
        }
    }

    public void setGenre() {
        if (this.genre.getValue() != null) {
            this.genre.setValue(this.genre.getValue().toLowerCase());
            IBookService.setGenre(this.genre.getValue());
        }
    }

    public void setFirstName() {
        if (this.firstName.getValue() != null) {
            this.firstName.setValue(this.firstName.getValue().toLowerCase());
            IBookService.setFirstName(this.firstName.getValue());
        }
    }

    public void setLastName() {
        if (this.lastName.getValue() != null) {
            this.lastName.setValue(this.lastName.getValue().toLowerCase());
            IBookService.setLastName(this.lastName.getValue());
        }
    }

    // maybe later I will add error to gui instead of print method :)))
    public void addBook() {
        if (this.title.getValue() != null && this.genre.getValue() != null &&
                this.firstName.getValue() != null && this.lastName.getValue() != null) {
            IBookService.addBook();
            this.title.set(null);
            this.genre.set(null);
            this.firstName.set(null);
            this.lastName.set(null);
        } else Logger.getInstance().log("AddViewModel - Error while adding something is not set");
    }
}