package window;

import java.util.List;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import bean.TransferFileBean;
import server.Transfer;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class TransferWin extends Dialog {

	protected Object result;
	protected Shell shell;
	protected List<TransferFileBean> fileList;
	private Table table;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 * @param fileList
	 */
	public TransferWin(Shell parent, int style, List<TransferFileBean> fileList) {
		super(parent, style);
		this.fileList = fileList;
		setText("迁移");
	}

	/**
	 * Open the dialog.
	 * 
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
		shell.setSize(600, 450);
		shell.setText(getText());

		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 10, 574, 332);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(130);
		tableColumn.setText("文件名");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("状态");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(160);
		tableColumn_2.setText("源路径");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(170);
		tableColumn_3.setText("目标路径");

		flashThread();
		
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {	
				for (int i=0;i<fileList.size();i++) {	
					//BUG:
					fileList.get(i).setStatus("迁移中...");
					table.removeAll();
					flashThread();
					Transfer.clone(fileList.get(i).getSource(),fileList.get(i).getTarget());
					fileList.get(i).setStatus("已迁移");
					table.removeAll();
					flashThread();
				}				
			}
		});
		button.setBounds(192, 365, 80, 27);
		button.setText("启动");

		Button button_1 = new Button(shell, SWT.NONE);
		button_1.setEnabled(false);
		button_1.setBounds(312, 365, 80, 27);
		button_1.setText("停止");
		
		

	}
	
	// table刷新
	private void flashThread() {
		for (TransferFileBean tfb : fileList) {
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(new String[] { tfb.getFileName(), tfb.getStatus(), tfb.getSource(), tfb.getTarget() });
		}

	}

}
