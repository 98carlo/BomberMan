
public class ModelBomb {
	public int x, y;
	int timer = 4;
	ModelMain model;
	ModelPlayer player;
	
	public ModelBomb(int x, int y, ModelMain model, ModelPlayer player){
		this.x = x;
		this.y = y;
		this.model = model;
		this.player = player;
	}
	
	public void tickBomb(){
		timer--;
		if(timer>0){
		model.setField(x, y, -model.field[x][y]);
		}
		if(timer == 0){
			explodeBomb();
		}
		if(timer == -1){
			model.resetExplosion(this);
			player.numBomb--;
			player.bombs.remove(this);
		}

	}
	
	private void explodeBomb(){
		model.explodeBomb(this);
	}
}
