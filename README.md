#2048

A [2048](https://gabrielecirulli.github.io/2048/) engine, written in [Java](https://en.wikipedia.org/wiki/Java_\(programming_language\)) with the [Lanterna](https://code.google.com/p/lanterna/) library, for use in the HF-L Programming Club 2048 AI competition.

##HOW TO USE:

**(note: see Main.java for an example implementation of a 2048 bot)**
  
###Steps for starting a game:

To use the engine for your 2048 bot you first need to import the Engine.java file in your program
    `import com.hflprogramming.espaker16.engine2048.Engine;`
  
Then you must initiate the engine class and call the start game method
    `engine = new Engine();`
    `engine.startGame(false);`
This will initiate the gameboard with two randomly placed tiles (they will be either of values 2 or 4)

###Accessing the board:

The Gameboard is stored as an int array in the form \[rows]\[columns] with [0][0] at the top left;

In order to access the state of the gameboard you must call the look method
    `engine.look();`
This will return a copy of the gameboard. You may want to store the gameboard variable like so.
    `int[][] gameboard = engine.look();`
And then find specific values with...
    `gameboard [<row>][<column>];`

Also, you can access specific tile values using look(row, column) like so
    int tileValue = engine.look(row, column)

###Making Moves:

To make moves you use the swipe method
    `engine.swipe(<direction>);`

Direction is passed in the form of integer constants:
    **Engine.D_DOWN**
    **Engine.D_UP**
    **Engine.D_RIGHT**
    **Engine.D_LEFT**
      
For example to swipe down you would call:
    `engine.swipe(Engine.D_DOWN)`
    
The swipe method also returns an interger to inform you of the result of the move:
    *1 : the move was successful*
    *0 : the move was invalid (no tiles moved)*
    *-1 : the move worked but the game is now over*
    
###Score:

the score is calculated cumulatively throughout the game and can be accessed with
    `engine.getScore();`
    
###Display: 

There is a built in display in the 2048 engine with uses lanterna+ to show the tiles
To use this you must initiate the display
    `engine.initDisplay();`
      
To later close the display call
    `engine.closeDisplay();`
      
The display can be opened and closed at any time.
