package p2.submibot.ui;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import p2.submibot.util.ShellActivationTracker;

public class CredentialsHandler {

	private Shell activeShell;

	private String nome;
	private String sobrenome;
	private String email;
	private String senha;

	public CredentialsHandler() {
		this.activeShell = getActiveShell();
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

	private Shell getActiveShell() {
		Display display = Display.getCurrent();
		if (display == null)
			display = Display.getDefault();
		ShellActivationTracker sat = new ShellActivationTracker(display);
		System.out.println(sat.getShell() == null);
		return sat.getShell();
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
