import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Board extends JPanel implements KeyListener{
    private Snake player;
    private Food food;
    private Font font;
    private boolean gameOver;
    private boolean start;
    public Board(){
	setBounds(Const.X,Const.Y,Const.GAME_WITH,Const.GAME_HEIGHT);
	setBorder(new LineBorder(Color.RED,2));
	setLayout(null);
	setFocusable(true);
	addFocusListener(new FocusListener() {
	    
	    @Override
	    public void focusLost(FocusEvent e) {
		requestFocusInWindow();
	    }
	    
	    @Override
	    public void focusGained(FocusEvent e) {
	    }
	});
	start=false;
	font=new Font("Consolas",Font.BOLD,24);
	addKeyListener(this);
    }
    public boolean isGameOver(){
	return gameOver;
    }
    public boolean isStart(){
	return start;
    }
    public void gameSetup(int s){
	player=new Snake(1,s);
	food=new Food();
	food.spawn(player);
	addKeyListener(player);
	gameOver=false;
	start=true;
	Game.resetScore();
	new Timer().scheduleAtFixedRate(new TimerTask() {
	    @Override
	    public void run() {
		gameLoop();
	    }
	},0,1000/Const.FPS);
    }
    @Override
    protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	render(g);
    }
    public void gameLoop(){
	if(start){
	if(player.die())	gameOver=true;
	if(player.eat(food)){
	    Game.incScore();
	    food.spawn(player);
	}
	repaint();
	}
    }
    public void gameOver(Graphics g){
	g.setColor(Color.RED);
	drawStringCenter(g,font.deriveFont(30f),"Game Over",Const.GAME_HEIGHT/3);
	g.setColor(Color.GREEN);
	drawStringCenter(g,font,"Press Enter to restart",Const.GAME_HEIGHT/2);
    }
    public void render(Graphics g){
	if(start && !gameOver){
	    player.render(g);
	    food.render(g);
	}else if (gameOver) {
	    gameOver(g);
	}else if (!start) {
	    gameStart(g);
	}
    }
    public void drawStringCenter(Graphics g,Font f,String text,int y){
	g.setFont(f);
	FontMetrics fm=g.getFontMetrics(f);
	int x=(Const.GAME_WITH-fm.stringWidth(text))/2;
	g.drawString(text,x,y);
    }
    public void gameStart(Graphics g){
	g.setColor(Color.GREEN);
	drawStringCenter(g,font,"Press Enter to start game",Const.GAME_HEIGHT/2);
    }
    @Override
    public void keyPressed(KeyEvent e) {
	if((!start||gameOver) && e.getKeyCode()==KeyEvent.VK_ENTER)
	    gameSetup(Game.getGameSpeed());
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
}












