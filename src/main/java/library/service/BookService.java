package library.service;

import jakarta.ws.rs.core.GenericType;
import library.model.Book;
import com.google.gson.Gson;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import library.utility.Logger;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;


public class BookService implements IBookService {
    private String title;
    private String genre;
    private String firstName;
    private String lastName;
    private int id;
    private boolean onlyOpen;
    private final PropertyChangeSupport property;
    private final Client client;

    public BookService() {
        this.property = new PropertyChangeSupport(this);

        this.client = ClientBuilder.newClient();
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public void setOnlyOpen(Boolean onlyOpen) {
        this.onlyOpen = onlyOpen;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void addBook() {
        if (this.genre != null && this.title != null && this.firstName != null && this.lastName != null) {
            sendRequest("add", new Book(0, this.title, this.genre, "open", this.firstName, this.lastName),
                    "post");
        } else Logger.getInstance().log("Error while adding on BookService something is null");
    }

    @Override
    public void returnBook() {
        if (this.id <= 0) {
            Logger.getInstance().log("Error while returning on BookService - ID <= 0");
        } else sendRequest("update", new Book(this.id, null, null, "open",
                null, null), "put");
    }

    @Override
    public void borrowBook() {
        if (this.id <= 0) {
            Logger.getInstance().log("Error while borrowing on BookService - ID <= 0");
        } else sendRequest("update", new Book(this.id, null, null, "close",
                null, null), "put");
    }

    @Override
    public void removeBook() {
        if (this.id <= 0) {
            Logger.getInstance().log("Error while removing on BookService - ID <= 0");
        } else sendRequest("remove?id=" + this.id, null, "delete");
    }

    @Override
    public void searchBook() {
        if (this.genre != null || this.title != null || this.firstName != null || this.lastName != null) {
            sendRequest("search?onlyOpen=" + this.onlyOpen, new Book(this.id, this.title, this.genre,
                    "", this.firstName, this.lastName), "get");
        } else {
            Logger.getInstance().log("Error while searching on ModelManager something is null");
        }
    }

    @Override
    public void searchAllBooks() {
        sendRequest("searchAll?onlyOpen=" + this.onlyOpen, new Book(0, null, null, null,
                null, null), "get");
    }

    @Override
    public void addListener(PropertyChangeListener listener) {
        property.addPropertyChangeListener(listener);
    }

    @Override
    public void removeListener(PropertyChangeListener listener) {
        property.removePropertyChangeListener(listener);
    }

    private void sendRequest(String action, Book book, String operation) {
        WebTarget resource = client.target("http://localhost:8080/" + action);

        Invocation.Builder request = resource.request(MediaType.APPLICATION_JSON);
        request.accept(MediaType.APPLICATION_JSON);

        Response response;
        try {
            switch (operation) {
                case "post": {
                    request.post(Entity.entity(book, MediaType.APPLICATION_JSON));
                }
                break;
                case "put": {
                    request.put(Entity.entity(book, MediaType.APPLICATION_JSON));
                }
                break;
                case "delete": {
                    request.delete();
                }
                break;
                case "get": {
                    response = request.post(Entity.entity(book, MediaType.APPLICATION_JSON));
                    ArrayList<Book> list = response.readEntity(new GenericType<ArrayList<Book>>() {
                    });
                    property.firePropertyChange("bookList", null, list);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}