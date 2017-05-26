package net.proselyte.bookmanager.controller;

import net.proselyte.bookmanager.model.Book;
import net.proselyte.bookmanager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Liza on 26.04.2017.
 */
@Controller
public class BookController {
    private BookService bookService;

    @Autowired(required = true)//автоматическое связывание
    @Qualifier(value = "bookService")
    public void setBookService(BookService bookService){
        this.bookService=bookService;
    }
    //страница books, которая выводит список книг
    @RequestMapping(value = "books",method= RequestMethod.GET)
    public String listBooks(Model model){
        model.addAttribute("book",new Book());
        model.addAttribute("listBooks",this.bookService.listBooks());
        return "books";
    }
    //способ добавлять книги(доп. страница)
    @RequestMapping(value = "/books/add", method=RequestMethod.POST)//потому что добавить,а не получить
    public String addBook(@ModelAttribute("book") Book book){
        //если id нашей книги равен 0(чего не может быть,если книга существует)
        //тогда просим сервис добавить новую книгу
        if (book.getId()==0){
            this.bookService.addBook(book);
        } else {
            //значит книга уже есть в нашей БД
            //просим сервис изменить книгу
            this.bookService.updateBook(book);

        }

        //перенаправляемся на главную страницу books
        return "redirect:/books";
    }

    //страница для удаления книг
    @RequestMapping("/remove/{id}")
    //передаем id
    public String removeBook(@PathVariable("id")int id){
        this.bookService.removeBook(id);

        //возвращаемся на  главную страницу
        return "redirect:/books";
    }

    //страница для редактирования книг
    @RequestMapping("edit/{id}")
    public String editBook(@PathVariable("id") int id,Model model){
        //получаем книгу по id
        model.addAttribute("book", this.bookService.getBookById(id));
        //выводим вусь список книг(то есть обновляем список)
        model.addAttribute("listBook",this.bookService.listBooks());

        return "books";
    }

    //страница для просматривания данных каждой книги отдельно
    @RequestMapping("bookdata/{id}")
    public String bookData(@PathVariable("id") int id,Model model){
        //для книги просим получить id
        model.addAttribute("book",this.bookService.getBookById(id));

        //возвращаемся к странице bookdata
        return "bookdata";
    }
}