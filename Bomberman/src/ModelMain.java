
public class ModelMain {

	int FLOOR = 0;
	int WALL = 1;
	int BOMB = 2;
	int BOMBBLUE = -2;
	int DEATH = 3;
	int BRICK = 4;
	final static int UP = 4;
	final static int DOWN = 5;
	final static int LEFT = 6;
	final static int RIGHT = 7;
	final static int STANDING = 0;
	int [][] field;
	int numRows;
	int numCols;
	double speed = 0.125;
	int squareSize;
	ModelPlayer Player1, Player2;
	
	public ModelMain(int numRows, int numCols, int squareSize){
		this.numRows = numRows;
		this.numCols = numCols;
		this.squareSize = squareSize;
		field = new int [numRows][numCols];
		Player1 = new ModelPlayer(1.5, 1.5, this);
		Player2 = new ModelPlayer(numRows-1.5, numCols-1.5, this);
		for(int i = 0; i < numRows; i++){
			for (int j = 0; j < numCols; j++){
				if(i == 0 || j == 0){
					field[i][j] = WALL;
				} else if((i+1) == numRows || (j+1) == numCols){
					field[i][j] = WALL;
				} else if(i % 2 == 0 && j % 2 == 0){
					field[i][j] = WALL;
				} else if(i<3 && j<3){
					field[i][j] = FLOOR;
				} else if(i>11 && j>9){
					field[i][j] = FLOOR;
				}else{
					int random = (int)(Math.random()*10);
					if(random<8){
						field[i][j] = BRICK;
					} else{
						field[i][j] = FLOOR;
					}
				}
			}
		}
	}
	
	public void update(){	
		Player1.movePlayer();
		Player2.movePlayer();
		if(field[(int)Player1.x][(int)Player1.y] == DEATH){
			Player1.PlayerAlive = false;
		}
		if(field[(int)Player2.x][(int)Player2.y] == DEATH){
			Player2.PlayerAlive = false;
		}
	}
	
	public boolean checkCollision(double x, double y, int direction){
		if(direction == UP){
			if((y-(.5+speed))<Math.round(y-1)){
				if(field[(int)x][(int)(y-(.5+speed))] == FLOOR && (x-Math.floor(x)) == 0.5){
					return true;
				} else{
					return false;
				}
			}
		}
		if(direction == DOWN){
			if((y+.5+speed)>Math.floor(y+1)){
				if(field[(int)x][(int)(y+(.5+speed))] == FLOOR && (x-Math.floor(x)) == 0.5){
					return true;
				} else{
					return false;
				}
			}
		}
		if(direction == RIGHT){
			if((x+.5+speed)>Math.floor(x+1)){
				if(field[(int)(x+(.5+speed))][(int)y] == FLOOR && (y-Math.floor(y)) == 0.5){
					return true;
				} else{
					return false;
				}
			}
		}
		if(direction == LEFT){
			if((x-(.5+speed))<Math.round(x-1)){
				if(field[(int)(x-(.5+speed))][(int)y] == FLOOR && (y-Math.floor(y)) == 0.5){
					return true;
				} else{
					return false;
				}
			}
		}
		return true;
	}
	
	public void explodeBomb(ModelBomb bomb){
		int x = bomb.x;
		int y = bomb.y;
		field[x][y] = DEATH;
		for(int i = x; i < (x+3); i++){
			if(i>0 && i < numRows){
				if(field[i][y] == BRICK){
					field[i][y] = DEATH;
					break;
				}else if(field[i][y] == WALL || field[i][y] == BOMB){
					break;
				} else{
					field[i][y] = DEATH;
				}
			}
		}
		for(int i = x; i > (x-3); i--){
			if(i>0 && i < numRows){
				if(field[i][y] == BRICK){
					field[i][y] = DEATH;
					break;
				}else if(field[i][y] == WALL || field[i][y] == BOMB){
					break;
				} else{
					field[i][y] = DEATH;
				}
			}
		}
		for(int i = y; i < (y+3); i++){

			if(i>0 && i < numCols){
				if(field[x][i] == BRICK){
					field[x][i] = DEATH;
					break;
				}else if(field[x][i] == WALL || field[x][i] == BOMB){
					break;
				} else{
					field[x][i] = DEATH;					
				}
			}
		}
		for(int i = y; i > (y-3); i--){
			if(i>0 && i < numCols){
				if(field[x][i] == BRICK){
					field[x][i] = DEATH;
					break;
				}else if(field[x][i] == WALL || field[x][i] == BOMB){
					break;
				} else{
					field[x][i] = DEATH;
				}
			}
		}

	}
	
	public void resetExplosion(ModelBomb bomb){
		int x = bomb.x;
		int y = bomb.y;
		for(int i = x; i < (x+3); i++){
			if(i>0 && i < numRows){
				if(field[i][y] == DEATH){
					field[i][y] = FLOOR;
				}
			}
		}
		for(int i = x; i > (x-3); i--){
			if(i>0 && i < numRows){
				if(field[i][y] == DEATH){
					field[i][y] = FLOOR;
				}
			}
		}
		for(int i = y; i < (y+3); i++){

			if(i>0 && i < numCols){
				if(field[x][i] == DEATH){
					field[x][i] = FLOOR;
				}
			}
		}
		for(int i = y; i > (y-3); i--){
			if(i>0 && i < numCols){
				if(field[x][i] == DEATH){
					field[x][i] = FLOOR;
				}
			}
		}
	}
	
	public boolean playersAlive(){
		if(Player1.PlayerAlive && Player2.PlayerAlive){
			return true;
		} else{
			return false;
		}
	}
	
	public void updateBomb(){
		Player1.updateBomb();
		Player2.updateBomb();
	}
	
	public void setField(int x, int y, int field){
		this.field[x][y] = field;
	}
	
}
