package p2.submibot.ui;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import p2.submibot.resources.Assignment;
import p2.submibot.resources.UserInfo;
import p2.submibot.services.Persistence;
import p2.submibot.services.Requests;

public class CredentialsManager {

	private final Requests CANCEL = null;

	private UserInfo uInfo;
	private Shell activeShell;
	private String nome, sobrenome, filename, assignment, token;
	private List<Assignment> assignments;

	public CredentialsManager(Shell shell) throws ClassNotFoundException, IOException {
		this.activeShell = shell;
		this.uInfo = Persistence.readUserInfo();
	}

	public Requests execute() throws ExecutionException, IOException {

		if (uInfo == null) {
			TokenDialog tokenDialog = Dialogs.tokenDialog(activeShell);
			switch (tokenDialog.open()) {

			case Window.OK:
				this.token = tokenDialog.getToken();
				break;
			default:
				tokenDialog.cancelPressed();
			}

			if (tokenDialog.getStatus() == SWT.CANCEL)
				return CANCEL;

		} else {
			this.token = uInfo.getToken();
		}

		if (token.trim().equals("")) {
			Dialogs.invalidToken(activeShell);
			throw new IllegalArgumentException("Token Invalido");
		}

		MainDialog dialog;
		
		if(uInfo == null)	
			dialog = new MainDialog(this.activeShell, token);	
		else 
			dialog = new MainDialog(this.activeShell, token, uInfo);
		

		if (!dialog.validRequest()) {
			Dialogs.invalidToken(activeShell);
			throw new IllegalArgumentException("Token Invalido");
		}

		switch (dialog.open()) {
		
		case Window.OK:
			saveCredentials(dialog);
			if (uInfo == null) {
				Persistence.writeUserInfo(nome, sobrenome, token);
			}
			break;
		default:
			dialog.cancelPressed();
		}	

		return dialog.getStatus() == SWT.CANCEL ? CANCEL : dialog.getRequests();
	}

	public String getNome() {
		return nome;
	}

	public String getToken() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public String getFilename() {
		return filename;
	}

	public String getAssignment() {
		return this.assignment;
	}

	private void saveCredentials(MainDialog dialog) {
		this.nome = dialog.getFirstName();
		this.sobrenome = dialog.getLastName();
		this.filename = dialog.getFilename();
		this.assignment = dialog.getAssignment();
		this.assignments = dialog.getAssignments();
	}

	public String getId() {
		for (Assignment a : assignments)
			if (a != null) {
				if (a.getName().equals(this.assignment)) {
					return a.getId();
				}
			}
		Dialogs.invalidAssignment(activeShell);
		throw new IllegalArgumentException("Assignment Invalido");

	}
}
