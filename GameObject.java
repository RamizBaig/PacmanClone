import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
    protected int x, y;//all of the variables that will need to be used from seperate files
    protected ID id;
    protected double velX, velY;
    protected int wantedDirection, actualDirection;
    protected int arrayPositionX, arrayPositionY, lastArrayPositionX, lastArrayPositionY;
    protected int lives, pelletsEaten = 0, level = 1;
    protected int ghostStatus;
    protected boolean buffed = false, extendBuff = false;
    protected long frightenedEnd;
    protected int combo = 0;
    protected boolean[] turnArray = new boolean[5];

    public GameObject(int x, int y, ID id) {//constructor for gameobjects
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();

    public abstract void render(Graphics g);// since its abstract when we extend this file to a player class then we can use these methods

    public abstract Rectangle getBounds();

    public void setX(int newX) {//contains setters/getters for most variables
        x = newX;
    }

    public void setY(int newY) {
        y = newY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public ID getId() {
        return id;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public double getVelX() {
        return velX;
    }

    public double getVelY() {
        return velY;
    }

    public int getWantedDirection() {
        return wantedDirection;
    }

    public void setWantedDirection(int newDirection) {
        wantedDirection = newDirection;
    }

    public int getLastArrayPositionX() {
        return lastArrayPositionX;
    }

    public int getLastArrayPositionY() {
        return lastArrayPositionY;
    }

    public int getArrayPositionX() {
        return arrayPositionX;
    }

    public int getArrayPositionY() {
        return arrayPositionY;
    }

    public int getActualDirection() {
        return actualDirection;
    }

    public void setActualDirection(int newActualDirection) {
        actualDirection = newActualDirection;
    }

    public int getLives() {
        return lives;
    }

    public int getLevel() {
        return level;
    }

    public int getPelletsEaten() {
        return pelletsEaten;
    }

    public boolean getBuffed() {
        return buffed;
    }

    public void setBuffed(boolean newBuffed) {
        buffed = newBuffed;
    }

    public void setExtendBuffed(boolean newBuffed) {
        buffed = newBuffed;
    }

    public void setGhostStatus(int newGhostStatus) {
        ghostStatus = newGhostStatus;
    }

    public int getGhostStatus() {
        return ghostStatus;
    }

    public long getFrightenedEnd() {
        return frightenedEnd;
    }

    public void setFrightenedEnd(long newFrightenedEnd) {
        frightenedEnd = newFrightenedEnd;
    }

    public void setCombo(int newCombo) {
        combo = newCombo;
    }

    public int getCombo() {
        return combo;
    }

    public boolean getTurnArray(int position) {
        return turnArray[position];
    }
}