package library.view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import library.view.ViewHandler;
import library.view.viewModel.RemoveViewModel;


public class RemoveController {
    @FXML
    private TextField id;
    private Region root;
    private ViewHandler viewHandler;
    private RemoveViewModel removeViewModel;

    public void init(ViewHandler viewHandler, RemoveViewModel removeViewModel, Region root) {
        this.removeViewModel = removeViewModel;
        this.root = root;
        this.viewHandler = viewHandler;
        this.id.textProperty().bindBidirectional(removeViewModel.idProperty());
    }

    public Region getRoot() {
        return root;
    }

    //might change it here
    @FXML
    private void removeBook() {
        removeViewModel.setId();
        removeViewModel.removeBook();
        viewHandler.openView("menu");
    }

    @FXML
    private void onEnter() {
        removeBook();
    }

    @FXML
    private void back() {
        viewHandler.openView("menu");
    }
}