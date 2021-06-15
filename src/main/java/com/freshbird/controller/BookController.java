package com.freshbird.controller;

import com.freshbird.pojo.Books;
import com.freshbird.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    // controller调service层

    @Autowired
    @Qualifier("BookServiceImpl")
    private BookService bookService;

    // 查询全部书籍，并返回到书籍展示页面
    @RequestMapping("/allBook")
    public String queryAllBook(Model model) {
        List<Books> booksList = bookService.queryAllBook();
        model.addAttribute("list", booksList);
        return "allBook";
    }

    // 跳转到增加书籍页面
    @RequestMapping("/toAddBook")
    public String toAddPage() {
        return "addBook";
    }

    // 添加书籍的请求
    @RequestMapping("/addBook")
    public String addBook(Books books) {
        System.out.println("addBook=>" + books);
        bookService.addBook(books);
        return "redirect:/book/allBook";
    }

    // 跳转到修改书籍页面
    @RequestMapping("/toUpdateBook")
    public String toUpdatePage(int id, Model model) {
        Books books = bookService.queryBookById(id);
        model.addAttribute("QBooks", books);
        return "updateBook";
    }

    // 修改书籍
    @RequestMapping("/updateBook")
    public String updateBook(Books books) {
        System.out.println("updateBook=>" + books);
        bookService.updateBook(books);
        return "redirect:/book/allBook";
    }

    // 删除书籍
    @RequestMapping("/deleteBook/{bookId}")
    public String deleteBookById(@PathVariable("bookId") int id) {
        bookService.deleteBookById(id);
        return "redirect:/book/allBook";
    }

    // 查询一本书
    @RequestMapping("/queryBook")
    public String queryBookName(String queryBookName, Model model) {
        List<Books> booksList = bookService.queryBookByName(queryBookName);
        System.err.println("BookController: queryBookName=> " + booksList);
        if (booksList == null || booksList.size() == 0 || queryBookName.equals("") || queryBookName == null) {
            booksList = bookService.queryAllBook();
            model.addAttribute("error", "未查到相关书籍信息！");
        }
        model.addAttribute("list", booksList);
        return "allBook";
    }
}
