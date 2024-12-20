
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PowerPellet extends GameObject {//is a game object

    public PowerPellet(int x, int y, ID id, Handler handler) {//constructor
        super(x, y, id);
    }

    public Rectangle getBounds() {//to see if pacman is touching it
        return new Rectangle(x - 6, y - 6, 12, 12);
    }

    public void tick() {
        //no tick method needed
    }

    public void render(Graphics g) {//draws the power pellets
        g.setColor(new Color(245, 245, 220));
        g.fillRect(x - 6, y - 6, 12, 12);

    }
}
