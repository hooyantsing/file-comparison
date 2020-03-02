package window;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

public class AboutWin extends Dialog {

	protected Object result;
	protected Shell shell;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public AboutWin(Shell parent, int style) {
		super(parent, style);
		setText("关于");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL);
		shell.setSize(450, 330);
		shell.setText(getText());
		
		Label lblhooyantsing = new Label(shell, SWT.NONE);
		lblhooyantsing.setBounds(10, 10, 424, 153);
		lblhooyantsing.setText("作者：Hooyantsing\r\n版本：v.0.0.2\r\n更新日期：20200302\r\nQQ:584945164\r\nGitHub:https://github.com/huyanqing1998/FileComparison\r\n\r\n软件说明：\r\n      本软件功能目前非常基础，主要功能是比较两个目录，适用于本地与服务器\r\n资源同步。");
		
		Label label = new Label(shell, SWT.NONE);
		label.setImage(SWTResourceManager.getImage(AboutWin.class, "/img/wechat.jpg"));
		label.setBounds(100, 211, 80, 80);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(10, 182, 61, 17);
		label_1.setText("打赏：");
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setImage(SWTResourceManager.getImage(AboutWin.class, "/img/alipay.png"));
		label_2.setBounds(264, 211, 80, 80);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setBounds(114, 188, 61, 17);
		label_3.setText("微信支付");
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setBounds(283, 188, 61, 17);
		label_4.setText("支付宝");

	}
}
