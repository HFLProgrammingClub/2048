package view;

import java.awt.Point;
import java.util.List;
import java.util.Map;

import com.googlecode.lanterna.screen.ScreenCharacter;

//class to be extended to control a structure
abstract public class Blueprints {
	
	Map<String, Point> points;
	List<Line> lines;//draws lines between points
	
	public void draw(Structure s){
		for(Line l : lines){
			s.line(points.get(l.pointOne).x, points.get(l.pointOne).y, points.get(l.pointTwo).x, points.get(l.pointTwo).y, l.character);
		}
	}
	
	public void setPoint(String name, int x, int y){
		points.get(name).x = x;
		points.get(name).y = y;

	}
	
}

class Line{
	String pointOne;
	String pointTwo;
	
	ScreenCharacter character;
	
	Line(String a, String b, ScreenCharacter c){
		pointOne= a;
		pointTwo= b;
		
		character= c;
	}
}

