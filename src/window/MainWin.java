package window;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.io.File;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import server.FileComparison;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class MainWin {

	protected Shell shlvByhooyantsing;
	private Table table;
	private Table table_1;
	private TableColumn tableColumn_1;
	private Table table_2;
	private TableColumn tableColumn_2;
	private Text text;
	private Text text_1;

	/**
	 * Launch the application.
	 * @param args
	 */
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
		shlvByhooyantsing.setText("文件比较器：v.0.0.1 By：Hooyantsing");
		
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
		
		table_2 = new Table(shlvByhooyantsing, SWT.BORDER | SWT.FULL_SELECTION);
		table_2.setLinesVisible(true);
		table_2.setHeaderVisible(true);
		table_2.setBounds(522, 10, 250, 400);
		
		tableColumn_2 = new TableColumn(table_2, SWT.NONE);
		tableColumn_2.setWidth(250);
		tableColumn_2.setText("结果区");
		
		Label label = new Label(shlvByhooyantsing, SWT.NONE);
		label.setBounds(249, 454, 49, 17);
		label.setText("源路径");
		
		text = new Text(shlvByhooyantsing, SWT.BORDER);
		text.setBounds(304, 451, 250, 23);
		
		Label label_1 = new Label(shlvByhooyantsing, SWT.NONE);
		label_1.setText("终路径");
		label_1.setBounds(249, 502, 49, 17);
		
		text_1 = new Text(shlvByhooyantsing, SWT.BORDER);
		text_1.setBounds(304, 499, 250, 23);
		
		Button button = new Button(shlvByhooyantsing, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dd=new DirectoryDialog(shlvByhooyantsing);
				dd.setText("路径选择");  
		        dd.setMessage("请选择源路径");  
		        dd.setFilterPath("G:\\");  
		        String locationPath = dd.open();  
		        if(locationPath!=null){                
		        	text.setText(locationPath);  
		        }  
			}
		});
		button.setBounds(570, 449, 80, 27);
		button.setText("浏览");
		
		Button button_1 = new Button(shlvByhooyantsing, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dd=new DirectoryDialog(shlvByhooyantsing);
				dd.setText("路径选择");  
		        dd.setMessage("请选择源路径");  
		        dd.setFilterPath("G:\\");  
		        String locationPath = dd.open();  
		        if(locationPath!=null){                
		        	text_1.setText(locationPath);  
		        }  
			}
		});
		button_1.setText("浏览");
		button_1.setBounds(570, 497, 80, 27);
		
		Button btnNewButton = new Button(shlvByhooyantsing, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				table.removeAll();
				table_1.removeAll();
				table_2.removeAll();
				String sourceFilePath = text.getText();
				String targetFilePath = text_1.getText();
				File[] sourceChildFiles = FileComparison.getChildFiles(sourceFilePath);
				File[] targetChildFiles = FileComparison.getChildFiles(targetFilePath);
				List<File> fileList = FileComparison.difference(sourceChildFiles,targetChildFiles);
				for(File file : sourceChildFiles) {
					TableItem tableItem = new TableItem(table, SWT.NONE);
					tableItem.setText(file.getName());
				}
				for(File file : targetChildFiles) {
					TableItem tableItem = new TableItem(table_1, SWT.NONE);
					tableItem.setText(file.getName());
				}
				for(File file : fileList) {
					TableItem tableItem = new TableItem(table_2, SWT.NONE);
					tableItem.setText(file.getName());
				}
			}
		});
		btnNewButton.setBounds(672, 439, 90, 90);
		btnNewButton.setText("比较");
		
		Label label_2 = new Label(shlvByhooyantsing, SWT.SEPARATOR | SWT.VERTICAL);
		label_2.setBounds(213, 416, 2, 135);
		
		Label lblNewLabel = new Label(shlvByhooyantsing, SWT.NONE);
		lblNewLabel.setBounds(10, 416, 197, 135);
		lblNewLabel.setText("\r\n欢迎使用 :)\r\n源目录含有的文件，终目录不含\r\n有的文件，则会显示在结果区。\r\n\r\n版本：v.0.0.1 20200220\r\n作者：Hooyantsing");

	}
}
