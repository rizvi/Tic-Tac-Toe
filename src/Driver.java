import com.rizvi.mit.du.console.ConsoleIO;
import com.rizvi.mit.du.service.GameLogic;

public class Driver {

	public static void main(String[] args) {
		GameLogic game = new GameLogic(new ConsoleIO());
		game.play();
	}
}
