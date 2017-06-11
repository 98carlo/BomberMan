import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Controller implements KeyListener{
	View view;
	ModelMain model;
	Thread thread;
	int numRows, numCols, squareSize, width, height;
	int UP = 4;
	int DOWN = 5;
	int LEFT = 6;
	int RIGHT = 7;
	int STANDING = 0;
	boolean gameRunning = true;
	int fps = 20;
	
	public Controller(){
		
		numRows = 15;
		numCols = 13;
		squareSize = 50;
		width = numRows*squareSize;
		height = numCols*squareSize;
				
		model = new ModelMain(numRows, numCols, squareSize);
		view = new View(model.field, width, height, numRows, numCols, squareSize, this, model.Player1, model.Player2/*, model.playerSize*/);
		
		thread = new Thread();
		thread.start();
		run();
	}
	
	
	public void run(){
		int t = 500/fps;
		while(gameRunning){
			if(model.playersAlive()){
				view.updateField(model.field, model.Player1, model.Player2);
				view.repaint();
				model.update();
				try {
					thread.sleep(1000/fps);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				t--;
				if(t == 0){
					model.updateBomb();
					t = 500/fps;
				}
			} else{
				System.out.println("GAME OVER");
				for(int i = 0; i < numRows; i++){
					for(int j = 0; j < numCols; j++){
						model.field[i][j] = 1;
						view.updateField(model.field, model.Player1, model.Player2);
						view.repaint();
						try {
							thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				gameRunning = false;
			}
		}
	}
	
	

	public static void main(String[] args) {
		new Controller();

	}


	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
			case KeyEvent.VK_W:
				model.Player1.changeDirection(UP);
				break;
			case KeyEvent.VK_S:
				model.Player1.changeDirection(DOWN);
				break;
			case KeyEvent.VK_A:
				model.Player1.changeDirection(LEFT);
				break;
			case KeyEvent.VK_D:
				model.Player1.changeDirection(RIGHT);
				break;
			case KeyEvent.VK_SPACE:
				model.Player1.placeBomb();
				break;
			case KeyEvent.VK_UP:
				model.Player2.changeDirection(UP);
				break;
			case KeyEvent.VK_DOWN:
				model.Player2.changeDirection(DOWN);
				break;
			case KeyEvent.VK_LEFT:
				model.Player2.changeDirection(LEFT);
				break;
			case KeyEvent.VK_RIGHT:
				model.Player2.changeDirection(RIGHT);
				break;
			case KeyEvent.VK_ENTER:
				model.Player2.placeBomb();
				break;
			default:
				System.out.println("wrong Key");
				break;
		}
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_W:
			model.Player1.fixPosition(UP);
			break;
		case KeyEvent.VK_S:
			model.Player1.fixPosition(DOWN);
			break;
		case KeyEvent.VK_A:
			model.Player1.fixPosition(LEFT);
			break;
		case KeyEvent.VK_D:
			model.Player1.fixPosition(RIGHT);
			break;
		case KeyEvent.VK_UP:
			model.Player2.fixPosition(UP);
			break;
		case KeyEvent.VK_DOWN:
			model.Player2.fixPosition(DOWN);
			break;
		case KeyEvent.VK_LEFT:
			model.Player2.fixPosition(LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			model.Player2.fixPosition(RIGHT);
			break;
	}
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
