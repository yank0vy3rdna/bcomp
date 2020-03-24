/*
 * $Id$
 */

package ru.ifmo.cs.bcomp;

import ru.ifmo.cs.components.Register;

/**
 *
 * @author Dmitry Afanasiev <KOT@MATPOCKuH.Ru>
 */
public class IODevTimer {
	private final IOCtrlBasicOutput ctrl;
	private final Register dr;
	private Thread timer;
	private volatile boolean running = true;

	public IODevTimer(IOCtrl ctrl) {
		this.ctrl = (IOCtrlBasicOutput)ctrl;
		this.dr = this.ctrl.getDR();
	}

	public void start(String name) {
		timer = new Thread(new Runnable() {
			public void run() {
				long countdown = 0;
				long value;

				while (running) {
					try {
						Thread.sleep(100);
					} catch (Exception ex) { }

					value = dr.getValue();

					if (countdown != 0)
						if (countdown <= value) {
							if ((--countdown) == 0)
								ctrl.setReady();
							else
								continue;
						}

					countdown = value;
				}
			}
		}, name);

		timer.start();
	}

	public void done() {
		running = false;

		try {
			timer.join();
		} catch (Exception ex) {
			System.out.println("Can't join thread: " + ex.getMessage());
		}
	}
}
