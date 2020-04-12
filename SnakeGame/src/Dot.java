import java.awt.Color;

public class Dot {
    private int x,y;
    private Color color;
    public Dot(){
	x=y=0;
	color=Color.BLUE;
    };
    public Dot(int x,int y,Color c){
	this.x=x;
	this.y=y;
	this.color=c;
    }
    public int getX() {
        return x;
    }
    public void setX(int val) {
        this.x =val;
    }
    @Override
    public boolean equals(Object obj) {
	if(super.equals(obj))	return true;
	if(obj instanceof Dot){
	    Dot d=(Dot)obj;
	    return this.x==d.getX() && this.y==d.getY();
	}
	return false;
    }
    public int getY() {
        return y;
    }
    public void setY(int val) {
        this.y =val;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color c) {
        this.color = c;
    }
    public void translate(int x,int y){
	this.x+=x;
	this.y+=y;
    }
}
