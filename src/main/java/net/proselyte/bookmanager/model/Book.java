package net.proselyte.bookmanager.model;

/**
 * Created by Liza on 26.04.2017.
 */
import javax.persistence.*;
@Entity
@Table(name="BOOKS")//с какой таблицей связан

public class Book {
    //связываем id с колонкой c название ID
    @Id
    @Column(name="ID")
    //указываем,что это генерируемое значение
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="BOOK_TITLE")
    private String bookTitle;

    @Column(name="BOOK_AUTHOR")
    private String bookAuthor;

    @Column(name="BOOK_PRICE")
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
//для логирования
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookTitle='" + bookTitle + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", price=" + price +
                '}';
    }
}
