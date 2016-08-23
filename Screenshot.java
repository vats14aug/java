import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.awt.Robot;
import java.io.File;
/**
 * Taking screenshots secretly, 007.
 * 
 * @author Vivek Kumar 
 * @version 0.1
 */
public class Screenshot
{
    public static void main(String[] args)
    {
        try
        {
           Robot robot = new Robot(); 
           int index = 0;
           
           while(true)
           {
               Thread.sleep(10000);
               Toolkit tool = Toolkit.getDefaultToolkit();
               Dimension ScrnSize = tool.getScreenSize();
               
               Rectangle Rect = new Rectangle(ScrnSize);
               BufferedImage ScreenShot = robot.createScreenCapture(Rect);
               
               
               File ImgFile = new File("Screen"+index+".JPG");
               ImageIO.write(ScreenShot,"jpg",ImgFile);
               index++;
               
           }
           
        }
        catch(Exception E)
        {
            E.printStackTrace();
        }
    }
}
