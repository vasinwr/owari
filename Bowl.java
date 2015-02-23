public class Bowl{
  private int stones;
  public Bowl(int stones){
    this.stones = stones;
  }
  
  public int getStones(){
    return stones;
  }

  public int takeAllStones(){
    int temp = stones;    
    stones = 0; 
    return temp; 
  }

  public void depositStones(){
    stones = stones + 1;  
  }
  
  public int updateAndGetScore(){
    int score = 0;
    if(stones == 1){
     depositStones();
     score = stones;
     takeAllStones();
     return score; 
    }
    else{
      depositStones();
      return 0; 
    }

  }
}
