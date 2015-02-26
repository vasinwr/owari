public class Owari{

  public static void main(String[] args){
    System.out.println("please choose mode");
    System.out.println("1. 2 Players");
    System.out.println("2. Play with AI");

    int mode = IOUtil.readInt();
    if(mode == 1){
      startGame();
    }
    else if(mode == 2){
      startGameAI();
    }
    else{
      System.out.println("wrong input: enter only 1 or 2");
    }
  }

  public static void startGame(){

  	Game game = new Game();
  	
  	while(!game.isOver()){
  	  game.display();
  	  
      if(game.canCurrentPlayerMove()){
  	    System.out.println("Player "+game.getCurrentPlayer()+
  		                   " please make your move."); 		
  	 
  	    int m = IOUtil.readInt();

        while(!game.isValidMove(m)){
      	  System.out.println("invalid move. Plase reselect move");
          m = IOUtil.readInt();
        }      
        game.move(m);
        game.swapPlayers(); 
      }

  	  else{
        System.out.println("Player Swap! No move available for the current player.");
  	    game.swapPlayers();
  	  }
  	}
  	System.out.println("GAMEOVER");
  	System.out.println("PLAYER "+game.getLeadingPlayer()+" HAS WON!");   
  }

  public static  void startGameAI(){

    Game game = new Game();

    while(!game.isOver()){
      game.display();

      if(game.canCurrentPlayerMove()){
        System.out.println("Player "+game.getCurrentPlayer()+
                         " please make your move.");    
     
        int m = IOUtil.readInt();
        
        while(!game.isValidMove(m)){
          System.out.println("invalid move. Please reselect move");
          m = IOUtil.readInt();
        }
        game.move(m);
      }
      

      else{
        System.out.println("Player Swap! No move available for the current player.");
        game.swapPlayers();
      }

      game.swapPlayers();
      game.display();
      System.out.println("AI move >:)");
      game.moveAI();
      game.swapPlayers();
    }

    if(game.getLeadingPlayer()==1){
      System.out.println("YOU WIN!");
    }
    else{
      System.out.println("YOU LOSE :(");  
    }
  }
}