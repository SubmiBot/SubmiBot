package p2.submibot.ui;

import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class TokenDialog extends InputDialog {

	public TokenDialog(Shell parentShell, String dialogTitle, String dialogMessage, String initialValue,
			IInputValidator validator) {
		super(parentShell, dialogTitle, dialogMessage, initialValue, validator);
	}
	
	@Override
	public void create() {
		super.create();
	}
	
	@Override
	protected boolean isResizable() {
		return true;
	}
	
	public String getToken() {
		return this.getValue();
	}

	@Override
	protected void cancelPressed() {
		MessageBox messageBox = new MessageBox(getShell(), SWT.ICON_QUESTION | SWT.YES | SWT.NO);
		messageBox.setMessage("Cancelar Submissão");
		messageBox.setText("Deseja realmente cancelar a submissão?");

		int response = messageBox.open();

		if (response == SWT.YES)
			System.exit(0);
	}

	
}
