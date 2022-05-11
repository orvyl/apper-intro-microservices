# GCash Mini
## _API Sepcification_

GCash Mini is a financial system that allows a person to send and receive money to someone, and able to buy items offered inside the system such as airtime load, steam credits, etc...

- Account creation
- Account verification
- Account login
- Add money to account
- Transfer money to an account
- Buy product
- Retrieve account details (personal info and balance)
- Manage products

### Account Creation
Allow client to create an account.
_Request_

| Field | Description | Type/Sample |
| ------ | ------ | ------ |
| `(Header)` `Reference-No` | Reference number of the transaction. | `string` "1234" |
| `email` | email address. This will be used as username | `string` "john.doe@apper.ph" |
| `firstName` | First name of the account owner | `string` "John" |
| `lastName` | Last name of the account owner | `string` "Doe" |
| `birthDate` | Date of birth of the account owner | `string` `FORMAT: YYYY-MM-DD` "2000-01-10" |

```json
POST /account
Header Reference-No: 123141
{
    "email": "john.doe@apper.ph",
    "firstName": "John",
    "lastName": "Doe",
    "birthDate": "2000-01-10"
}
```

_Response_

`201` Account successfully created. Ready for verification.
```json
{
    "verification_code": 92372
}
```

`400` Bad request. E.g email already registered, last name is required
```json
{
    "message": "Email already registered"
}
```

`500` Something went wrong creating the account

### Account Verification
Verify created account using a verification code
_Request_

| Field | Description | Type/Sample |
| ------ | ------ | ------ |
| `(Header)` `Reference-No` | Reference number of the transaction. | `string` "john.oe@apper.ph" |
| `verification_code` | Verification code reseived during account registration | `numeric` 928183 |
| `email` | email/username of the created account to verify | `string` "john.doe@apper.ph" |

```json
POST /account
Header Reference-No: 123141
{
    "email": "john.doe@apper.ph",
    "verification_code": 928183
}
```

_Response_

`200` Account successfully verified. Ready for authentication.

`400` Bad request. E.g invalid verification code
```json
{
    "message": "Invalid verification code"
}
```

`500` Something went wrong creating the account
