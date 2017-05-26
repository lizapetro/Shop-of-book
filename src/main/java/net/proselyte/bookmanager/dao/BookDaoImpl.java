package net.proselyte.bookmanager.dao;

import net.proselyte.bookmanager.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Liza on 26.04.2017.
 */
@Repository
public class BookDaoImpl implements BookDao {
    //дл€ обеспечени€ логировани€
    private static final Logger logger= LoggerFactory.getLogger(BookDaoImpl.class);

    //создаем фабрику сессий
    //нека€ сущность,котора€ будет создавать сессии дл€ создани€ соединени€ с Ѕƒ
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addBook(Book book) {
        //сесси€,котора€ просит фабрику сессий получить текущую сессию
        Session session=this.sessionFactory.getCurrentSession();

        //сесси€ сохран€ет объект,который мы ей передали
        session.persist(book);

        //добавл€ет информ дл€ логировани€
        logger.info("Book successfully saved. Book details:"+book);
    }

    @Override
    public void updateBook(Book book) {
        Session session=this.sessionFactory.getCurrentSession();
        session.update(book);
        logger.info("Book successfully update. Book details"+book);
    }

    @Override
    public void removeBook(int id) {
        Session session=this.sessionFactory.getCurrentSession();
        Book book=(Book) session.load(Book.class, new Integer(id));

        //провер€ем существование книги
        if (book!=null){
            session.delete(book);
        }
        logger.info("Book successfully removed. Book details"+book);
    }

    @Override
    public Book getBookById(int id) {
        Session session=this.sessionFactory.getCurrentSession();
        Book book=(Book) session.load(Book.class, new Integer(id));
        logger.info("Book successfully loaded. Book details"+book);
        return book;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> listBooks() {

        Session session = this.sessionFactory.getCurrentSession();
        //запрос всех книг, которые в таблице
        List<Book> bookList = session.createQuery("from Book").list();

        for (Book book : bookList) {
            logger.info("Book list: " + book);
        }
    return bookList;
    }

}
