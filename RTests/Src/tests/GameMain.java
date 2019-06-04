package tests;

import game.GameUI;

import java.awt.Dimension;

import tests.Model;
import tests.View;
import tests.Controller;

public class GameMain {

	public static void main(String[] args) {

		Model model = new Model();
		View view = new View(model);
		Controller controller = new Controller(model, view);

		Dimension d = new Dimension(1024, 768);
		new GameUI(model, view, controller, d);

		return;
	}

}
