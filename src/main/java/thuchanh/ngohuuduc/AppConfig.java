// package thuchanh.ngohuuduc;

// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import thuchanh.ngohuuduc.entities.Book;
// import thuchanh.ngohuuduc.entities.Category;
// import thuchanh.ngohuuduc.repositories.IBookRepository;
// import thuchanh.ngohuuduc.repositories.ICategoryRepository;

// @Configuration
// public class AppConfig {
//     @Bean
//     public CommandLineRunner initDatabase(IBookRepository bookRepository, ICategoryRepository categoryRepository) {
//         return args -> {
//             if (categoryRepository.count() == 0) {
//                 // Tạo category trước
//                 Category category = new Category();
//                 category.setName("Công nghệ thông tin");
//                 categoryRepository.save(category);

//                 // Tạo books
//                 Book book1 = new Book();
//                 book1.setTitle("Lập trình Web Spring Framework");
//                 book1.setAuthor("Ánh Nguyễn");
//                 book1.setPrice(29.99);
//                 book1.setCategory(category);
//                 bookRepository.save(book1);

//                 Book book2 = new Book();
//                 book2.setTitle("Lập trình ứng dụng Java");
//                 book2.setAuthor("Huy Cường");
//                 book2.setPrice(45.63);
//                 book2.setCategory(category);
//                 bookRepository.save(book2);

//                 Book book3 = new Book();
//                 book3.setTitle("Lập trình Web Spring Boot");
//                 book3.setAuthor("Xuân Nhân");
//                 book3.setPrice(12.0);
//                 book3.setCategory(category);
//                 bookRepository.save(book3);

//                 Book book4 = new Book();
//                 book4.setTitle("Lập trình Web Spring MVC");
//                 book4.setAuthor("Ánh Nguyễn");
//                 book4.setPrice(0.12);
//                 book4.setCategory(category);
//                 bookRepository.save(book4);
//             }
//         };
//     }
// }
