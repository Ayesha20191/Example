package p3;

//import java.awt.Color;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Text;
//import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.part.ViewPart;

public class TaskView extends ViewPart {

	public TaskView() {
		// TODO Auto-generated constructor stub
	}
	private Text messageText;
	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		Composite top = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		top.setLayout(layout);
		// top banner
		Composite banner = new Composite(top, SWT.NONE);
		banner.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL, GridData.VERTICAL_ALIGN_BEGINNING, true, false));
		layout = new GridLayout();
		layout.marginHeight = 5;
		layout.marginWidth = 10;
		layout.numColumns = 2;
		banner.setLayout(layout);
		
		// setup bold font
		Font boldFont = JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT);    
		/*Label l = new Label(banner, SWT.NONE);
		l.setText("Task 1");
		l.setFont(boldFont);
		//l.setAlignment(PROP_TITLE);
		l.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false));*/
		
	    Label l = new Label(banner, SWT.NONE);
		l.setText("Subject:");
		l.setFont(boldFont);
		l.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
		
		l = new Label(banner, SWT.WRAP);
		l.setText("This is a message about the XYZ task to be performed");
		l.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false));
		
		l = new Label(banner, SWT.NONE);
		l.setText("From:");
		l.setFont(boldFont);
		l.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
    
		final Link link = new Link(banner, SWT.NONE);
		link.setText("<a>customer1@org.co.in</a>");
		link.addSelectionListener(new SelectionAdapter() {    
			public void widgetSelected(SelectionEvent e) {
				MessageDialog.openInformation(getSite().getShell(), "Not Implemented", "Imagine the address book or a new message being created now.");
			}    
		});
		link.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false));
    
		l = new Label(banner, SWT.NONE);
		l.setText("Date:");
		l.setFont(boldFont);
		l.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
		
		l = new Label(banner, SWT.WRAP);
		l.setText("17/09/2021");
		l.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false));
		
		// message contents
		messageText = new Text(top, SWT.MULTI | SWT.WRAP);
		messageText.setText("This RCP Application needs to perform the following task:\n"+
						"- add a top-level menu and toolbar with actions\n"+
						"- add keybindings to actions\n" +
						"- create views that can't be closed and\n"+
						"  multiple instances of the same view\n"+
						"- perspectives with placeholders for new views\n"+
						"- use the default about dialog\n"+
						"- create a product definition\n");
		messageText.setLayoutData(new GridData(GridData.FILL_BOTH));
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		messageText.setFocus();
	}

}
