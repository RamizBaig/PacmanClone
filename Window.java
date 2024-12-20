import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Window extends Canvas {

    private static final long serialVersionUID = -240840600533728354L;

    public Window(int width, int height, String title, GameMain game) {//constructor for the window\
        JFrame frame = new JFrame(title);//new window
        frame.setPreferredSize(new Dimension(width, height));//setting the window size/making it unsizable
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close so it doesnt kill ur pc in the background
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);//centers
        frame.add(game);//will run game
        frame.setVisible(true);
        game.start();
    }
}
