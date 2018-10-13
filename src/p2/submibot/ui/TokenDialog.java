package p2.submibot.ui;

import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

public class TokenDialog extends InputDialog {

	private int status;
	
	public TokenDialog(Shell parentShell, String dialogTitle, String dialogMessage, String initialValue,
			IInputValidator validator) {
		super(parentShell, dialogTitle, dialogMessage, initialValue, validator);
		this.status = SWT.OPEN;
	}

	@Override
	public void create() {
		super.create();
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	public int getStatus() {
		return this.status;
	}
	
	public String getToken() {
		return this.getValue();
	}

	@Override
	protected void cancelPressed() {
		if (Dialogs.cancelSubmission(getShell()) == SWT.YES) {
			this.status = SWT.CANCEL;
			super.cancelPressed();
		}
	}

}
