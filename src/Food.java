//This file contains code that handles the spawning of food for the snake
import java.awt.*;

public class Food 
{

    public Rectangle background;
    public Snake snake;
    public int width, height;
    public Color color;
    public Rectangle rectangle;

    
    public int xCentring;

    public boolean isGenerated = false;

    public Food(Rectangle background, Snake snake, int width, int height, Color color)
    {
        this.background = background;
        this.snake = snake;
        this.width = width;
        this.height = height;
        this.color = color;
        this.rectangle = new Rectangle(0, 0, width, height);

        xCentring = (int)((Constants.TILE_WIDTH - this.width) / 2.0);
    }

    public void generate()
    {
        do
        {
            double randomX = (int)(Math.random() * (int)(background.width/ Constants.TILE_WIDTH)) * Constants.TILE_WIDTH + background.x;
            double randomY = (int)(Math.random() * (int)(background.height/ Constants.TILE_WIDTH)) * Constants.TILE_WIDTH + background.y;
            this.rectangle.x = randomX;
            this.rectangle.y = randomY;
        }
        while(snake.bisectingWithRectangle(this.rectangle));
        
        this.isGenerated = true;

    }
    
  
    public void update(double dt)
    {
        if (snake.bisectingWithRectangle(this.rectangle))
        {
            snake.grow();
            this.rectangle.x = -100;
            this.rectangle.y = - 100;

            isGenerated = false;
         }
    }


//this section creates a drawing of the food
    public void draw (Graphics2D g2)
    {
        g2.setColor(color);
        g2.fillRect((int)this.rectangle.x + xCentring, (int)this.rectangle.y + xCentring, width, height);
    }
}
