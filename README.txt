RUNNING THE PROGRAM
1. To play the game, go to the owari directory in terminal
2. run "java -ea Owari"
3. to exit press Ctrl+c


THE RULES 

Owari is played on a board containing 12 bowls divided into two rows of six bowls. At the start of the game each bowl contains 4 stones. In addition, there are two pots used to store stones that have been captured by the players. Initially the pots of captured stones are empty ie 0.

The two players take it in turn to make moves. Player 1 starts.

A move consists of choosing a bowl, removing all the stones from it and then, moving in an anti-clockwise direction, placing one stone in each bowl following, until all the stones have been placed. Note that the board wraps around with the “last” bowl for one player being next to the “first” bowl for the other player.

If a player has no stones on their side of the board then they must pass their move. Conversely, a player must move if a move is possible.

If a bowl contains a single stone before a move is made and ends up with two or more stones after the move, then the player captures the stones in that bowl, moving them to their capture pot. (THIS IS HOW YOU SCORE!).

The game is over when one of the players has captured at least 24 stones. There are some rare situations where the game continues forever, but for the purposes of your program you can assume that all games will end with a clear winner, the ai!