package p2.submibot.ui;

import java.io.IOException;
import java.text.Normalizer;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import p2.submibot.resources.Assignment;
import p2.submibot.services.Persistence;
import p2.submibot.services.Requests;

public class DialogUI extends TitleAreaDialog {

	private Text firstNameText, lastNameText;

	private String firstName, lastName, token, assignment;

	private List<Assignment> assignments;

	private Requests request;

	private int status;

	public DialogUI(Shell parentShell, String token) throws IOException {
		super(parentShell);
		this.token = token;
		this.status = SWT.OPEN;
		this.request = new Requests(token, /*"1374512"*/ "1388632" /* p2-plugin-test/LP2-2018.2*/);
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
		firstNameText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				Text text = (Text) e.widget;
				if (text.getText().isEmpty())
					getButton(IDialogConstants.OK_ID).setEnabled(false);
				else
					getButton(IDialogConstants.OK_ID).setEnabled(true);
			}
		});
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
		if (Dialogs.cancelSubmission(getShell()) == SWT.YES) {
			this.status = SWT.CANCEL;
			super.cancelPressed();
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
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
		String filename = (this.firstName + " " + this.lastName + " " + this.assignment)
				.trim().replaceAll(" ", "_");

		return Normalizer.normalize(filename, Normalizer.Form.NFD).replaceAll("[^A-Za-z_0-9]", ""	).toUpperCase();
	}

	public int getStatus() {
		return this.status;
	}

	public boolean validRequest() {
		return request.isValid();
	}
}