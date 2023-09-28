//This code file customizes the menu screen displayed when the code is run
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class MenuScreen extends Screen {

    public KP keyListener;
    public MK mouseListener;
    public BufferedImage name, play, playPushed, exit, exitPushed;
    public Rectangle playRectangle, exitRectangle, nameRectangle;

    public BufferedImage playPresentImage, exitPresentImage;

    public MenuScreen(KP keyListener, MK mouseListener) {
        this.keyListener = keyListener;
        this.mouseListener = mouseListener;

        try {
            BufferedImage spritesheet = ImageIO.read(new File("assets/menuSprite.png"));
            name = spritesheet.getSubimage(0, 242, 960, 240);
            play = spritesheet.getSubimage(0, 121, 261, 121);
            playPushed = spritesheet.getSubimage(264, 121, 261, 121);
            exit = spritesheet.getSubimage(0, 0, 233, 93);
            exitPushed = spritesheet.getSubimage(264, 0, 233, 93);

        }

        catch (Exception e) {
            e.printStackTrace();
        }

        playPresentImage = play;
        exitPresentImage = exit;

        nameRectangle = new Rectangle(230, 100, 320, 100);
        playRectangle = new Rectangle(310, 280, 150, 70);
        exitRectangle = new Rectangle(318, 355, 130, 55);

    }

    @Override
    public void update(double dt) {
        
        if (mouseListener.getX() >= playRectangle.x && mouseListener.getX() <= playRectangle.x + playRectangle.width &&
                mouseListener.getY() >= playRectangle.y && mouseListener.getY() <= playRectangle.y + playRectangle.height) {
            playPresentImage = playPushed;

            if (mouseListener.isPressed()){
                Window.getWindow().changeState(1);
            }
            
        } else {

            playPresentImage = play;
        }

        if (mouseListener.getX() >= exitRectangle.x && mouseListener.getX() <= exitRectangle.x + exitRectangle.width &&
                mouseListener.getY() >= exitRectangle.y && mouseListener.getY() <= exitRectangle.y + exitRectangle.height) {
            exitPresentImage = exitPushed;
           if(mouseListener.isPressed()){
            Window.getWindow().close();
           }

           
        } else {

            exitPresentImage = exit;
        }
    }

    @Override
    public void draw(Graphics g) {

        g.setColor(new Color(64, 106, 131));
        g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);


     


        g.drawImage(name, (int) nameRectangle.x, (int) nameRectangle.y, (int) nameRectangle.width,
                (int) nameRectangle.height, null);
        g.drawImage(play, (int) playRectangle.x, (int) playRectangle.y, (int) playRectangle.width,
                (int) playRectangle.height, null);
        g.drawImage(exit, (int) exitRectangle.x, (int) exitRectangle.y, (int) exitRectangle.width,
                (int) exitRectangle.height, null);

    }

}
