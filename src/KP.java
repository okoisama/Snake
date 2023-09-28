//This code is used to listen and respond to keys being pushed
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KP extends KeyAdapter implements KeyListener {
    private boolean[] keyPushed = new boolean[128];

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        keyPushed[keyEvent.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        keyPushed[keyEvent.getKeyCode()] = false;
    }

    public boolean isKeyPushed(int keyCode) {
        return keyPushed[keyCode];
    }
}
