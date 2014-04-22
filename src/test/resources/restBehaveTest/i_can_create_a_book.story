Meta:

Narrative:
As a user
I want to create a book
So that I can store the book and make sure the system guarantees the book is well formed

Scenario: various books we have created and validated
Given a book: Bainbridge by JG Clingenpeel
Then the book is valid

Given a book with no title or author
Then the book is not valid

Given a book: Bainbrige by JG Clingenpeel
When the author is null
Then the book has this error: [Author must not be null.]

Given a book: Bainbridge by JG Clingenpeel
When the title is null
Then the book has this error: [Title must not be null.]

Given a book: Bainbridge by JG Clingenpeel
When the title is null
When the author is null
Then the book has this error:
[Author must not be null., Title must not be null.]