package p2.submibot.ui;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class CredentialsHandler {

	private static final Shell SHELL = getActiveShell();

	public static void execute(Shell shell) throws ExecutionException {
		Dialog dialog = new Dialog(shell);

		// get the new values from the dialog
		if (dialog.open() == Window.OK) {
			String nome = dialog.getFirstName();
			String sobrenome = dialog.getLastName();
			String email = dialog.getMail();
			String senha = dialog.getPassword();
			System.out.println(nome);
			System.out.println(sobrenome);
			System.out.println(email);
			System.out.println(senha);
			System.out.println(dialog.getFilename());
		}
	}

	public static void main(String[] args) throws ExecutionException {
		execute(SHELL);
	}

	private static Shell getActiveShell() {
		Display display = Display.getDefault();
		Shell result = display.getActiveShell();

		if (result == null) {
			Shell[] shells = display.getShells();
			for (Shell shell : shells) {
				if (shell.getShells().length == 0) {
					if (result != null)
						throw new RuntimeException();
					result = shell;
				}
			}
		}

		return result;
	}

}
