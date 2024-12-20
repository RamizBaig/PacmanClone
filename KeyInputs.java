import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInputs extends KeyAdapter {
    private Handler handler;

    public KeyInputs(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {//listes for keypress
        int key = e.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Pacman) {//will move pacman when buttons are pressed
                if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP)
                    tempObject.setWantedDirection(1);
                if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN)
                    tempObject.setWantedDirection(3);
                if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT)
                    tempObject.setWantedDirection(4);
                if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT)
                    tempObject.setWantedDirection(2);

            }
        }
        if (key == KeyEvent.VK_ESCAPE)
            System.exit(1);

    }

}