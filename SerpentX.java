
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class SerpentX extends JFrame implements KeyListener {
	cellule tete =new cellule(10,10,Color.orange);
	
	ArrayList<cellule> hanx=new ArrayList<cellule>();
	int tilesize =10;
	JPanel monPanel;
	Timer horloge;
	int mx=0;
	int my=1;
	Random random = new Random();
	cellule food=new cellule(20,20,Color.BLUE)  ;
   
	
	
	
	public SerpentX() {
		super("Snake !");
		this.setSize(506, 509);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
		
		monPanel =new JPanel() {

			@Override
			public void paint(Graphics g) {
				super.paint(g);
				for(int i=0;i<monPanel.getWidth()/tilesize;i++) {
					g.drawLine(i*tilesize, 0,i*tilesize,monPanel.getWidth() );
					
				}
				for(int i=0;i<monPanel.getHeight()/tilesize;i++) {
					g.drawLine(0,i*tilesize,monPanel.getWidth(),i*tilesize);
					
				}
				g.setColor(food.couleur);
				g.fillRect(food.x*tilesize,food.y*tilesize,tilesize,tilesize);
				g.setColor(tete.couleur);
				g.fillRect(tete.x*tilesize,tete.y*tilesize,tilesize,tilesize);
				
				for(cellule c : hanx) {
					g.setColor(c.couleur);
					g.fillRect(c.x*tilesize,c.y*tilesize,tilesize,tilesize);
					
				}
				
			}
				
			};
		
		
		this.addKeyListener(this);
		monPanel.setBackground(Color.black);
		this.setContentPane(monPanel);
		
		
		
		
		horloge =new Timer(100, new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(tete.x*tilesize >monPanel.getWidth()) {
					tete.x=0;
				}
				if(tete.x*tilesize<0) {
					tete.x=monPanel.getWidth()/tilesize;
				}
				if(tete.y*tilesize<0) {
					tete.y=monPanel.getHeight()/tilesize;
				}
				if(tete.y*tilesize>monPanel.getHeight()) {
					tete.y=0;
				}
				
				if(collotion(tete,food)) {
					cellule monger = food ;
					hanx.add(monger);
				    food=creefood();
				}
				
				
				for(int i=hanx.size()-1;i>=0;i--) {
					cellule c=hanx.get(i);
					if(i==0) {
						c.x=tete.x;
						c.y=tete.y;
						
					}
					else {
						cellule cpre=hanx.get(i-1);
						c.x=cpre.x;
						c.y=cpre.y;
					}
				}
				
				tete.x +=mx;
				tete.y +=my;
				
				for(int i=0 ;i< hanx.size();i++) {
					cellule c=hanx.get(i);
					if(collotion(tete ,c)) {
						horloge.stop();
					}
				}
				
				
				
				  
				
				
				repaint();
				
			}
		});
		horloge.start();
		
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode()==KeyEvent.VK_UP && my!=1) {
			
			mx=0;
			my=-1;
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN  && my!=-1) {
			mx=0;
			my=1;
		}
		else if(e.getKeyCode()==KeyEvent.VK_LEFT && mx!=1) {
			mx=-1;
			my=0;
		}
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT && mx!=-1) {
			mx=1;
			my=0;
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public Boolean collotion(cellule c1 ,cellule c2) {
		return c1.x==c2.x && c1.y==c2.y;
		
	}
	
	
	public cellule creefood() {
		boolean exist =false ;
		int  x;
		int y;
		do {
		exist =false ;
		x=random.nextInt(0,monPanel.getWidth()/tilesize);
		y=random.nextInt(0,monPanel.getHeight()/tilesize);
		for(cellule c : hanx) {
			if(x==c.x && y==c.y) {
				exist=true;
				break;
			}
		}
		}while(exist==true);
		food =new cellule(x,y,Color.BLUE);
		return food;
	}
	
	
	
			  
			
	public static void main(String[] args) {
		SerpentX serpent=new SerpentX();
		serpent.setVisible(true);
		

	}
	
}





class cellule {
	int x , y;
	Color couleur ;
	public cellule(int x, int y, Color couleur) {
		super();
		this.x = x;
		this.y = y;
		this.couleur = couleur;
	}

	
}

class obstacle {
	cellule tab[];
	int x , y ;
	public obstacle(cellule[] tab, int x, int y) {
		super();
		this.tab = tab;
		this.x = x;
		this.y = y;
	}
	
	
}