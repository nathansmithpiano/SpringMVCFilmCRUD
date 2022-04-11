# SpringMVCFilmCRUD

  -- Developed for Skill Distillery Bootcamp Cohort 32 by Alex Trill, Miguel Barrios, and Nathan Smith --

### Description

  This is a web  application that allows the user to retrieve and or manipulates data from a database. The user is presented with a home page that displays a table of films from which they can view details about existing  films or execute a search  by either entering the film's ID, or by inputting a keyword that attempts to match their keyword to a  films description or title. From this main page, the user also has the option to remove or update and existing film, but only if the film is one that has been added by that user as none of the original films from the database are modifiable. Or they can navigate to a separate page that allows them to add a film to the database, this is done by filling out a form, inputing the necessary information for that film and submitting.
  
### Cool Features

- A user can select a film from the table and be taken to a more detailed view of its information.
- A Navigation bar allows the user to quickly navigate to the page they are looking for.
- When updating and adding new films the forms restrict the user so only valid information can be added.
- The user can search for films by keywords or film id.

### Technologies Used

- Java
- MySql
- MAMP
- sdvid database
- Maven
- SpringToolSuite4
- Atom
- Github
- Terminal
- Google Chrome
- MacBook Pro Retina 2015

### Thoughts For The Future

This project involved a lot of new JSP/form issues, which were certainly a learning experience, albeit a frustrating one.  Some strange things we noticed:
  - Sometimes, an int/double would return 0 even when we were calling a wrapper class getter.
  - Sometimes, a JSP would stop to function as intended but would appear on the page as working.
  - Sometimes, the only error message given in the browser was so generic it was not helpful at all.
  - Using <c:choose>, <c:if...> etc tags caused strange issues that were difficult to diagnose.
  - Using <script> within a <form> caused strange things when the form would have an issue but there was no sign of an issue as the page displayed just fine.
Also, we experienced numerous issues with git workflow, merging conflicts, rebase, etc.  As this was our first project collaborating through git (rather than just solo work) it seems that we could have benefited from some practice before this project... i.e., having a lab where we test out merging changes, dealing with problems, things to avoid, etc.  We ended up sharing code via Slack more than we probably should have, though we did eventually seem to get better at the git workflow.
