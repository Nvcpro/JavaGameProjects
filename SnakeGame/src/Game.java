import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javafx.scene.transform.Scale;

import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.SpinnerNumberModel;

public class Game extends JFrame {

    private JPanel contentPane;
    private static JLabel scoreLb,highScoreLb;
    private static int score=0,highScore=0;
    private static JSpinner spinner;
    private Board gameBoard;
    
    public Game() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100,Const.WINDOW_WITH,Const.WINDOW_HEIGHT);
	setResizable(false);
	contentPane = new JPanel();
	contentPane.setBackground(Color.BLACK);
	contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	gameBoard=new Board();
	contentPane.add(gameBoard);
	
	JPanel titlePanel = new JPanel();
	titlePanel.setBackground(Color.BLACK);
	titlePanel.setBounds(50, 11, 500, 28);
	contentPane.add(titlePanel);
	titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	
	JLabel title = new JLabel("Snake Game");
	title.setForeground(Color.WHITE);
	title.setFont(new Font("Segoe UI", Font.BOLD, 16));
	titlePanel.add(title);
	
	JPanel scorePanel = new JPanel();
	scorePanel.setBackground(Color.BLACK);
	scorePanel.setBounds(560, 50, 74, 500);
	contentPane.add(scorePanel);
	FlowLayout fl_scorePanel = new FlowLayout(FlowLayout.CENTER, 100, 20);
	scorePanel.setLayout(fl_scorePanel);
	
	JLabel lblNewLabel = new JLabel("Your Score");
	lblNewLabel.setForeground(Color.WHITE);
	lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
	scorePanel.add(lblNewLabel);
	
	scoreLb = new JLabel("0");
	scoreLb.setForeground(Color.WHITE);
	scoreLb.setFont(new Font("Tahoma", Font.BOLD, 14));
	scorePanel.add(scoreLb);
	
	JLabel lb = new JLabel("High Score");
	lb.setForeground(Color.WHITE);
	lb.setFont(new Font("Segoe UI", Font.BOLD, 14));
	scorePanel.add(lb);
	
	highScoreLb = new JLabel("0");
	highScoreLb.setForeground(Color.WHITE);
	highScoreLb.setFont(new Font("Segoe UI", Font.BOLD, 14));
	scorePanel.add(highScoreLb);
	
	JLabel lblNewLabel_1 = new JLabel("Speed");
	lblNewLabel_1.setForeground(Color.WHITE);
	lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
	scorePanel.add(lblNewLabel_1);
	
	spinner = new JSpinner();
	spinner.setModel(new SpinnerNumberModel(0, 0, 10, 1));
	spinner.setFont(new Font("Tahoma", Font.PLAIN, 14));
	scorePanel.add(spinner);
	
	JLabel lblNewLabel_2 = new JLabel("<html><center>Developed<br/>by<br/>Chung</center></html>");
	lblNewLabel_2.setForeground(Color.WHITE);
	lblNewLabel_2.setFont(new Font("Consolas", Font.PLAIN, 12));
	scorePanel.add(lblNewLabel_2);
	
    }
    public static void incScore(){
	score++;
	scoreLb.setText(score+"");
    }
    public static void resetScore(){
	if(score>highScore){
	    highScore=score;
	    highScoreLb.setText(highScore+"");
	}
	score=0;
	scoreLb.setText("0");
    }
    public static int getGameSpeed(){
	return (int) spinner.getValue();
    }
}













