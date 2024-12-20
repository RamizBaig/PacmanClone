import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Pacman extends GameObject {
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
    Handler handler;

    boolean teleporting = false;
    boolean moving;
    boolean resetting = false;
    long lastTime = System.currentTimeMillis();
    long now = System.currentTimeMillis();
    long release;
    int score = 0;
    int combo = 0;
    int highScore;
    
    boolean death = false;

    BufferedImage pacmanLeft1, pacmanRight1, pacmanUp1, pacmanDown1;
    BufferedImage pacmanImages[] = new BufferedImage[4];

    public Pacman(int x, int y, ID id, Handler handler) {//constructor
        super(x, y, id);
        this.handler = handler;
        actualDirection = 1;
        lives = 3;
        importImages();
        try {
            highScore = getHighscore();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getHighscore() throws java.io.FileNotFoundException {//reads the highscore and saves it
        Scanner file = new Scanner(new File("Highscore.txt"));
        if(file.hasNext()==true)return file.nextInt();
        else return 0;
    }

    public void setHighscore() throws java.io.FileNotFoundException {////if there is a new highscore, it will make it the new highscore
        PrintWriter writer = new PrintWriter("Highscore.txt");
        if (score > highScore)
            writer.println(score);
        else
            writer.println(highScore);
        writer.close();
    }

    public Rectangle getBounds() {//to see if it interacts with other objects
        return new Rectangle(x, y, 20, 20);
    }

    private void collision() {//checks each object to see if they are interacting
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempoObject = handler.object.get(i);
            if (tempoObject.getId() == ID.Pellet) {
                if (getBounds().intersects(tempoObject.getBounds())) {//if its a pellet delete it and increase the score
                    handler.removeObject(tempoObject);
                    pelletsEaten++;
                    score += 10;
                }
            }
            if (tempoObject.getId() == ID.Blinky || tempoObject.getId() == ID.Pinky || tempoObject.getId() == ID.Inky || tempoObject.getId() == ID.Clyde) {//if its a ghost either die and respawn or eat the ghost
                if (getBounds().intersects(tempoObject.getBounds())) {
                    if (tempoObject.getGhostStatus() != 2)
                        death = true;
                    else {
                        handler.addObject(new Ghost(360, 350, tempoObject.getId(), handler));
                        handler.removeObject(tempoObject);
                        score += Math.pow(2, combo) * 200;
                        combo++;
                    }
                }
            }
            if (tempoObject.getId() == ID.PowerPellet) {//if its a power pellet and activates buff
                if (getBounds().intersects(tempoObject.getBounds())) {
                    combo = 0;
                    handler.removeObject(tempoObject);
                    buffed = true;
                    score += 10;
                    pelletsEaten++;
                }
            }
        }
    }

    public void tick() {
        if (lives > 0) {//only working if user has more than 3 lives
            x += velX * 2;//to increase speed 
            y += velY * 2;
            arrayPositionX = (x - 90) / 20;
            arrayPositionY = (y - 90) / 20;
            if (teleporting == false) {//as long as it is not teleporting it will let you turn
                lastArrayPositionX = (x - 90) / 20;//saving its possition for ghosts to use
                lastArrayPositionY = (y - 90) / 20;
                checkTurn();
            }
            if (actualDirection == 1 && moving == true) {//following 4 operatives are used to stop the user from moivng past walls
                if (currentBoard[((y - 71) / 20) - 1][((x - 90) / 20)] == 2) {
                    velY = 0;
                    x = arrayPositionX * 20 + 90;
                    moving = false;
                }
            }
            if (actualDirection == 2 && moving == true) {
                if (currentBoard[((y - 90) / 20)][((x - 90) / 20) + 1] == 2) {
                    velX = 0;
                    y = arrayPositionY * 20 + 90;
                    moving = false;
                }
            }
            if (actualDirection == 3 && moving == true) {
                if (currentBoard[((y - 90) / 20) + 1][((x - 90) / 20)] == 2) {
                    velY = 0;
                    x = arrayPositionX * 20 + 90;
                    moving = false;
                }
            }
            if (actualDirection == 4 && moving == true) {
                if (currentBoard[((y - 90) / 20)][((x - 71) / 20) - 1] == 2) {
                    velX = 0;
                    y = arrayPositionY * 20 + 90;
                    moving = false;
                }
            }

            if ((pelletsEaten >= 256 && currentBoard == levelOneBoard) || (pelletsEaten >= 252 && currentBoard == levelTwoBoard) || death == true)
                reset();//for when user is dieing or advancig to next level
            if ((lastArrayPositionX == 1 && lastArrayPositionY == 14 && actualDirection == 4) || (lastArrayPositionX == 26 && lastArrayPositionY == 14 && actualDirection == 2)) {
                teleportAcross();//when teleporting
            }
            collision();//check for if pacmna is touching anything
        } else {
            for (int i = 0; i < handler.object.size(); i++) {//removing all objects at end of game
                GameObject tempoObject = handler.object.get(i);
                if (tempoObject.getId() == ID.Blinky || tempoObject.getId() == ID.Pinky || tempoObject.getId() == ID.Inky || tempoObject.getId() == ID.Clyde || tempoObject.getId() == ID.Pellet || tempoObject.getId() == ID.PowerPellet) {
                    handler.removeObject(tempoObject);
                }
            }
            try {
                setHighscore();//at end of game
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void reset() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempoObject = handler.object.get(i);
            if (tempoObject.getId() == ID.Blinky || tempoObject.getId() == ID.Pinky || tempoObject.getId() == ID.Inky || tempoObject.getId() == ID.Clyde) {
                handler.removeObject(tempoObject); //removing all ghosts
            }
        }
        if (resetting == false)
            release = System.currentTimeMillis() + 3000;//user can moved again in 3s

        resetting = true;
        x = 360;//moving pacman to og spot
        y = 550;

        long now = System.currentTimeMillis();
        if (now > release) {
            if (death == false) {//new level
                level++;
                pelletsEaten = 0;
                if (currentBoard == levelOneBoard)//switches the board
                    currentBoard = levelTwoBoard;
                else
                    currentBoard = levelOneBoard;
                for (int i = 0; i < 28; i++) {
                    for (int j = 0; j < 30; j++) {
                        if (currentBoard[j][i] == 1)
                            handler.addObject(new Pellet(i * 20 + 100, j * 20 + 100, ID.Pellet, handler));//adds the pellets
                        if (currentBoard[j][i] == 3)
                            handler.addObject(new PowerPellet(i * 20 + 100, j * 20 + 100, ID.PowerPellet, handler));
                    }
                }
            } else
                lives--;
            handler.addObject(new Ghost(360, 350, ID.Blinky, handler));//adds the ghosts and resets the variables
            handler.addObject(new Ghost(360, 350, ID.Pinky, handler));
            handler.addObject(new Ghost(360, 350, ID.Inky, handler));
            handler.addObject(new Ghost(360, 350, ID.Clyde, handler));
            resetting = false;
            teleporting = false;
            death = false;
            moving = false;
        }

    }

    public void teleportAcross() {//starts the porcess for teleportation
        teleporting = true;
        if (lastArrayPositionX == 1) {//the user continues to move until reaches a certain point, is then teleported, and continues to move until it is ready to be released - this prevents bugs
            velX = -1;
            if (x < 70)
                x = 650;

            if (x < 625 && x > 620) {
                teleporting = false;
                moving = true;
            }
        }
        if (lastArrayPositionX == 26) {

            velX = 1;
            if (x > 660)
                x = 80;

            if (x < 95) {
                teleporting = false;
                moving = true;
            }
        }
    }

    public void checkTurn() {//it checks the direction the user wants to move in, and sees if the user is in the center of a tile and if the tile in that direction is open
        if (lastArrayPositionX > 0 && lastArrayPositionX < 27) {
            if (wantedDirection == 1) {
                if ((currentBoard[((y - 90) / 20) - 1][((x - 71) / 20)] != 2 && currentBoard[((y - 90) / 20) - 1][((x - 90) / 20)] != 2 && actualDirection == 4) || (currentBoard[((y - 90) / 20) - 1][((x - 71) / 20)] != 2 && actualDirection == 2 && x < lastArrayPositionX * 20 + 91) || (actualDirection == 3)) {
                    actualDirection = 1;
                    velX = 0;
                    velY = -1;
                    wantedDirection = 1;
                    moving = true;
                }

            } 
            if (wantedDirection == 2) {
                if ((currentBoard[((y - 90) / 20)][((x - 90) / 20) + 1] != 2 && actualDirection == 3 && y < lastArrayPositionY * 20 + 91) || (currentBoard[((y - 71) / 20)][((x - 90) / 20) + 1] != 2 && currentBoard[((y - 90) / 20)][((x - 90) / 20) + 1] != 2 && actualDirection == 1) || (actualDirection == 4)) {
                    actualDirection = 2;
                    velX = 1;
                    velY = 0;
                    wantedDirection = 2;
                    moving = true;
                }
            }
            if (wantedDirection == 3) {//
                if ((currentBoard[((y - 90) / 20) + 1][((x - 90) / 20)] != 2 && actualDirection == 2 && x < lastArrayPositionX * 20 + 91) || (currentBoard[((y - 90) / 20) + 1][((x - 71) / 20)] != 2 && currentBoard[((y - 90) / 20) + 1][((x - 90) / 20)] != 2 && actualDirection == 4) || (actualDirection == 1)) {
                    actualDirection = 3;
                    velX = 0;
                    velY = 1;
                    wantedDirection = 3;
                    moving = true;
                }
            }
            if (wantedDirection == 4) {// down to left is good, up to left is early
                if ((currentBoard[((y - 90) / 20)][((x - 90) / 20) - 1] != 2 && actualDirection == 3 && y < lastArrayPositionY * 20 + 91) || (currentBoard[((y - 71) / 20)][((x - 90) / 20) - 1] != 2 && currentBoard[((y - 90) / 20)][((x - 90) / 20) - 1] != 2 && actualDirection == 1) || (actualDirection == 2)) {
                    actualDirection = 4;
                    velX = -1;
                    velY = 0;
                    wantedDirection = 4;
                    moving = true;
                }
            }
        }
    }

    public void render(Graphics g) {//draws the score, pacman, final score
        g.setColor(Color.white);

        if (lives > 0) {
            g.drawString("Score: " + score, 20, 40);
            g.drawString("Lives: " + lives, 20, 70);
            g.drawImage(pacmanImages[actualDirection - 1], x, y, null);
        } else {
            g.drawString("Final Score: " + score, 300, 415);
            g.drawString("Highscore: " + highScore, 300, 435);
        }
    }

    public void importImages() {// the 4 images for pacman in each direction
        InputStream is1 = getClass().getResourceAsStream("_pacmanLeft1.png");
        InputStream is2 = getClass().getResourceAsStream("_pacmanRight1.png");
        InputStream is3 = getClass().getResourceAsStream("_pacmanUp1.png");
        InputStream is4 = getClass().getResourceAsStream("_pacmanDown1.png");
        try {
            pacmanLeft1 = ImageIO.read(is1);
            pacmanRight1 = ImageIO.read(is2);
            pacmanUp1 = ImageIO.read(is3);
            pacmanDown1 = ImageIO.read(is4);
        } catch (IOException e) {
            e.printStackTrace();
        }
        pacmanImages[3] = pacmanLeft1;
        pacmanImages[1] = pacmanRight1;
        pacmanImages[0] = pacmanUp1;
        pacmanImages[2] = pacmanDown1;
    }
}