/*
 * $Id$
 */

package ru.ifmo.cs.bcomp.ui.io;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import ru.ifmo.cs.bcomp.IOCtrl;
import ru.ifmo.cs.elements.DataDestination;

/**
 *
 * @author Dmitry Afanasiev <KOT@MATPOCKuH.Ru>
 */
class FlagIndicator extends JComponent {
	private static final Color LED_OFF = new Color(128, 128, 128);
	private static final Color LED_ON = new Color(0, 160, 0);

	private final Dimension DIMS;
	private final IOCtrl ioctrl;

	protected FlagIndicator(IOCtrl ioctrl, int size) {
		this.ioctrl = ioctrl;

		DIMS = new Dimension(size, size);
		setMinimumSize(DIMS);
		setMaximumSize(DIMS);
		setPreferredSize(DIMS);
		setSize(DIMS);
		setToolTipText("Готовность");

		ioctrl.addDestination(IOCtrl.ControlSignal.SETFLAG, new DataDestination() {
			public void setValue(int value) {
				repaint();
			}
		});
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(ioctrl.getFlag() == 1 ? LED_ON : LED_OFF);
		g.fillOval(DIMS.width / 4, DIMS.height / 4, DIMS.width / 2, DIMS.height / 2);
	}
}
