public class Owari{

  public static void main(String[] args){
  	Game game = new Game();
  	
  	while(!game.isOver()){
  	  game.display();
  	  
      if(game.canCurrentPlayerMove()){
  	    System.out.println("Player "+game.getCurrentPlayer()+
  		                   " please make your move."); 		
  	 
  	    int m = IOUtil.readInt();

        while(!game.isValidMove(m)){
      	  System.out.println("invalid move");
          m = IOUtil.readInt();
        }      
        game.move(m);
        game.swapPlayers(); 
      }

  	  else{
  	    game.swapPlayers();
  	  }
  	}
  	System.out.println("GAMEOVER");
  	System.out.println("PLAYER "+game.getLeadingPlayer()+" HAS WON!");   
  }
}