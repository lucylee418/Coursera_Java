import edu.duke.*;
import java.io.File;

public class ImageSaver {

    public void doSave() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource image = new ImageResource(f);
            // Get the file name
            String fname = image.getFileName();
            // Generate a new name
            String newName = "copy-"+fname;
            // Assigne the new name
            image.setFileName(newName);
            // Show the image (not necessary)
            image.draw();
            // Save
            image.save();
        }
    }


    public static void main (String[] args) {
        System.out.println("Start!");
        ImageSaver pr = new ImageSaver();
        pr.doSave();
    }


}
