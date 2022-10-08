package library.view.controller;


import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import library.view.ViewHandler;


public class MenuController {
    private Region root;
    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler, Region root) {
        this.viewHandler = viewHandler;
        this.root = root;
    }

    public Region getRoot() {
        return root;
    }

    @FXML
    private void searchButton() {
        viewHandler.openView("search");
    }

    @FXML
    private void addButton() {
        viewHandler.openView("add");
    }

    @FXML
    private void removeButton() {
        viewHandler.openView("remove");
    }

    @FXML
    private void borrowButton() {
        viewHandler.openView("borrow");
    }

}