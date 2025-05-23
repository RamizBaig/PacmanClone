import java.awt.Graphics;
import java.util.LinkedList;
public class Handler{
    LinkedList<GameObject> object = new LinkedList<GameObject>();
 
    public void tick(){//ticks each object
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }
    }
    public void render(Graphics g){//renders each object
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.render(g);
        }
    }
    public void addObject (GameObject object){//allows us to add objects that can be altered easily
        this.object.add(object);
    }
    
    public void removeObject(GameObject object){//removes objects
        this.object.remove(object);
    }
}