package dbmods;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.apache.commons.lang.StringUtils;

import sql.Connector;
import sql.InsertIntoParseTemplate;
import sql.QueryProvider;

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
    private JCheckBox jUpdateOldTemplateCheckbox;
    private JTextField jOldTemplateNameText;
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
	
	private static final int LEFT_MARGIN = 28;
	private static final int LABEL_HEIGHT = 16;
	private static final int FIELD_HEIGHT = 23;
	private static final int CHECKBOX_WIDTH = 122;
    private static final int CHECKBOX_HEIGHT = 20;
    
    private static final int AVERAGE_COMPONENT_HEIGHT = 35;
    
    private int currentHeightUsed = 5;

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
			
	        addServer();
	        addOldTemplateName();
			addTemplateName();
			addTechnology();
			addUserName();
            addCheckboxes();
			addLogWindow();
			addInsertButton();
			
			pack();
			this.setSize(410, 475);
			jTemplateNameText.requestFocus();
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
    private void addServer() {
        // START >> jServerList
        ComboBoxModel<String> jServerListModel =
                new DefaultComboBoxModel<String>(SERVER);
        jServerList = new JComboBox<String>();
        getContentPane().add(jServerList);
        jServerList.setModel(jServerListModel);
        jServerList.setBounds(5, currentHeightUsed, 78, FIELD_HEIGHT);
        // END << jServerList
        incrementCurrentHeightUsed();
    }
    
    private static final String DEFAULT_OLD_TEMPLATE_STRING = "Enter old template name here";
    private void addOldTemplateName() {
        jUpdateOldTemplateCheckbox = new JCheckBox();
        getContentPane().add(jUpdateOldTemplateCheckbox);
        jUpdateOldTemplateCheckbox.setText("Update existing?    ");
        jUpdateOldTemplateCheckbox.setBounds(LEFT_MARGIN - 3, currentHeightUsed, CHECKBOX_WIDTH + 20, CHECKBOX_HEIGHT);
        jUpdateOldTemplateCheckbox.setHorizontalTextPosition(SwingConstants.LEADING);
        jUpdateOldTemplateCheckbox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jUpdateOldTemplateCheckboxActionPerformed(evt);
            }
        });
        
        jOldTemplateNameText = new JTextField();
        getContentPane().add(jOldTemplateNameText);
        jOldTemplateNameText.setText(DEFAULT_OLD_TEMPLATE_STRING);
        jOldTemplateNameText.setSelectedTextColor(Color.GRAY);
        jOldTemplateNameText.setBounds(192, currentHeightUsed, 185, FIELD_HEIGHT);
        jOldTemplateNameText.setVisible(false);
        jOldTemplateNameText.addMouseListener(new MouseListener() {
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                jOldTemplateNameTextActionPerformed(e);
            }

            @Override
            public void mousePressed(MouseEvent e) { }

            @Override
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) { }
        });
        incrementCurrentHeightUsed();
    }
    
    private void jUpdateOldTemplateCheckboxActionPerformed(ActionEvent evt) {
        boolean selected = jUpdateOldTemplateCheckbox.isSelected();
        if (selected) {
            jOldTemplateNameText.setVisible(true);
            jOldTemplateNameText.requestFocusInWindow();
            jOldTemplateNameText.selectAll();
            jRunButton.setText("Update");
        } else {
            jOldTemplateNameText.setVisible(false);
            jRunButton.setText("Insert");
        }
    }
    
    protected void jOldTemplateNameTextActionPerformed(MouseEvent evt) {
        if (jOldTemplateNameText.getText().equals(DEFAULT_OLD_TEMPLATE_STRING)) {
            jOldTemplateNameText.setText("");
        }
    }

    private void addTemplateName() {
        // START >> jTemplateNameLabel
        jTemplateNameLabel = new JLabel();
        getContentPane().add(jTemplateNameLabel);
        jTemplateNameLabel.setText("Template Name");
        jTemplateNameLabel.setBounds(LEFT_MARGIN, currentHeightUsed + 3, 152, LABEL_HEIGHT);
        // END << jTemplateNameLabel
        // START >> jTemplateNameText
        jTemplateNameText = new JTextField();
        getContentPane().add(jTemplateNameText);
        jTemplateNameText.setText("");
        jTemplateNameText.setBounds(192, currentHeightUsed, 185, FIELD_HEIGHT);
        // END << jTemplateNameText
        incrementCurrentHeightUsed();
    }
    
    private void addTechnology() {
        //START >>  jTechnologyLabel
        jTechnologyLabel = new JLabel();
        getContentPane().add(jTechnologyLabel);
        jTechnologyLabel.setText("Technology");
        jTechnologyLabel.setBounds(LEFT_MARGIN, currentHeightUsed + 3, 152, LABEL_HEIGHT);
        //END <<  jTechnologyLabel
        
        //START >>  jTechnologyList
        ComboBoxModel<String> jTechnologyListModel = 
        new DefaultComboBoxModel<String>(new String[]{Technology.java.name(), Technology.js.name()});
        jTechnologyList = new JComboBox<String>();
        getContentPane().add(jTechnologyList);
        jTechnologyList.setModel(jTechnologyListModel);
        jTechnologyList.setBounds(192, currentHeightUsed, 118, FIELD_HEIGHT);
        //END <<  jTechnologyList
        incrementCurrentHeightUsed();
    }

    private void addUserName() {
        //START >>  jUserNameLabel
        jUserNameLabel = new JLabel();
        getContentPane().add(jUserNameLabel);
        jUserNameLabel.setText("User");
        jUserNameLabel.setBounds(LEFT_MARGIN, currentHeightUsed + 3, 146, LABEL_HEIGHT);
        //END <<  jUserNameLabel
        //START >>  jUserList
        ComboBoxModel<String> jUserListModel = 
        new DefaultComboBoxModel<String>(USERS);
        jUserList = new JComboBox<String>();
        getContentPane().add(jUserList);
        jUserList.setModel(jUserListModel);
        jUserList.setBounds(192, currentHeightUsed, 118, FIELD_HEIGHT);
        //END <<  jUserList
        incrementCurrentHeightUsed();
    }

    private void addCheckboxes() {
        //START >>  jHasIdStageCheckbox
        jHasIdStageCheckbox = new JCheckBox();
        getContentPane().add(jHasIdStageCheckbox);
        jHasIdStageCheckbox.setText("Has ID Stage?");
        jHasIdStageCheckbox.setBounds(47, currentHeightUsed + 5, CHECKBOX_WIDTH, CHECKBOX_HEIGHT);
        //END <<  jHasIdStageCheckbox
        //START >>  jIsExchange
        jIsExchange = new JCheckBox();
        getContentPane().add(jIsExchange);
        jIsExchange.setText("Is Exchange?");
        jIsExchange.setBounds(227, currentHeightUsed + 5, CHECKBOX_WIDTH, CHECKBOX_HEIGHT);
        //END <<  jIsExchange
        
        //START >>  jHasDivideStageCheckbox
        jHasDivideStageCheckbox = new JCheckBox();
        getContentPane().add(jHasDivideStageCheckbox);
        jHasDivideStageCheckbox.setText("Has Divide Stage?");
        jHasDivideStageCheckbox.setBounds(47, currentHeightUsed + 35, CHECKBOX_WIDTH + 20, CHECKBOX_HEIGHT);
        //END <<  jHasDivideStageCheckbox
        //START >>  jHasParseStageCheckbox
        jHasParseStageCheckbox = new JCheckBox();
        getContentPane().add(jHasParseStageCheckbox);
        jHasParseStageCheckbox.setText("Has Parse Stage?");
        jHasParseStageCheckbox.setBounds(227, currentHeightUsed + 35, CHECKBOX_WIDTH + 20, CHECKBOX_HEIGHT);
        //END <<  jHasParseStageCheckbox
        incrementCurrentHeightUsed(40, 43);
    }

	private void addLogWindow() {
		//START >>  jInfoArea
		jInfoArea = new JTextArea();
		scrollPane = new JScrollPane(jInfoArea);
		scrollPane.setViewportView(jInfoArea);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		Rectangle rect = new Rectangle(22, currentHeightUsed, 350, 115);
		scrollPane.setBounds(rect);
		jInfoArea.setEditable(false);
		jInfoArea.setLineWrap(true);
		jInfoArea.setWrapStyleWord(true);
		jInfoArea.setBounds(rect);
		//END <<  jInfoArea
		incrementCurrentHeightUsed(130);
	}
	
    private void addInsertButton() {
        //START >>  jRunButton
        jRunButton = new JButton();
        getContentPane().add(jRunButton);
        jRunButton.setText("Insert");
        jRunButton.setBounds(157, currentHeightUsed, 75, FIELD_HEIGHT);
        jRunButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jRunButtonActionPerformed(evt);
            }
        });
        //END <<  jRunButton
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
        jUpdateOldTemplateCheckbox.setForeground(Color.black);
        jUpdateOldTemplateCheckbox.setText("Update existing?    ");
    }
    
	private void jRunButtonActionPerformed(ActionEvent evt) {
		initLabels();
		boolean isUpdate = jUpdateOldTemplateCheckbox.isSelected(); 
		String oldTemplateName = jOldTemplateNameText.getText();
		String template = jTemplateNameText.getText();
		String technology = jTechnologyList.getSelectedItem().toString();
		String username = jUserList.getSelectedItem().toString();
		boolean hasIdStage = jHasIdStageCheckbox.isSelected();
		boolean isExchange = jIsExchange.isSelected();
		boolean hasDivideStage = jHasDivideStageCheckbox.isSelected();
		boolean hasParseStage = jHasParseStageCheckbox.isSelected();
		String newTemplateName = StringUtils.isBlank(template) ? oldTemplateName : template;
		if (isInputDataValid(isUpdate, newTemplateName, technology, username, hasIdStage, hasDivideStage, hasParseStage)) {
			try {
				String query = "";
				if (isUpdate) {
				    query = QueryProvider.toRegularQuery(QueryProvider.getUpdateParseTemplateQuery(), newTemplateName, technology, hasIdStage,isExchange, hasDivideStage, hasParseStage, oldTemplateName);
				} else {
				    query = QueryProvider.toRegularQuery(QueryProvider.getInsertIntoParseTemplateIdempotentQuery(), template, technology, hasIdStage, isExchange, hasDivideStage, hasParseStage);
				}
				String server = jServerList.getSelectedItem().toString();
				Connection connection = null;
				if (server.matches("(?i)Live")) {
					connection = Connector.getLiveFullConnection();
				} else if (server.matches("(?i)Tsunami")) {
					connection = Connector.getTsunamiConnection();
				}
				int result = 0;
				if (isUpdate) {
				    result = InsertIntoParseTemplate.updateInDatabase(connection, newTemplateName, query);
				    if (result == 1)
				    jInfoArea.append(InsertIntoParseTemplate.writeDbmodsFile(query, newTemplateName, username) + "\n");
				} else {
				    result = InsertIntoParseTemplate.insertIntoDatabase(connection, template, query);
				    if (result == 1)
				    jInfoArea.append("\n" + InsertIntoParseTemplate.writeDbmodsFile(query, template, username) + "\n\n");
				}
			    
				
				if(result == 1) {
				    if (isUpdate) {
				        jInfoArea.append(String.format("%nUpdate for [%s] successfully executed%n", oldTemplateName));
				    } else {
				        jInfoArea.append(String.format("%nTemplate name [%s] successfully added%n", template));
				    }
				} else if (result == 0){
			        jInfoArea.append(String.format("%n[%s] is already present in parse_template table %n", template));
				} else if (result == -1 && isUpdate) {
				    jInfoArea.append(String.format("%n[%s] was not found in parse_template. 0 rows affected %n", oldTemplateName));
				} else {
					jInfoArea.append(String.format("%n[%s] was NOT addet to parse_template table. The template might be already present in the database%n", template));
				}
				jInfoArea.append("\n");
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
	
	private boolean isInputDataValid(boolean isUpdate, String template, String technology, String username, boolean hasIdStage, boolean hasDivideStage, boolean hasParseStage) {
		boolean isValid = true;
		if (isUpdate && StringUtils.isNotBlank(template) && !isTemplateNameValid(template, technology)) {
                jTemplateNameLabel.setForeground(Color.red);
                jTemplateNameLabel.setText("Invalid Template Name");
                isValid = false;
		} else {
    		if (StringUtils.isBlank(template)) {
    			jTemplateNameLabel.setForeground(Color.red);
    			jTemplateNameLabel.setText("Template Name is blank");
    			isValid = false;
    		}
    		if (!isTemplateNameValid(template, technology)) {
    		    jTemplateNameLabel.setForeground(Color.red);
                jTemplateNameLabel.setText("Invalid Template Name");
                isValid = false;
    		}
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
	
    private static boolean isTemplateNameValid(String template, String technology) {
        final String JAVA_TEMPLATE_REGEXP = "(PdfPesc|Pdf|Pesc|Image)\\.(HS|CO)\\.\\p{Alpha}+";
        final String JS_TEMPLATE_REGEXP = "\\p{Alpha}+Js";
        if (technology.equals(Technology.java.name()) && template.matches(JAVA_TEMPLATE_REGEXP)
                || technology.equals(Technology.js.name()) && template.matches(JS_TEMPLATE_REGEXP)) {
            return true;
        }
        return false;
    }
	
	private static enum Technology {
	    java, js
	}
	private static final String[] SERVER = {
		"LIVE", "Tsunami"
	};
	private JTextArea jInfoArea;
	private JScrollPane scrollPane;
	private JComboBox<String> jUserList;
	
	private static final String[] USERS = {
		"achernikhov", "akhakhlin", "mvasilenko", "sshupko", "vkisly", "vshuplyak", "vteryohina"
	};
	
	private void incrementCurrentHeightUsed(int...increment) {
        if (increment.length == 0) {
            currentHeightUsed += AVERAGE_COMPONENT_HEIGHT;
            return;
        }
	    for (int currentIncrement : increment) {
	        currentHeightUsed += currentIncrement;
	    }
	}
}
