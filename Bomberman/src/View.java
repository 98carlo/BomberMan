import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class View extends JPanel{
	
	int prefWidth, prefHeight;
	int numCols, numRows;
	int squareSize;
	
	int [][] field;
	ModelPlayer Player1;
	ModelPlayer Player2;
	
	Controller controller;
	
	final static int FLOOR = 0;
	final static int WALL = 1;
	final static int BOMBRED = 2;
	final static int BOMBBLUE = -2;
	final static int DEATH = 3;
	final static int BRICK = 4;
	
	public View(int [][] field, int width, int height, int numRows, int numCols, int squareSize, Controller controller, ModelPlayer Player1, ModelPlayer Player2){
		this.prefWidth = width;
		this.prefHeight = height;
		this.numCols = numCols;
		this.numRows = numRows;
		this.field = field;
		this.Player1 = Player1;
		this.Player2 = Player2;
		this.squareSize = squareSize;
		JFrame frame = new JFrame("BomberMan");
		
		this.setPreferredSize(new Dimension(this.prefWidth, this.prefHeight));
		
		frame.add(this);
		frame.addKeyListener(controller);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void updateField(int [][] field, ModelPlayer Player1, ModelPlayer Player2){
		this.field = field;
		this.Player1 = Player1;
		this.Player2 = Player2;
	}
	
	public void paint(Graphics g){
		for(int x = 0; x < this.numRows; x++) {
			for(int y = 0; y < this.numCols; y++){
				
				switch(field[x][y]){
					case FLOOR:
						g.setColor(Color.yellow);
						break;
					case WALL:
						g.setColor(Color.gray);
						break;
					case BOMBRED:
						g.setColor(Color.red);
						break;
					case BOMBBLUE:
						g.setColor(Color.CYAN);
						break;
					case DEATH:
						g.setColor(Color.red);
						break;
					case BRICK:
						g.setColor(Color.orange);
						break;
					default:
						System.out.println(field[x][y] + " " + x + " " + y);
						
				}
				
				g.fillRect(x*this.squareSize, y*this.squareSize, squareSize, squareSize);
				
			}
		}
		g.setColor(Color.black);
		g.fillOval((int)(Player1.x*squareSize-squareSize/2), (int)(Player1.y*squareSize-squareSize/2), squareSize, squareSize);
		g.setColor(Color.blue);
		g.fillOval((int)(Player2.x*squareSize-squareSize/2), (int)(Player2.y*squareSize-squareSize/2), squareSize, squareSize);
	}
	
}
