package righclk_opendir.popup.actions;

import java.io.IOException;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.jdt.internal.core.JavaProject;

public class NewAction implements IObjectActionDelegate {

	private Shell shell;
	private IStructuredSelection select;

	/**
	 * Constructor for Action1.
	 */
	public NewAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	/*
	 * 如果查找不到JavaProject这个类，增加org.eclipse.jdt.internal.core.JavaProject目录
	 * 这可能是一个Bug
	 */
	public void run(IAction action) {
		Object obj = select.getFirstElement();
		JavaProject pro = (JavaProject) obj;
		String path = pro.getProject().getParent().getLocation().toOSString()
				+ pro.getPath().toOSString();
		// MessageDialog.openInformation(shell, "RighClk_OpenDir",""+path);

		if (path != null && path.length() > 0) {
			try {
				Runtime.getRuntime().exec("explorer " + path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			select = (IStructuredSelection) selection;
		}
	}

}
