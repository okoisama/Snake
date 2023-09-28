//This file contains code that handles the appearance of the snake, its movements and growth
import java.awt.*;
import java.awt.geom.*;

import javax.swing.plaf.basic.BasicLabelUI;

public class Snake {
    public Rectangle[] figure = new Rectangle[30];
    public double figureWidth, figureHeight;
    
    public int size;
    public int tail = 0;
    public int head = 0;

    public Direction direction = Direction.EAST;

    public double oIntervals = 0.2f;
    public double pauseTime = oIntervals;

    public Rectangle background;

    public Snake(int size, double firstX, double firstY, double figureWidth, double figureHeight, Rectangle background)
    {
        this.size = size;
        this.figureWidth = figureWidth;
        this.figureHeight = figureHeight;
        this.background = background;

        for (int j = 0; j <= size; j++)
        {
            Rectangle figurePart = new Rectangle(firstX + j * figureWidth, firstY, figureWidth, figureHeight);
            figure[j] = figurePart;
            head++;
        }

        head--;

    }

    public void alterDirection(Direction newDirection)
    {   
        if (newDirection == Direction.EAST && direction != Direction.WEST)
        direction = newDirection;
        else if (newDirection == Direction.WEST && direction != Direction.EAST)
        direction = newDirection;
        else if (newDirection == Direction.NORTH && direction != Direction.SOUTH)
        direction = newDirection;
        else if (newDirection == Direction.SOUTH && direction != Direction.NORTH)
        direction = newDirection;
    }

    public void update(double dt) 
    {

        if(pauseTime > 0)
        {
            pauseTime -= dt;
            return;
        }

        if(bisectingWithSelf())
        {
            Window.getWindow().changeState(0);
        }

        
        pauseTime = oIntervals;
        double newX = 0;
        double newY = 0;

        if(direction == Direction.EAST)
        {
            newX = figure[head].x + figureWidth;
            newY = figure[head].y;

        }
        else if (direction == Direction.WEST){
            newX = figure[head].x - figureWidth;
            newY = figure[head].y;
        }
        else if (direction == Direction.NORTH){
            newX = figure[head].x;
            newY = figure[head].y - figureHeight;
        }
        else if (direction == Direction.SOUTH){
            newX = figure[head].x;
            newY = figure[head].y + figureHeight;
        }

        figure[(head + 1) % figure.length] = figure[tail];
        figure[tail] = null;
       
        head = (head + 1) % figure.length;
        tail = (tail + 1) % figure.length;
        

        figure[head].x = newX;
        figure[head].y = newY;


        

    }


    public boolean bisectingWithSelf()
    {
        Rectangle rHead = figure[head];

        return bisectingWithRectangle(rHead) || bisectingBorder(rHead);
    }

    public boolean bisectingWithRectangle(Rectangle rectangle)
    {
                
        for(int j = tail; j != head; j =(j + 1) % figure.length)
        {
            if(bisecting(rectangle, figure[j]))
            return true;
        }

        return false;
    }

    public boolean bisecting(Rectangle r1, Rectangle r2)
    {
        return(r1.x >= r2.x && r1.x + r1.width <= r2.x + r2.width &&
               r1.y >= r2.y && r1.y + r1.height <= r2.y + r2.height);
    }


    public boolean bisectingBorder(Rectangle head)
    {
        return(head.x < background.x || (head.x + head.width) > background.x + background.width ||
               head.y < background.y || (head.y + head.height) > background.y + background.height);
   
    }


    public void grow()
    {
        double newX = 0;
        double newY = 0;

        if(direction == Direction.EAST)
        {
            newX = figure[tail].x - figureWidth;
            newY = figure[tail].y;

        }
        else if (direction == Direction.WEST){
            newX = figure[tail].x - figureWidth;
            newY = figure[tail].y;
        }
        else if (direction == Direction.NORTH){
            newX = figure[tail].x;
            newY = figure[tail].y + figureHeight;
        }
        else if (direction == Direction.SOUTH){
            newX = figure[tail].x;
            newY = figure[tail].y - figureHeight;
        }

        Rectangle newBodyPart = new Rectangle(newX, newY, figureWidth, figureHeight);

        tail = (tail - 1) % figure.length;
        figure[tail] = newBodyPart;
    }

    public void draw(Graphics2D g2)
    {
        for(int j = tail; j != head; j =(j + 1) % figure.length)
        {
            Rectangle part = figure[j];
            double subWidth=(part.width - 6.0) / 2.0;
            double subHeight=(part.height - 6.0) / 2.0;

            g2.setColor(Color.BLACK);
            g2.fill(new Rectangle2D.Double(part.x + 2.0, part.y + 2.0, subWidth, subHeight));
            g2.fill(new Rectangle2D.Double(part.x + 4.0 + subWidth, part.y + 2.0, subWidth, subHeight));
            g2.fill(new Rectangle2D.Double(part.x + 2.0, part.y + 4.0 + subHeight, subWidth, subHeight));
            g2.fill(new Rectangle2D.Double(part.x + 4.0 + subWidth, part.y + 4.0 + subHeight, subWidth, subHeight));


        }

    }
}
