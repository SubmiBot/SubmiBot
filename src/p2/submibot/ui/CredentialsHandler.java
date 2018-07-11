package p2.submibot.ui;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;

public class CredentialsHandler {

	private Shell activeShell;

	private String nome;
	private String sobrenome;
	private String email;
	private String senha;

	public CredentialsHandler(Shell shell) {
		this.activeShell = shell;
	}

	public void execute() throws ExecutionException {
		Dialog dialog = new Dialog(this.activeShell);

		if (dialog.open() == Window.OK) {
			this.nome = dialog.getFirstName();
			this.sobrenome = dialog.getLastName();
			this.email = dialog.getMail();
			this.senha = dialog.getPassword();
		}
	}
	
	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

}
