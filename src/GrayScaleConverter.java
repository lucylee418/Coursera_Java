import edu.duke.*;
import java.io.File;

public class GrayScaleConverter {

    public ImageResource makeGray(ImageResource inImage) {
        // Make an empty ImageResource
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        // Check every pixel in outImage
        for (Pixel outPixel: outImage.pixels()) {
            // Get same pixel in inImage
            Pixel inPixel = inImage.getPixel(outPixel.getX(), outPixel.getY());
            // Compute the average
            int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen()) / 3 ;
            // Fill in the pixel in outImage
            outPixel.setRed(average);
            outPixel.setGreen(average);
            outPixel.setBlue(average);
        }
        return outImage;
    }


    public void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource gray = makeGray(inImage);
            gray.draw();
        }
    }


    public void testGray() {
        ImageResource ir = new ImageResource();
        ImageResource gray = makeGray(ir);
        gray.draw();
    }


    public static void main (String[] args) {
        System.out.println("Start!");
        GrayScaleConverter pr = new GrayScaleConverter();
        // pr.testGray();
        pr.selectAndConvert();
    }

}