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
public class InsertTemplateName extends javax.swing.JFrame {
	private JTextField jTemplateNameText;
	private JLabel jTemplateNameLabel;
	private JButton jRunButton;
	private JComboBox jTechnologyList;
	private JLabel jUserNameLabel;
	private JLabel jTechnologyLabel;
	private JCheckBox jHasIdStageCheckbox;
	private JCheckBox jIsExchange;

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
			//START >>  jTemplateNameText
			jTemplateNameText = new JTextField();  
			getContentPane().add(jTemplateNameText);
			jTemplateNameText.setText("");
			jTemplateNameText.setBounds(192, 40, 152, 23);
			//END <<  jTemplateNameText
			//START >>  jTemplateNameLabel
			jTemplateNameLabel = new JLabel();
			getContentPane().add(jTemplateNameLabel);
			jTemplateNameLabel.setText("Template Name");
			jTemplateNameLabel.setBounds(28, 43, 152, 16);
			//END <<  jTemplateNameLabel
			//START >>  jHasIdStageCheckbox
			jHasIdStageCheckbox = new JCheckBox();
			getContentPane().add(jHasIdStageCheckbox);
			jHasIdStageCheckbox.setText("Has ID Stage?");
			jHasIdStageCheckbox.setBounds(77, 149, 122, 20);
			//END <<  jHasIdStageCheckbox
			//START >>  jIsExchange
			jIsExchange = new JCheckBox();
			getContentPane().add(jIsExchange);
			jIsExchange.setText("Is Exchange?");
			jIsExchange.setBounds(247, 149, 122, 20);
			//END <<  jIsExchange
			//START >>  jTechnologyLabel
			jTechnologyLabel = new JLabel();
			getContentPane().add(jTechnologyLabel);
			jTechnologyLabel.setText("Technology");
			jTechnologyLabel.setBounds(28, 78, 152, 16);
			//END <<  jTechnologyLabel
			//START >>  jUserNameLabel
			jUserNameLabel = new JLabel();
			getContentPane().add(jUserNameLabel);
			jUserNameLabel.setText("User");
			jUserNameLabel.setBounds(28, 113, 146, 16);
			//END <<  jUserNameLabel
			//START >>  jRunButton
			jRunButton = new JButton();
			getContentPane().add(jRunButton);
			jRunButton.setText("Insert");
			jRunButton.setBounds(157, 291, 67, 23);
			jRunButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jRunButtonActionPerformed(evt);
				}
			});
			//END <<  jRunButton
			//START >>  jTechnologyList
			ComboBoxModel jTechnologyListModel = 
			new DefaultComboBoxModel(TECHNOLOGY);
			jTechnologyList = new JComboBox();
			getContentPane().add(jTechnologyList);
			jTechnologyList.setModel(jTechnologyListModel);
			jTechnologyList.setBounds(192, 75, 118, 23);
			//END <<  jTechnologyList
			//START >>  jUserList
			ComboBoxModel jUserListModel = 
			new DefaultComboBoxModel(USERS);
			jUserList = new JComboBox();
			getContentPane().add(jUserList);
			jUserList.setModel(jUserListModel);
			jUserList.setBounds(192, 110, 118, 23);
			//END <<  jUserList
			//START >>  jInfoArea
			jInfoArea = new JTextArea();
			scrollPane = new JScrollPane(jInfoArea);
			scrollPane.setViewportView(jInfoArea);
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			
			Rectangle rect = new Rectangle(22, 183, 350, 95);
			scrollPane.setBounds(rect);
			jInfoArea.setEditable(false);
			jInfoArea.setLineWrap(true);
			jInfoArea.setWrapStyleWord(true);
			jInfoArea.setBounds(rect);
			//END <<  jInfoArea
			pack();
			this.setSize(410, 364);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void initLabels() {
		jTemplateNameLabel.setForeground(Color.black);
		jTemplateNameLabel.setText("Template Name");
		jTechnologyLabel.setForeground(Color.black);
		jTechnologyLabel.setText("Technology");
		jUserNameLabel.setForeground(Color.black);
		jUserNameLabel.setText("User");
	}
	
	private void jRunButtonActionPerformed(ActionEvent evt) {
		initLabels();
		String template = jTemplateNameText.getText();
		String technology = jTechnologyList.getSelectedItem().toString();
		String username = jUserList.getSelectedItem().toString();
		boolean hasIdStage = jHasIdStageCheckbox.isSelected();
		boolean isExchange = jIsExchange.isSelected();
		if (isInputDataValid(template, technology, username)) {
			try {
				String query = QueryProvider.toRegularQuery(QueryProvider.getInsertIntoParseTemplateIdempotentQuery(), template, technology, hasIdStage, isExchange);
				int result = InsertIntoParseTemplate.insertIntoDatabase(Connector.getLiveFullConnection(), template, query);
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
	
	private boolean isInputDataValid(String template, String technology, String username) {
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
		return isValid;
	}
	
	private static final String[] TECHNOLOGY = {
		"java", "js"
	};
	private JTextArea jInfoArea;
	private JScrollPane scrollPane;
	private JComboBox jUserList;
	
	private static final String[] USERS = {
		"abogomolov", "akhakhlin", "mvasilenko", "sshupko", "vkisly", "vshuplyak", "vteryohina"
	};

}
