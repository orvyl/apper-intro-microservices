# Homework#1

1. Create a new project in https://start.spring.io/. Set `artifact` to `hw1`. Set your own `group` and choose your java version.
2. Create a class `User` with the following fields:
    * `id`. string
    * `msisdn`. string. phone number
    * `password`. string. clear password
    * `firstName`. string
    * `middleName`. string
    * `lastName`. string
    * `email`. string. valid email format
    * `createdAt`. LocalDateTime. Date/time of registration
    * `lastUpdated`. LocalDateTime. Date/time when the user last updated
3. Create a `UserService` interface with the following methods:
    ```
    User register(String msisdn, String firstName, String middleName, String lastName, String email, String password) throws InvalidUserFieldRegistrationException;
    
    User authenticate(String msisdn, String password) throws UserNotFoundException;
    
    List<User> getAllUsers();
    
    void update(String msisdn, String firstName, String middleName, String lastName, String email, String password)  throws InvalidUserFieldRegistrationException;
    ```
    Creat a class `UserServiceImpl` that implements the interface. Make this class a bean.
4. Create a `TransactionLogService` interface with the following methods:
    ```
    void log(String identifier, String log);
    void displayLogs();
    ```
    Creat a class `TransactionLogServiceImpl` that implements the interface. Make this class a bean.
5. Using the bean of `TransactionLogService`, add log in every action inside `UserService` bean. Please see program output for reference.
6. Execute the following code in a `CommandLineRunner` bean: 
    ```
    userService.register("639175861661", "John", "Nuary", "Juan", "j.doe@test.com", "mypassword123");
    User john = userService.authenticate("639175861661", "mypassword123");

    System.out.println("Login success! Welcome, " + john.getFirstName() + "!");
    System.out.println("++=++=");

    try {
        userService.register("639175861662", "Feb", "Buary", "Por", "feb2022", "mypassword456");
    } catch (InvalidUserFieldRegistrationException e) {
        System.out.println("ERROR! " + e.getMessage());
    }

    System.out.println("++=++=");

    System.out.println("At this point, we have " + userService.getAllUsers().size() + " registered user/s");

    System.out.println("++=++=");

    try {
        userService.register("639175861663", "Kelly", "", "Klarkzon", "kelly@gmail.com", "thebrownfox");
        userService.authenticate("639175861663", "mypassword123");
    } catch (UserNotFoundException e) {
        System.out.println(e.getMessage());
    }

    System.out.println("++=++=");

    System.out.println("At this point, we have " + userService.getAllUsers().size() + " registered user/s");

    System.out.println("++=++=");

    try {
        userService.register("639175861664", "Teri", "Teri", "", "teri@bol.com", "mypassword456");
    } catch (InvalidUserFieldRegistrationException e) {
        System.out.println("ERROR! " + e.getMessage());
    }

    System.out.println("++=++=");

    try {
        userService.register("639175861661", "John", "Nuary", "Juan", "j.doe@test.com", "mypassword123");
    } catch (InvalidUserFieldRegistrationException e) {
        System.out.println("ERROR! " + e.getMessage());
    }

    System.out.println("++=++=");

    System.out.println("Here are the current users: ");
    userService.getAllUsers().forEach(user -> System.out.println("First Name: " + user.getFirstName() + ", Registration Date: " + user.getCreatedAt() + ", Last Updated: " + user.getLastUpdated()));

    System.out.println("++=++=");

    userService.update("639175861661", "John", "", "Juan", "j.doe@test.com", "$trong3rP@ssw0rd");
    john = userService.authenticate("639175861661", "$trong3rP@ssw0rd");

    System.out.println("User " + john.getMsisdn() + " updated!");
    System.out.println(john);

    System.out.println("++=++=");

    transactionLogService.displayLogs();
    ```
7. Here's a sample output:
    ```
    Login success! Welcome, John!
    ++=++=
    ERROR! Invalid email format: feb2022
    ++=++=
    At this point, we have 1 registered user/s
    ++=++=
    639175861663 with provided password is invalid
    ++=++=
    At this point, we have 2 registered user/s
    ++=++=
    ERROR! lastName field is required
    ++=++=
    ERROR! 639175861661 msisdn is already registered
    ++=++=
    Here are the current users: 
    First Name: Kelly, Registration Date: 2022-05-18T00:02:11.187395800, Last Updated: 2022-05-18T00:02:11.187395800
    First Name: John, Registration Date: 2022-05-18T00:02:11.155401500, Last Updated: 2022-05-18T00:02:11.155401500
    ++=++=
    User 639175861661 updated!
    User(id=9dcb7cbe-dc04-4cad-8532-93a176633352, msisdn=639175861661, password=dac2e053ddb819eb5a7cf1515ccf5618, firstName=John, middleName=, lastName=Juan, email=j.doe@test.com, createdAt=2022-05-18T00:02:11.155401500, lastUpdated=2022-05-18T00:02:11.187395800)
    ++=++=
    [2022-05-18T00:02:11.182395900] 639175861661 : user created
    [2022-05-18T00:02:11.184396700] 639175861661 : user successfully authenticated
    [2022-05-18T00:02:11.187395800] 639175861663 : user created
    [2022-05-18T00:02:11.187395800] 639175861663 : authentication failed
    [2022-05-18T00:02:11.187395800] 639175861661 : user updated
    [2022-05-18T00:02:11.188394400] 639175861661 : user successfully authenticated
    ```
8. Create classes and exception you think you need to solve the program.


