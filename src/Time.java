//The class gets the time since the game window has started
public class Time {
    public static double timeInitiated = System.nanoTime();

    public static double obtainTime() {return (System.nanoTime() - timeInitiated) * 1E-9;}

    public static double getTime() {
        return 0;
    }
    
}
