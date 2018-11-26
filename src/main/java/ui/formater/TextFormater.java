package ui.formater;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.newdawn.slick.Font;

/**
 * Code sous licence GPLv3 (http://www.gnu.org/licenses/gpl.html)
 * 
 * @author <b>Shionn</b>, shionn@gmail.com <i>http://shionn.org</i><br>
 *         GCS d- s+:+ a+ C++ UL/M P L+ E--- W++ N K- w-- M+ t+ 5 X R+ !tv b+ D+ G- e+++ h+ r- y+
 */
public class TextFormater {

	private static final Pattern END_LINE = Pattern.compile("\\n");
	private static final Pattern SEPARATOR = Pattern.compile(" ");

	public List<String> splitParagraph(String text) {
		return Arrays.asList(END_LINE.split(text));
	}

	public List<String> splitLine(String paragraph, Font font, int length) {
		List<String> lines = new ArrayList<String>();
		do {
			String line = getLine(paragraph, font, length);
			lines.add(line);
			paragraph = paragraph.substring(line.length());
		} while (paragraph.length() > 0);
		return lines;
	}

	private String getLine(String text, Font font, int length) {
		if (font.getWidth(text) <= length)
			return text;
		return text.substring(0, getSeparatorIndex(text, font, length));
	}

	private int getSeparatorIndex(String text, Font font, int length) {
		Matcher m = SEPARATOR.matcher(text);
		int index = text.length();
		while (m.find() && font.getWidth(text.substring(0, m.end())) < length) {
			index = m.end();
		}
		return index;
	}

}
