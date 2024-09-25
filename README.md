# Book Service

This service handles all operations related to books in the Library Management System.

## Features:
- CRUD Operations for Books
- Search Books by title, author, subject, and publication date
- Integration with MySQL using Spring Data JPA
- Dockerized MySQL Database

## Endpoints:
- POST `/api/books` - Add a new book
- GET `/api/books/{id}` - Get details of a specific book
- GET `/api/books` - Get all books
- DELETE `/api/books/{id}` - Delete a book by ID
