//This class is used to differentiate between the main menu screen and the game screen
import java.awt.*;

public abstract class Screen {
    public abstract void update(double dt);
    public abstract void draw(Graphics g);

}