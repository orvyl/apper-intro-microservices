# Lab#2

1. Convert `BookService` to singleton.
2. Create a class `IdGenerator` with an instance method `String getNext()` that will return an ID of type string. It should be random or you can use UUID generator.
3. Add `id` string field in `Book` class.
4. Update `Book registerBook(...)`:
    * Populate `id` field of `Book` using `IdGenerator.getNext()` method.
    * Validate that fields `isbn, title, author, publishedYear` are not empty strings. Create and throw an `InvalidFieldFormatException` if at least one field is an empty string.
    * Validate book age. If the book age is 3 years old below, throw an exception `BookTooYoungException`
    * Expception messages is up  to you.
