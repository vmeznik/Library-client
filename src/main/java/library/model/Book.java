package library.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import library.utility.StringFormatter;

import java.io.Serializable;

@XmlRootElement
public class Book implements Serializable {

    private String title;
    private String genre;
    private String status;
    private String authorsFirstName;
    private String authorsLastName;
    private int id;

    // book has only status open / close

    public Book(int id, String title, String genre, String status, String authorsFirstName, String authorsLastName) {
        this.title = title;
        this.genre = genre;
        this.status = status;
        this.authorsFirstName = authorsFirstName;
        this.authorsLastName = authorsLastName;
        this.id = id;
    }

    public Book(){}

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getStatus() {
        return status;
    }

    public String getAuthorsFirstName() {
        return authorsFirstName;
    }

    public String getAuthorsLastName() {
        return authorsLastName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAuthorsFirstName(String authorsFirstName) {
        this.authorsFirstName = authorsFirstName;
    }

    public void setAuthorsLastName(String authorsLastName) {
        this.authorsLastName = authorsLastName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        if (title != null || authorsFirstName != null || authorsLastName != null) {
            return "ID: " + id + " " +
                    StringFormatter.capitalizeWord(title) + " by " +
                    StringFormatter.capitalizeWord(authorsFirstName) + " " +
                    StringFormatter.capitalizeWord(authorsLastName) + " - " +
                    status + " ("
                    + genre + ") ";
        }
        return "ID: " + id + " " +
                title + " by " +
                authorsFirstName + " " +
                authorsLastName + " - " +
                status + " ("
                + genre + ") ";
    }
}
