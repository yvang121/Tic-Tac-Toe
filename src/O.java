import acm.graphics.GCompound;
import acm.graphics.GImage;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Created by Ye Vang on 5/18/2015.
 */
public class O extends GCompound {
    private GImage O = null;
    public void buildOImage() {
        try {
            O = new GImage(ImageIO.read(getClass().getResource("/O.png")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        add(O);
    }
}
