import edu.duke.*;
import java.io.File;

public class ImageInversion {

    public ImageResource makeGray(ImageResource inImage) {
        // Make an empty ImageResource
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        // Check every pixel in outImage
        for (Pixel outPixel: outImage.pixels()) {
            // Get same pixel in inImage
            Pixel inPixel = inImage.getPixel(outPixel.getX(), outPixel.getY());
            // // Compute the average
            // int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen()) / 3 ;
            // Fill in the pixel in outImage
            outPixel.setRed(255-inPixel.getRed());
            outPixel.setGreen(255-inPixel.getGreen());
            outPixel.setBlue(255-inPixel.getBlue());
        }
        return outImage;
    }


    public void multipleGray() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource outImage = makeGray(inImage);
            // Get the original name
            String oldName = inImage.getFileName();
            // Generate a new name
            String newName = "inverted-"+oldName;
            // Assigne the new name
            outImage.setFileName(newName);
            // Save
            outImage.save();
            // Show
            outImage.draw();
        }
    }


    public static void main (String[] args) {
        System.out.println("Start!");
        ImageInversion pr = new ImageInversion();
        pr.multipleGray();
    }


    
}
