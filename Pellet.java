
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Pellet extends GameObject {//is a game object

    public Pellet(int x, int y, ID id, Handler handler) {//constructor
        super(x, y, id);
    }

    public Rectangle getBounds() {//to see if pacman touches it
        return new Rectangle(x - 2, y - 2, 4, 4);
    }

    public void tick() {
        //not tick method needed
    }

    public void render(Graphics g) {//draws the pellets
        g.setColor(new Color(245, 245, 220));
        g.fillRect(x - 2, y - 2, 4, 4);
    }
}
