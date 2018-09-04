package p2.submibot.handlers;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import p2.submibot.resources.Assignment;
import p2.submibot.services.Requests;
import p2.submibot.ui.CredentialsUI;
import p2.submibot.util.Zip;

public class SubmitProject extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);

		if (window != null) {
			IStructuredSelection selection = (IStructuredSelection) window.getSelectionService().getSelection();
			Object firstElement = selection.getFirstElement();

			if (firstElement instanceof IAdaptable) {
				IProject project = (IProject) ((IAdaptable) firstElement).getAdapter(IProject.class);

				if (project != null) {

					try {
						CredentialsUI handler = new CredentialsUI(window.getShell());
						Requests req = handler.execute();

						while (handler.getState())
							continue;

						try {
							IPath path = project.getLocation();
							Zip.zip(path.toOSString(),
									path.toOSString() + IPath.SEPARATOR + handler.getFilename() + ".zip");
							MessageDialog.openInformation(window.getShell(), "Zip criado com sucesso",
									"Zip do projeto em " + path.toOSString());
						} catch (IOException e) {
							MessageDialog.openInformation(window.getShell(), "Erro",
									"Não foi possível criar o zip do projeto");
							e.printStackTrace();
						}
						
						try {
							System.out
									.println(req.submitAssignment(handler.getId(), new ProjectLocation().execute(event)
											+ System.getProperty("file.separator") + handler.getFilename() + ".zip"));
						} catch (Exception e) {
							e.printStackTrace();
						}

					} catch (Exception e) {
						MessageDialog.openError(window.getShell(), "Erro", "Não foi possível efetuar a submissão");
					}

				} else {
					MessageDialog.openError(window.getShell(), "Erro", "Clique sobre um projeto");
				}
			}
		}
		return null;
	}
}
