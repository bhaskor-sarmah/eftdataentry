package util;

import java.io.File;
import java.util.ArrayList;

public class CustomUtils {
  public static ArrayList<File> listf(String directoryName, ArrayList<File> resultList) {
    File directory = new File(directoryName);
    File[] fList = directory.listFiles();
    for (File file : fList) {
      if (file.isDirectory()) {
        System.out.println("DIRECTORY :" + file.getAbsolutePath());
        listf(file.getAbsolutePath(), resultList);
      } else if (file.getName().contains(".JPG") || file.getName().contains(".jpg")) {
        resultList.add(file);
      } 
    } 
    return resultList;
  }
}
