package p2.submibot.handlers;

import java.io.IOException;

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

import p2.submibot.util.Zip;


public class ProjectZip extends AbstractHandler {

    public Object execute(ExecutionEvent event) throws ExecutionException {
    	IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);

	    if (window != null) {
	        IStructuredSelection selection = (IStructuredSelection) window.getSelectionService().getSelection();
	        Object firstElement = selection.getFirstElement();
	        if (firstElement instanceof IAdaptable) {
	            IProject project = (IProject)((IAdaptable)firstElement).getAdapter(IProject.class);
	            if (project != null) {
		            IPath path = project.getLocation();
		    		try {
						Zip.zip(path.toOSString(), 
								path.toOSString() + IPath.SEPARATOR + project.getName().toString() + ".zip");
			    		MessageDialog.openInformation(
			    				window.getShell(),
			    				"Zip criado com sucesso",
				    			"Zip do projeto em " + path.toOSString());
					} catch (IOException e) {
			    		MessageDialog.openError(
			    				window.getShell(),
			    				"Erro",
				    			"Um erro ocorreu ao tentar criar o zip do projeto");
					}
	            } else {
		    		MessageDialog.openError(
		    				window.getShell(),
		    				"Erro",
		    				"Clique sobre um projeto");
	            }
	        }
	    }
		return null;
	}

}