public class Game{

  private final Player[] players;
  private final Bowl[] bowls; 
  private int currentplayer;

  public Game(){
    this.players = new Player[] { new Player(), new Player() };
    this.bowls   = new Bowl[12];
    for(int i=0; i<this.bowls.length; i++){
      bowls[i] = new Bowl(4);
    }
    currentplayer = 1;
      
  }

  public int getCurrentPlayer(){
    return currentplayer;
  }

  public void swapPlayers(){
    if(currentplayer == 1) {
      currentplayer = 2;
    } 
    else{
      currentplayer = 1;
    }
  }
  
  //player1 can't pick from p2 bowls <-- add to
  public boolean isValidMove(int bowl){
    if(bowls[bowl-1].getStones() == 0){
      return false;
    }
    if(currentplayer == 1){
      if(bowl > 6){
        return false; 
      }
    }
    if(currentplayer == 2){
      if(bowl<=6){
        return false;
      }
    }
    return true;
  }


  public boolean canCurrentPlayerMove(){
    if (currentplayer == 1){
      for(int i = 0; i < bowls.length/2; i++){
        if(bowls[i].getStones()>0){ 
          return true;         
        } 
      }
      return false;
    }
    else{
      for(int i = 6; i< bowls.length; i++) {
       if(bowls[i].getStones()>0){ 
          return true;        
        }
      }
      return false;
    }
  }

  public void move(int bowl){
    int numberstones = bowls[bowl-1].getStones();
    bowls[bowl-1].takeAllStones();
    for(int i = bowl-1; i<(bowl-1)+numberstones; i++)
    {
      players[currentplayer-1].addToScore( bowls[(i+1)%12].updateAndGetScore() );
    }
  }

  public int getLeadingPlayer(){
    assert (players[0].getScore() != players[1].getScore()): "no leading player"; 
    if(players[0].getScore() >players[1].getScore()){
      return 1;
    }
    else{
      return 2;
    }
  }

  public boolean isOver(){
    for(int i=0; i<players.length; i++){
      if(players[i].getScore() >= 24){
        return true;
      }
    }
    return false;
  }

  public void display(){
    System.out.println();
    System.out.println("Player 2: " + players[1].getScore());
    System.out.println();
    System.out.println("  12     11     10      9      8      7 ");
    for(int i = bowls.length-1; i>= bowls.length/2; i--){
      System.out.print(" ( "+bowls[i].getStones()+" ) ");
    }
    System.out.println();
    System.out.println();
    for(int i = 0; i <bowls.length/2; i++){
      System.out.print(" ( "+bowls[i].getStones()+" ) ");
    }
    System.out.println();
    System.out.println("   1      2      3      4      5      6  ");
    System.out.println();
    System.out.println("Player 1: "+players[0].getScore());
    System.out.println();
  }
}
