package clicking;

public class Resource implements Runnable {

  private int amount;
  private int rateperhour;
  private int max;
  
  private long timePassed;
  private int amountGenerated;
  private long timeStarted;
  
  public boolean debug;
  
  public Resource( int rate ) {
    timePassed = 0;
    amountGenerated = 1;
    timeStarted = System.currentTimeMillis()-1;
    this.rateperhour = rate;
  }
  
  public Resource( int rate, int current, int max ) {
    this(rate);
    this.max = max;
    this.amount = current;
  }
  
  public int getAmount() {
    return amount;
  }
  public void useAmount(int used) {
    amount -= used;
  }
  public void updateValue(int value) {
    amount = value;
  }
  @Override
  public void run() {
    
    while( true ) {
      if( amount >= max ) {
        long previousTimePassed = timePassed;
        timePassed = System.currentTimeMillis() - timeStarted;
        long timeSinceLastTic = timePassed - previousTimePassed;
        timeStarted += timeSinceLastTic;
      }
      
      timePassed = System.currentTimeMillis() - timeStarted;
      
      double ratio = ratio();
      if( debug )
        System.out.println(ratio);
      if( ratio < rateperhour ) {
        amount++;
        amountGenerated++;
        //System.out.println(amount);
      }
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
  
  private double ratio() {
    return (1.0*amountGenerated/timePassed)*3600000.0;
  }
  
  
  
}
