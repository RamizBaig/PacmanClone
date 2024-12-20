import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Ghost extends GameObject {
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
            { 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2 }, // 10
            { 2, 2, 2, 2, 2, 2, 1, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 1, 2, 2, 2, 2, 2, 2 }, // 11
            { 2, 2, 2, 2, 2, 2, 1, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 1, 2, 2, 2, 2, 2, 2 }, // 12
            { 2, 2, 2, 2, 2, 2, 1, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 1, 2, 2, 2, 2, 2, 2 }, // 13
            { 2, 1, 1, 1, 1, 2, 1, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 1, 2, 1, 1, 1, 1, 2 }, // 14
            { 2, 2, 2, 2, 2, 2, 1, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 1, 2, 2, 2, 2, 2, 2 }, // 15
            { 2, 2, 2, 2, 2, 2, 1, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 1, 2, 2, 2, 2, 2, 2 }, // 16
            { 2, 2, 2, 2, 2, 2, 1, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 1, 2, 2, 2, 2, 2, 2 }, // 17
            { 2, 2, 2, 2, 2, 2, 1, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 1, 2, 2, 2, 2, 2, 2 }, // 18
            { 2, 2, 2, 2, 2, 2, 1, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 1, 2, 2, 2, 2, 2, 2 }, // 19
            { 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2 }, // 20
            { 2, 1, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 1, 2 }, // 21
            { 2, 1, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 1, 2 }, // 22
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
            { 2, 1, 1, 1, 1, 2, 1, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 1, 2, 1, 1, 1, 1, 2 }, // 14
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
    int targetX, targetY;
    GameObject pacmansID, blinkysID;
    int pacmanDirection;
    int test;
    boolean moving = false;
    boolean chosenDirection;
    double distance1, distance2, distance3, distance4;
    boolean turn1, turn2, turn3, turn4;
    long lastTime;
    boolean timeChecked = false;
    int lastGhostStatus;
    boolean released;
    int pelletThreshold[] = { 236, 226, 216, 216, 216, 206, 206, 206, 196, 196, 196, 156, 156, 156, 136 };
    int slowGhost = 0;
    int statusTime[] = { 0, 10000, 35000, 47000, 77000, 87000, 112000, 0 };
    int statusTracker = 0;
    boolean renderedImages = false;
    BufferedImage blinkyImage, inkyImage, pinkyImage, clydeImage, frightenedGhostImage;

    public Ghost(int x, int y, ID id, Handler handler) {//constructor
        super(x, y, id);
        this.handler = handler;
        velY = -1;
        actualDirection = 1;
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Pacman) {//finding the ids for blinky and pacman to be used by ghosts in there movements
                pacmansID = handler.object.get(i);
            }
            if (tempObject.getId() == ID.Blinky) {
                blinkysID = handler.object.get(i);
            }
        }
        lastTime = System.currentTimeMillis();
    }

    public Rectangle getBounds() {//to check if in contact with pacman
        return new Rectangle(x, y, 18, 20);
    }

    public void tick() {
        if (renderedImages == false)
            importImages();
        if (level % 2 == 1)//board for each level
            currentBoard = levelOneBoard;
        else
            currentBoard = levelTwoBoard;

        level = pacmansID.getLevel();
        slowGhost++;
        slowGhost %= 10;

        if (slowGhost != 1) {//ghosts are slowed once every 10th tick so 10% slower than pacman

            Random rand = new Random();
            int randomNumber;
            long now = System.currentTimeMillis();
            if ((id == ID.Blinky) || (now - lastTime > 1500 && id == ID.Pinky) || (now - lastTime > 4000 && id == ID.Inky) || (now - lastTime > 8000 && id == ID.Clyde)) {
                released = true;//ghosts are released at there correct times
            }
            if (pacmansID.getBuffed() == true && ghostStatus != 2) {//when pacman easts a power pellet loop through all ghosts, frighten them, and reverse their direction
                for (int i = 0; i < handler.object.size(); i++) {
                    GameObject tempoObject = handler.object.get(i);
                    if (tempoObject.getId() == ID.Blinky || tempoObject.getId() == ID.Pinky || tempoObject.getId() == ID.Inky || tempoObject.getId() == ID.Clyde) {
                        tempoObject.setGhostStatus(2);
                        tempoObject.setFrightenedEnd(System.currentTimeMillis() + 7000);
                        if (tempoObject.getTurnArray((tempoObject.getActualDirection() + 1) % 4 + 1) == true) {
                            tempoObject.setVelX(tempoObject.getVelX() * -1);
                            tempoObject.setVelY(tempoObject.getVelY() * -1);
                            tempoObject.setActualDirection((tempoObject.getActualDirection() + 1) % 4 + 1);
                        }
                    }
                }
                pacmansID.setBuffed(false);
            }
            if (ghostStatus == 2) {
                now = System.currentTimeMillis();
                if (now > frightenedEnd) {//checking for when frighten should end
                    ghostStatus = lastGhostStatus;
                    if (x % 2 != 0)//making sure ghosts arent at an odd pixel to prevent glitching through walls
                        x++;
                    if (y % 2 != 0)
                        y++;
                    if (turnArray[(actualDirection + 1) % 4 + 1] == true) {//reversing direction
                        velY *= -1;
                        velX *= -1;
                        actualDirection = (actualDirection + 1) % 4 + 1;
                    }
                }
            }
            if (statusTracker < 8 && now - lastTime > statusTime[statusTracker] && (ghostStatus == 1 || ghostStatus == 0)) {//switches the ghosts movement patterns according to the time
                if (ghostStatus == 0) {
                    ghostStatus = 1;
                } else if (ghostStatus == 1) {
                    ghostStatus = 0;
                }
                if (id == ID.Blinky && pacmansID.getPelletsEaten() > pelletThreshold[Math.min(14, level)] && ghostStatus != 2) {
                    ghostStatus = 0;//when pacman is close to completing board start to chasd no matter what
                } else {
                    statusTracker++;

                    if (turnArray[(actualDirection + 1) % 4 + 1] == true) {//reverse direction
                        velY *= -1;
                        velX *= -1;
                        actualDirection = (actualDirection + 1) % 4 + 1;
                    }
                }
            }
            if (released == true) {//movement
                if (ghostStatus == 2) {
                    x += velX;
                    y += velY;
                } else {
                    x += velX * 2;
                    y += velY * 2;
                }

            }

            if (ghostStatus == 0) {//targeting system for each ghost
                if (id == ID.Blinky) {//pacman
                    targetX = pacmansID.getLastArrayPositionX();
                    targetY = pacmansID.getLastArrayPositionY();
                }
                if (id == ID.Pinky) {//infront of pacman(unless facing up)
                    if (pacmansID.getActualDirection() == 1) {
                        targetY = pacmansID.getLastArrayPositionY() - 4;
                        targetX = pacmansID.getLastArrayPositionX() - 4;
                    }
                    if (pacmansID.getActualDirection() == 2) {
                        targetX = pacmansID.getLastArrayPositionX() + 4;
                        targetY = pacmansID.getLastArrayPositionX();
                    }
                    if (pacmansID.getActualDirection() == 3) {
                        targetY = pacmansID.getLastArrayPositionY() + 4;
                        targetX = pacmansID.getLastArrayPositionX();
                    }
                    if (pacmansID.getActualDirection() == 4) {
                        targetX = pacmansID.getLastArrayPositionX() - 4;
                        targetY = pacmansID.getLastArrayPositionX();
                    }
                }
                if (id == ID.Inky) {//vector flipped 180degrees from blinky to pacman
                    targetX = pacmansID.getLastArrayPositionX() - blinkysID.getArrayPositionX() + pacmansID.getLastArrayPositionX();
                    targetY = pacmansID.getLastArrayPositionY() - blinkysID.getArrayPositionY() + pacmansID.getLastArrayPositionY();
                }
                if (id == ID.Clyde) {//cahses pacman but when close runs away
                    if (Math.pow(Math.abs(pacmansID.getLastArrayPositionX() - arrayPositionX), 2) + Math.pow(Math.abs(pacmansID.getLastArrayPositionY() - arrayPositionY), 2) >= 64) {
                        targetX = pacmansID.getLastArrayPositionX();
                        targetY = pacmansID.getLastArrayPositionY();
                    } else {
                        targetX = 0;
                        targetY = 30;
                    }
                }
            } else if (ghostStatus == 1) {//respective corners
                if (id == ID.Blinky) {
                    targetX = 30;
                    targetY = 0;
                }
                if (id == ID.Pinky) {
                    targetX = 0;
                    targetY = 0;
                }
                if (id == ID.Inky) {
                    targetX = 30;
                    targetY = 27;
                }
                if (id == ID.Clyde) {
                    targetX = 0;
                    targetY = 27;
                }
            } else if (ghostStatus == 2) {//chosing random places to go to
                randomNumber = rand.nextInt(4) + 1;
                if (randomNumber == 1) {
                    targetX = 30;
                    targetY = 0;
                } else if (randomNumber == 2) {
                    targetX = 0;
                    targetY = 0;
                } else if (randomNumber == 3) {
                    targetX = 30;
                    targetY = 27;
                } else if (randomNumber == 4) {
                    targetX = 0;
                    targetY = 27;
                }
            }


            arrayPositionX = (x - 90) / 20;//array positions
            arrayPositionY = (y - 90) / 20;

            //finding fastest way to gte to pacman ignoring walls
            distance1 = Math.pow(Math.abs((arrayPositionY - 1) - targetY), 2) + Math.pow(Math.abs((arrayPositionX) - targetX), 2);
            distance2 = Math.pow(Math.abs((arrayPositionY) - targetY), 2) + Math.pow(Math.abs((arrayPositionX + 1) - targetX), 2);
            distance3 = Math.pow(Math.abs((arrayPositionY + 1) - targetY), 2) + Math.pow(Math.abs((arrayPositionX) - targetX), 2);
            distance4 = Math.pow(Math.abs((arrayPositionY) - targetY), 2) + Math.pow(Math.abs((arrayPositionX - 1) - targetX), 2);
            
            //are the ghosts allowed to turn in each direction
            if (currentBoard[arrayPositionY - 1][arrayPositionX] != 2)
                turn1 = true;
            else
                turn1 = false;
            if (currentBoard[arrayPositionY][arrayPositionX + 1] != 2)
                turn2 = true;
            else
                turn2 = false;
            if (currentBoard[arrayPositionY + 1][arrayPositionX] != 2)
                turn3 = true;
            else
                turn3 = false;
            if (currentBoard[arrayPositionY][arrayPositionX - 1] != 2)
                turn4 = true;
            else
                turn4 = false;
            turnArray[1] = turn1;//array to be used when reversing ghosts direction
            turnArray[2] = turn2;
            turnArray[3] = turn3;
            turnArray[4] = turn4;

            pacmanDirection = pacmansID.getActualDirection();

            
            // ghosts try to dind the path that minimize distance from pacman and if they can move in the direction until finding a valid way to move
             
            if (ghostStatus == 0 || ghostStatus == 1 || ghostStatus == 2) {
                if (actualDirection == 1) {
                    if ((distance4 <= distance1 || turn1 == false) && (distance4 <= distance2 || turn2 == false) && currentBoard[arrayPositionY][arrayPositionX - 1] != 2 && currentBoard[(y - 71) / 20][arrayPositionX - 1] != 2) {
                        actualDirection = 4;
                        velX = -1;
                        velY = 0;
                    } else if ((distance1 <= distance2 || turn2 == false) && currentBoard[arrayPositionY - 1][arrayPositionX] != 2 && currentBoard[(y - 71) / 20 - 1][arrayPositionX] != 2) {
                        actualDirection = 1;
                        velX = 0;
                        velY = -1;
                    } else if (currentBoard[arrayPositionY][arrayPositionX + 1] != 2 && currentBoard[(y - 71) / 20][arrayPositionX + 1] != 2) {
                        actualDirection = 2;
                        velX = 1;
                        velY = 0;
                    }
                } else if (actualDirection == 2) {
                    if ((distance1 <= distance2 || turn2 == false) && (distance1 <= distance3 || turn3 == false) && currentBoard[arrayPositionY - 1][arrayPositionX] != 2 && currentBoard[arrayPositionY - 1][(x - 71) / 20] != 2) {
                        actualDirection = 1;
                        velX = 0;
                        velY = -1;
                    } else if ((distance3 <= distance2 || turn2 == false) && currentBoard[arrayPositionY + 1][arrayPositionX] != 2 && currentBoard[arrayPositionY + 1][(x - 71) / 20] != 2) {
                        actualDirection = 3;
                        velX = 0;
                        velY = 1;
                    } else if (currentBoard[arrayPositionY][arrayPositionX + 1] != 2 && currentBoard[(y - 71) / 20][arrayPositionX + 1] != 2) {
                        actualDirection = 2;
                        velX = 1;
                        velY = 0;
                    }
                } else if (actualDirection == 3) {
                    if ((distance4 <= distance3 || turn3 == false) && (distance4 <= distance2 || turn2 == false) && currentBoard[arrayPositionY][arrayPositionX - 1] != 2 && currentBoard[(y - 71) / 20][arrayPositionX - 1] != 2) {
                        actualDirection = 4;
                        velX = -1;
                        velY = 0;
                    } else if ((distance3 <= distance2 || turn2 == false) && currentBoard[arrayPositionY + 1][arrayPositionX] != 2 && currentBoard[arrayPositionY + 1][(x - 71) / 20] != 2) {
                        actualDirection = 3;
                        velX = 0;
                        velY = 1;
                    } else if (currentBoard[arrayPositionY][arrayPositionX + 1] != 2 && currentBoard[(y - 71) / 20][arrayPositionX + 1] != 2) {
                        actualDirection = 2;
                        velX = 1;
                        velY = 0;
                    }
                } else if (actualDirection == 4) {
                    if ((distance4 <= distance1 || turn1 == false) && (distance4 <= distance3 || turn3 == false) && currentBoard[arrayPositionY][arrayPositionX - 1] != 2 && currentBoard[(y - 71) / 20][arrayPositionX - 1] != 2) {
                        actualDirection = 4;
                        velX = -1;
                        velY = 0;
                    } else if ((distance1 <= distance3 || turn3 == false) && currentBoard[arrayPositionY - 1][arrayPositionX] != 2 && currentBoard[(y - 71) / 20 - 1][(x - 71) / 20] != 2) {
                        actualDirection = 1;
                        velX = 0;
                        velY = -1;
                    } else if (currentBoard[arrayPositionY + 1][arrayPositionX] != 2 && currentBoard[(y - 90) / 20 + 1][(x - 71) / 20] != 2 && turn3 == true) {
                        actualDirection = 3;
                        velX = 0;
                        velY = 1;
                    }
                }
            }
        }
    }

    public void render(Graphics g) {//images for ghosts

        if (ghostStatus == 2)
            g.drawImage(frightenedGhostImage, x, y, null);
        else {
            if (id == ID.Blinky)
                g.drawImage(blinkyImage, x, y, null);
            if (id == ID.Pinky)
                g.drawImage(pinkyImage, x, y, null);
            if (id == ID.Inky)
                g.drawImage(inkyImage, x, y, null);
            if (id == ID.Clyde)
                g.drawImage(clydeImage, x, y, null);
            ;
        }

    }

    public void importImages() {//importing images for ghosts
        InputStream is1 = getClass().getResourceAsStream("_BlinkyImage.png");
        InputStream is2 = getClass().getResourceAsStream("_InkyImage.png");
        InputStream is3 = getClass().getResourceAsStream("_PinkyImage.png");
        InputStream is4 = getClass().getResourceAsStream("_ClydeImage.png");
        InputStream is5 = getClass().getResourceAsStream("_FrightenedGhostImage.png");
        try {
            blinkyImage = ImageIO.read(is1);
            inkyImage = ImageIO.read(is2);
            pinkyImage = ImageIO.read(is3);
            clydeImage = ImageIO.read(is4);
            frightenedGhostImage = ImageIO.read(is5);

        } catch (IOException e) {
            e.printStackTrace();
        }
        renderedImages = true;

    }

}
