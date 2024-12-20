import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import java.awt.image.BufferStrategy;
import java.util.Random;

public class GameMain extends Canvas implements Runnable {
    Random rand = new Random();

    private static final long serialVersionUID = 1550691097823471818L;// constant UID lowers the chances of bugs

    public static final int WIDTH = 750, HEIGHT = 850;
    private Thread thread;
    private boolean running = false;
    private Handler handler;
    GameObject pacmansID;
    int lives = 3, level = 1;;
    int levelOneBoard[][] = {
            { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 }, // 0
            { 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2 }, // 1
            { 2, 1, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 1, 2 }, // 2
            { 2, 3, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 3, 2 }, // 3
            { 2, 1, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 1, 2 }, // 4
            { 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2 }, // 5
            { 2, 1, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 1, 2 }, // 6
            { 2, 1, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 1, 2 }, // 7
            { 2, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2 }, // 8
            { 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 0, 2, 2, 0, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2 }, // 9
            { 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 0, 2, 2, 0, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2 }, // 10
            { 2, 2, 2, 2, 2, 2, 1, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 1, 2, 2, 2, 2, 2, 2 }, // 11
            { 2, 2, 2, 2, 2, 2, 1, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 1, 2, 2, 2, 2, 2, 2 }, // 12
            { 2, 2, 2, 2, 2, 2, 1, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 1, 2, 2, 2, 2, 2, 2 }, // 13
            { 2, 1, 1, 1, 1, 1, 1, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2 }, // 14
            { 2, 2, 2, 2, 2, 2, 1, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 1, 2, 2, 2, 2, 2, 2 }, // 15
            { 2, 2, 2, 2, 2, 2, 1, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 1, 2, 2, 2, 2, 2, 2 }, // 16
            { 2, 2, 2, 2, 2, 2, 1, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 1, 2, 2, 2, 2, 2, 2 }, // 17
            { 2, 2, 2, 2, 2, 2, 1, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 1, 2, 2, 2, 2, 2, 2 }, // 18
            { 2, 2, 2, 2, 2, 2, 1, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 1, 2, 2, 2, 2, 2, 2 }, // 19
            { 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2 }, // 20
            { 2, 1, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 1, 2 }, // 21
            { 2, 1, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 1, 2 }, // 22
            { 2, 3, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 3, 2 }, // 23
            { 2, 2, 2, 1, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 1, 2, 2, 2 }, // 24
            { 2, 2, 2, 1, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 1, 2, 2, 2 }, // 25
            { 2, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2 }, // 26
            { 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2 }, // 27
            { 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2 }, // 28
            { 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2 }, // 29
            { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 } };// 30
    int levelTwoBoard[][] = {
            { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 }, // 0
            { 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2 }, // 1
            { 2, 1, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 1, 2 }, // 2
            { 2, 3, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 3, 2 }, // 3
            { 2, 1, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1, 2 }, // 4
            { 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2 }, // 5
            { 2, 1, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 1, 2 }, // 6
            { 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 2, 1, 1, 1, 1, 2, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2 }, // 7
            { 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2 }, // 8
            { 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2 }, // 9
            { 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2 }, // 10
            { 2, 2, 2, 2, 2, 2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 2, 2, 2 }, // 11
            { 2, 2, 2, 2, 2, 2, 1, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 1, 2, 2, 2, 2, 2, 2 }, // 12
            { 2, 2, 2, 2, 2, 2, 1, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 1, 2, 2, 2, 2, 2, 2 }, // 13
            { 2, 1, 1, 1, 1, 1, 1, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 1, 1, 1, 1, 1, 1, 2 }, // 14
            { 2, 2, 2, 2, 2, 2, 1, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 1, 2, 2, 2, 2, 2, 2 }, // 15
            { 2, 2, 2, 2, 2, 2, 1, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 1, 2, 2, 2, 2, 2, 2 }, // 16
            { 2, 2, 2, 1, 1, 1, 1, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 1, 1, 1, 1, 2, 2, 2 }, // 17
            { 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2 }, // 18
            { 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2 }, // 19
            { 2, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 2 }, // 20
            { 2, 1, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 1, 2 }, // 21
            { 2, 1, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 1, 2 }, // 22
            { 2, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2 }, // 23
            { 2, 1, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 1, 2 }, // 24
            { 2, 1, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 1, 2 }, // 25
            { 2, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 2 }, // 26
            { 2, 1, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 1, 2 }, // 27
            { 2, 3, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 3, 2 }, // 28
            { 2, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 1, 1, 1, 1, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 2 }, // 29
            { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 } // 30
    };
    int currentBoard[][] = levelOneBoard;


    public GameMain() {
        new Window(WIDTH, HEIGHT, "Pacman", this);
        handler = new Handler();
        this.addKeyListener(new KeyInputs(handler));
        this.requestFocusInWindow();// focuses game
        addPellets();//setting up board and getting variables
        handler.addObject(new Pacman(360, 550, ID.Pacman, handler));
        handler.addObject(new Ghost(360, 350, ID.Blinky, handler));
        handler.addObject(new Ghost(360, 350, ID.Pinky, handler));
        handler.addObject(new Ghost(360, 350, ID.Inky, handler));
        handler.addObject(new Ghost(360, 350, ID.Clyde, handler));
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Pacman) {
                pacmansID = handler.object.get(i);
            }
        }

    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();// catches errors on close
        }
    }

    public void run() {// game loop
        // very common game loop used in many games including minecraft
        long lastTime = System.nanoTime();// current time
        double amountOfTicks = 60.0;// displayed framerate
        double ns = 1000000000 / amountOfTicks;// nanoseonds per frame
        double delta = 0;// will be used keep track of time between each frame
        long timer = System.currentTimeMillis();// timer for frames updating(not visual)
        int frames = 0;// framerate
        while (running) {
            long now = System.nanoTime();// time of this loop starting
            delta += (now - lastTime) / ns;// time between this and last fram divided by ns per frame(gives you the amount of seconds since the last tick)
            lastTime = now;// will be used for next loop to keep track of time again
            frames++;// frames being processed(not visual)      
            while (delta >= 1) {// once 1 seconds has passed it will tick and subtract delta
                tick();
                delta--;
            }
            if (System.currentTimeMillis() - timer > 1000) {// after 1000ms make timer go up 1000ms
                timer += 1000;
                System.out.println("FPS: " + frames);// displays frames every second
                frames = 0;// resets fram count every second
            }
            if (running)
                render();// renders graphics
        }
        stop();
    }

    private void tick() {
        handler.tick();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();// to draw 'screens'
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();// graphics to draw
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);// black backdrop to prevent flashing

        try {
            lives = pacmansID.getLives();
            level = pacmansID.getLevel();
        } catch (NullPointerException e) {
            // e.printStackTrace(); // expected error while all graphics are being rendered
        }
        if (lives > 0) {//draws the borders while alive
            if (level % 2 == 1)
                drawBorders1(g);
            if (level % 2 == 0)
                drawBorders2(g);
        }

        handler.render(g);// it must render before we dispose of it
        g.dispose();// disposes so it can prepare to render next visual frame
        bs.show();// draws/prints the screen
    }

    public void addPellets() {//adds all the pellets by fully looping through the array
        for (int i = 0; i < currentBoard[0].length; i++) {
            for (int j = 0; j < currentBoard.length; j++) {
                if (currentBoard[j][i] == 1)
                    handler.addObject(new Pellet(i * 20 + 100, j * 20 + 100, ID.Pellet, handler));
                if (currentBoard[j][i] == 3)
                    handler.addObject(new PowerPellet(i * 20 + 100, j * 20 + 100, ID.PowerPellet, handler));
            }
        }
    }

    public void drawBorders1(Graphics g) {//this and the following method to draw out the boards.
        g.setColor(Color.blue);
        g.drawRect(98, 98, 544, 4);// top barrier

        g.drawRect(98, 98, 4, 184);// top left barriers
        g.drawRect(98, 278, 104, 4);
        g.drawRect(198, 278, 4, 84);
        g.drawRect(98, 358, 104, 4);

        g.drawRect(638, 98, 4, 184);// top right barriers
        g.drawRect(538, 278, 104, 4);
        g.drawRect(538, 278, 4, 84);
        g.drawRect(538, 358, 104, 4);

        g.drawRect(98, 398, 104, 4);// bottom left barrier
        g.drawRect(198, 398, 4, 84);
        g.drawRect(98, 478, 104, 4);
        g.drawRect(98, 478, 4, 224);

        g.drawRect(538, 398, 104, 4);// bottom right barrier
        g.drawRect(538, 398, 4, 84);
        g.drawRect(538, 478, 104, 4);
        g.drawRect(638, 478, 4, 224);

        g.drawRect(98, 698, 544, 4);// bottom barrier

        g.drawRect(360, 98, 24, 80);

        g.drawRect(138, 138, 64, 44);// top left obstructions
        g.drawRect(138, 218, 64, 24);
        g.drawRect(238, 138, 84, 44);

        g.drawRect(538, 138, 64, 44);// top right obstructions
        g.drawRect(538, 218, 64, 24);
        g.drawRect(418, 138, 84, 44);

        g.drawRect(238, 218, 24, 144);// top t's
        g.drawRect(238, 278, 84, 24);
        g.drawRect(298, 218, 144, 24);
        g.drawRect(358, 218, 24, 84);
        g.drawRect(478, 218, 24, 144);
        g.drawRect(418, 278, 84, 24);

        g.drawRect(298, 338, 144, 84);// middle
        g.drawRect(238, 398, 24, 84);// left mid
        g.drawRect(478, 398, 24, 84);// right mid
        g.drawRect(298, 458, 144, 24);// bottom t
        g.drawRect(358, 458, 24, 84);
        g.drawRect(298, 578, 144, 24);// bottom t 2
        g.drawRect(358, 578, 24, 84);

        g.drawRect(98, 578, 44, 24);// bttom left edge obstruction
        g.drawRect(138, 518, 64, 24);// t
        g.drawRect(178, 518, 24, 84);
        g.drawRect(238, 518, 84, 24);// higher
        g.drawRect(138, 638, 184, 24);// bottom left t
        g.drawRect(238, 578, 24, 84);

        g.drawRect(598, 578, 44, 24);// bottom right edge obstruction
        g.drawRect(538, 518, 64, 24);// t
        g.drawRect(538, 518, 24, 84);
        g.drawRect(418, 518, 84, 24);// higher
        g.drawRect(418, 638, 184, 24);// bottom right t
        g.drawRect(478, 578, 24, 84);

    }

    public void drawBorders2(Graphics g) {
        g.setColor(Color.red);
        g.drawRect(98, 98, 544, 4);// top barrier

        g.drawRect(98, 98, 4, 164);// top left barrier
        g.drawRect(98, 258, 124, 4);
        g.drawRect(218, 258, 4, 44);
        g.drawRect(198, 298, 24, 4);
        g.drawRect(198, 298, 4, 64);
        g.drawRect(98, 358, 104, 4);

        g.drawRect(98, 398, 104, 4);// bottom left barrier
        g.drawRect(198, 398, 4, 24);
        g.drawRect(138, 418, 64, 4);
        g.drawRect(138, 418, 4, 64);
        g.drawRect(98, 478, 44, 4);
        g.drawRect(98, 478, 4, 224);

        g.drawRect(98, 698, 144, 4);// bottom barrier
        g.drawRect(238, 638, 4, 64);
        g.drawRect(238, 638, 84, 4);
        g.drawRect(318, 638, 4, 64);
        g.drawRect(318, 698, 104, 4);
        g.drawRect(418, 638, 4, 64);
        g.drawRect(418, 638, 84, 4);
        g.drawRect(498, 638, 4, 64);
        g.drawRect(498, 698, 144, 4);

        g.drawRect(538, 398, 104, 4);// bottom right barrier
        g.drawRect(538, 398, 4, 24);
        g.drawRect(538, 418, 64, 4);
        g.drawRect(598, 418, 4, 64);
        g.drawRect(598, 478, 44, 4);
        g.drawRect(638, 478, 4, 224);

        g.drawRect(638, 98, 4, 164);// top right barrier
        g.drawRect(518, 258, 124, 4);
        g.drawRect(518, 258, 4, 44);
        g.drawRect(518, 298, 24, 4);
        g.drawRect(538, 298, 4, 64);
        g.drawRect(538, 358, 104, 4);

        g.drawRect(138, 138, 64, 84);// top left obstuctions
        g.drawRect(138, 198, 84, 24);
        g.drawRect(238, 138, 84, 24);
        g.drawRect(258, 138, 64, 64);
        g.drawRect(258, 238, 24, 64);

        g.drawRect(538, 138, 64, 84);// top right obstuctions
        g.drawRect(518, 198, 84, 24);
        g.drawRect(418, 138, 84, 24);
        g.drawRect(418, 138, 64, 64);
        g.drawRect(458, 238, 24, 64);

        g.drawRect(238, 338, 24, 144);// bottom left obstructions
        g.drawRect(238, 458, 84, 24);
        g.drawRect(178, 458, 24, 84);
        g.drawRect(138, 518, 64, 24);
        g.drawRect(238, 518, 84, 24);
        g.drawRect(238, 518, 24, 84);
        g.drawRect(138, 578, 64, 84);

        g.drawRect(478, 338, 24, 144);// bottom right obstructions
        g.drawRect(418, 458, 84, 24);
        g.drawRect(538, 458, 24, 84);
        g.drawRect(538, 518, 64, 24);
        g.drawRect(418, 518, 84, 24);
        g.drawRect(478, 518, 24, 84);
        g.drawRect(538, 578, 64, 84);

        g.drawRect(358, 98, 24, 124);// middle ones
        g.drawRect(318, 238, 4, 64);
        g.drawRect(418, 238, 4, 64);
        g.drawRect(318, 258, 104, 44);
        g.drawRect(298, 338, 144, 84);// middle
        g.drawRect(358, 458, 24, 84);
        g.drawRect(358, 578, 24, 84);
        g.drawRect(298, 578, 144, 24);
    }

    public static void main(String[] args) throws Exception {
        new GameMain();
    }
}