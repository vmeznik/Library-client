package library.view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import library.view.ViewHandler;
import library.view.viewModel.BorrowViewModel;

public class BorrowController {
    @FXML
    private TextField id;
    private Region root;
    private ViewHandler viewHandler;
    private BorrowViewModel borrowViewModel;

    public void init(ViewHandler viewHandler, BorrowViewModel borrowViewModel, Region root) {
        this.borrowViewModel = borrowViewModel;
        this.root = root;
        this.viewHandler = viewHandler;
        this.id.textProperty().bindBidirectional(borrowViewModel.idProperty());
    }

    public Region getRoot() {
        return root;
    }

    //might change it here
    @FXML
    private void borrow() {
        borrowViewModel.setId();
        borrowViewModel.borrowBook();
        viewHandler.openView("menu");
    }

    @FXML
    private void returnBook() {
        borrowViewModel.setId();
        borrowViewModel.returnBook();
        viewHandler.openView("menu");
    }

    @FXML
    private void back() {
        viewHandler.openView("menu");
    }
}
