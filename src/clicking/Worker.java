package clicking;

import java.awt.Robot;

public class Worker {

  private Item[][] orderedAgenda;
  private Item[][] unorderedAgenda;
  private int[] orderedIndex;
//  private Item[] priorityAgenda;
//  private int sidePriority;
//  private Item[] agenda;
//  private int index;
  private long timeWorked;
  private boolean startedWorking;
  private Item workingOn;
  
  private Action action;
  
  private String name;
  
  public Worker(Action action, Item[][] orderedAgenda, String name) {
    this.action = action;
    this.orderedAgenda = orderedAgenda;
    unorderedAgenda = new Item[][] {{}};
    this.name = name;
//    this.index = 0;
    this.timeWorked = 0;
    orderedIndex = new int[orderedAgenda.length];
    for( int a = 0; a < orderedIndex.length; a++ ) {
      orderedIndex[a] = 0;
    }
//    priorityAgenda = new Item[0];
  }
  public Worker(Action action, Item[][] orderedAgenda, Item[][] unorderedAgenda, String name) {
    this(action, orderedAgenda, name);
    this.unorderedAgenda = unorderedAgenda;
  }
  

//  public Worker(Action action, Item[] agenda, Item[] priorityAgenda, String name) {
//    this(action, new Item[][] {agenda}, name);
////    this.priorityAgenda = priorityAgenda;
////    this.sidePriority = Item.PRIORITY_HIGH;
//  }
  
//  public Worker(Action action, Item[] agenda, Item[] sideagenda, int priority, String name) {
//    this(action, new Item[][] {agenda}, name);
////    this.priorityAgenda = sideagenda;
////    this.sidePriority = priority;
//  }
  
  public void startNextItem(Robot r) {
    
    if( !startedWorking ) {
      for( int priority = 0; priority < orderedAgenda.length || priority < unorderedAgenda.length; priority++ ) {
        if( priority < orderedAgenda.length) {
          Item[] agenda = orderedAgenda[priority];
          int index = orderedIndex[priority];
          if( agenda.length > 0 && agenda[index].costsSatisfied(orderedAgenda, unorderedAgenda, priority) ) {
            startWorkingOn(agenda[index], r);
            orderedIndex[priority] = (index + 1) % agenda.length;
            return;
          }
        }
        if (priority < unorderedAgenda.length) {
          for( Item item : unorderedAgenda[priority] ) {
            if( item.costsSatisfied(orderedAgenda, unorderedAgenda, priority) ) {
              startWorkingOn(item, r);
              return;
            }
          }
        }
      }
      
//      if( sidePriority == Item.PRIORITY_HIGH ) {
//        for( Item item : priorityAgenda ) {
//          if( item.costsSatisfied() ) {
//            startWorkingOn(item, r);
//            return;
//          }
//        }
//      }
//      if( agenda.length > 0 && agenda[index].costsSatisfied() ) {
//        startWorkingOn(agenda[index], r);
//        return;
//      }
//      if( sidePriority == Item.PRIORITY_LOW ) {
//        for( Item item : priorityAgenda ) {
//          if( item.costsSatisfied() ) {
//            startWorkingOn(item, r);
//            return;
//          }
//        }
//      }
    }
  }
  
  private void startWorkingOn(Item item, Robot r ) {
    if( Driver.DEBUG ) {
      System.out.println(this + " starting " + item );
    }
    workingOn = item;
    action.execute(r);
    item.startWork(r);
    //agenda[index].getAction().execute(r);
    timeWorked = 0;
    startedWorking = true;
  }
  
  /**
   * 
   * @param time
   * @return true if finished an item on the agenda, false otherwise
   */
  public boolean work( long time ) {
    if( startedWorking ) {
      timeWorked += time;
      if( workingOn.isDone(timeWorked) ) {
//        if( agenda.length > 0 && agenda[index] == workingOn ) {
//          index = (index + 1) % agenda.length;
//        }
        startedWorking = false;
        workingOn.amount++;
        System.out.println("Now have " + workingOn.amount + " " + workingOn.toString());
        workingOn = null;
        Driver.itemsCrafted ++;

        return true;
      }
      return false;
    }
    return true;
  }
  
  @Override
  public String toString() {
    return name;
  }
}
