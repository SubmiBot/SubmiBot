package p2.submibot.ui;

import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;

import p2.submibot.resources.Assignment;

public class CredentialsUI {

	private Shell activeShell;
	private String nome, sobrenome, senha, filename, assignment, token;
	List<Assignment> assignments;
	private boolean state;

	public CredentialsUI(Shell shell) {
		this.activeShell = shell;
		this.state = true;
	}

	public void execute() throws ExecutionException {
		TokenDialog tokenDialog = new TokenDialog(this.activeShell, "Submibot", "Insira seu token para continuar",
				token, null);

		if (tokenDialog.open() == Window.OK) {
			this.token = tokenDialog.getToken();
		}

		DialogUI dialog = new DialogUI(this.activeShell, token);
		if (dialog.open() == Window.OK) {
			this.nome = dialog.getFirstName();
			this.sobrenome = dialog.getLastName();
			this.filename = dialog.getFilename();
			this.assignment = dialog.getAssignment();
			this.assignments = dialog.getAssignments();
			this.state = false;
		}
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

	public String getSenha() {
		return senha;
	}

	public String getFilename() {
		return filename;
	}

	public boolean getState() {
		return this.state;
	}

	public String getAssignment() {
		return this.assignment;
	}

	public String getId() {
		for (Assignment a : assignments)
			if (a.getName().equals(this.assignment)) {
				System.out.println(a.getId());
				return a.getId();
			}
		return null;
	}
}