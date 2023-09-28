//This file contains code which customizes the features of the window the game runs on
import java.awt.*;
import javax.swing.JFrame;
import java.time.Duration;
import java.time.Instant;


public class Window extends JFrame implements Runnable {

    public static Window window=null;
    public boolean isRunning;

    public int currentState;
    public Screen currentScreen;

    public KP keyListener = new KP();
    public MK mouseListener = new MK();

    public Window(int width, int height, String title) {

        isRunning = true;

        setSize(width, height);
        setTitle(title);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(keyListener);
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);

        changeState(0);
    }


    public static Window getWindow(){
        if (Window.window == null){
            Window.window = new Window(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, Constants.SCREEN_NAME);
        }

        return Window.window;
    }


    public void close(){

        isRunning = false;
    }

    public void changeState(int newState) {

        currentState = newState;
        switch (currentState) {

            case 0:
                currentScreen = new MenuScreen(keyListener, mouseListener);
                break;

            case 1:
                currentScreen = new GameScreen(keyListener);
                break;

            default:
                System.out.println("Unknown Screen.");
                currentScreen = null;
                break;
        }
    }

    public void update(double dt) {
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        getGraphics().drawImage(dbImage, 0, 0, this);

        currentScreen.update(dt);

    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        currentScreen.draw(g);
    }

    @Override
    public void run() {
        Instant lastFrameTime = Instant.now();
        try {
            while (isRunning) {
                Instant time = Instant.now();
                double deltaTime = Duration.between(lastFrameTime, time).toNanos() * 10E-10;
                lastFrameTime = Instant.now();

                double deltaWanted = 0.0167;
                update(deltaWanted);
                long msToSleep = (long)((deltaWanted - deltaTime) * 1000);
                if (msToSleep > 0) {
                    Thread.sleep(msToSleep);
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        this.dispose();
    }


}
