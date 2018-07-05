package p2.submibot.ui;

import java.text.Normalizer;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class Dialog extends TitleAreaDialog {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private Text firstNameText;
	private Text lastNameText;
	private Text mailText;
	private Text passwordText;

	private String firstName;
	private String lastName;
	private String mail;
	private String password;

	public Dialog(Shell parentShell) {
		super(parentShell);
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
		container.setLayout(layout);

		createFirstName(container);
		createLastName(container);
		createMail(container);
		createPassword(container);
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

	private void createMail(Composite container) {
		Label mailLabel = new Label(container, SWT.NONE);
		mailLabel.setText("E-mail");

		GridData mailData = new GridData();
		mailData.grabExcessHorizontalSpace = true;
		mailData.horizontalAlignment = GridData.FILL;

		mailText = new Text(container, SWT.BORDER);
		mailText.setLayoutData(mailData);
		
	}

	private void createPassword(Composite container) {
		Label passwordLabel = new Label(container, SWT.NONE);
		passwordLabel.setText("Senha");

		GridData passwordData = new GridData();
		passwordData.grabExcessHorizontalSpace = true;
		passwordData.horizontalAlignment = GridData.FILL;
		passwordText = new Text(container, SWT.BORDER | SWT.PASSWORD);
		passwordText.setLayoutData(passwordData);
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	private void saveInput() {
		this.firstName = firstNameText.getText();
		this.lastName = lastNameText.getText();
		this.mail = mailText.getText();
		this.password = passwordText.getText();
	}

	@Override
	protected void okPressed() {
		saveInput();
		super.okPressed();
	}
	
	@Override
	protected void cancelPressed() {
		MessageDialog.openWarning(getShell(), "Cancelar Submissão", "Deseja realmente cancelar a submissão?");
		super.cancelPressed();
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMail() {
		return mail;
	}

	public String getPassword() {
		return password;
	}

	public String getLastName() {
		return lastName;
	}
	
	public String getFilename() {
		String filename = this.firstName.toUpperCase() + "_" + this.lastName.toUpperCase();
	    return Normalizer.normalize(filename, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}

}