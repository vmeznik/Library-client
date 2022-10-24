package library;

import javafx.application.Application;
import javafx.stage.Stage;
import library.service.IBookService;
import library.service.BookService;
import library.view.ViewHandler;
import library.view.viewModel.ViewModelFactory;

public class MyApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            IBookService iBookService = new BookService();
            ViewModelFactory viewModelFactory = new ViewModelFactory(iBookService);
            ViewHandler view = new ViewHandler(viewModelFactory);
            view.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
