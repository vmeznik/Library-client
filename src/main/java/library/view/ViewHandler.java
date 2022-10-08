package library.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import library.view.controller.*;
import library.view.viewModel.ViewModelFactory;

import java.io.IOException;
import java.net.URL;

public class ViewHandler {
    private Stage primaryStage;
    private Scene currentScene;
    private MenuController menuController;
    private AddController addController;
    private BorrowController borrowController;
    private RemoveController removeController;
    private SearchController searchController;
    private final ViewModelFactory viewModelFactory;
    private Region root = null;

    public ViewHandler(ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
        currentScene = new Scene(new Region());
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        currentScene = new Scene(new Region());
        openView("menu");

    }

    public void openView(String id) {
        switch (id) {
            case "add" -> root = loadAddView("/Add.fxml");
            case "search" -> root = loadSearchView("/Search.fxml");
            case "remove" -> root = loadRemoveView("/Remove.fxml");
            case "borrow" -> root = loadBorrowView("/Borrow.fxml");
            default -> root = loadMenuView("/Menu.fxml");
        }
        currentScene.setRoot(root);

        String title = "";
        if (root.getUserData() != null) {
            title += root.getUserData();
        }
        primaryStage.setTitle(title);
        primaryStage.setScene(currentScene);
        primaryStage.setWidth(root.getPrefWidth());
        primaryStage.setHeight(root.getPrefHeight());
        primaryStage.show();

    }

    private Region loadMenuView(String fxmlFile) {
        if (menuController == null) {
            try {
                URL fxmlLocation = MenuController.class.getResource(fxmlFile);
                FXMLLoader loader = new FXMLLoader(fxmlLocation);
                root = loader.load();
                menuController = loader.getController();
                menuController.init(this, root);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
        return menuController.getRoot();
    }

    private Region loadAddView(String fxmlFile) {
        if (addController == null) {
            try {
                URL fxmlLocation = AddController.class.getResource(fxmlFile);
                FXMLLoader loader = new FXMLLoader(fxmlLocation);
                root = loader.load();
                addController = loader.getController();
                addController.init(this, viewModelFactory.getAddViewModel(), root);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
        return addController.getRoot();
    }

    private Region loadSearchView(String fxmlFile) {
        if (searchController == null) {
            try {
                URL fxmlLocation = SearchController.class.getResource(fxmlFile);
                FXMLLoader loader = new FXMLLoader(fxmlLocation);
                root = loader.load();
                searchController = loader.getController();
                searchController.init(this, viewModelFactory.getSearchViewModel(), root);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
        return searchController.getRoot();
    }

    private Region loadRemoveView(String fxmlFile) {
        if (removeController == null) {
            try {
                URL fxmlLocation = RemoveController.class.getResource(fxmlFile);
                FXMLLoader loader = new FXMLLoader(fxmlLocation);
                root = loader.load();
                removeController = loader.getController();
                removeController.init(this, viewModelFactory.getRemoveViewModel(), root);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
        return removeController.getRoot();
    }

    private Region loadBorrowView(String fxmlFile) {
        if (borrowController == null) {
            try {
                URL fxmlLocation = BorrowController.class.getResource(fxmlFile);
                FXMLLoader loader = new FXMLLoader(fxmlLocation);
                root = loader.load();
                borrowController = loader.getController();
                borrowController.init(this, viewModelFactory.getBorrowViewModel(), root);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
        return borrowController.getRoot();
    }
}