import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SimpleSkinToneDetection {

    public static void main(String[] args) {
        
        String imagePath = "E:\\2-2\\AOOP\\project\\WhatsApp Image 2025-04-02 at 2.55.50 PM.jpeg";  
        try {
          
            BufferedImage image = ImageIO.read(new File(imagePath));
            
           
            Color averageColor = calculateAverageColor(image);
            String hexCode = rgbToHex(averageColor);
            
          
            String skinTone = classifySkinTone(averageColor);
            
            
            System.out.println("Skin Tone Results");
            System.out.println("Average RGB: " + averageColor.toString());
            System.out.println("Hex Code: " + hexCode);
            System.out.println("Skin Tone Classification: " + skinTone);
            
        } catch (IOException e) {
            System.out.println("Error loading the image.");
            e.printStackTrace();
        }
    }

    
    public static Color calculateAverageColor(BufferedImage image) {
        long totalRed = 0, totalGreen = 0, totalBlue = 0;
        int pixelCount = 0;

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = new Color(image.getRGB(x, y));
                totalRed += color.getRed();
                totalGreen += color.getGreen();
                totalBlue += color.getBlue();
                pixelCount++;
            }
        }

       
        int avgRed = (int) (totalRed / pixelCount);
        int avgGreen = (int) (totalGreen / pixelCount);
        int avgBlue = (int) (totalBlue / pixelCount);

        return new Color(avgRed, avgGreen, avgBlue);
    }

    
    public static String rgbToHex(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    
    public static String classifySkinTone(Color color) {
        
        double brightness = 0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue();
        
       
        if (brightness < 50) {
            return "Dark";
        } else if (brightness >= 50 && brightness <= 150) {
            return "Medium";
        } else {
            return "Light";
        }
    }
}
