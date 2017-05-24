package clicking;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class ImageProcessing {

  public BufferedImage original;
  public BufferedImage copy;
  public static List<NumberImage> numbers;
  public static BufferedImage details;
  public static BufferedImage okay;
  public static BufferedImage done;
  public static BufferedImage finish;

  public ImageProcessing(BufferedImage image) {
    this.original = image;
    copy = ImageProcessing.clone(image);
  }
  
  public Rectangle getGameScreen() {
    Rectangle rect = new Rectangle(300, 295, 967, 740);
    Graphics g = copy.getGraphics();
    //g.setColor(Color.red);
    //g.fillRect(rect.x, rect.y, rect.width, rect.height);
    int startx = 0;
    int width = 1700;
    int starty = 50;
    int height = 1000;
    int darkgray = -13948117;
//    System.out.println(darkgray);
    //copy.setRGB(200, 200, Color.white.getRGB());
    //new Color(copy.getRGB(x, y))
    
    int top = 9999;
    int bottom = 0;
    int left = 9999;
    int right = 0;
    for( int x = startx; x < startx+width; x++ ) {
      for(int y = starty; y < starty+height; y++ ) {
        int color = copy.getRGB(x, y);
        if( color == darkgray) {
          if( x < left ) 
            left = x;
          if( x > right) 
            right = x;
          if( y < top )
            top = y;
          if( y > bottom )
            bottom = y;
          copy.setRGB(x, y, Color.orange.getRGB());
        }
        else {
          copy.setRGB(x, y, Color.black.getRGB());
        }
      }
    }
    startx = left;
    starty = top;
    width = right-left;
    height = bottom - top;
    int[][] pixels = this.getPixelArray(copy, startx, starty, width, height);
    
    int[] hline = new int[height];
    for( int a = 0; a < hline.length; a++ ) {
      hline[a] = 0;
    }
    
    for( int y = 0; y < height; y++ ) {
      boolean match = true;
      int first = pixels[0][y];
      for( int x = 1; x < width; x++ ) {
        if( pixels[x][y] != first ) {
          match = false;
          break;
        }
      }
      if( match ) {
        hline[y] = 1;
      }
    }

    int id = 1;
    int[] hline2 = new int[height];
    for( int a = 0; a < hline2.length; a++ ) {
      hline2[a] = 0;
    }
    for( int a = 0; a < hline.length; a++ ) {
      if( hline[a] == 1 ) {
        if( a > 0 && hline[a-1] == 0 ) {
          id++;
        }
        hline2[a] = id;
      }
    }
    
    int[] hdist = new int[id];
    int[][] section = new int[id][2];
    boolean currentlyIn = false;
    int currentID = 0;
    int ended = 0;
    for( int a = 0; a < hline.length; a++ ) {
      if( hline[a] != 0 ) {
        if( !currentlyIn) {
          currentlyIn = true;
          
          int length = a - ended;
          section[currentID][0] = ended;
          section[currentID][1] = a;
          hdist[currentID] = length;
        }
      }
      else {
        if( currentlyIn ) {
          currentlyIn = false;
          currentID++;
          ended = a;
        }
      }
    }
    int longestStart = 0;
    int longestEnd = 0;
    int longest = 0;
    for( int a = 0; a< hdist.length; a++ ) {
      if( hdist[a] > longest ) {
        longest = hdist[a];
        longestStart = section[a][0];
        longestEnd = section[a][1];
      }
    }
//    System.out.println(longestStart + " -> " + longestEnd + " = " + longest);
    
    starty += longestStart;
    height = longestEnd - longestStart;
    
    int lastOrange = -1;
    int firstOrange = -1;
    for( int x = 0; x < width; x++ ) {
      for( int y = longestStart; y < height; y++ ) {
        if( pixels[x][y] != Color.orange.getRGB() ) {
          lastOrange = x;
          x = width;
          break;
        }
      }
    }
    for( int x = lastOrange+1; x < width; x++ ) {
      boolean all = true;
      for( int y = longestStart; y < height; y++ ) {
        if( pixels[x][y] != Color.orange.getRGB() ) {
          all = false;
          break;
        }
      }
      if( all ) {
        firstOrange = x;
        x = width;
      }
    }
//    System.out.println(lastOrange + ", " + firstOrange);
    startx += lastOrange;
    width = firstOrange - lastOrange;
    rect = new Rectangle(startx, starty, width, height);
//    for( int a = 0; a < hline2.length; a++ ) {
//      if( hline2[a] != 0 ) {
//        int blobid = hline2[a];
//        if (blobid%6 == 0) {
//          g.setColor(Color.RED);
//        }
//        else if (blobid%6 == 1) {
//          g.setColor(Color.GREEN);
//        }
//        else if (blobid%6 == 2) {
//          g.setColor(Color.BLUE);
//        }
//        else if (blobid%6 == 3) {
//          g.setColor(Color.MAGENTA);
//        }
//        else if (blobid%6 == 4) {
//          g.setColor(Color.YELLOW);
//        }
//        else {
//          g.setColor(Color.CYAN);
//        }
//        g.drawLine(startx, starty+a, startx+width, starty+a);
//      }
//    }
    g.setColor(Color.red);
    g.drawRect(startx-1, starty-1, width+2, height+2);
    
    
    return rect;
    
  }

  public void saveOriginal(String name) {
    try {
      System.out.println("Saving " + name);
      File outputfile = new File(name + ".png");
      ImageIO.write(original, "png", outputfile);
    } catch (IOException e) {
    }
  }

  public void saveCopy(String name) {
    try {
      System.out.println("Saving " + name);
      File outputfile = new File(name + ".png");
      ImageIO.write(copy, "png", outputfile);
    } catch (IOException e) {
    }
  }
  
  public void saveImage(String name, BufferedImage image) {
    try {
      System.out.println("Saving " + name);
      File outputfile = new File(name + ".png");
      ImageIO.write(image, "png", outputfile);
    } catch (IOException e) {
    }
  }
  
  private boolean isBlackPixel(Color color) {
    if (color.getRed() < 40 && color.getGreen() < 40 && color.getBlue() < 40) {
      return true;
    }
    return false;
  }
  private boolean isWhitePixel(Color color) {
    if (color.getRed() > 230 && color.getGreen() > 230 && color.getBlue() > 230) {
      return true;
    }
    return false;
  }
  
  private boolean isGreyPixel(Color color, int threshold, int brightness) {
    if (Math.abs(color.getRed() - color.getGreen()) < threshold
        && Math.abs(color.getBlue() - color.getGreen()) < threshold
        && Math.abs(color.getBlue() - color.getRed()) < threshold
        && color.getBlue() > brightness
        && color.getRed() > brightness
        && color.getGreen() > brightness
        ) {
      return true;
    }
    return false;
  }

  public int[][] getPixelArray(BufferedImage image, int startx, int starty, int width, int height ) {
    int[][] pixels = new int[width][height];
    for (int x = startx, px = 0; x < startx+width; x++, px++) {
      for (int y = starty, py = 0; y < starty+height; y++, py++) {
        Color color = new Color(copy.getRGB(x, y));
        pixels[px][py] = copy.getRGB(x, y);
      }
    }
    return pixels;
  }
  public boolean readResources(List<ResourceScreenLocation> resources) {
    int startx = 100;
    int width = 320;
    int starty = 670;
    int height = 80;
    for (int x = startx; x < startx+width; x++) {
      for (int y = starty; y < starty+height; y++) {
        Color color = new Color(copy.getRGB(x, y));
        copy.setRGB(x, y, Color.BLACK.getRGB());
        if( isGreyPixel(color, 50, 150) ) {
          copy.setRGB(x, y, Color.WHITE.getRGB());
        }
      }
    }
    
//    this.saveCopy("resources");
    
    int[][] pixels = getPixelArray(copy, startx, starty, width, height);
    int blobid = 0;
    boolean previous = false;
    int highestblobid = 0;
    for (int x = startx, px = 0; x < startx+width; x++, px++) {
      for (int y = starty, py = 0; y < starty+height; y++, py++) {
        Color color = new Color(copy.getRGB(x, y));
        if( isWhitePixel(color) ) {
          pixels[px][py] = blobid;
          highestblobid = blobid;
          previous = true;
        }
        else {
          pixels[px][py] = -1;
          if( previous ) {
            previous = false;
            blobid++;
          }
        }
      }
    }
    
    starty = starty-height-1;
    for (int x = startx; x < startx+width; x++) {
      for (int y = starty; y < starty+height; y++) {
        //Color color = new Color(copy.getRGB(x, y));
        blobid = pixels[x-startx][y-starty];
        if( blobid == -1) {
          copy.setRGB(x, y, Color.BLACK.getRGB());
        }
        else if (blobid%3 == 0) {
          copy.setRGB(x, y, Color.RED.getRGB());
        }
        else if (blobid%3 == 1) {
          copy.setRGB(x, y, Color.GREEN.getRGB());
        }
        else if (blobid%3 == 2) {
          copy.setRGB(x, y, Color.BLUE.getRGB());
        }
      }
    }
    int[] merged = new int[highestblobid+1];
    for( int i = 0; i < merged.length; i++ ) {
      merged[i] = -1;
    }
    // create up tree for all the columns
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        blobid = pixels[x][y];
        if( blobid != -1 ) {
          if( x+1 < width ) {
            if( pixels[x+1][y] != -1 ) {
              if( merged[blobid] == -1) {
                merged[blobid] = pixels[x+1][y];
              }
              else {
                merged[pixels[x+1][y]] = merged[blobid];
              }
            }
          }
        }
      }
    }
    for (int x = width-1; x >= 0; x--) {
      for (int y = 0; y < height; y++) {
        blobid = pixels[x][y];
        if( blobid != -1 ) {
          if( x-1 >= 0 ) {
            if( pixels[x-1][y] != -1 ) {
              if( merged[blobid] == -1) {
                merged[blobid] = pixels[x-1][y];
              }
              else {
                merged[pixels[x-1][y]] = merged[blobid];
              }
            }
          }
        }
      }
    }
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        blobid = pixels[x][y];
        if( blobid != -1 ) {
          if( x+1 < width ) {
            if( pixels[x+1][y] != -1 ) {
              if( merged[blobid] == -1) {
                merged[blobid] = pixels[x+1][y];
              }
              else {
                merged[pixels[x+1][y]] = merged[blobid];
              }
            }
          }
        }
      }
    }
    // reassign blobids based on uptree
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        blobid = pixels[x][y];
        if( blobid != -1 ) {
          pixels[x][y] = merged[blobid];
        }
      }
    }
    // reassign blobids starting from 0
    redistributeID(pixels);
    
    // delete small blobs
    deleteBlobsSmallerThan(pixels, 50);
    
    List<Rectangle> rectangles = getBlobRectangles(pixels);
    
    parseRectanglesForIntegers(rectangles);
    if( rectangles.size() < 10 ) {
      System.out.println("Unable to process resources");
      return false;
    }
    //pixels = newpixels;
    starty = starty-height-1;
    for (int x = startx; x < startx+width; x++) {
      for (int y = starty; y < starty+height; y++) {
        //Color color = new Color(copy.getRGB(x, y));
        blobid = pixels[x-startx][y-starty];
        if( blobid == -1) {
          copy.setRGB(x, y, Color.BLACK.getRGB());
        }
        else if (blobid%6 == 0) {
          copy.setRGB(x, y, Color.RED.getRGB());
        }
        else if (blobid%6 == 1) {
          copy.setRGB(x, y, Color.GREEN.getRGB());
        }
        else if (blobid%6 == 2) {
          copy.setRGB(x, y, Color.BLUE.getRGB());
        }
        else if (blobid%6 == 3) {
          copy.setRGB(x, y, Color.MAGENTA.getRGB());
        }
        else if (blobid%6 == 4) {
          copy.setRGB(x, y, Color.YELLOW.getRGB());
        }
        else if (blobid%6 == 5) {
          copy.setRGB(x, y, Color.CYAN.getRGB());
        }
      }
    }
    Graphics2D g = (Graphics2D) copy.getGraphics();
    for( Rectangle rect : rectangles ) {
      g.setColor(Color.white);
      g.drawRect(rect.x + startx, rect.y + starty, rect.width, rect.height);
    }
    
    List<NumLoc> locations = new ArrayList<NumLoc>();
    for( Rectangle rect : rectangles ) {
//      System.out.println(rect.x + " " + rect.y);
      BufferedImage image = getRectangleImage(pixels, rect);
      NumberImage closestImage = getClosestMatch(image);
      NumberLocation location = new NumberLocation(closestImage, rect.x, rect.y);
      locations.add(location);
    }
    //saveCopy("asdf");
    List<NumLoc> finallocations = new ArrayList<NumLoc>();
    for( int a = 0; a+1 < locations.size(); a++ ) {
      NumLoc one = locations.get(a);
      for( int b = a+1; b < locations.size(); b++ ) {
        NumLoc two = locations.get(b);
        if( Math.abs(one.getY() - two.getY()) < 5 ) {
          NumLoc left = one;
          NumLoc right = two;
          if( two.getX() < one.getX() ) {
            left = two;
            right = one;
          }
          if( Math.abs( left.getX() + 13 - right.getX() ) < 5 ) {
            NumLoc pair = new NumberPair(left.getNumberImage(), right.getNumberImage(), left.getX(), left.getY());
            finallocations.add(pair);
            locations.remove(b--);
            locations.remove(a--);
            b--;
          }
        }
      }
    }
    for( NumLoc leftover : locations ) {
      finallocations.add(leftover);
    }
//    System.out.println("Final Locations:");
//    for( NumLoc loc : finallocations ) {
//      System.out.println(loc.getValue() + " at position: " + loc.getX() + "," + loc.getY());
//    }
    boolean reloadNumbers = false;
    for( Rectangle rect : rectangles ) {
      if( addToCollection(getRectangleImage(pixels, rect)) ) {
        reloadNumbers = true;
      }
    }
//    System.out.print("Final Numbers:");
    
    try {
    for( NumLoc loc : finallocations ) {
//      System.out.print(" " + loc.getValue());
      ResourceScreenLocation closestResource = getClosestResourceWithin(loc.getX(), loc.getY(), resources, 10);
//      System.out.println("at position: " + loc.getX() + "," + loc.getY());
      if( closestResource == null ) {
        closestResource = getClosestResourceWithin(loc.getX() - 13, loc.getY(), resources, 10);
      }
      closestResource.resource.updateValue(loc.getValue());
    }
//    System.out.println();

    }
    catch( Exception e ) {
      return true;
    }
    return reloadNumbers;
  }

  
  public ResourceScreenLocation getClosestResourceWithin(int x, int y, List<ResourceScreenLocation> resources , int maxDistance) {
    int closest = 99999;
    ResourceScreenLocation closestResource = null;
    for( ResourceScreenLocation r : resources ) {
      int dist = r.distanceTo(x, y);
      if( dist < closest && dist < maxDistance) {
        dist = closest;
        closestResource = r;
      }
    }
    return closestResource;
  }
  
  public BufferedImage readFromFile(String name) {
    BufferedImage img = null;
    try {
        img = ImageIO.read(new File(name + ".png"));
    } catch (IOException e) {
    }
    return img;
  }
  
  public class NumberPair implements NumLoc {
    public NumberImage imageleft;
    public NumberImage imageright;
    public int x;
    public int y;
    public NumberPair(NumberImage imageleft, NumberImage imageright, int x, int y) {
      this.imageleft = imageleft;
      this.imageright = imageright;
      this.x = x;
      this.y = y;
    }
    @Override
    public int getValue() {
      return this.imageleft.number*10 + this.imageright.number;
    }
    @Override
    public int getX() {
      return x;
    }
    @Override
    public int getY() {
      return y;
    }
    @Override
    public NumberImage getNumberImage() {
      return imageleft;
    }
    
  }
  public interface NumLoc {
    public int getValue();
    public int getX();
    public int getY();
    public NumberImage getNumberImage();
  }
  public class NumberLocation implements NumLoc {
    public NumberImage image;
    public int x;
    public int y;
    public NumberLocation(NumberImage image, int x, int y ) {
      this.image = image;
      this.x = x;
      this.y = y;
    }
    
    public int getValue() {
      return image.number;
    }

    @Override
    public int getX() {
      return x;
    }

    @Override
    public int getY() {
      return y;
    }

    @Override
    public NumberImage getNumberImage() {
      return image;
    }
  }
  public class NumberImage {
    public BufferedImage image;
    public int number;
    public String name;
    public NumberImage(BufferedImage image, int number) {
      this.image = image;
      this.number = number;
    }
  }
  
  public NumberImage getClosestMatch(BufferedImage image) {
    int closest = 999999;
    NumberImage closestImage = null;
    for( NumberImage num : numbers ) {
      int diff = pixelsDifferent(num.image, image);
      if( diff < closest ) {
        closest = diff;
        closestImage = num;
      }
    }
    return closestImage;
  }
  
  
  public boolean addToCollection( BufferedImage image ) {
    NumberImage num = getClosestMatch(image);
    int diff = this.pixelsDifferent(num.image, image);
    char index = 'a';
    for( NumberImage numimage : numbers ) {
      if( numimage.number == num.number ) {
        index++;
      }
    }
    String name = "numbers/" + num.number + index;
    if( diff == 0 ) {
      //System.out.println("NOT ADDING TO COLLECTION: " + name + " diff=" + diff);
      return false;
    }
    else {
      System.out.println("ADDING TO COLLECTION: " + name + " diff=" + diff + " to " + num.name);
      saveImage(name, image);
      return true;
    }
  }
  
  public List<NumberImage> processNumbers() {
    
    details = this.readFromFile("numbers/details");
    okay = this.readFromFile("numbers/aokay");
    done = this.readFromFile("numbers/adone");
    finish = this.readFromFile("numbers/afinish");
    
    numbers = new ArrayList<NumberImage>();
    
    for( int number = 0; number <= 9; number++ ) {
      
      for( char suffix = 'a'; suffix <= 'z'; suffix++ ){
        BufferedImage image = readFromFile("numbers/" + number + suffix);
        if( image == null ) {
          break;
        }
        else {
          NumberImage num = new NumberImage(image, number);
          num.name =  "" + number + suffix;
          numbers.add(num);
          //System.out.println("read " + num.name);
        }
      }
    }
    
    int smallestdiff = 9999;
    for( int a = 0; a+1 < numbers.size(); a++ ) {
      NumberImage one = numbers.get(a);
      for( int b = a+1; b < numbers.size(); b++ ) {
        NumberImage two = numbers.get(b);
        int diff = pixelsDifferent(one.image, two.image);
        if( one.number == two.number ) {
          //System.out.println(one.name + " vs " + two.name + "\t" + diff);
        }
        else {
          if( diff < smallestdiff ) {
            smallestdiff = diff;
          }
        }
      }
    }
    //System.out.println("SmallestDifference was " + smallestdiff);
    
//    for( NumberImage num : numbers ) {
//      System.out.println(num.image);
//    }
    return numbers;
  }
  
  public int pixelsDifferent(BufferedImage one, BufferedImage two) {
    int diff = 0;
    int x = 0;
    int y = 0;
    for( x = 0; x < one.getWidth() && x < two.getWidth(); x++ ) {
      for( y = 0; y < one.getHeight() && y < two.getHeight(); y++ ) {
        if( one.getRGB(x, y) != two.getRGB(x, y) ) {
          diff++;
        }
      }
    }
    for( x = 0; (x < one.getWidth() && x >= two.getWidth()) || (x >= one.getWidth() && x < two.getWidth()); x++ ) {
      for( y = 0; (y < one.getHeight() && y >= two.getHeight()) || (y >= one.getHeight() && y < two.getHeight()); y++ ) {
        diff++;
      }
    }
    return diff;
  }
  
  public void parseRectanglesForIntegers(List<Rectangle> rectangles) {
    for( int a = rectangles.size()-1; a >= 0; a-- ) {
      Rectangle r = rectangles.get(a);
      if( r.width > r.height ) {
        rectangles.remove(r);
      }
      else if( r.x < 10 ) {
        rectangles.remove(r);
      }
      else if( r.width < 1 || r.height < 1 ) {
        rectangles.remove(r);
      }
    }
  }
  
  public BufferedImage getRectangleImage(int[][] pixels, Rectangle r) {
    BufferedImage save = new BufferedImage(r.width, r.height, BufferedImage.TYPE_INT_RGB);
    Graphics g = save.getGraphics();
    for (int x = 0; x < r.width; x++) {
      for (int y = 0; y < r.height; y++) {
        if( pixels[x+r.x][y+r.y] == -1 ) {
          g.setColor(Color.BLACK);
        }
        else {
          g.setColor(Color.WHITE);
        }
        g.drawRect(x, y, 1, 1);
      }
    }
    return save;
  }
  
  char index = 'g';
  public void saveRectangleAsImage(int[][] pixels, Rectangle r) {
    BufferedImage save = getRectangleImage(pixels, r);
    //String name = r.x + " " + r.y + " " + System.currentTimeMillis();
    String name = r.x + " " + r.y + " " + System.currentTimeMillis();
    saveImage(name, save);
  }
  
  public List<Rectangle> getBlobRectangles(int[][] pixels) {
    List<Rectangle> rectangles = new ArrayList<Rectangle>();
    int highestblobid = getHighestBlobID(pixels);
    for( int blobid = 0; blobid <= highestblobid; blobid++ ) {
      int top = pixels[0].length;
      int bottom = 0;
      int left = pixels.length;
      int right = 0;
      for (int x = 0; x < pixels.length; x++) {
        for (int y = 0; y < pixels[x].length; y++) {
          if( pixels[x][y] == blobid ) {
            if( x < left ) {
              left = x;
            }
            if( x > right ) {
              right = x;
            }
            if( y < top ) {
              top = y;
            }
            if( y > bottom ) {
              bottom = y;
            }
          }
        }
      } 
      Rectangle rect = new Rectangle(left, top, right-left, bottom - top);
      rectangles.add(rect);
    }
    return rectangles;
  }
  
  public void deleteBlobsSmallerThan(int[][] pixels, int minsize) {
    int highestblobid = getHighestBlobID(pixels);
    for( int blobid = 0; blobid <= highestblobid; blobid++ ) {
      int size = 0;
      for (int x = 0; x < pixels.length; x++) {
        for (int y = 0; y < pixels[x].length; y++) {
          if( pixels[x][y] == blobid ) {
            size++;
          }
        }
      } 
      if( size < minsize ) {
        for (int x = 0; x < pixels.length; x++) {
          for (int y = 0; y < pixels[x].length; y++) {
            if( pixels[x][y] == blobid ) {
              pixels[x][y] = -1;
            }
          }
        } 
      }
    }
  }
  public int getHighestBlobID(int[][] pixels) {
    int highestblobid = 0;
    for (int x = 0; x < pixels.length; x++) {
      for (int y = 0; y < pixels[x].length; y++) {
        if( pixels[x][y] > highestblobid ) {
          highestblobid = pixels[x][y];
        }
      }
    }
    return highestblobid;
  }
  
  public void redistributeID( int[][] pixels) {
    int highestblobid = getHighestBlobID(pixels);
    int newblobid = 0;
    for( int parse = 0; parse <=highestblobid; parse++ ) {
      boolean found = false;
      for (int x = 0; x < pixels.length; x++) {
        for (int y = 0; y < pixels[x].length; y++) {
          if( pixels[x][y] == parse ) {
            found = true;
            pixels[x][y] = newblobid;
          }
        }
      }
      if( found ) {
        newblobid++;
      }
    }
  }

  public boolean detectUpgrade() {

    int startx = 415;
    int width = 150;
    int starty = 525;
    int height = 50;

    int total = 0;
    int number = 0;
    
    int top = 9999;
    int bottom = 0;
    int left = 9999;
    int right = 0;
    int blueColor = -16739363;
    boolean hasText = false;
    int blueCounter = 0;
    for( int x = startx; x < startx+width; x++ ) {
      for (int y = starty; y <starty+height; y++) {
        if( Math.abs(original.getRGB(x, y) - blueColor ) < 10000 ) {
          blueCounter++;
        }
        
      }
    }
//    System.out.println(blueCounter);
    if( blueCounter <= 200 ) 
      return false;
    
    for( int x = startx; x < startx+width; x++ ) {
      for (int y = starty; y <starty+height; y++) {
        //System.out.println(original.getRGB(x, y));
        Color color = new Color(original.getRGB(x, y));
        total += copy.getRGB(x, y);
        number++;
        if( isGreyPixel(color, 10, 150) ) {
          if( x < left ) 
            left = x;
          if( x > right) 
            right = x;
          if( y < top )
            top = y;
          if( y > bottom )
            bottom = y;
          copy.setRGB(x, y, Color.WHITE.getRGB());
        }
        else {
          copy.setRGB(x, y, Color.BLACK.getRGB());
        }
      }
    }
    BufferedImage tempDetails = new BufferedImage(right-left, bottom-top, BufferedImage.TYPE_INT_RGB);
    for( int x = left; x < right; x++ ) {
      for (int y = top; y < bottom; y++) {
        Color color = new Color(copy.getRGB(x, y));
        tempDetails.setRGB(x-left, y-top, color.getRGB());
      }
    }
    
    int diff = this.pixelsDifferent(details, tempDetails);
    System.out.println("Pixels different of Details:" + diff);
    if( diff < 100 ) {
      return true;
    }
    else {
      return false;
    }

//    Graphics g = copy.getGraphics();
//    g.drawRect(left, top, right-left, bottom-top);
//    this.saveImage("details", details);
//    double average = 1.0*total/number;
////    System.out.println("Average:" + average);
//    int threshold = 10;
//    int target = -75937;
//    if( Math.abs(average - target) < threshold ) {
//      return true;
//    }
//    else {
//      return false;
//    }
  }
  
  public boolean detectLag() {

    boolean lag = false;
    for( int yoffset = 0; yoffset < 20 && !lag; yoffset++ ) {
      lag = true;
      int startx = 300;
      int width = 350;
      int starty = 330 + yoffset;
      int height = 1;
      
      for( int x = startx; x < startx+width; x++ ) {
        for (int y = starty; y <starty+height; y++) {
          Color color = new Color(original.getRGB(x, y));
          copy.setRGB(x, y, Color.RED.getRGB());
          if( !isWhitePixel(color) ) {
            lag = false;
          }
        }
      }
    }
    return lag;
  }
  
  public boolean detectFinishCrafting() {

    int startx = 370;
    int width = 240;
    int starty = 165;
    int height = 50;

    int total = 0;
    int number = 0;
    
    int top = 9999;
    int bottom = 0;
    int left = 9999;
    int right = 0;
    int blueColor = -12635610;
    boolean hasText = false;
    int blueCounter = 0;
    for( int x = startx; x < startx+width; x++ ) {
      for (int y = starty; y <starty+height; y++) {
//        System.out.println(original.getRGB(x, y));
        if( Math.abs(original.getRGB(x, y) - blueColor ) < 10000 ) {
          blueCounter++;
        }
        
      }
    }
    
    for( int x = startx; x < startx+width; x++ ) {
      for (int y = starty; y <starty+height; y++) {
        //System.out.println(original.getRGB(x, y));
        Color color = new Color(original.getRGB(x, y));
        total += copy.getRGB(x, y);
        number++;
        if( isGreyPixel(color, 10, 150) ) {
          if( x < left ) 
            left = x;
          if( x > right) 
            right = x;
          if( y < top )
            top = y;
          if( y > bottom )
            bottom = y;
          copy.setRGB(x, y, Color.WHITE.getRGB());
        }
        else {
          copy.setRGB(x, y, Color.BLACK.getRGB());
        }
      }
    }
    if( right-left <= 0 || bottom-top <= 0) {
      return false;
    }
    BufferedImage tempAchiev = new BufferedImage(right-left+1, bottom-top+1, BufferedImage.TYPE_INT_RGB);
    for( int x = left; x <= right; x++ ) {
      for (int y = top; y <= bottom; y++) {
        Color color = new Color(copy.getRGB(x, y));
        tempAchiev.setRGB(x-left, y-top, color.getRGB());
      }
    }
    
    int diff = this.pixelsDifferent(finish, tempAchiev);
    if( diff < 20 ) {
      System.out.println("Pixels different of Finish Crafting Now:" + diff);
      return true;
    }
    else {
      return false;
    }

//    Graphics g = copy.getGraphics();
//    g.drawRect(left, top, right-left, bottom-top);
//    this.saveImage("afinish", tempAchiev);
//    double average = 1.0*total/number;
////    System.out.println("Average:" + average);
//    int threshold = 10;
//    int target = -75937;
//    if( Math.abs(average - target) < threshold ) {
//      return true;
//    }
//    else {
//      return false;
//    }
  }
  
  public boolean detectCapcha() {
    int startx = 335;
    int width = 40;
    int starty = 350;
    int height = 150;
    
    int total = 0;
    int number = 0;
    
    for( int x = startx; x < startx+width; x++ ) {
      for (int y = starty; y <starty+height; y++) {
        Color color = new Color(original.getRGB(x, y));
        total += copy.getRGB(x, y);
        number++;
        copy.setRGB(x, y, Color.RED.getRGB());
      }
    }
    double average = 1.0*total/number;
//    System.out.println("Average:" + average);
    int threshold = 10;
    int target = -75937;
    if( Math.abs(average - target) < threshold ) {
      return true;
    }
    else {
      return false;
    }
  }
  
  public boolean detectUnlock() {

    int startx = 710;
    int width = 130;
    int starty = 385;
    int height = 50;

    int total = 0;
    int number = 0;
    
    int top = 9999;
    int bottom = 0;
    int left = 9999;
    int right = 0;
    int blueColor = -16739363;
    boolean hasText = false;
    int blueCounter = 0;
    for( int x = startx; x < startx+width; x++ ) {
      for (int y = starty; y <starty+height; y++) {
        if( Math.abs(original.getRGB(x, y) - blueColor ) < 10000 ) {
          blueCounter++;
        }
        
      }
    }
//    System.out.println(blueCounter);
    if( blueCounter <= 4000 ) 
      return false;
    
    for( int x = startx; x < startx+width; x++ ) {
      for (int y = starty; y <starty+height; y++) {
        //System.out.println(original.getRGB(x, y));
        Color color = new Color(original.getRGB(x, y));
        total += copy.getRGB(x, y);
        number++;
        if( isGreyPixel(color, 10, 150) ) {
          if( x < left ) 
            left = x;
          if( x > right) 
            right = x;
          if( y < top )
            top = y;
          if( y > bottom )
            bottom = y;
          copy.setRGB(x, y, Color.WHITE.getRGB());
        }
        else {
          copy.setRGB(x, y, Color.BLACK.getRGB());
        }
      }
    }
    BufferedImage tempAchiev = new BufferedImage(right-left+1, bottom-top+1, BufferedImage.TYPE_INT_RGB);
    for( int x = left; x <= right; x++ ) {
      for (int y = top; y <= bottom; y++) {
        Color color = new Color(copy.getRGB(x, y));
        tempAchiev.setRGB(x-left, y-top, color.getRGB());
      }
    }
    
    int diff = this.pixelsDifferent(done, tempAchiev);
    System.out.println("Pixels different of item unlock:" + diff);
    if( diff < 20 ) {
      return true;
    }
    else {
      return false;
    }

//    Graphics g = copy.getGraphics();
//    g.drawRect(left, top, right-left, bottom-top);
//    this.saveImage("adone", tempAchiev);
//    double average = 1.0*total/number;
////    System.out.println("Average:" + average);
//    int threshold = 10;
//    int target = -75937;
//    if( Math.abs(average - target) < threshold ) {
//      return true;
//    }
//    else {
//      return false;
//    }
  }
  
  public boolean detectAchievement() {

    int startx = 477;
    int width = 25;
    int starty = 525;
    int height = 25;

    int total = 0;
    int number = 0;
    
    int top = 9999;
    int bottom = 0;
    int left = 9999;
    int right = 0;
    int blueColor = -16739363;
    boolean hasText = false;
    int blueCounter = 0;
    for( int x = startx; x < startx+width; x++ ) {
      for (int y = starty; y <starty+height; y++) {
        if( Math.abs(original.getRGB(x, y) - blueColor ) < 10000 ) {
          blueCounter++;
        }
        
      }
    }
//    System.out.println(blueCounter);
    if( blueCounter <= 200 ) 
      return false;
    
    for( int x = startx; x < startx+width; x++ ) {
      for (int y = starty; y <starty+height; y++) {
        //System.out.println(original.getRGB(x, y));
        Color color = new Color(original.getRGB(x, y));
        total += copy.getRGB(x, y);
        number++;
        if( isGreyPixel(color, 10, 150) ) {
          if( x < left ) 
            left = x;
          if( x > right) 
            right = x;
          if( y < top )
            top = y;
          if( y > bottom )
            bottom = y;
          copy.setRGB(x, y, Color.WHITE.getRGB());
        }
        else {
          copy.setRGB(x, y, Color.BLACK.getRGB());
        }
      }
    }
    BufferedImage tempAchiev = new BufferedImage(right-left+1, bottom-top+1, BufferedImage.TYPE_INT_RGB);
    for( int x = left; x <= right; x++ ) {
      for (int y = top; y <= bottom; y++) {
        Color color = new Color(copy.getRGB(x, y));
        tempAchiev.setRGB(x-left, y-top, color.getRGB());
      }
    }
    
    int diff = this.pixelsDifferent(okay, tempAchiev);
    System.out.println("Pixels different of achievement:" + diff);
    if( diff < 30 ) {
      return true;
    }
    else {
      return false;
    }

//    Graphics g = copy.getGraphics();
//    g.drawRect(left, top, right-left, bottom-top);
////    this.saveImage("aokay", tempAchiev);
//    double average = 1.0*total/number;
////    System.out.println("Average:" + average);
//    int threshold = 10;
//    int target = -75937;
//    if( Math.abs(average - target) < threshold ) {
//      return true;
//    }
//    else {
//      return false;
//    }
  }
  
  public boolean detectTrading() {
    int startx = 850;
    int width = 100;
    int starty = 150;
    int height = 100;
    
    int total = 0;
    int number = 0;
    
    for( int x = startx; x < startx+width; x++ ) {
      for (int y = starty; y <starty+height; y++) {
        Color color = new Color(copy.getRGB(x, y));
        total += copy.getRGB(x, y);
        number++;
        copy.setRGB(x, y, Color.RED.getRGB());
      }
    }
    double average = 1.0*total/number;
//    System.out.println("Average:" + average);
    int threshold = 100;
    int target1 = 33789;
    int target2 = 32875;
        
    if( Math.abs(average - target1) < threshold || Math.abs(average - target2) < threshold ) {
      return true;
    }
    else {
      return false;
    }
  }
  
  public boolean detectNightTime() {
    boolean night = true;
    for (int x = 0; x < 250; x++) {
      for (int y = 50; y < 150; y++) {
        Color color = new Color(original.getRGB(x, y));
        if (color.getRed() < 40 && color.getGreen() < 40 && color.getBlue() < 40) {
          copy.setRGB(x, y, Color.RED.getRGB());
        } else {
          night = false;
        }
      }
    }
    return night;
  }

  public void turnBlacktoRed() {
    for (int x = 0; x < copy.getWidth(); x++) {
      for (int y = 0; y < copy.getHeight(); y++) {
        Color color = new Color(copy.getRGB(x, y));
        if (color.getRed() < 50 && color.getGreen() < 50 && color.getBlue() < 50) {
          copy.setRGB(x, y, Color.RED.getRGB());
        }
      }
    }
  }

  public void turnWhitetoCyan() {
    for (int x = 0; x < copy.getWidth(); x++) {
      for (int y = 0; y < copy.getHeight(); y++) {
        Color color = new Color(copy.getRGB(x, y));
        if (color.getRed() > 220 && color.getGreen() > 220 && color.getBlue() > 220) {
          copy.setRGB(x, y, Color.CYAN.getRGB());
        }
      }
    }
  }

  public static final BufferedImage clone(BufferedImage image) {
    BufferedImage clone = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
    Graphics2D g2d = clone.createGraphics();
    g2d.drawImage(image, 0, 0, null);
    g2d.dispose();
    return clone;
  }

}
