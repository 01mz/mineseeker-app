Optional Features:
Features Implemented:

3.1 Functional requirements
1.4 Welcome screen must have a button (or similar interface) which allows the user to skip
animations (if any) and go to the Main Menu.
1.5 The Welcome screen may automatically advance to the Main Menu after all animations (if
any) have finished, plus at least 4 extra seconds.

3.13 May display text stating the total number of games started (saved between application
launches).
3.14 May display text stating the best score so far of any completed game of this specific
configuration (board size and number of mines); must save best score for each possible
configuration.

5.4 May allow user to reset number of times game has been played and best scores for each
game configuration (if supported).

3.2 Non-functional requirements.
1.3 The theme may affect the name given to your application; it need not be Mine Seeker.




All Features Implemented:
3.1 Functional requirements
1. The application’s first screen must be a nice-looking welcome screen.
1.1 The program must start up showing the welcome screen.
1.2 Welcome screen must include at least the following elements:
- Name of application
- Name of application's author(s)
- One or more images. Could include a picture or cartoon of the authors, an icon for the
application, or related images.

1.4 Welcome screen must have a button (or similar interface) which allows the user to skip
animations (if any) and go to the Main Menu.
1.5 The Welcome screen may automatically advance to the Main Menu after all animations (if
any) have finished, plus at least 4 extra seconds.

2. The Main Menu must allow the user to navigate to the game, options, and help screens.
2.1 Display a button to navigate to the Game screen.
2.2 Navigating to the Game screen creates a new game with the correct configuration specified
on the Options screen
2.3 Display a button to navigate to the Options screen.
2.4 Display a button to navigate to the Help screen.

3. The Game screen must allow the user to play the Mine Seeker game.
3.1 Display text stating how many mines total there are on the game board (hidden or revealed)
3.2 Display text stating how many mines the player has revealed.
3.3 Display text stating how many scans it has taken the user so far this game.
3.4 Display a grid of buttons (or UI elements which have button-like functionality). The grid size
is set by the options screen.
3.5 The number of mines on the game board is set by the options screen.
3.6 Tapping a cell investigates the cell, which either:
1) Reveals a mine if one is present.
2) Performs a scan if either no mine is present, or the mine has already been revealed.
Tapping on an already scanned cell has no effect and does not count as an additional
scan.
3.7 When a mine is revealed, the button must indicate that it contains a mine. The button must
display an icon or image on it showing it is a mine.
3.8 When a scan is performed, the count of hidden mines in the row and column is displayed in
that button.
3.9 When a mine is revealed, any of the buttons in its row and column which show a count of
hidden mines must be updated with the new count of hidden mines (count decreases by 1).


3.13 May display text stating the total number of games started (saved between application
launches).
3.14 May display text stating the best score so far of any completed game of this specific
configuration (board size and number of mines); must save best score for each possible
configuration.

4. When the player wins, congratulate the player and return to the Main Menu.
4.1 When the player finds the last mine, redraw the game board showing the mine and updated
hidden-mine counts.
4.2 When the player finds all mines on the board, display a congratulations dialog.
4.3 The congratulations dialog must have at least one image, and some text congratulating the
player.
4.4 When the player dismisses the dialog (taps OK, or the like), return to the Main Menu.
4.5 From the Main Menu, pressing the Android back button must then quit the application.

5. The Options screen must allow the user select board size and number of mines.
5.1 User can select the board size, from options including at least:
- 4 rows by 6 columns
- 5 rows by 10 columns
- 6 rows by 15 columns
5.2 User can select number of mines, from options including at least:
- 6 mines
- 10 mines
- 15 mines
- 20 mines
5.3 The game size and number of mines are saved between application runs.
5.4 May allow user to reset number of times game has been played and best scores for each
game configuration (if supported).

6. The Help screen displays some information about who wrote the application and some text
explaining the game.
6.1 The about-the-author text must include a hyperlink to the CMPT 276 home-page.
6.2 The game information text must explain some of the basics about the game. You must use
your own wording, not copying the text from the assignment document. Your text should reflect
the theme of your game.
6.3 The Help screen must provide the correct citation for any images, icons, or other resources
(such as music) used in the game (for copyright purposes). Include a hyperlink if applicable.
6.4 Pressing the Android back button on the Help screen returns to the Main Menu.

3.2 Non-functional requirements.
1. The application must have the game play described in this document, but must have a theme of your
choosing, such as:
- Searching for rebel bases in space.
- Finding virus infected cells to fight an infection.
- Identifying bugs in the Linux kernel.
- Finding gophers in a field.
- Finding bad-apples in a case of apples.
- Finding zombies in a graveyard.
-Anything else you can imagine!
1.1 Images chosen for backgrounds and buttons must be consistent with this theme.
1.2 Game help text must be consistent with this theme.
1.3 The theme may affect the name given to your application; it need not be Mine Seeker.
1.4 If you want to update game play slightly to be in line with your game's theme, you must
consult the customer (It’s me. I’m the customer).
2. Application source code must be maintainable.
2.1 Code must be well organized into methods and classes.
2.2 Game logic must be in a separate class from game UI class.
2.3 Game logic must be in a separate Java package from UI code.
2.4 Code must use good naming conventions and have good indentation and formatting.
3. The application runs on Android smartphones and tablets.
3.1 The application must run under at least Android OS version 10.0 (API 29, Q) or newer.
3.2 The application must display well on at least the “Pixel 2” configuration.
3.3 The application must support at least horizontal (landscape) orientation.
3.4 The application must not be able to be rotated to an unsupported orientation.
4. Pressing the "back" button on the Android phone must always take the user to the previous screen
in a reasonable way.
4.1 From the Welcome screen, back should exit the program.
4.2 From the Main Menu, the game must exit without returning to the Welcome screen.
4.3 From other screens, back must return to the previous activity on the activity stack.
5. The application should appear complete and well built.
5.1 The application must have an appropriate (non-default) icon.
5.2 Each screen must have a background image. Each screen may use the same background
image.
5.3 All text must be clearly readable over the background.
5.4 All text that appears on the UI must be read from the strings.xml file to support
internationalization.

6. Quick to learn for a new user.
6.1 An average grade 10 student must take no more than two minutes to learn to play the game
after a one minute demonstration on how to use the application. This is a rhetorical example,
you don’t need to actually get a grade 10 student to play your game.
7. Must be responsive to the user.
7.1 Each user interaction (such as a button press) must start to generate its response within 0.5s
when run on a real device. (Looser performance criteria applied for running in the emulator).
This won’t be strictly enforced, so don’t worry too much about the exact timing unless your
code is doing something which takes significantly more time than average.