import java.util.ArrayList;
import java.util.List;

public class Game{

  private final Player[] players;
  private Bowl[] bowls; 
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

  public void moveAI(){
    int aibowl = calculateBowl();
    System.out.println("AI chooses bowl: "+aibowl);
    int numberstones = bowls[aibowl-1].getStones();
    bowls[aibowl-1].takeAllStones();
    for(int i = aibowl-1; i<(aibowl-1)+numberstones; i++){
      players[currentplayer-1].addToScore( bowls[(i+1)%12].updateAndGetScore() );
    }
  }

  private int calculateBowl(){
   // check all ai bowls to find the best bowl
   // check each bowl's score and to each check maxscore of opponent
   // create a temporary bowl array to store the value of after first move to calculate net score
   
   // currentplayer = 2
   // bowl number 7 - 12
   int[] netscores = new int[6];   
   for(int i = 7; i <= 12; i++){
     int netscore = netScore(i);
     netscores[i-7] = netscore;
   }
   //System.out.println("netscore array" + Arrays.toString(netscores));
   
   int maxNetScore = Integer.MIN_VALUE;
   List<Integer> bestmoves = new ArrayList<Integer>();
   for(int netscoresindex = 0; netscoresindex< netscores.length; netscoresindex++){
     int current = netscores[netscoresindex];
     if(maxNetScore < current && bowls[netscoresindex+6].getStones() != 0){
       maxNetScore = current;
       bestmoves.clear();
       bestmoves.add(netscoresindex);
     }
     else if( maxNetScore == current && bowls[netscoresindex+6].getStones() != 0){
       bestmoves.add(netscoresindex);
     }
   }
//   Random rnd = new Random();
   int random = (int) (Math.random()*bestmoves.size());
   int chosenNetIndex = (int) bestmoves.get(random);
   //netscoresindex + 7 = bowl number
   int bestBowl = chosenNetIndex + 7; 
   //System.out.println("bestmoves"+bestmoves+ "Size" + bestmoves.size());
   //System.out.println(bestBowl);
   // if bestmoves is empty then swap player...
   
   return bestBowl;
  }
  
  private int canScore(int bowlnumber){
    
    int bowlindex = bowlnumber - 1;
    int handstones = bowls[bowlindex].getStones();
    int score = 0;
    for(int i = 1; i<=handstones; i++){
      if(bowls[(bowlindex+i)%12].getStones() == 1){
        score = score + 1;
      }
    }
    return score;
  }
  
  private int maxCanScore(int player){
    int maxscore = 0;
    int playerindex = player - 1;
    for(int i = playerindex * 6 +1; i<= playerindex*6 + 6 ; i++ ){
      if(canScore(i)>maxscore){
        maxscore = canScore(i);
      }
    }
    return maxscore;
  }
  
  private int netScore(int bowlnumber){
    int bowlindex = bowlnumber - 1;
    int posscore = canScore(bowlnumber);
    int negscore;
    
    Bowl[] temporary = new Bowl[bowls.length];
    int tempscore = players[currentplayer-1].getScore();
    for(int i = 0; i < bowls.length; i++){
      temporary[i] = new Bowl (bowls[i].getStones());
    }
    move(bowlnumber);
    negscore = maxCanScore(currentplayer%2 + 1);
    bowls = temporary;
    players[currentplayer-1].setScore(tempscore);
    int netscore = posscore - negscore;
    //System.out.println("netscore calculated, netscore = "+netscore);
    //display();
    return netscore;
    
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
