package model;

public class ImageList {
  int id;
  
  String path;
  
  String folder;
  
  public ImageList() {}
  
  public ImageList(int id, String path, String folder) {
    this.id = id;
    this.path = path;
    this.folder = folder;
  }
  
  public int getId() {
    return this.id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public String getPath() {
    return this.path;
  }
  
  public void setPath(String path) {
    this.path = path;
  }
  
  public String getFolder() {
    return this.folder;
  }
  
  public void setFolder(String folder) {
    this.folder = folder;
  }
}
