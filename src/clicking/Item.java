package clicking;

import java.awt.Robot;
import java.util.ArrayList;
import java.util.List;

public class Item {
  
  public static final long EXTRATIME = 5000;
  public static final long GREEN = 20000 + EXTRATIME;
  public static final long YELLOW = 27500 + EXTRATIME;
  public static final long ORANGE = 35000 + EXTRATIME;
  public static final long PINK = 57500 + EXTRATIME;
  public static final long PURPLE = 80000 + EXTRATIME;

  public static final int PRIORITY_HIGH = 10;
  public static final int PRIORITY_LOW = 1;
  
  private String name;
  private Action[] actions;
  private long time;
  
  public int amount;
  
  private List<ResourceCost> costs;
  private List<ItemCost> itemCosts;
  
  public int getCost(Resource resource) {
    for( ResourceCost cost : costs ) {
      if( cost.resource == resource ) {
        return cost.amount;
      }
    }
    return 0;
  }
  
  public void addCost(ResourceCost cost) {
    if( costs == null ) {
      costs = new ArrayList<ResourceCost>();
    }
    costs.add(cost);
  }
  public void addCost(ItemCost cost) {
    if( itemCosts == null ) {
      itemCosts = new ArrayList<ItemCost>();
    }
    itemCosts.add(cost);
  }
  
  public boolean costsSatisfied(Item[][] orderedAgenda, Item[][] unorderedAgenda, int priority) {

    for( ResourceCost cost : costs ) {
      int highestCost = 0;
      for( int prio = 0; prio < priority && prio < orderedAgenda.length; prio++ ) {
        for( Item item : orderedAgenda[prio] ) {
          int resourceCost = item.getCost(cost.resource);
          if( resourceCost > highestCost ) {
            highestCost = resourceCost;
          }
        }
      }
      for( int prio = 0; prio < priority && prio < unorderedAgenda.length; prio++ ) {
        for( Item item : unorderedAgenda[prio] ) {
          int resourceCost = item.getCost(cost.resource);
          if( resourceCost > highestCost ) {
            highestCost = resourceCost;
          }
        }
      }
      if( cost.resource.getAmount() - cost.amount < highestCost ) {
//        if( highestCost > 0 ) {
//          System.out.print("Current amount:" + cost.resource.getAmount() + " - cost:" + cost.amount);
//          System.out.println(" > Highest upper tier cost:" + highestCost + "              NOT ENOUGH");
//        }
        return false;
      }
    }
    for( ItemCost cost : itemCosts ) {
      if( cost.item.amount < cost.amount ) {
        return false;
      }
    }
    return true;
  }
  
  public boolean costsSatisfied() {
    if( costs == null ) {
      return true;
    }
    else {
      for( ResourceCost cost : costs ) {
        if( cost.resource.getAmount() < cost.amount ) {
          return false;
        }
      }
      for( ItemCost cost : itemCosts ) {
        if( cost.item.amount < cost.amount ) {
          return false;
        }
      }
      return true;
    }
  }
  
  public boolean isDone( long timeWorked ) {
    if( Driver.DEBUG ) {
      long timeLeft = (time - timeWorked)/1000;
      if( timeLeft < 0 ) {
        timeLeft = 0;
      }
      if( timeLeft <= 5 ) {
        System.err.println( this + ": " + timeLeft );
      }
      else if( (timeLeft < 100 && timeLeft % 10 == 0) || timeLeft % 50 == 0) {
        System.out.println( this + ": " + timeLeft );
      }
    }
    return timeWorked > time;
  }
  
  public void startWork(Robot r) {
    if( costs != null ) {
      for( ResourceCost cost : costs ) {
        cost.resource.useAmount(cost.amount);
      }
    }
    if( itemCosts != null ) {
      for( ItemCost cost : itemCosts ) {
        cost.item.amount -= cost.amount;
        System.out.println("Used " + cost.amount + " " + cost.item + ", Now have " + cost.item.amount + " " + cost.item);
      }
    }
    for( Action action : actions ) {
//      Driver.checkBlock();
      action.execute(r);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  
  public Item( Action[] actions, long time ) {
    this.actions = actions;
    this.time = time;
    if( costs == null ) {
      costs = new ArrayList<ResourceCost>();
      itemCosts = new ArrayList<ItemCost>();
    }
  }
  
  public Item(Action[] actions, long time, String name) {
    this(actions, time);
    this.name = name;
  }
  
  @Override
  public String toString() {
    return name;
  }
}
