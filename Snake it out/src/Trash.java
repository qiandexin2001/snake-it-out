	import java.awt.Color;
	import java.awt.Graphics;
	import java.util.Random;

	public class Trash {
		
		private int xCoor, yCoor, width, height;
		private int color= (int)(Math.random()*4+1);;
		private Color[] colours = {Color.blue, Color.yellow, Color.green,Color.gray};
		
		/* Colour code: 
		 * blue = recycling
		 * yellow = compost
		 * green = refundables
		 * gray = waste
		 */
		
		public Trash(int xCoor, int yCoor, int tileSize) {
	        this.xCoor = xCoor;
	        this.yCoor = yCoor;
	        width = tileSize;
	        height = tileSize;
	        
	    }
		public void tick(){}
		
		 public void draw(Graphics g) {
			 if(color==1)
		        	g.setColor(Color.BLUE);
		        else if(color==2)
		        	g.setColor(Color.YELLOW);
		        else if(color==3)
		        	g.setColor(Color.GREEN);
		        else if(color==4)
		        	g.setColor(Color.GRAY);
		    	g.fillRect(xCoor * 20 , yCoor * 20, 20, 20);
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

