package net.proselyte.bookmanager.dao;

import net.proselyte.bookmanager.model.Book;

import java.util.List;

/**
 * Created by Liza on 26.04.2017.
 */
public interface BookDao {
    //что можно делать с объектом книга
    public void addBook(Book book);//добавление книги

    public void updateBook(Book book);//изменение книги

    public void removeBook(int id);//удаление книги

    public Book getBookById(int id);//получение книги по id

    public List<Book> listBooks(); // получение списка книг находящихся в таблице
}
