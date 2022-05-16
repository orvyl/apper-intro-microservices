# Lab#1

1. Create a class Book with fields `isbn`, `title`, `author`, and `publishedYear`. Apply encapsulation and proper datatypes.
2. Create a `BookService` with a private instance variable `Map<String, Book> books` and the following methods:
    * `Book registerBook(isbn, title, author, publishedYear)`. The method should create a `Book` instance with the provided details and save it in a `books` map. Use the `isbn` as the key. Return created `Book` instance.
    * `List<Book> getAllBooks()`. The method should return a list that contains all registered `Book`s in the `books` map.
    * `Book getBook(isbn)`. The method should search the `Book` from the list by `isbn`. Throw an `Exception("Book not found!")` if the book is not in the `books` map.
    * `void updateBook(Book)`. Apply changes from the received Book instance to the saved Book with the same ISBN. Throw an `Exception("Book not found!")` if the book is not in the `books` map.
    * `void removeBook(isbn)`. Remove the book with the provided ISBN from the `book` map.
    * `long countPublishedBooks(publishedYear)`. count all books that are published in year `publishedYear`
3. In your main class, run the following:
```
public static void main(String[] args) {
    BookService bookService = new BookService();

    bookService.registerBook("ISBN-112", "Ain't Colorblind Coz I See Yellow", "O.Tumaneng", 2020);
    bookService.registerBook("ISBN-223", "Ego is the Enemy", "Ryan Holiday", 2016);
    bookService.registerBook("ISBN-334", "Head First Java", "Bert Bates", 2003);
    bookService.registerBook("ISBN-445", "How to take charge of shyness", "O.Tumaneng", 2003);

    System.out.println();

    System.out.println("Here are all the registered books so far...");
    for (Book book : bookService.getAllBooks()) {
        System.out.println("[" + book.getIsbn() + "] " + book.getTitle() + " by " + book.getAuthor() + ". Published year: " + book.getPublishedYear());
    }

    System.out.println();

    try {
        System.out.println("My favorite book is " + bookService.getBook("ISBN-112").getTitle());
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }

    System.out.println();

    try {
        Book book = bookService.getBook("ISBN-445");
        String oldAuthor = book.getAuthor();
        book.setAuthor("OFT");
        bookService.updateBook("ISBN-445", book);

        System.out.println("Book " + book.getIsbn() + " updated! From " + oldAuthor + " to " + book.getAuthor());
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }

    System.out.println();

    bookService.registerBook("ISBN-556", "Good to Great", "Jim Collins", 2001);
    System.out.println("Current book count: " + bookService.getAllBooks().size());
    bookService.removeBook("ISBN-556");
    System.out.println("ISBN-556 deleted! Current book count: " + bookService.getAllBooks().size());

    System.out.println();

    System.out.println("There are " + bookService.countPublishedBooks(2003) + " book/s published in year 2003");
}
```
