package p2.ui;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;

public class CredentialsHandler {

    private static final Shell SHELL = null;

	public static void execute(
            Shell shell) {
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
    
    public static void main(String[] args) {
		execute(SHELL);
	}

}
