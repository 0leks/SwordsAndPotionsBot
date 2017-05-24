package clicking;

import java.awt.Robot;
import java.awt.event.InputEvent;

public class Action {
  
  public static int XOFFSET = 0;
  public static int YOFFSET = 0;
  
  public int x;
  public int y;
  
  public Action( int x, int y ) {
    this.x = x;
    this.y = y;
  }
  
  public void move(Robot r ) {

    r.mouseMove(x + XOFFSET, y + YOFFSET);
  }
  public void execute(Robot r) {
    
    r.mouseMove(x + XOFFSET, y + YOFFSET);
    r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    try {
      Thread.sleep(200);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
  }
  
}