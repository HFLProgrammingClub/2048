package view;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.screen.ScreenWriter;
import com.googlecode.lanterna.terminal.Terminal;

public class View {
	
	Screen screen;
	Terminal terminal;
	ScreenWriter writer;
	
	List<Pane> panes = new ArrayList<Pane>(); //objects to draw, top is last in list
	
	int width;
	int height;
	
	public View(){
	    
		terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
		terminal.setCursorVisible(false);
		
		width = terminal.getTerminalSize().getColumns();
		height = terminal.getTerminalSize().getRows();

	    screen = new Screen(terminal);

	    screen.clear();
	    screen.refresh();
	    
	    screen.startScreen();
	    
	    screen.refresh();
	    
	    writer = new ScreenWriter(screen);
	}
	
	public void test(){
	    screen.putString(10, 5, "Hello Lanterna!", Terminal.Color.RED, Terminal.Color.GREEN);
	    writer.setForegroundColor(Terminal.Color.BLACK);
	    writer.setBackgroundColor(Terminal.Color.WHITE);
	    writer.drawString(5, 3, "Hello Lanterna!", ScreenCharacterStyle.Bold);
	    writer.setForegroundColor(Terminal.Color.DEFAULT);
	    writer.setBackgroundColor(Terminal.Color.DEFAULT);
	    writer.drawString(5, 5, "Hello Lanterna!");
	    writer.drawString(5, 7, "Hello Lanterna!");
	    screen.refresh();
	}
	
	public void refresh(){
		for(int i = 0 ; i < panes.size() ; i++){
			Pane pane = panes.get(i);
			pane.refresh();
			
			//need to fix issue with small panes (index out of range)
			for(int x = 0 ; x < width ; x++){
				for(int y = 0 ; y < height ; y++){
					if(pane.buffer[x][y] != null){
					    screen.putCharacter(x, y, pane.buffer[x][y]);
					}
				}
			}
		}
		screen.refresh();

	}
	
	public Pane newPane(){
		Pane pane = new Pane(width,height);
		panes.add(pane);
		return pane;
	}
	
	public Pane newPane(int x,int y){
		Pane pane = new Pane(x,y);
		panes.add(pane);
		return pane;
	}
	
	public Pane newPane(int x,int y, int offsetX, int offsetY){
		Pane pane = new Pane(x,y,offsetX,offsetY);
		panes.add(pane);
		return pane;
	}
	
	public void stop(){
	    screen.stopScreen();
	}
	
}
