package com.noobian.scalarcreator;

import java.awt.Font;

import javax.swing.JComponent;

public class AwtUtils {

	public static void setBold(JComponent c) {
		c.setFont(c.getFont().deriveFont(c.getFont().getStyle() | Font.BOLD));
	}

	public static void unsetBold(JComponent c) {
		c.setFont(c.getFont().deriveFont(c.getFont().getStyle() & ~Font.BOLD));
	}

	public static void setItalic(JComponent c) {
		c.setFont(c.getFont().deriveFont(c.getFont().getStyle() | Font.ITALIC));
	}

	public static void unsetItalic(JComponent c) {
		c.setFont(c.getFont().deriveFont(c.getFont().getStyle() & ~Font.ITALIC));
	}
}
