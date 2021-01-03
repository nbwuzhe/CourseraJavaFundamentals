
/**
 * Write a description of ImageInvertor here.
 * 
 * @author Tim Wu 
 * @version 0.1
 */
import edu.duke.*;
import java.io.*;

public class ImageInvertor {
    public ImageResource makeInversion(ImageResource inImage) {
        int width = inImage.getWidth();
        int height = inImage.getHeight();
        ImageResource outImage = new ImageResource(width, height);
        
        for (Pixel p : outImage.pixels()) {
            int x = p.getX();
            int y = p.getY();
            Pixel pIn = inImage.getPixel(x, y);
            
            int red = pIn.getRed();
            int green = pIn.getGreen();
            int blue = pIn.getBlue();
            
            red = 255 - red;
            green = 255 - green;
            blue = 255 - blue;
            
            p.setRed(red);
            p.setGreen(green);
            p.setBlue(blue);
        }
        
        return outImage;
    }
    
    public void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource outImage = makeInversion(inImage);
            
            String fn = inImage.getFileName();
            String fnOut = "inverted-" + fn;
            
            outImage.setFileName(fnOut);
            outImage.save();
            outImage.draw();
        }
    }
}
