# Flashcard

Use Eclipse and MySQL Workbench and Postman for testing API.
database name - flashcard_db

POST METHOD

User can use POST method to add details in this format using endpoint url as "localhost:8080/flashcard"

{
  "student_id": "stu001",
  "question": "What is Newton's Second Law?",
  "answer": "Force equals mass times acceleration"
}

Backend will automatically detect the subject of the flashcard based on the question.
Condition: Questions should contain any one of these keywords to get detected by backend for a valid subject.

"Biology"   -> ("photosynthesis", "cell", "evolution", "organism", "organ"),
"Physics"   -> ("velocity", "mass", "force", "energy", "newton", "motion"),
"Math"      -> ("algebra", "equation", "calculus", "geometry", "geometry", "LCM"),
"Chemistry" -> ("atom", "electron", "reaction", "acid", "base", "bond"),
"History"   -> ("war", "divide", "empire", "ancient", "king", "ruler")


GET METHOD

User can use the GET method to get flashcards details by mixed subject using the endpoin url as "localhost:8080/get-subject?student_id=stu001&limit=5"
