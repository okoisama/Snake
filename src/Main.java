//This is the main class of the project. It's basically the entry point of the program
public class Main {
    public static void main(String[] args) {
        Window window = Window.getWindow();

        Thread thread = new Thread(window);
        thread.start();
    }
}
