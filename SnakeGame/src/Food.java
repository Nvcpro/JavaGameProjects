import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.ThreadLocalRandom;

public class Food {
    private Dot d;
    public Food(){
	d=new Dot();
	d.setColor(Color.BLUE);
    }
    public Dot getDot(){
	return d;
    }
    public void spawn(Snake s){
	int x,y;
	do{
	    x=Const.DOT_SIZE*ThreadLocalRandom.current().nextInt(0,Const.GAME_WITH/Const.DOT_SIZE);
	    y=Const.DOT_SIZE*ThreadLocalRandom.current().nextInt(0,Const.GAME_WITH/Const.DOT_SIZE);
	    
	}while(!checkValid(s,x,y));
	d.setX(x);
	d.setY(y);
    }
    public void render(Graphics g){
	g.setColor(d.getColor());
	g.fillRect(d.getX(),d.getY(),Const.DOT_SIZE,Const.DOT_SIZE);
    }
    private boolean checkValid(Snake s,int x,int y){
	if (s.getHead().getX()==x && s.getHead().getY()==y)	return false;
	return !s.getBody().stream().anyMatch(d->d.getX()==x && d.getY()==y);
    }
}
