# Movie API Documentation

## Prerequisites
Ensure you have:
- Java 17+
- Maven
- MySQL

## Clone the Repository
```bash
git clon https://github.com/AlhassanEbrahime/Movies_api.git
```

## Configure the Database
Update `application.properties` or `application.yml` with your MySQL database credentials:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

## Install Dependencies & Run
```bash
mvn clean install
mvn spring-boot:run
```

## Tech Stack
- Spring Boot 3 (Backend)
- Spring Security (JWT & Role-based Access)
- Spring Data JPA (Database Interaction)
- MySQL (Database)
- JWT (JSON Web Token) (Authentication with Refresh Token and Access Token)
- MapStruct (Object Mapping)
- OMDb API (Fetching Movie Data)

## Features
### Movie Management
- **Fetch Movies**: Retrieve movies from OMDb API or the database.
- **Add Movies**: Admins can add movies to the database.
- **Delete Movies**: Admins can delete movies.
- **Pagination**: Supports paginated movie listings.

### Authentication & Authorization
- User Registration & Login using JWT (Access Token and Refresh Token).
- Role-based Access Control:
  - **ADMIN**: Can add, delete, and manage movies.
  - **USER**: Can search , view movies and can rate a movie.

## Base URL
All endpoints are relative to:
```
http://localhost:8080/api/v1
```

## Authentication

### Register
Register a new user account.

- **Endpoint**: `POST /auth/register`
- **Body** (JSON):
  ```json
  {
      "username": "admin",
      "email": "admin@org.com",
      "password": "1234234"
  }
  ```
- **Description**: Creates a new user with the provided username, email, and password.

### Login
Authenticate a user and receive a JWT token.

- **Endpoint**: `POST /auth/login`
- **Body** (JSON):
  ```json
  {
      "username": "hasan",
      "password": "1234234"
  }
  ```
- **Description**: Returns a Bearer token for authenticated requests.

### Refresh Token
Obtain a new JWT token using a refresh token.

- **Endpoint**: `POST /auth/refresh-token`
- **Headers**:
  ```
  Authorization: Bearer <refresh_token>
  ```
- **Example Token**:
  ```
  eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6IlJPTEVfVVNFUiIsInN1YiI6Imhhc2FuIiwiaWF0IjoxNzQ0MjA4MDIwLCJleHAiOjE3NDQyOTQ0MjB9.v2_Cu4yw3DrnJ4HHNptaGg6Z7beQlvgM5A5_oMqkh4w
  ```
- **Description**: Refreshes the access token for continued API access.

## Movie Endpoints

### Search Movies
Search for movies by query.

- **Endpoint**: `GET /movies/search?query=<search_term>`
- **Query Params**:
  - `query`: Search term (e.g., `Guardians`)
- **Example**:
  ```
  GET /movies/search?query=Guardians
  ```
- **Description**: Returns a list of movies matching the search term.

### Get Movie by ID
Retrieve details of a specific movie.

- **Endpoint**: `GET /movies/<id>`
- **Headers**:
  ```
  Authorization: Bearer <access_token>
  ```
- **Example**:
  ```
  GET /movies/3
  Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6IlVTRVIiLCJzdWIiOiJoYXNhbiIsImlhdCI6MTc0NDM4OTcyMCwiZXhwIjoxNzQ0NDc2MTIwfQ.zHygzDRu--MKHgoV1bCEKV_yA7YfRrZfjL-NlPWT3G4
  ```
- **Description**: Fetches details of the movie with the specified ID.

### Add Movie
Add a new movie to the database (Admin only).

- **Endpoint**: `POST /movies`
- **Headers**:
  ```
  Authorization: Bearer <admin_token>
  ```
- **Body** (JSON):
  ```json
  {
      "imdbID": "tt0111161",
      "Title": "The Shawshank Redemption",
      "Type": "movie",
      "Plot": "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
      "Released": "14 Oct 1994",
      "Rated": "R",
      "Runtime": "142 min",
      "Genre": "Drama",
      "Director": "Frank Darabont",
      "Actors": "Tim Robbins, Morgan Freeman, Bob Gunton, William Sadler",
      "Writer": "Stephen King (short story \"Rita Hayworth and Shawshank Redemption\"), Frank Darabont (screenplay)",
      "Language": "English",
      "Country": "USA",
      "Poster": "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg",
      "imdbRating": "9.3"
  }
  ```
- **Description**: Adds a movie with the provided details.

### Delete Movie
Remove a movie by ID (Admin only).

- **Endpoint**: `DELETE /movies/<id>`
- **Example**:
  ```
  DELETE /movies/1
  ```
- **Description**: Deletes the movie with the specified ID.

### Get All Movies
Retrieve a list of all movies.

- **Endpoint**: `GET /movies`
- **Description**: Returns all movies in the database.

### Batch Add Movies
Add multiple movies at once (Admin only).

- **Endpoint**: `POST /movies/batch-add`
- **Body** (JSON):
  ```json
  [
      {
          "imdbID": "tt0068646",
          "Title": "The Godfather",
          "Type": "movie",
          "Plot": "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.",
          "Released": "24 Mar 1972",
          "Rated": "R",
          "Runtime": "175 min",
          "Genre": "Crime, Drama",
          "Director": "Francis Ford Coppola",
          "Actors": "Marlon Brando, Al Pacino, James Caan, Diane Keaton",
          "Writer": "Mario Puzo (novel), Francis Ford Coppola (screenplay)",
          "Language": "English, Italian, Latin",
          "Country": "USA",
          "Poster": "https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmYtYTAwZS00ZjQ3LTk3ZWQtYzBmZTA5ZWM3ZDU3XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg",
          "imdbRating": "9.2"
      },
      {
          "imdbID": "tt1375666",
          "Title": "Inception",
          "Type": "movie",
          "Plot": "A thief who steals corporate secrets through use of dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO.",
          "Released": "16 Jul 2010",
          "Rated": "PG-13",
          "Runtime": "148 min",
          "Genre": "Action, Adventure, Sci-Fi",
          "Director": "Christopher Nolan",
          "Actors": "Leonardo DiCaprio, Joseph Gordon-Levitt, Ellen Page, Tom Hardy",
          "Writer": "Christopher Nolan",
          "Language": "English, Japanese, French",
          "Country": "USA, UK",
          "Poster": "https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwODI5OTM0Mw@@._V1_SX300.jpg",
          "imdbRating": "8.8"
      }
  ]
  ```
- **Description**: Adds multiple movies in a single request.

### Batch Delete Movies
Delete multiple movies by IDs (Admin only).

- **Endpoint**: `DELETE /movies/batch-delete`
- **Body** (JSON):
  ```json
  [1, 2]
  ```
- **Description**: Deletes movies with the specified IDs.

### Rate Movie
Submit a rating for a movie.

- **Endpoint**: `POST /movie/rate`
- **Headers**:
  ```
  Authorization: Bearer <access_token>
  ```
- **Body** (JSON):
  ```json
  {
      "imdbId": "tt0111161",
      "rate": 5
  }
  ```
- **Description**: Allows a user to rate a movie by its IMDb ID.

## Notes
- **Authorization**: Most endpoints require a Bearer token in the `Authorization` header. Ensure tokens are valid and not expired.
- **Admin Access**: Endpoints like adding or deleting movies require admin-level authorization.
- **Error Handling**: The API returns standard HTTP status codes (e.g., 200 for success, 401 for unauthorized, 404 for not found).


# moviesApi
