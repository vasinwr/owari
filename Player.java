public class Player {
  private int score = 0;

  public void addToScore(int points){
    score = score + points;  
  }
  
  public int getScore(){
    return score; 
  }
  
  public void setScore(int score){
    this.score = score;
  }
}
