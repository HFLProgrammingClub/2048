package view;


public class Structure extends Pane {
	
	//this will hold the unaltered draw data
	Pane archive;
	
	Blueprints blueprints;
		
	public Structure(int x, int y) {
		super(x, y);
		archive = new Pane(x,y);
	}
	
	public Structure(int x, int y, int posx, int posy) {
		super(x, y,posx ,posy);
		archive = new Pane(x,y,posx,posy);

	}
	
	void update(){
		blueprints.draw(this);
		reset();
	}
	
	void reset(){
		buffer = archive.buffer;
	}
	
	public void refresh(){
		preRefresh();
		super.refresh();
		postRefresh();
	}
	
	void preRefresh(){
		update();
	}
	
	void postRefresh(){
		
	}
	
	
}
