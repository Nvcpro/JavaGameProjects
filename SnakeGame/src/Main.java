import java.awt.EventQueue;

public class Main {
//hello world
    public static void main(String[] args) {

	EventQueue.invokeLater(() -> {
	    new Game().setVisible(true);
	});

    }

}
