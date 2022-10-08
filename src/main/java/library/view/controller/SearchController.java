package library.view.controller;

import library.model.Book;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import library.view.ViewHandler;
import library.view.viewModel.SearchViewModel;

public class SearchController {
    @FXML
    private TextField title;
    @FXML
    private TextField genre;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private ListView<Book> listView;
    @FXML
    private Button removeButton;
    @FXML
    private Button returnButton;
    @FXML
    private Button borrowButton;

    private Book selectedBook;
    private Region root;
    private ViewHandler viewHandler;
    private SearchViewModel searchViewModel;

    public void init(ViewHandler viewHandler, SearchViewModel searchViewModel, Region root) {
        this.searchViewModel = searchViewModel;
        this.root = root;
        this.viewHandler = viewHandler;
        this.title.textProperty().bindBidirectional(searchViewModel.titleProperty());
        this.genre.textProperty().bindBidirectional(searchViewModel.genreProperty());
        this.firstName.textProperty().bindBidirectional(searchViewModel.firstNameProperty());
        this.lastName.textProperty().bindBidirectional(searchViewModel.lastNameProperty());
        listView.itemsProperty().bindBidirectional(searchViewModel.bookList());
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Book> observableValue, Book book, Book t1) {
                returnButton.setVisible(true);
                removeButton.setVisible(true);
                borrowButton.setVisible(true);

                selectedBook = listView.getSelectionModel().getSelectedItem();
            }
        });

    }

    public Region getRoot() {
        return root;
    }

    //might change it here
    @FXML
    private void searchBook() {
        searchViewModel.setTitle();
        searchViewModel.setGenre();
        searchViewModel.setFirstName();
        searchViewModel.setLastName();
        searchViewModel.searchBook();
    }

    @FXML
    private void allBooks() {
        searchViewModel.searchAllBooks();
    }

    @FXML
    private void back() {
        viewHandler.openView("menu");
    }

    @FXML
    private void openOnly() {
        searchViewModel.setOnlyOpen();
    }


    @FXML
    private void onEnterTitle() {
        searchBook();
    }

    @FXML
    private void onEnterGenre() {
        searchBook();
    }

    @FXML
    private void onEnterFirstName() {
        searchBook();
    }

    @FXML
    private void onEnterLastName() {
        searchBook();
    }

    @FXML
    private void onBorrowClick() {
        searchViewModel.borrowBook(selectedBook);
    }

    @FXML
    private void onReturnClick() {
        searchViewModel.returnBook(selectedBook);
    }

    @FXML
    private void onRemoveClick() {
        searchViewModel.removeBook(selectedBook);
    }


}