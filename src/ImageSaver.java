import edu.duke.*;
import java.io.File;

public class ImageSaver {

    public void doSave() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource image = new ImageResource(f);
            image.draw();
        }
    }


    public static void main (String[] args) {
        System.out.println("Start!");
        ImageSaver pr = new ImageSaver();
        pr.doSave();
    }



}
