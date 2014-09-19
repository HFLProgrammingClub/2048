package view;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.lanterna.screen.ScreenCharacter;

public class Pane {
	List<Pane> subpanes = new ArrayList<Pane>();//top is last in list
	ScreenCharacter[][] buffer;
	
	int width;
	int height;
	
	int offsetX = 0;
	int offsetY = 0;
	
	public Pane(int x , int y){
		width=x;
		height=y;
		buffer = new ScreenCharacter[x][y];
	}
	
	public Pane(int x , int y, int offX, int offY){
		width= x;
		height= y;
		
		//position in parent pane/view
		offsetX= offX;
		offsetY= offY;

		buffer = new ScreenCharacter[x][y];
	}
	
	
	public void putChar(int x,int y, ScreenCharacter charater){		
		if(x < width && y < height){
			buffer[x][y] = charater;
		}
	}
	
	public void write(int x,int y, String text){
		write(x,y,text,"right");
	}
	
	public void write(int x,int y, String text, String direction){
		char[] chars = text.toCharArray();
		
		switch(direction){
			case "up":
				for(int i = 0 ; i < chars.length ; i++){
					if(x+i < width && y < height && x+i >= 0 && y >= 0){
						buffer[x][y-i] = new ScreenCharacter(chars[i]);
					}
				}
				break;
				
			case "down":
				for(int i = 0 ; i < chars.length ; i++){
					if(x+i < width && y < height && x+i >= 0 && y >= 0){
						buffer[x][y+i] = new ScreenCharacter(chars[i]);
					}
				}
				break;
				
			case "left":
				for(int i = 0 ; i < chars.length ; i++){
					if(x+i < width && y < height && x+i >= 0 && y >= 0){
						buffer[x-i][y] = new ScreenCharacter(chars[i]);
					}
				}
				break;
				
			case "right":
				for(int i = 0 ; i < chars.length ; i++){
					if(x+i < width && y < height && x+i >= 0 && y >= 0){
						buffer[x+i][y] = new ScreenCharacter(chars[i]);
					}
				}
				break;
				
			case "up-right":
				for(int i = 0 ; i < chars.length ; i++){
					if(x+i < width && y < height && x+i >= 0 && y >= 0){
						buffer[x+i][y-i] = new ScreenCharacter(chars[i]);
					}
				}
				break;
				
			case "down-right":
				for(int i = 0 ; i < chars.length ; i++){
					if(x+i < width && y < height && x+i >= 0 && y >= 0){
						buffer[x+i][y+i] = new ScreenCharacter(chars[i]);
					}
				}
				break;
				
			case "up-left":
				for(int i = 0 ; i < chars.length ; i++){
					if(x+i < width && y < height && x+i >= 0 && y >= 0){
						buffer[x-i][y-i] = new ScreenCharacter(chars[i]);
					}
				}
				break;
				
			case "down-left":
				for(int i = 0 ; i < chars.length ; i++){
					if(x+i < width && y < height && x+i >= 0 && y >= 0){
						buffer[x-i][y+i] = new ScreenCharacter(chars[i]);
					}
				}
				break;
		}
		
	}
	
	//A line from point x1 y1 to point x2 y2
	public void line(int x1,int y1,int x2, int y2, ScreenCharacter character){		
		
		//first find change in x and y
		int deltaX = x2-x1;
		int deltaY = y2-y1;
		
		//make sure slope isn't undefined
		if(deltaX != 0){
			
			//calculate the slope
			int m = deltaY/deltaX;
			
			//determine which axis to draw line by(larger delta is better)
			if(Math.abs(deltaX) > Math.abs(deltaY)){
				
				//go from x1 to x2, decrementing/incrementing by one
				for (int posX = x1; posX != x2 ; posX += Integer.signum(deltaX)){
					//calculate then y value for the given x using point slope formula, then round to the nearest integer draw the char
					int posY = Math.round( m * (posX - x1) + y1 );
						if(posX >= 0 && posX < width && posY >= 0 && posY < height){
							putChar(posX,posY,character);
						}
				}
				
			}else{
				for (int posY = y1; posY != y2 ; posY += Integer.signum(deltaY)){
					int posX = Math.round( 1/m * (posY - y1) + x1 );
					
					if(posX >= 0 && posX < width && posY >= 0 && posY < height){
						putChar(posX,posY,character);
					}		
				}
			}
			
			//or else last char wont print
			putChar(x2,y2,character);

			
		}else{
			for (int posY = y1; posY != y2 ; posY += Integer.signum(deltaY)){
				putChar(x1,posY,character);
			}
		}
		
		
		/* code for exact line
		 * 		int factor = Math.abs(GCF(deltaX, deltaY));
		
		deltaX = deltaX/factor;
		deltaY = deltaY/factor;
		
		for( int posX = x1, posY = y1; posX != x2 || posY != y2; posX += deltaX, posY += deltaY ){
			if(posX<width && posY<height){
				buffer[posX][posY]= new ScreenCharacter(character);
			}
		}
		if(x2<width && y2<height){
			buffer[x2][y2]= new ScreenCharacter(character);
		}
		*/

	}

	public void vectorLine(int x,int y, int length, String direction, ScreenCharacter character){
		switch(direction){
		case "up":
			for(int i = 0 ; i < length ; i++){
				if(x+i < width && y < height && x+i >= 0 && y >= 0){
					buffer[x][y-i] = new ScreenCharacter(character);
				}
			}
			break;
			
		case "down":
			for(int i = 0 ; i < length ; i++){
				if(x+i < width && y < height && x+i >= 0 && y >= 0){
					buffer[x][y+i] = new ScreenCharacter(character);
				}
			}
			break;
			
		case "left":
			for(int i = 0 ; i < length ; i++){
				if(x+i < width && y < height && x+i >= 0 && y >= 0){
					buffer[x-i][y] = new ScreenCharacter(character);
				}
			}
			break;
			
		case "right":
			for(int i = 0 ; i < length ; i++){
				if(x+i < width && y < height && x+i >= 0 && y >= 0){
					buffer[x+i][y] = new ScreenCharacter(character);
				}
			}
			break;
			
		case "up-right":
			for(int i = 0 ; i < length ; i++){
				if(x+i < width && y < height && x+i >= 0 && y >= 0){
					buffer[x+i][y-i] = new ScreenCharacter(character);
				}
			}
			break;
			
		case "down-right":
			for(int i = 0 ; i < length ; i++){
				if(x+i < width && y < height && x+i >= 0 && y >= 0){
					buffer[x+i][y+i] = new ScreenCharacter(character);
				}
			}
			break;
			
		case "up-left":
			for(int i = 0 ; i < length ; i++){
				if(x+i < width && y < height && x+i >= 0 && y >= 0){
					buffer[x-i][y-i] = new ScreenCharacter(character);
				}
			}
			break;
			
		case "down-left":
			for(int i = 0 ; i < length ; i++){
				if(x+i < width && y < height && x+i >= 0 && y >= 0){
					buffer[x-i][y+i] = new ScreenCharacter(character);
				}
			}
			break;
		}
	}
	
	public void offsetLine(){
		
	}
	
	public void clear(){
		for(int x = 0 ; x < width ; x++){
			for(int y = 0 ; y < width ; y++){
				buffer[x][y] = null;
			}
		}
	}
	
	
	
	public void fill(ScreenCharacter character){
		for(int x = 0 ; x < width ; x++){
			for(int y = 0 ; y < height ; y++){
				buffer[x][y] = new ScreenCharacter(character);
			}
		}
	}
	
	public void fill(char character){
		fill(new ScreenCharacter(character));
	}
	
	public Pane newPane(){
		Pane pane = new Pane(width,height);
		subpanes.add(pane);
		return pane;
	}
	
	public Pane newPane(int x,int y){
		Pane pane = new Pane(x,y);
		subpanes.add(pane);
		return pane;
	}
	
	public Pane newPane(int x,int y, int offsetX, int offsetY){
		Pane pane = new Pane(x,y,offsetX,offsetY);
		subpanes.add(pane);
		return pane;
	}
	
	public void refresh(){
		for(int i = 0 ; i < subpanes.size() ; i++){
			Pane pane = subpanes.get(i);
			pane.refresh();
			
			for(int x = 0 ; x < pane.width ; x++){
				for(int y = 0 ; y < pane.height ; y++){
					if(x + pane.offsetX < width && y + pane.offsetY < height && pane.buffer[x][y] != null){
						buffer[x + pane.offsetX][y + pane.offsetY]= pane.buffer[x][y];
					}
				}
			}
		}
	}
	
	
	public static int GCF(int a, int b) {
	   if (b==0) return a;
	   return GCF(b,a%b);
	}
	

}
