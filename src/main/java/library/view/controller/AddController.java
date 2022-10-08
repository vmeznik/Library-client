package library.view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import library.view.ViewHandler;
import library.view.viewModel.AddViewModel;

public class AddController {
    @FXML
    private TextField title;
    @FXML
    private TextField genre;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    private Region root;
    private ViewHandler viewHandler;
    private AddViewModel addViewModel;

    public void init(ViewHandler viewHandler, AddViewModel addViewModel, Region root) {
        this.addViewModel = addViewModel;
        this.root = root;
        this.viewHandler = viewHandler;
        this.title.textProperty().bindBidirectional(addViewModel.titleProperty());
        this.genre.textProperty().bindBidirectional(addViewModel.genreProperty());
        this.firstName.textProperty().bindBidirectional(addViewModel.firstNameProperty());
        this.lastName.textProperty().bindBidirectional(addViewModel.lastNameProperty());
    }

    public Region getRoot() {
        return root;
    }

    //might change it here
    @FXML
    private void add() {
        addViewModel.setTitle();
        addViewModel.setGenre();
        addViewModel.setFirstName();
        addViewModel.setLastName();
        addViewModel.addBook();
        viewHandler.openView("menu");
    }

    @FXML
    private void onEnter() {
        add();
    }

    @FXML
    private void back() {
        viewHandler.openView("menu");
    }
}
