import acm.graphics.GCompound;
import acm.graphics.GImage;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Created by Ye Vang on 5/18/2015.
 */
public class X extends GCompound {
    private GImage X = null;
    public void buildXImage() {
        try {
            X = new GImage(ImageIO.read(getClass().getResource("/X.png")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        add(X);
    }
}
