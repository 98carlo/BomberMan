import java.util.ArrayList;

public class ModelPlayer {
	final static int UP = 4;
	final static int DOWN = 5;
	final static int LEFT = 6;
	final static int RIGHT = 7;
	final static int STANDING = 0;
	double x, y;
	int maxNumBomb = 2;
	int direction = STANDING;
	boolean PlayerAlive = true;
	ModelMain model;
	int numBomb = 0;
	ArrayList<ModelBomb> bombs = new ArrayList<ModelBomb>();
	
	public ModelPlayer(double x, double y, ModelMain model){
		this.x = x;
		this.y = y;
		this.model = model;
	}
	
	public void changeDirection(int direction){
		this.direction = direction;
	}
	
	public void movePlayer(){
		if(model.checkCollision(x, y, direction)){
			switch(direction){
				case UP:
					y = y-model.speed;
					break;
				case DOWN:
					y = y+model.speed;
					break;
				case RIGHT:
					x = x+model.speed;
					break;
				case LEFT:
					x = x-model.speed;
					break;					
			}
		}
	}
	
	public void fixPosition(int lastDirection){
		switch(lastDirection){
			case UP:
				y = y-(y*2-Math.floor(y*2))/2;
				break;
			case DOWN:
				if(y-Math.floor(y) != 0.5){
				y = y-(y*2-Math.floor(y*2+1))/2;
				}
				break;
			case RIGHT:
				if(x-Math.floor(x) != 0.5){
				x = x-(x*2-Math.floor(x*2+1))/2;
				}
				break;
			case LEFT:
				x = x-(x*2-Math.floor(x*2))/2;
				break;
		}
		this.direction = STANDING;
	}
	
	public void placeBomb(){
		if(numBomb != maxNumBomb){
			ModelBomb bomb = new ModelBomb((int)x, (int)y, model, this);
			bombs.add(bomb);
			numBomb++;
			model.setField((int)x, (int)y, 2);
		}
	}
	
	public void updateBomb(){
		for(int i = 0; i < numBomb; i++){
			bombs.get(i).tickBomb();
		}
	}
}
