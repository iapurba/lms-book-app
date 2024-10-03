# Book Service

This service handles all operations related to books in the Library Management System.

## Features:
- CRUD Operations for Books, BookItems
- Search Books by title, author, subject, and publication date
- Integration with MySQL using Spring Data JPA
- Dockerized MySQL Database

## Endpoints:
#### Book APIs
- POST `/api/books` - Add a new book
- GET `/api/books/isbn/{isbn}` - Get details of a specific book by ISBN
- GET `/api/books` - Get all books
- PUT `/api/books/isbn/{isbn}` - Update a book by ISBN
- DELETE `/api/books/isbn/{isbn}` - Delete a book by ISBN

#### BookItem APIs
- POST `/api/book/items` - Add a new book item
- GET `/api/books/items/{id}` - Get details of a specific book item by ID
- PUT `/api/books/items/{id}` - Update a book item by ID
- DELETE `/api/books/items/{id}` - Delete a book item by ID
