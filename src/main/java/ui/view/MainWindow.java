package ui.view;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Group;

import ui.view.View;
import ui.view.listeners.Open;

import org.eclipse.wb.swt.SWTResourceManager;

public class MainWindow {

	protected Shell shell;
	protected Text txtInput;
	protected Table tableWords;
	protected View viewProxy;
	protected Text txtOutput;
	protected Table tableThematicDicts;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow();
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
		
		viewProxy = new View(this);
		
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(695, 554);
		shell.setText("\u041A\u0443\u0440\u0441\u043E\u0432\u043E\u0439 \u043F\u0440\u043E\u0435\u043A\u0442");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem menuFileCascade = new MenuItem(menu, SWT.CASCADE);
		menuFileCascade.setText("\u0424\u0430\u0439\u043B");
		
		Menu menuFile = new Menu(menuFileCascade);
		menuFileCascade.setMenu(menuFile);
		
		MenuItem itemOpen = new MenuItem(menuFile, SWT.NONE);
		itemOpen.addSelectionListener(new Open());
		itemOpen.setText("\u041E\u0442\u043A\u0440\u044B\u0442\u044C");
		
		MenuItem itemSaveInput = new MenuItem(menuFile, SWT.NONE);
		itemSaveInput.setText("Сохранить входной");
		
		MenuItem itemSaveOutput = new MenuItem(menuFile, SWT.NONE);
		itemSaveOutput.setText("Сохранить результат");
		
		MenuItem menuDictCascade = new MenuItem(menu, SWT.CASCADE);
		menuDictCascade.setText("\u0421\u043B\u043E\u0432\u0430\u0440\u0438");
		
		Menu menuDict = new Menu(menuDictCascade);
		menuDictCascade.setMenu(menuDict);
		
		MenuItem menuItem_3 = new MenuItem(menuDict, SWT.NONE);
		menuItem_3.setText("Управление");
		
		MenuItem menuItemExit = new MenuItem(menu, SWT.NONE);
		menuItemExit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.exit(0);
			}
		});
		menuItemExit.setText("Выход");
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new FormLayout());
		
		SashForm sashForm = new SashForm(composite, SWT.VERTICAL);
		FormData fd_sashForm = new FormData();
		fd_sashForm.top = new FormAttachment(0, 10);
		fd_sashForm.left = new FormAttachment(0, 10);
		fd_sashForm.right = new FormAttachment(100);
		Button btnReferate = new Button(composite, SWT.NONE);
		fd_sashForm.bottom = new FormAttachment(btnReferate, -6);
		sashForm.setLayoutData(fd_sashForm);
		
		SashForm sashFormNorth = new SashForm(sashForm, SWT.NONE);
		
		Group groupInText = new Group(sashFormNorth, SWT.NONE);
		groupInText.setText("Входной текст");
		groupInText.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		txtInput = new Text(groupInText, SWT.BORDER | SWT.WRAP | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		txtInput.setText("Каким образом XP снижает перечисленные ранее риски?\r\nТочка точки точкой точками точка");
		
		Group groupOutText = new Group(sashFormNorth, SWT.NONE);
		groupOutText.setText("Логгер/Примерный смысл");
		groupOutText.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		txtOutput = new Text(groupOutText, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		txtOutput.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txtOutput.setText("asdfgh");
		sashFormNorth.setWeights(new int[] {333, 333});
		
		SashForm sashFormSouth = new SashForm(sashForm, SWT.NONE);
		
		Group groupFoundedWords = new Group(sashFormSouth, SWT.NONE);
		groupFoundedWords.setText("\u041D\u0430\u0439\u0434\u0435\u043D\u043D\u044B\u0435 \u0441\u043B\u043E\u0432\u0430");
		groupFoundedWords.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		tableWords = new Table(groupFoundedWords, SWT.BORDER | SWT.CHECK | SWT.FULL_SELECTION);
		tableWords.setHeaderVisible(true);
		tableWords.setLinesVisible(true);
		
		TableColumn tableColumn_5 = new TableColumn(tableWords, SWT.NONE);
		tableColumn_5.setWidth(47);
		tableColumn_5.setText("#");
		
		TableColumn tableColumn = new TableColumn(tableWords, SWT.NONE);
		tableColumn.setMoveable(true);
		tableColumn.setWidth(121);
		tableColumn.setText("\u0421\u043B\u043E\u0432\u043E");
		
		TableColumn tableColumn_4 = new TableColumn(tableWords, SWT.NONE);
		tableColumn_4.setMoveable(true);
		tableColumn_4.setWidth(96);
		tableColumn_4.setText("Связанные");
		
		TableColumn tableColumn_1 = new TableColumn(tableWords, SWT.NONE);
		tableColumn_1.setMoveable(true);
		tableColumn_1.setWidth(40);
		tableColumn_1.setText("\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E");
		
		TableItem tableItem_2 = new TableItem(tableWords, SWT.NONE);
		tableItem_2.setText("Слово");
		
		TableItem tableItem_3 = new TableItem(tableWords, SWT.NONE);
		tableItem_3.setText("слово-2");
		
		Group groupThematicDicts = new Group(sashFormSouth, SWT.NONE);
		groupThematicDicts.setText("\u0421\u043B\u043E\u0432\u0430\u0440\u0438");
		groupThematicDicts.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		tableThematicDicts = new Table(groupThematicDicts, SWT.BORDER | SWT.CHECK | SWT.FULL_SELECTION);
		tableThematicDicts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent arg0) {
				viewProxy.msgTurnDicts();
			}
		});
		tableThematicDicts.setLinesVisible(true);
		tableThematicDicts.setHeaderVisible(true);
		
		TableColumn tableColumn_2 = new TableColumn(tableThematicDicts, SWT.NONE);
		tableColumn_2.setWidth(131);
		tableColumn_2.setText("\u0421\u043B\u043E\u0432\u0430\u0440\u044C");
		tableColumn_2.setMoveable(true);
		
		TableColumn tableColumn_3 = new TableColumn(tableThematicDicts, SWT.NONE);
		tableColumn_3.setWidth(99);
		tableColumn_3.setText("\u0421\u043E\u0432\u043F\u0430\u0434\u0435\u043D\u0438\u0435");
		tableColumn_3.setMoveable(true);
		
		TableItem tableItem_1 = new TableItem(tableThematicDicts, 0);
		tableItem_1.setText(new String[] {"физика", "сов-падение"});
		
		TableItem tableItem = new TableItem(tableThematicDicts, 0);
		tableItem.setText("New TableItem");
		sashFormSouth.setWeights(new int[] {1, 1});
		sashForm.setWeights(new int[] {202, 202});
		FormData fd_btnReferate = new FormData();
		fd_btnReferate.left = new FormAttachment(100, -136);
		fd_btnReferate.top = new FormAttachment(100, -26);
		fd_btnReferate.bottom = new FormAttachment(100);
		fd_btnReferate.right = new FormAttachment(100);
		btnReferate.setLayoutData(fd_btnReferate);
		
		btnReferate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				viewProxy.msgReferate();
			}
		});
		btnReferate.setText("Реферирование");
		
		Button btnRubricate = new Button(composite, SWT.NONE);
		btnRubricate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent arg0) {
				viewProxy.msgRubricate();
			}
		});
		FormData fd_btnRubricate = new FormData();
		fd_btnRubricate.left = new FormAttachment(btnReferate, -118, SWT.LEFT);
		fd_btnRubricate.bottom = new FormAttachment(100);
		fd_btnRubricate.right = new FormAttachment(btnReferate, -6);
		btnRubricate.setLayoutData(fd_btnRubricate);
		btnRubricate.setText("Рубрикация");
	}
}
