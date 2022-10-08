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

public class SearchViewModel implements PropertyChangeListener {
    private final StringProperty title;
    private final StringProperty genre;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final ListProperty<Book> list;
    private boolean onlyOpen;
    private final IBookService IBookService;

    public SearchViewModel(IBookService IBookService) {
        this.IBookService = IBookService;
        this.title = new SimpleStringProperty();
        this.genre = new SimpleStringProperty();
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.list = new SimpleListProperty<>();
        IBookService.addListener(this);
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
        IBookService.setTitle(this.title.getValue());
    }

    public void setGenre() {
        if (this.genre.getValue() != null) {
            this.genre.setValue(this.genre.getValue().toLowerCase());
        }
        IBookService.setGenre(this.genre.getValue());
    }

    public void setFirstName() {
        if (this.firstName.getValue() != null) {
            this.firstName.setValue(this.firstName.getValue().toLowerCase());
        }
        IBookService.setFirstName(this.firstName.getValue());
    }

    public void setLastName() {
        if (this.lastName.getValue() != null) {
            this.lastName.setValue(this.lastName.getValue().toLowerCase());
        }
        IBookService.setLastName(this.lastName.getValue());
    }

    public void setOnlyOpen() {
        if (this.onlyOpen) {
            this.onlyOpen = false;
        } else this.onlyOpen = true;
        IBookService.setOnlyOpen(this.onlyOpen);
    }


    // maybe later I will add error to gui instead of print method :)))
    public void searchBook() {
        if (this.title.getValue() != null || this.genre.getValue() != null ||
                this.firstName.getValue() != null || this.lastName.getValue() != null) {
            IBookService.searchBook();
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
        IBookService.searchAllBooks();
    }

    public void removeBook(Book book) {
        IBookService.setId(book.getId());
        IBookService.removeBook();
        IBookService.searchAllBooks();
    }

    public void returnBook(Book book) {
        IBookService.setId(book.getId());
        IBookService.returnBook();
        IBookService.searchAllBooks();
    }

    public void borrowBook(Book book) {
        IBookService.setId(book.getId());
        IBookService.borrowBook();
        IBookService.searchAllBooks();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            this.list.set(FXCollections.observableList((ArrayList<Book>) evt.getNewValue()));
        });

    }
}