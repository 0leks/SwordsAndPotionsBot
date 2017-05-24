package clicking;

public class ResourceScreenLocation {

  public Resource resource;
  public int x;
  public int y;
  
  public ResourceScreenLocation(Resource resource, int x, int y ) {
    this.resource = resource;
    this.x = x;
    this.y = y;
  }
  
  public int distanceTo(int nx, int ny) {
    return Math.abs(x-nx) + Math.abs(y-ny);
  }
}
