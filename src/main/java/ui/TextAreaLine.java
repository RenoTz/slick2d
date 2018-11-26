package ui;

import org.newdawn.slick.Font;

import ui.align.VerticalAlignement;

class TextAreaLine {

	private String text;
	private Font font;
	private VerticalAlignement align;

	public TextAreaLine(String text, Font font, VerticalAlignement align) {
		this.text = text;
		this.font = font;
		this.align = align;
	}

	public String getText() {
		return text;
	}

	public Font getFont() {
		return font;
	}

	public VerticalAlignement getAlign() {
		return align;
	}

}
