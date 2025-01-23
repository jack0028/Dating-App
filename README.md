# Dating-App


## Introduction
Welcome to the Dating Recommendation Engine! This application leverages algorithms to suggest the best matches for users based on various factors like gender, age, and interests. The engine ensures users don't have to sift through irrelevant profiles, instead providing a refined selection of potential matches.

## Project Overview

- **Backend**: Spring Boot application that manages the business logic, including user data, recommendation engine, and matching rules.
- **Frontend**: JSP-based pages for interacting with users, displaying user profiles, and showing recommended matches.
- **Database**: MySQL (or any other RDBMS) to store user profiles, gender information, hobbies, and match-related data.

## Key Features

- **User Management**: Users can create profiles with their personal details, hobbies, and preferences.
- **Recommendation Engine**: Recommends top matches based on the interests and gender preferences of users.
- **Match Sorting**: Matches are sorted based on certain rules (e.g., interest matching, age, gender, etc.).
- **User Profiles**: The frontend provides a view of the user profile and recommended matches.
- **Mobile Responsive Design**: The frontend is built to be fully responsive using **Bootstrap 4**, ensuring that the application works seamlessly on both desktop and mobile devices.


## Backend (Spring Boot)

### Technologies Used
- **Spring Boot**: For building the backend services and APIs.
- **Spring Data JPA**: For interacting with the MySQL database and storing/retrieving user data.
- **Maven**: For dependency management and building the project.

### Backend Architecture

1. **Controllers**: Handle HTTP requests and interact with the service layer. For example, `RecommendationEngineController` provides endpoints for fetching recommended matches.
2. **Services**: Contains the business logic. `RecommendationEngineService` contains methods to calculate recommendations.
3. **Repositories**: Responsible for database interactions using Spring Data JPA. The repository layer interacts with entities like `User` and `Gender`.
4. **Entities**: Represents the database models, such as `User`, `Gender`, and `Interest`.
5. **Matching Rules**: The `MatchingRule` interface defines various rules for calculating compatibility between users.

## Example
Consider the following registered users:

| Name   | Gender | Age | Interests                       |
|--------|--------|-----|---------------------------------|
| User 1 | Female | 25  | Cricket, Chess                  |
| User 2 | Male   | 27  | Cricket, Football, Movies       |
| User 3 | Male   | 26  | Movies, Tennis, Football, Cricket|
| User 4 | Female | 24  | Tennis, Football, Badminton     |
| User 5 | Female | 32  | Cricket, Football, Movies, Badminton |

If the system is asked to fetch the top 2 matches for User 2, the output would be:
- **User 1**
- **User 4**

### Explanation
- Although User 5 shares the maximum number of interests with User 2, age is given preference over interests.
- User 2 is closest in age to User 3, but gender is given preference over age and interests.
- Interest match counts of User 1 and User 4 with User 2 are identical. However, User 1 is closer in age to User 2 than User 4, thus given more preference.

## Author
Developed by [Jackson]

!Output(path_to_image)
![Dating - localhost](https://github.com/user-attachments/assets/4bfc5a89-4a5b-49c5-ab78-4360a7f85c70)
- Here the User 2 Searches top 2 recommendation for dating and details is fetched from the backend.

![Recommendation - localhost](https://github.com/user-attachments/assets/a5e62e57-1314-4130-ad69-d9b330872ee0)

This project demonstrates how to build a dating recommendation system with Spring Boot and JSP, including logic for matching users based on common interests and gender preferences. The backend is built using Spring Boot and manages data with a MySQL database. The frontend is powered by JSP with Bootstrap for responsive design.

