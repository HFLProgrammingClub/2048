package view;

import com.googlecode.lanterna.screen.ScreenCharacter;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		View view = new View();
		Pane p = view.newPane();
		
		Pane wp = new Pane(90,24,10,2);
		
		Pane dp = new Pane(88,22,1,1);
		
		wp.subpanes.add(dp);
		p.subpanes.add(wp);

		wp.line(0, 0, wp.width-1, 0, new ScreenCharacter('='));
		wp.line(0, wp.height-1, wp.width-1, wp.height-1, new ScreenCharacter('_'));
		wp.line(0, 0, 0, wp.height-1, new ScreenCharacter('|'));
		wp.line(wp.width-1, 0, wp.width-1, wp.height-1, new ScreenCharacter('|'));
		
		dp.fill('+');
		view.refresh();
		
		
	}

}
