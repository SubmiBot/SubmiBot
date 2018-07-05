package p2.submibot.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class ShellActivationTracker implements Listener {
	Shell activeShell;

	public ShellActivationTracker(Display display) {
		activeShell = display.getActiveShell();
		display.addFilter(SWT.Activate, this);
	}

	@Override
	public void handleEvent(org.eclipse.swt.widgets.Event event) {
		if (event.widget instanceof Shell) {
			activeShell = (Shell) event.widget;
		}
	}

	public Shell getShell() {
		return this.activeShell;
	}
}