### Change log 
##### Dec/26
- Login handler
  - Removed duplicate code in the way remember was handled. Switched to one conditional and moved the duplicate code out (L68:L71)
- Database
  - added `not null` and `unique` constraints to department name, group name, and combination of employee first name last name and email. added unique constraint to username
  - removed redundant insert statements
  - combined employee and user table
    - gave employee token, username, and password. Switched `position` to `role`
- Removed User class
- DatabaseAccess
  - changed all references of User to Employee as well as a couple logic errors as a result
- Employee
  - Combined employee and user
  - added an empty default constructor
  - moved over any important methods from user
- includes/Navigation
  - changed the user object reference into and Employee

    
 