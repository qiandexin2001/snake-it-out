import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Gamepanel extends JPanel implements Runnable, KeyListener{
	
	private static final long serialVersionUID=1L;
	
	public static final int WIDTH = 800, HEIGHT = 800;
	
	private Thread thread;
	
	private boolean running;
	private boolean right = true, left = false, up = false, down = false;
	
	private BodyPart b;
	private ArrayList<BodyPart> snake;
	
	private Trash trash;
	private ArrayList<Trash> trashes;
	
	private Trap trap;
	private ArrayList<Trap> traps;
	
	private Random r;
	
	private int xCoor = 10, yCoor = 10, size = 15;
	private static int ticks = 0, moves=0;
	
	private Image cardBoard;
	

	public Gamepanel(){
		setFocusable(true);
		
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		addKeyListener(this);
		
		snake= new ArrayList<BodyPart>();
		trashes = new ArrayList<Trash>();
		traps = new ArrayList<Trap>();
		
		r = new Random();
		
		start();
		}
	
	public void loadImage(Graphics g)
	{
		ImageIcon iid = new ImageIcon("src/picture/cardboard.jpg");
		cardBoard = iid.getImage();
	}
	public void start()
	{
		running = true;
		thread = new Thread(this);
		thread.start();
	}
//	public void resume()
//	{
//		running=true;
//		thread.resume();
//	}
	public void stop()
	{
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void tick()
	{
		if(snake.size()==0)
		{
			b = new BodyPart(xCoor, yCoor, 20);
			snake.add(b);
		}
		ticks++;
		//250000= 500*500
		if(ticks > 250000 ) {
			if(right) xCoor++;
			if(left) xCoor--;
			if(up) yCoor--;
			if(down) yCoor++;
			
			ticks = 0;
			moves++;
			if(moves>20){
				moves=0;
				BodyPart.changeColor();
				
			}
			
			b = new BodyPart(xCoor, yCoor, 20);
			snake.add(b);
			
			if(snake.size() > size){
				snake.remove(0);
			}
		}
		
		if(traps.size()==0)
		{
			int xCoor = r.nextInt(9);
			int yCoor = r.nextInt(9);
			
			trap = new Trap(xCoor, yCoor, 40);
			traps.add(trap);}
			for (int i = 0; i< traps.size(); i++)
			{
				if(xCoor == traps.get(i).getxCoor() && yCoor == traps.get(i).getyCoor()) 
				    {
							trap.generateQuiz();
							thread.suspend();
							thread.resume();
							size++;
							traps.remove(i);
							i++;
				}
			
		}
		if (trashes.size()==0)
		{
	 // B/c width =500, height =500
			//Trash 1
			int xCoor1 = r.nextInt(9);
			int yCoor1 = r.nextInt(9);
			
			trash= new Trash(xCoor1, yCoor1, 40);
			trashes.add(trash);
			//trash 2
			int xCoor2 = r.nextInt(9);
			int yCoor2 = r.nextInt(9);
			
			trash= new Trash(xCoor2, yCoor2, 40);
			trashes.add(trash);
			//trash 3
			int xCoor3 = r.nextInt(9);
			int yCoor3 = r.nextInt(9);
			
			trash= new Trash(xCoor3, yCoor3, 40);
			trashes.add(trash);
			//trash 4
			int xCoor4 = r.nextInt(9);
			int yCoor4 = r.nextInt(9);
			
			trash= new Trash(xCoor4, yCoor4, 40);
			trashes.add(trash);
		}
		// Snake eats trash
		for (int i = 0; i< trashes.size(); i++)
		{
			Trash rightTrash;
			if(xCoor == trashes.get(i).getxCoor() && yCoor == trashes.get(i).getyCoor()) 
				{	rightTrash=trashes.get(i);
					if (BodyPart.color== rightTrash.getColor())
						{
							size++;
							trashes.remove(i);
							i++;
						}
					else if (!	(BodyPart.color==trash.getColor())) 
						{
							System.out.println("Game over");
							System.out.println(snake.get(i).getxCoor());
							System.out.println(snake.get(i).getyCoor());
							System.out.println(rightTrash.getxCoor());
							System.out.println(rightTrash.getyCoor());
							stop();
			}}
		}
		//collision on snake body part 
		for (int i = 0; i < snake.size(); i++)
			{if(xCoor == snake.get(i).getxCoor() && yCoor == snake.get(i).getyCoor())
			{
				if(i != snake.size()-1)
				{
					System.out.println("Game Over");
					stop();
				}
			}
			}
		//collision on border
			if (xCoor < 0 || xCoor > 39 || yCoor > 39)
			{ System.out.println("Game Over");
				stop();
			}
		}
	
	public void paint(Graphics g)
	{
		
		g.clearRect(0,0,WIDTH,HEIGHT);
		g.setColor(Color.orange);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		loadImage(g);
		g.drawImage(cardBoard, 0, 0, null);
		g.setColor(Color.BLACK);
		for (int i =0; i< WIDTH/20; i++)
			{
				g.drawLine(i*20, 0, i*20, HEIGHT);
			}
		for(int i=0; i<HEIGHT/20; i++)
			{
				g.drawLine(0, i*20, HEIGHT, i*20);
			}
		for (int i = 0; i < snake.size(); i++ )
		{
			snake.get(i).draw(g);
		}
		for (int i = 0 ; i < trashes.size(); i++)
		{
			trashes.get(i).draw(g);
		}
		for (int i = 0 ; i < traps.size(); i++)
		{
			traps.get(i).draw(g);
		}
	}

	@Override
	public void run() {
		while (running)
		{
			tick();
			repaint();
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT && !left)
		{
			right = true;
			up = false;
			down = false;
		}
		if(key == KeyEvent.VK_LEFT && !right)
		{
			left = true;
			up = false;
			down = false;
		}
		if(key == KeyEvent.VK_UP && !down)
		{
			up = true;
			left = false;
			right = false;
		}
		if(key == KeyEvent.VK_DOWN && !up)
		{
			down = true;
			left = false;
			right = false;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
