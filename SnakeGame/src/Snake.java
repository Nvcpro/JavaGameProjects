import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.Timer;

enum Direction {
    UP, DOWN, LEFT, RIGHT
}

public class Snake implements KeyListener, ActionListener {
    private Dot head;
    public Dot getHead() {
        return head;
    }

    public Deque<Dot> getBody() {
        return body;
    }

    private int speed;
    private Deque<Dot> body;
    private boolean isUpdated;
    private Direction dir;
    private Timer t;

    public Snake(int l,int s) {
	head = new Dot(15*Const.DOT_SIZE,15*Const.DOT_SIZE, Color.RED);
	body = new LinkedList<Dot>();
	int x = head.getX() - l * Const.DOT_SIZE, y = head.getY();
	for (int i = 0; i < l; i++) {
	    body.add(new Dot(x, y, Color.GREEN));
	    x += Const.DOT_SIZE;
	}
	isUpdated = true;
	dir = Direction.RIGHT;
	speed = 1050-s*100;
	t = new Timer(speed, this);
	t.start();
    }
    public boolean eat(Food f){
	if(head.equals(f.getDot())){
	    body.add(new Dot(body.getLast().getX(),body.getLast().getY(),Color.GREEN));
	    return true;
	}
	return false;
    }
    public void move() {
	Dot gap = new Dot(head.getX(), head.getY(), Color.GREEN);
	switch (dir) {
	case LEFT:
	    head.translate(-Const.DOT_SIZE, 0);
	    break;
	case RIGHT:
	    head.translate(Const.DOT_SIZE, 0);
	    break;
	case UP:
	    head.translate(0, -Const.DOT_SIZE);
	    break;
	case DOWN:
	    head.translate(0, Const.DOT_SIZE);
	    break;
	}
	if(head.getX()<0)	head.setX(Const.GAME_WITH-Const.DOT_SIZE);
	else if(head.getX()>=Const.GAME_WITH)	head.setX(0);
	else if(head.getY()<0)	head.setY(Const.GAME_HEIGHT-Const.DOT_SIZE);
	else if(head.getY()>=Const.GAME_HEIGHT)	head.setY(0);
	body.add(gap);
	body.poll();
	isUpdated=true;
    }
    public boolean die(){
	if(body.stream().anyMatch(d->head.getX()==d.getX()&&head.getY()==d.getY())){
	    t.stop();
	    return true;
	}
	return false;
    }
    public void render(Graphics g) {
	
	for (Dot d : body) {
	    g.setColor(d.getColor());
	    g.fillRect(d.getX(), d.getY(), Const.DOT_SIZE, Const.DOT_SIZE);
	}
	g.setColor(head.getColor());
	g.fillRect(head.getX(), head.getY(), Const.DOT_SIZE, Const.DOT_SIZE);
    }

    @Override
    public void keyPressed(KeyEvent e) {
	if (isUpdated) {
	    isUpdated = false;
	    int key = e.getKeyCode();
	    if (key == KeyEvent.VK_UP && dir != Direction.DOWN) {
		dir = Direction.UP;
	    } else if (key == KeyEvent.VK_DOWN && dir != Direction.UP) {
		dir = Direction.DOWN;
	    } else if (key == KeyEvent.VK_LEFT && dir != Direction.RIGHT) {
		dir = Direction.LEFT;
	    } else if (key == KeyEvent.VK_RIGHT && dir != Direction.LEFT) {
		dir = Direction.RIGHT;
	    }
	}
    }

    @Override
    public void keyReleased(KeyEvent e) {
	
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	move();
    }

}
