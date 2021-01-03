
/**
 * Write a description of GrayScaleConverter here.
 * 
 * @author Tim Wu 
 * @version 0.1
 */
import edu.duke.*;
import java.io.*;

public class GrayScaleConverter {
    public ImageResource makeGray(ImageResource inImage) {
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
            int avgIntensity = (red+green+blue)/3;
            
            p.setRed(avgIntensity);
            p.setBlue(avgIntensity);
            p.setGreen(avgIntensity);
        }
        
        return outImage;
    }
    
    public void batchConvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource outImage = makeGray(inImage);
            
            String fn = inImage.getFileName();
            String fnOut = "gray-" + fn;
            
            outImage.setFileName(fnOut);
            outImage.save();
            outImage.draw();
        }
    }
    
    public void testConvertToGray() {
        ImageResource inImage = new ImageResource();
        inImage.draw();
        ImageResource outImage = makeGray(inImage);
        outImage.draw();
    }
}
