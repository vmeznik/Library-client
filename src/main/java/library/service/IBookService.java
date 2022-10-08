package library.service;

import library.utility.UnnamedPropertyChangeSubject;

public interface IBookService extends UnnamedPropertyChangeSubject {
    void setTitle(String title);

    void setGenre(String genre);

    void setFirstName(String firstName);

    void setLastName(String lastName);

    void setOnlyOpen(Boolean onlyOpen);

    void setId(int id);

    void addBook();

    void returnBook();

    void borrowBook();

    void removeBook();

    void searchBook();

    void searchAllBooks();
}