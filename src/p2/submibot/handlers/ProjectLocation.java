package p2.submibot.handlers;

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


public class ProjectLocation extends AbstractHandler {

    public Object execute(ExecutionEvent event) throws ExecutionException {
    	IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);

	    if (window != null) {
	    	
	        IStructuredSelection selection = (IStructuredSelection) window.getSelectionService().getSelection();
	        Object firstElement = selection.getFirstElement();
	        
	        if (firstElement instanceof IAdaptable) {
	            IProject project = (IProject)((IAdaptable)firstElement).getAdapter(IProject.class);
	        
	            if (project != null) {
		            IPath path = project.getLocation();
		    		MessageDialog.openInformation(
		    				window.getShell(),
		    				"Localização do projeto",
		    				path.toOSString());
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