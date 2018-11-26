package ui;

import java.util.regex.Pattern;

import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.TextField;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
public class PasswordTextField extends TextField {
	private static final Pattern ANYTHING = Pattern.compile("[^\\*]");

	public PasswordTextField(GUIContext container, Font font, int x, int y, int width, int height) {
		super(container, font, x, y, width, height);
	}

	@Override
	public void render(GUIContext container, Graphics g) {
		String realText = super.getText();
		super.setText(ANYTHING.matcher(realText).replaceAll("*"));
		super.render(container, g);
		super.setText(realText);
	}

}
