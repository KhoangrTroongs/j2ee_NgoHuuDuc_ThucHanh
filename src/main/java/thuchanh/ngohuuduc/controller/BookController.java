package thuchanh.ngohuuduc.controller;

import thuchanh.ngohuuduc.entities.Book;
import thuchanh.ngohuuduc.services.BookService;
import thuchanh.ngohuuduc.services.CategoryService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import thuchanh.ngohuuduc.daos.Item;
import thuchanh.ngohuuduc.services.CartService;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor

public class BookController {
    private final BookService bookService;
    private final CategoryService categoryService;
    private final CartService cartService;
    

    @GetMapping
    public String showAllBooks(
        @NotNull Model model,
        @RequestParam(defaultValue = "0") Integer pageNo,
        @RequestParam(defaultValue = "20") Integer pageSize,
        @RequestParam(defaultValue = "id") String sortBy) 
    {
        model.addAttribute("books", bookService.getAllBooks(pageNo, pageSize, sortBy));
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("totalPages",
        bookService.getAllBooks(pageNo, pageSize, sortBy).size() / pageSize);
        return "book/list";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(HttpSession session,
            @RequestParam long id,
            @RequestParam String name,
            @RequestParam double price,
            @RequestParam(defaultValue = "1") int quantity) {
        var cart = cartService.getCart(session);
        cart.addItems(new Item(id, name, price, quantity));
        cartService.updateCart(session, cart);
        return "redirect:/books";
    }

    @GetMapping("/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "book/add";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") Book book, @RequestParam Long categoryId) {
        var category = categoryService.getCategoryById(categoryId).orElse(null);
        book.setCategory(category);
        bookService.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditBookForm(Model model, @PathVariable Long id) {
        var book = bookService.getBookById(id).orElse(null);
        model.addAttribute("book", book != null ? book : new Book());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "book/edit";
    }

    @PostMapping("/edit")
    public String editBook(@ModelAttribute("book") Book book, @RequestParam Long categoryId) {
        var category = categoryService.getCategoryById(categoryId).orElse(null);
        book.setCategory(category);
        bookService.updateBook(book);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        if (bookService.getBookById(id).isPresent()) {
            bookService.deleteBookById(id);
        }
        return "redirect:/books";
    }

}