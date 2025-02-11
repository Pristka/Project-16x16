package project_16x16.windows;

import project_16x16.scene.GameplayScene;
import project_16x16.PClass;
import project_16x16.SideScroller;
import project_16x16.Util;
import project_16x16.ui.Anchor;
import project_16x16.ui.Button;
import project_16x16.ui.List;
import project_16x16.ui.ScrollBarVertical;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

import processing.core.PApplet;

public class LoadLevelWindow extends PClass {

	String path = "src/resources/Storage/Maps/save";
	// Map editor Scene
	public GameplayScene scene;
	public List list;
	File f;

	public LoadLevelWindow(SideScroller a, GameplayScene scene) {
		
		super(a);

		this.scene = scene;
		f = new File(path);
		f.mkdirs();
		File[] files = f.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				String name = pathname.getName().toLowerCase();
				return name.endsWith(".dat") && pathname.isFile();
			}
		});
		
		list = new List(a, Arrays.stream(files).map(File::getName).toArray(String[]::new), 30);
		list.setSizeH(200);
		list.setPosition(applet.width / 2, 325);
		list.setConfirmButton("Confirm", applet.width / 2, 500);
		list.setCancelButton("Cancel", applet.width / 2, 550);
	}

	public void display() {
		// Display Privacy Area
		applet.fill(0, 100);
		applet.noStroke();
		applet.rect(applet.width / 2, applet.height / 2, applet.width, applet.height);

		// Display Window
		applet.fill(29, 33, 45);
		applet.stroke(47, 54, 73);
		applet.strokeWeight(8);
		applet.rect(applet.width / 2, applet.height / 2, 400, 500);

		// Display Window Title
		applet.pushMatrix();
		applet.fill(255);
		applet.textSize(30);
		applet.textAlign(CENTER, CENTER);
		applet.text("Load Level", applet.width / 2, applet.height / 2 - 200);
		applet.popMatrix();
		// Display Load Press
		list.display();
	}

	public void update() {
		list.update();
		confirmButton();
		cancelButton();
	}

	public void confirmButton() {
		if (list.getConfirmPress() && !list.getElement().isEmpty()) {
			scene.loadLevel(path + list.getElement());
			list.resetElement();
			scene.tool = GameplayScene.Tools.MOVE;
		} else if (list.getConfirmPress() && list.getElement().isEmpty())
			scene.tool = GameplayScene.Tools.MOVE;
	}

	public void cancelButton() {
		if (list.getCancelPress()) {
			scene.tool = GameplayScene.Tools.MOVE;
			list.resetElement();
		}
	}

}
