# Web Quiz Engine

The backend of a multi-user web service for creating and solving quizzes. Users can register and create or delete their own quizzes. 
Other users can access other quizzes and solve them. Users and quizzes are stored on a H2 database.

## API Endpoints
* Register a user given a JSON object containing email and password. Email must be unique. Password is encrypted using BCrypt.
\
\
```POST: /api/register```

* Saves a given quiz on the database. Quiz object must contain title, text (question), options (array of size 2 minimum), and an answer 
(an integer array of size 0, 1 or more than 1, which corresponds to indices of options array). Returns a JSON object of the quiz including its generated id.
\
\
```POST: /api/quizzes```

* Get the quiz given its id.
\
\
```GET: /api/quizzes/{id}```


* Get all quizzes with pagination. Returns 10 quizzes at a time given a page number.
\
\
```GET: /api/quizzes?page=#```

    For example, ```/api/quizzes?page=1``` returns 10 quizzes on page 1.


* Get all completed/solved quizzes of a user with pagination. Returns 10 quizzes at a time given a page number.
\
\
```GET: /quizzes/completed?page=#```

   Again, ```/api/quizzes/completed?page=1``` returns 10 completed quizzes on page 1.
   
   
* Answer a quiz, given the id of a quiz and an answer object containing an integer array corresponding to indices of options array.
Returns a JSON object that contains a success boolean and a feedback message.
\
\
```POST: /api/quizzes/{id}```


* Delete a quiz given its id. The user deleting the quiz must be the author.
\
\
```DELETE: /api/quizzes/{id}```
