# GCash Mini

## Functions

- User can register and login.
- User can CASH IN and CASH OUT in his/her account
- User can transfer money to a registered user if his/her balance is sufficient

### For development

You will be developing the API that the UIs of GCash Mini will have.

1. Create an API for registering a user that captures first name, middle name(optinal), last name, email, and password. Hash the password before saving.
2. Create an API for login/authenticate. Email and password are the credentials.
3. Create an API that allows a user to CASH IN.
    - CASH IN request must contain `channel` with only two possible values: `OTC` and `TOPUP`
    - This operation has at least 3 fields for it to be processed: identification of the user(user id or email), amount, and channel. You can add additional fields if it is needed.
    - Must validate if the user exists.
4. Create an API that allows a user to CASH OUT / WITHDRAW
    - This operation has at least 2 fields: identification of the user(user id or email) and amount.
    - Must validate if the amount to cash out is sufficient with the current balance of the user.
    - Must validate if the user exists.
5. Create an API that allows a user to TRANSFER money to an existing user.
    - This operation has at least 3 fields: identification of the sender, identification or the recipient, and amount to transfer.
    - Must validate if both sender and recipient exist.
    - Must validate if the sender's balance is sufficient to do transfer
6. Create an API for ADMIN to retrieve all registered users with their current balance.
7. [BONUS] Create an API Gateway where all operations are consolidated and can be called.
8. [BONUS] Create an ACTIVITY service that logs all user registration, cash in and out, and transfer. Fields are up to you.
9. [BONUS] if you will do #8, create an api where ADMIN can retrieve all activities.
10. [BIG BONUS] Deploy your project to AWS (one microservice is to one ec2.)
    - USE freetier EC2s
    - Notify me ASAP if you were able to deploy it.

### Output of the project

1. Architectural design of the microservices (image).
2. Documentation/API Specification. You can create an MD file or in Swagger.
3. Code repository of your project.
    - Name of the repo: gcash-mini-{your group name} E.g gcach-mini-boombusters
    - Include inside the architectural design of your microservices.
    - Your leader/representative will email me the link along with the names of the members.
4. If you will do the deployment to AWS. Include in your email the public IP/DNS.
5. INDIVIDUAL. Email me:
    - Your contribution to your final project
    - Top 3 things you've learned in this course
    - What's your moving forward after completing this course?

### Groupings:
Lorenzo Gabriel
Bernabe
Renz Joshua
Joshua
William

Densel Ross
Jemina Grace
Stanley
Lemuel Aldwim
Regina

Louie Angelo
Renz Kristopher
Francis Paolo
Christelle Nina

Carl Jefferson
Mac Robert
Celso
Christine Angela
Arthur Akira

