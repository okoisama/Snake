//This code handles the screen seen while the game is being played
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.KeyEvent;



public class GameScreen extends Screen {
    Rectangle background, foreground;
    Snake snake;
    KP keyListener;

    public Food food;
    
    public GameScreen(KP keyListener){
        background = new Rectangle(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        foreground = new Rectangle(24, 48, Constants.TILE_WIDTH * 31, Constants.TILE_WIDTH * 22);
        snake = new Snake(2, 48, 48+24, 24, 24, foreground);
        this.keyListener = keyListener;
        food = new Food(foreground, snake, 12, 12, Color.PINK);
        food.generate();
    }


    @Override
    public void update(double dt) {
        
        if (keyListener.isKeyPushed(KeyEvent.VK_UP))
        {
            snake.alterDirection(Direction.NORTH);

        } else if (keyListener.isKeyPushed(KeyEvent.VK_DOWN))
        {
            snake.alterDirection(Direction.SOUTH);
        }else if (keyListener.isKeyPushed(KeyEvent.VK_RIGHT))
        {
            snake.alterDirection(Direction.EAST);
        }else if (keyListener.isKeyPushed(KeyEvent.VK_LEFT))
        {
            snake.alterDirection(Direction.WEST);
        }
        

        if (!food.isGenerated) food.generate();

        food.update(dt);
        snake.update(dt);
        
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);
        g2.fill( new Rectangle2D.Double(background.x , background.y, background.width, background.height));
        
        g2.setColor(Color.WHITE);
        g2.fill( new Rectangle2D.Double(foreground.x , foreground.y, foreground.width, foreground.height));

        snake.draw(g2);
        food.draw(g2);
    }
    
}
