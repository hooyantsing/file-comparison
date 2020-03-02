package server;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class MessageTips {

	public static void getWarning(Shell shell, String title, String msg) {
		MessageBox mb = new MessageBox(shell, SWT.ICON_WARNING);
		mb.setText(title);
		mb.setMessage(msg);
		mb.open();
	}

	public static void getError(Shell shell, String title, String msg) {
		MessageBox mb = new MessageBox(shell, SWT.ICON_ERROR);
		mb.setText(title);
		mb.setMessage(msg);
		mb.open();
	}

}
