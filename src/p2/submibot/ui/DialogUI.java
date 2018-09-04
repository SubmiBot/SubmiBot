package p2.submibot.ui;

import java.io.IOException;
import java.text.Normalizer;
import java.util.List;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import p2.submibot.resources.Assignment;
import p2.submibot.services.Persistence;
import p2.submibot.services.Requests;

public class DialogUI extends TitleAreaDialog {

	private Text firstNameText, lastNameText, matrText;

	private String firstName, lastName, matr, token, assignment;

	private List<Assignment> assignments;

	private Requests request;

	public DialogUI(Shell parentShell, String token) {
		super(parentShell);
		this.token = token;
		this.request = new Requests(token, "1374512"/* "1388632" */);
	}

	@Override
	public void create() {
		super.create();
		setTitle("Submibot");
		setMessage("Submissão de atividades - LP2@UFCG", IMessageProvider.INFORMATION);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);

		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		GridLayout layout = new GridLayout(2, false);

		layout.marginHeight = 15;
		layout.marginWidth = 20;

		container.setLayout(layout);

		createFirstName(container);
		createLastName(container);
		createMatr(container);
		/*
		 * try { if (Persistence.readUserInfo() == null) createToken(container); } catch
		 * (ClassNotFoundException | IOException e) { e.printStackTrace();
		 * 
		 * }
		 */
		createCombo(container);

		return area;
	}

	private void createFirstName(Composite container) {
		Label firstNameLabel = new Label(container, SWT.NONE);
		firstNameLabel.setText("Nome");

		GridData firstNameData = new GridData();
		firstNameData.grabExcessHorizontalSpace = true;
		firstNameData.horizontalAlignment = GridData.FILL;

		firstNameText = new Text(container, SWT.BORDER);
		firstNameText.setLayoutData(firstNameData);
	}

	private void createLastName(Composite container) {
		Label firstNameLabel = new Label(container, SWT.NONE);
		firstNameLabel.setText("Sobrenome");

		GridData lastNameData = new GridData();
		lastNameData.grabExcessHorizontalSpace = true;
		lastNameData.horizontalAlignment = GridData.FILL;

		lastNameText = new Text(container, SWT.BORDER);
		lastNameText.setLayoutData(lastNameData);
	}

	private void createMatr(Composite container) {
		Label matrLabel = new Label(container, SWT.NONE);
		matrLabel.setText("Matrícula");

		GridData matrData = new GridData();
		matrData.grabExcessHorizontalSpace = true;
		matrData.horizontalAlignment = GridData.FILL;

		matrText = new Text(container, SWT.BORDER);
		matrText.setLayoutData(matrData);
	}

	private void createCombo(Composite container) {
		String[] combo = new String[0];
		try {
			String names = "";
			this.assignments = request.getAssignments();
			for (Assignment a : this.assignments)
				names += a.getName() + "/";
			combo = names.split("/");
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		Label comboLabel = new Label(container, SWT.NONE);
		comboLabel.setText("Selecione a atividade:");
		Combo c = new Combo(container, SWT.READ_ONLY);
		String items[] = combo;
		c.setItems(items);

		GridData comboData = new GridData();
		comboData.grabExcessHorizontalSpace = true;
		comboData.horizontalAlignment = GridData.FILL;
		c.setLayoutData(comboData);

		c.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = c.getSelectionIndex();

				String selected = c.getItem(index);
				assignment = (selected);

			}
		});
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	private void saveInput() {
		this.firstName = catchText(firstNameText);
		this.lastName = catchText(lastNameText);

		if (this.token == null) {
			try {
				this.token = Persistence.readUserInfo().getToken();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}

		this.matr = catchText(matrText);
	}

	private String catchText(Text text) {
		return (text != null) ? text.getText() : null;
	}

	@Override
	protected void okPressed() {
		saveInput();
		super.okPressed();
	}

	@Override
	protected void cancelPressed() {
		MessageBox messageBox = new MessageBox(getShell(), SWT.ICON_QUESTION | SWT.YES | SWT.NO);
		messageBox.setMessage("Cancelar Submissão");
		messageBox.setText("Deseja realmente cancelar a submissão?");

		int response = messageBox.open();

		if (response == SWT.YES)
			System.exit(0);
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMatr() {
		return matr;
	}

	public String getAssignment() {
		return assignment;
	}

	public List<Assignment> getAssignments() {
		return assignments;
	}
	
	public Requests getRequests() {
		return this.request;
	}

	public String getFilename() {
		String filename = (this.firstName.toUpperCase() + " " + this.lastName.toUpperCase() + " " + this.assignment)
				.trim().replaceAll(" ", "_");

		return Normalizer.normalize(filename, Normalizer.Form.NFD).replaceAll("[^A-Za-z_]", "");
	}
}