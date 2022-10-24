package library.view.viewModel;

import library.model.Book;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import library.service.IBookService;
import library.utility.Logger;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class SearchViewModel implements PropertyChangeListener {
    private final StringProperty title;
    private final StringProperty genre;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final ListProperty<Book> list;
    private boolean onlyOpen;
    private final IBookService iBookService;

    public SearchViewModel(IBookService iBookService) {
        this.iBookService = iBookService;
        this.title = new SimpleStringProperty();
        this.genre = new SimpleStringProperty();
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.list = new SimpleListProperty<>();
        iBookService.addListener(this);
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

    public ListProperty<Book> bookList() {
        return list;
    }

    public void setTitle() {
        if (this.title.getValue() != null) {
            this.title.setValue(this.title.getValue().toLowerCase());
        }
        iBookService.setTitle(this.title.getValue());
    }

    public void setGenre() {
        if (this.genre.getValue() != null) {
            this.genre.setValue(this.genre.getValue().toLowerCase());
        }
        iBookService.setGenre(this.genre.getValue());
    }

    public void setFirstName() {
        if (this.firstName.getValue() != null) {
            this.firstName.setValue(this.firstName.getValue().toLowerCase());
        }
        iBookService.setFirstName(this.firstName.getValue());
    }

    public void setLastName() {
        if (this.lastName.getValue() != null) {
            this.lastName.setValue(this.lastName.getValue().toLowerCase());
        }
        iBookService.setLastName(this.lastName.getValue());
    }

    public void setOnlyOpen() {
        if (this.onlyOpen) {
            this.onlyOpen = false;
        } else this.onlyOpen = true;
        iBookService.setOnlyOpen(this.onlyOpen);
    }


    // maybe later I will add error to gui instead of print method :)))
    public void searchBook() {
        if (this.title.getValue() != null || this.genre.getValue() != null ||
                this.firstName.getValue() != null || this.lastName.getValue() != null) {
            iBookService.searchBook();
            try {
                this.title.set(null);
                this.genre.set(null);
                this.firstName.set(null);
                this.lastName.set(null);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        } else Logger.getInstance().log("SearchViewModel - Error while searching  ,something is not set");
    }

    public void searchAllBooks() {
        iBookService.searchAllBooks();
    }

    public void removeBook(Book book) {
        iBookService.setId(book.getId());
        iBookService.removeBook();
        iBookService.searchAllBooks();
    }

    public void returnBook(Book book) {
        iBookService.setId(book.getId());
        iBookService.returnBook();
        iBookService.searchAllBooks();
    }

    public void borrowBook(Book book) {
        iBookService.setId(book.getId());
        iBookService.borrowBook();
        iBookService.searchAllBooks();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            this.list.set(FXCollections.observableList((ArrayList<Book>) evt.getNewValue()));
        });

    }
}