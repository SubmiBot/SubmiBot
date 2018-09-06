package p2.submibot.ui;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class Dialogs {

	public static int cancelSubmission(Shell parent) {
		MessageBox messageBox = new MessageBox(parent, SWT.ICON_QUESTION | SWT.YES | SWT.NO);
		messageBox.setMessage("Cancelar Submissão");
		messageBox.setText("Deseja realmente cancelar a submissão?");
		return messageBox.open();
	}

	public static TokenDialog tokenDialog(Shell parent) {
		TokenDialog tokenDialog = new TokenDialog(parent, "Submibot", "Insira seu token para continuar", null, null);
		return tokenDialog;
	}

	public static void invalidToken(Shell parent) {
		MessageDialog.openError(parent, "Erro - Submibot", "Token Inválido");

	}
}
