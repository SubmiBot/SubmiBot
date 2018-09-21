package p2.submibot.handlers;

import java.io.File;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
	
public class ClearCache extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);

		if (window != null) {
			
			IStructuredSelection selection = (IStructuredSelection) window.getSelectionService().getSelection();
			Object firstElement = selection.getFirstElement();
			
			if (firstElement instanceof IAdaptable) {
				IProject project = (IProject) ((IAdaptable) firstElement).getAdapter(IProject.class);
			
				if (project != null) {
					String home = System.getProperty("user.home") + System.getProperty("file.separator");
					File file = new File(home + ".submibot");
					if (file.exists()) {
						if(file.delete()) {
							MessageDialog.openInformation(window.getShell(), "Submibot", "Limpeza de cache efetuada com sucesso");
						} else {
							MessageDialog.openInformation(window.getShell(), "Submibot", "Não foi possível efetuar a Limpeza de cache");
						}
					} else {
						MessageDialog.openInformation(window.getShell(), "Submibot", "Não foram encontrados arquivos de configuração");
					}
				} else {
					MessageDialog.openError(window.getShell(), "Erro", "Clique sobre um projeto");
				}
			}
		}
		return null;
	}

}