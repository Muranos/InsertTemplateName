package dbmods;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicTreeUI.TreeExpansionHandler;

import org.apache.commons.lang.StringUtils;

import sql.*;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
@SuppressWarnings("serial")
public class InsertTemplateName extends javax.swing.JFrame {
	private JTextField jTemplateNameText;
	private JLabel jTemplateNameLabel;
	private JButton jRunButton;
	private JComboBox<String> jServerList;
	private JComboBox<String> jTechnologyList;
	private JLabel jUserNameLabel;
	private JLabel jTechnologyLabel;
	private JCheckBox jHasIdStageCheckbox;
	private JCheckBox jIsExchange;
	private JCheckBox jHasDivideStageCheckbox;
	private JCheckBox jHasParseStageCheckbox;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				InsertTemplateName inst = new InsertTemplateName();
				inst.setLocationRelativeTo(null);
				inst.setTitle("Insert Into parse_template");
				Image icon = Toolkit.getDefaultToolkit().getImage("gf.png");
				inst.setIconImage(icon);
				inst.setVisible(true);
			}
		});
	}
	
	public InsertTemplateName() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);  
			getContentPane().setName("Insert Into parse_template");
			
			addTemplateName();
			addCheckboxes();
			addTechnology();
			addUserName();
			addServer();
			addLogWindow();
			addInsertButton();
			
			pack();
			this.setSize(410, 424);
			jTemplateNameText.requestFocus();
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void addInsertButton() {
		//START >>  jRunButton
		jRunButton = new JButton();
		getContentPane().add(jRunButton);
		jRunButton.setText("Insert");
		jRunButton.setBounds(157, 351, 67, 23);
		jRunButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jRunButtonActionPerformed(evt);
			}
		});
		//END <<  jRunButton
	}

	private void addLogWindow() {
		//START >>  jInfoArea
		jInfoArea = new JTextArea();
		scrollPane = new JScrollPane(jInfoArea);
		scrollPane.setViewportView(jInfoArea);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		Rectangle rect = new Rectangle(22, 223, 350, 115);
		scrollPane.setBounds(rect);
		jInfoArea.setEditable(false);
		jInfoArea.setLineWrap(true);
		jInfoArea.setWrapStyleWord(true);
		jInfoArea.setBounds(rect);
		//END <<  jInfoArea
	}

	private void addServer() {
		//START >>  jServerList
		ComboBoxModel<String> jServerListModel = 
		new DefaultComboBoxModel<String>(SERVER);
		jServerList = new JComboBox<String>();
		getContentPane().add(jServerList);
		jServerList.setModel(jServerListModel);
		jServerList.setBounds(5, 5, 78, 23);
		//END <<  jServerList
	}

	private void addUserName() {
		//START >>  jTechnologyLabel
		jTechnologyLabel = new JLabel();
		getContentPane().add(jTechnologyLabel);
		jTechnologyLabel.setText("Technology");
		jTechnologyLabel.setBounds(28, 78, 152, 16);
		//END <<  jTechnologyLabel
		
		//START >>  jUserList
		ComboBoxModel<String> jUserListModel = 
		new DefaultComboBoxModel<String>(USERS);
		jUserList = new JComboBox<String>();
		getContentPane().add(jUserList);
		jUserList.setModel(jUserListModel);
		jUserList.setBounds(192, 110, 118, 23);
		//END <<  jUserList
	}

	private void addTechnology() {
		//START >>  jUserNameLabel
		jUserNameLabel = new JLabel();
		getContentPane().add(jUserNameLabel);
		jUserNameLabel.setText("User");
		jUserNameLabel.setBounds(28, 113, 146, 16);
		//END <<  jUserNameLabel
		
		//START >>  jTechnologyList
		ComboBoxModel<String> jTechnologyListModel = 
		new DefaultComboBoxModel<String>(TECHNOLOGY);
		jTechnologyList = new JComboBox<String>();
		getContentPane().add(jTechnologyList);
		jTechnologyList.setModel(jTechnologyListModel);
		jTechnologyList.setBounds(192, 75, 118, 23);
		//END <<  jTechnologyList
	}

	private void addTemplateName() {
		//START >>  jTemplateNameText
		jTemplateNameText = new JTextField();  
		getContentPane().add(jTemplateNameText);
		jTemplateNameText.setText("");
		jTemplateNameText.setBounds(192, 40, 185, 23);
		//END <<  jTemplateNameText
		//START >>  jTemplateNameLabel
		jTemplateNameLabel = new JLabel();
		getContentPane().add(jTemplateNameLabel);
		jTemplateNameLabel.setText("Template Name");
		jTemplateNameLabel.setBounds(28, 43, 152, 16);
		//END <<  jTemplateNameLabel
	}
	
	private void addCheckboxes() {
		//START >>  jHasIdStageCheckbox
		jHasIdStageCheckbox = new JCheckBox();
		getContentPane().add(jHasIdStageCheckbox);
		jHasIdStageCheckbox.setText("Has ID Stage?");
		jHasIdStageCheckbox.setBounds(47, 149, 122, 20);
		//END <<  jHasIdStageCheckbox
		//START >>  jIsExchange
		jIsExchange = new JCheckBox();
		getContentPane().add(jIsExchange);
		jIsExchange.setText("Is Exchange?");
		jIsExchange.setBounds(227, 149, 122, 20);
		//END <<  jIsExchange
		
		//START >>  jHasDivideStageCheckbox
		jHasDivideStageCheckbox = new JCheckBox();
		getContentPane().add(jHasDivideStageCheckbox);
		jHasDivideStageCheckbox.setText("Has Divide Stage?");
		jHasDivideStageCheckbox.setBounds(47, 180, 142, 20);
		//END <<  jHasDivideStageCheckbox
		//START >>  jHasParseStageCheckbox
		jHasParseStageCheckbox = new JCheckBox();
		getContentPane().add(jHasParseStageCheckbox);
		jHasParseStageCheckbox.setText("Has Parse Stage?");
		jHasParseStageCheckbox.setBounds(227, 180, 142, 20);
		//END <<  jHasParseStageCheckbox
	}

	private void initLabels() {
		jTemplateNameLabel.setForeground(Color.black);
		jTemplateNameLabel.setText("Template Name");
		jTechnologyLabel.setForeground(Color.black);
		jTechnologyLabel.setText("Technology");
		jUserNameLabel.setForeground(Color.black);
		jUserNameLabel.setText("User");
		jHasIdStageCheckbox.setForeground(Color.black);
		jHasDivideStageCheckbox.setForeground(Color.black);
		jHasParseStageCheckbox.setForeground(Color.black);
	}
	
	private void jRunButtonActionPerformed(ActionEvent evt) {
		initLabels();
		String template = jTemplateNameText.getText();
		String technology = jTechnologyList.getSelectedItem().toString();
		String username = jUserList.getSelectedItem().toString();
		boolean hasIdStage = jHasIdStageCheckbox.isSelected();
		boolean isExchange = jIsExchange.isSelected();
		boolean hasDivideStage = jHasDivideStageCheckbox.isSelected();
		boolean hasParseStage = jHasParseStageCheckbox.isSelected();
		if (isInputDataValid(template, technology, username, hasIdStage, hasDivideStage, hasParseStage)) {
			try {
				String query = QueryProvider.toRegularQuery(QueryProvider.getInsertIntoParseTemplateIdempotentQuery(), template, technology, hasIdStage, isExchange, hasDivideStage, hasParseStage);
				String server = jServerList.getSelectedItem().toString();
				Connection connection = null;
				if (server.matches("(?i)Live")) {
					connection = Connector.getLiveFullConnection();
				} else if (server.matches("(?i)Tsunami")) {
					connection = Connector.getTsunamiConnection();
				}
				int result = InsertIntoParseTemplate.insertIntoDatabase(connection, template, query);
				jInfoArea.append(InsertIntoParseTemplate.writeDbmodsFile(query, template, username) + "\n");
				if(result == 1) {
					jInfoArea.append(String.format("Template name [%s] successfully added%n", template));
				} else if (result == 0){
					jInfoArea.append(String.format("[%s] is already present in parse_template table %n", template));
				} else {
					jInfoArea.append(String.format("[%s] was NOT addet to parse_template table%n", template));
				}
				jInfoArea.setCaretPosition(jInfoArea.getDocument().getLength());
			} catch (Exception e) {
				jInfoArea.append(String.format("Error occurred while adding template name [%s]%n", template));
				jInfoArea.append(log(e));
				e.printStackTrace();
			}
		} else {
			jInfoArea.append("Input data are incomplete or invalid\n");
		}
	}
	
	private String log(Exception ex) {
		StackTraceElement[] trace = ex.getStackTrace();
		StringBuilder message = new StringBuilder();
		message.append(ex.getClass() + ": ");
		message.append(ex.getMessage());
		for (StackTraceElement element : trace) {
			message.append("\n\t" + element);
		}
		PrintWriter pw = null;
		try {
			//pw = new PrintWriter("D:\\Temp\\InsertTemplateName.log");
			//pw.append(message);
			//pw.flush();
			return message.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}
	
	private boolean isInputDataValid(String template, String technology, String username, boolean hasIdStage, boolean hasDivideStage, boolean hasParseStage) {
		boolean isValid = true;
		if (StringUtils.isBlank(template)) {
			jTemplateNameLabel.setForeground(Color.red);
			jTemplateNameLabel.setText("Template Name is blank");
			isValid = false;
		}
		if (StringUtils.isBlank(technology)) {
			jTechnologyLabel.setForeground(Color.red);
			jTechnologyLabel.setText("Technology is blank");
			isValid = false;
		}
		if (StringUtils.isBlank(username)) {
			jUserNameLabel.setForeground(Color.red);
			jUserNameLabel.setText("User is blank");
			isValid = false;
		}
		if (!hasIdStage && !hasDivideStage && !hasParseStage) {
			jHasIdStageCheckbox.setForeground(Color.red);
			jHasDivideStageCheckbox.setForeground(Color.red);
			jHasParseStageCheckbox.setForeground(Color.red);
			isValid = false;
		}
		return isValid;
	}
	
	private static final String[] TECHNOLOGY = {
		"java", "js"
	};
	private static final String[] SERVER = {
		"LIVE", "Tsunami"
	};
	private JTextArea jInfoArea;
	private JScrollPane scrollPane;
	private JComboBox<String> jUserList;
	
	private static final String[] USERS = {
		"abogomolov", "akhakhlin", "mvasilenko", "sshupko", "vkisly", "vshuplyak", "vteryohina"
	};

}
