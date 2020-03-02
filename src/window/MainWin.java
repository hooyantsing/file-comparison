package window;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import server.FileComparison;
import server.MessageTips;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.ToolBar;

import bean.TransferFileBean;

import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.CheckboxTableViewer;

public class MainWin {

	protected Shell shlvByhooyantsing;
	private Table table;
	private Table table_1;
	private TableColumn tableColumn_1;
	private Text text;
	private Text text_1;
	private Table table_2;

	public static void main(String[] args) {
		try {
			MainWin window = new MainWin();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlvByhooyantsing.open();
		shlvByhooyantsing.layout();
		while (!shlvByhooyantsing.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlvByhooyantsing = new Shell();
		shlvByhooyantsing.setSize(800, 600);
		shlvByhooyantsing.setText("服务器资源同步器");

		table = new Table(shlvByhooyantsing, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 10, 250, 400);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(250);
		tableColumn.setText("源目录");

		table_1 = new Table(shlvByhooyantsing, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		table_1.setBounds(266, 10, 250, 400);

		tableColumn_1 = new TableColumn(table_1, SWT.NONE);
		tableColumn_1.setWidth(250);
		tableColumn_1.setText("终目录");

		Label label = new Label(shlvByhooyantsing, SWT.NONE);
		label.setBounds(77, 442, 49, 17);
		label.setText("源路径");

		text = new Text(shlvByhooyantsing, SWT.BORDER);
		text.setBounds(132, 439, 250, 23);

		Label label_1 = new Label(shlvByhooyantsing, SWT.NONE);
		label_1.setText("终路径");
		label_1.setBounds(77, 490, 49, 17);

		text_1 = new Text(shlvByhooyantsing, SWT.BORDER);
		text_1.setBounds(132, 487, 250, 23);

		Button button = new Button(shlvByhooyantsing, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dd = new DirectoryDialog(shlvByhooyantsing);
				dd.setText("路径选择");
				dd.setMessage("请选择源路径");
				dd.setFilterPath("G:\\");
				String locationPath = dd.open();
				if (locationPath != null) {
					text.setText(locationPath);
				}
			}
		});
		button.setBounds(398, 437, 80, 27);
		button.setText("浏览");

		Button button_1 = new Button(shlvByhooyantsing, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dd = new DirectoryDialog(shlvByhooyantsing);
				dd.setText("路径选择");
				dd.setMessage("请选择源路径");
				dd.setFilterPath("G:\\");
				String locationPath = dd.open();
				if (locationPath != null) {
					text_1.setText(locationPath);
				}
			}
		});
		button_1.setText("浏览");
		button_1.setBounds(398, 485, 80, 27);

		Button btnNewButton = new Button(shlvByhooyantsing, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				table.removeAll();
				table_1.removeAll();
				table_2.removeAll();
				if("".equals(text.getText().trim()) || "".equals(text_1.getText().trim())) {
					MessageTips.getWarning(shlvByhooyantsing, "警告","请检查目录是否正确！");
					return;
				}	
				String sourceFilePath = text.getText().trim();
				String targetFilePath = text_1.getText().trim();
				File[] sourceChildFiles = FileComparison.getChildFiles(sourceFilePath);
				File[] targetChildFiles = FileComparison.getChildFiles(targetFilePath);
				List<File> fileList = FileComparison.difference(sourceChildFiles, targetChildFiles);
				for (File file : sourceChildFiles) {
					TableItem tableItem = new TableItem(table, SWT.NONE);
					tableItem.setText(file.getName());
				}
				for (File file : targetChildFiles) {
					TableItem tableItem = new TableItem(table_1, SWT.NONE);
					tableItem.setText(file.getName());
				}
				for (File file : fileList) {
					TableItem ti = new TableItem(table_2, SWT.NONE);
					ti.setText(0, file.getName());
					ti.setText(1, "");
				}
			}
		});
		btnNewButton.setBounds(500, 427, 90, 90);
		btnNewButton.setText("比较");

		Menu menu = new Menu(shlvByhooyantsing, SWT.BAR);
		shlvByhooyantsing.setMenuBar(menu);

		MenuItem menuItem = new MenuItem(menu, SWT.CASCADE);
		menuItem.setText("系统");

		Menu menu_1 = new Menu(menuItem);
		menuItem.setMenu(menu_1);

		MenuItem menuItem_1 = new MenuItem(menu_1, SWT.NONE);
		menuItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.exit(0);
			}
		});
		menuItem_1.setText("退出");

		MenuItem menuItem_2 = new MenuItem(menu, SWT.CASCADE);
		menuItem_2.setText("帮助");

		Menu menu_2 = new Menu(menuItem_2);
		menuItem_2.setMenu(menu_2);

		MenuItem menuItem_4 = new MenuItem(menu, SWT.NONE);
		menuItem_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AboutWin about = new AboutWin(shlvByhooyantsing, SWT.NONE);
				about.open();
			}
		});
		menuItem_4.setText("关于");

		TableViewer tableViewer = new TableViewer(shlvByhooyantsing, SWT.CHECK | SWT.BORDER | SWT.FULL_SELECTION);
		table_2 = tableViewer.getTable();
		table_2.setBounds(522, 10, 252, 400);

		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumn_2 = tableViewerColumn.getColumn();
		tableColumn_2.setWidth(240);
		tableColumn_2.setText("文件名");

		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumn_3 = tableViewerColumn_1.getColumn();
		tableColumn_3.setWidth(0);
		tableColumn_3.setText("");

		Button button_2 = new Button(shlvByhooyantsing, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<TransferFileBean> fileList = new ArrayList<TransferFileBean>();
				TableItem[] items = table_2.getItems();
				if (items != null && items.length > 0) {
					for (TableItem item : items) {
						if (item.getChecked()) {
							TransferFileBean tfb = new TransferFileBean(
									item.getText(),
									"未迁移",
									text.getText(),
									text_1.getText()
									);
							fileList.add(tfb);
						}
					}
					if(fileList.size()>0) {
						TransferWin transfer = new TransferWin(shlvByhooyantsing,SWT.NONE,fileList);
						transfer.open();
					}else {
						MessageTips.getWarning(shlvByhooyantsing, "警告","请先选择迁移的对象！");
						return;
					}
				} else {
					MessageTips.getWarning(shlvByhooyantsing, "警告","请先比较两个目录！");
					return;
				}

			}
		});
		button_2.setBounds(613, 427, 90, 90);
		button_2.setText("同步");

	}
}
