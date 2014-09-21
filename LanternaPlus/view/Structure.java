package view;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import com.googlecode.lanterna.screen.ScreenCharacter;

public class Structure extends Pane {

	//this will hold the unaltered draw data
	Pane archive;

	public Map<String, Point> points;
	public Map<String, Line> lines;//draws lines between points

	public Structure(int x, int y) {
		this(x, y, 0, 0);
	}

	public Structure(int x, int y, int posx, int posy) {
		super(x, y, posx, posy);

		lines = new HashMap<String, Line>();
		points = new HashMap<String, Point>();

		archive = new Pane(x, y, posx, posy);
	}

	public void update(String message) {

	}

	public void draw() {
		fill(new ScreenCharacter(' '));

		for (final Line l : lines.values()) {
			line(points.get(l.pointOne).x, points.get(l.pointOne).y,
					points.get(l.pointTwo).x, points.get(l.pointTwo).y,
					l.character);
		}
	}

	void reset() {
		buffer = archive.buffer;
	}

	@Override
	public void refresh() {
		preRefresh();
		super.refresh();
		postRefresh();
	}

	void preRefresh() {
		update("refresh");
	}

	void postRefresh() {

	}

}
