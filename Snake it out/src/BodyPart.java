import java.awt.Color;
import java.awt.Graphics;
public class BodyPart {
    private int xCoor, yCoor, width, height;
    public static int color;//blue=1,yellow=2,green=3
    public BodyPart(int xCoor, int yCoor, int tileSize) {
        this.xCoor = xCoor;
        this.yCoor = yCoor;
        width = tileSize;
        height = tileSize;
    }
    public void tick() {
    }
    public static void changeColor() {
    	color=(int)(Math.random()*4+1);
    }
    public void draw(Graphics y) {
        if(color==1)
        	y.setColor(Color.BLUE);
        else if(color==2)
        	y.setColor(Color.YELLOW);
        else if(color==3)
        	y.setColor(Color.GREEN);
        else if(color==4)
        	y.setColor(Color.GRAY);
        y.fillRect(xCoor * 20, yCoor * 20, 20, 20); //20
        //g.setColor(Color.GREEN);
        //g.fillRect(xCoor * width + 2, yCoor * height + 2, width -4, height-4);
    }
    public int getxCoor() {
        return xCoor;
    }
    public void setxCoor(int xCoor) {
        this.xCoor = xCoor;
    }
    public int getyCoor() {
        return yCoor;
    }
    public void setyCoor(int yCoor) {
        this.yCoor = yCoor;
    }
    public int getColor(){return color;}
}
