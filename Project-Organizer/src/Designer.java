import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import com.thoughtworks.xstream.XStream;


public class Designer {

	private JFrame frame;
	HashMap<String, Project> projectMap;

	XStream xstream;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Designer window = new Designer();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Designer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		xstream = new XStream();
		
		projectMap=new HashMap<String, Project>();

		DefaultMutableTreeNode top =
		        new DefaultMutableTreeNode("Projects");
		
		DefaultMutableTreeNode newChild =
		        new DefaultMutableTreeNode("test1");
		
		top.add(newChild);
		
		LogEntry logs[]=new LogEntry[]{new LogEntry(new Date(), "Started test1.")};
		TodoEntry todos[]=new TodoEntry[]{new TodoEntry("Make Better Test")};
		
		
		Project test1=new Project("test1",logs,todos);
		
		projectMap.put("test1", test1);
		
		
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		
		final JTextArea textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(0, 0, 5, 0);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 0;
		frame.getContentPane().add(textArea, gbc_textArea);
		
		final JTextArea textArea_1 = new JTextArea();
		GridBagConstraints gbc_textArea_1 = new GridBagConstraints();
		gbc_textArea_1.fill = GridBagConstraints.BOTH;
		gbc_textArea_1.gridx = 1;
		gbc_textArea_1.gridy = 1;
		frame.getContentPane().add(textArea_1, gbc_textArea_1);
		
		
		JTree tree = new JTree(top);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent arg0) {
				String selectedName=arg0.getPath().getLastPathComponent().toString();
				Project selectedProject=projectMap.get(selectedName);
				if(selectedProject!=null)
				{
					String logText="";
					for(LogEntry entry:selectedProject.logs)
					{
						logText+=entry.getText();
					}
					textArea.setText(logText);
					
					String todoText="";
					for(TodoEntry entry:selectedProject.todos)
					{
						todoText+=entry.getName();
					}
					textArea_1.setText(todoText);
				}
				else
				{
					System.out.println(selectedName+" not found");
				}
			}
		});
		GridBagConstraints gbc_tree = new GridBagConstraints();
		gbc_tree.gridheight = 2;
		gbc_tree.insets = new Insets(0, 0, 5, 5);
		gbc_tree.fill = GridBagConstraints.BOTH;
		gbc_tree.gridx = 0;
		gbc_tree.gridy = 0;
		frame.getContentPane().add(tree, gbc_tree);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String xml = xstream.toXML(projectMap);
				try {
					FileWriter f0 = new FileWriter("out.xml");
					f0.write(xml);
					f0.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		mnFile.add(mntmSave);
		
		JMenuItem mntmLoad = new JMenuItem("Load");
		mntmLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File f = new File("out.xml");
			    FileInputStream fin;
				try {
					fin = new FileInputStream(f);
				    byte[] buffer = new byte[(int) f.length()];
				    new DataInputStream(fin).readFully(buffer);
				    fin.close();
				    String xml = new String(buffer, "UTF-8");
					projectMap = (HashMap<String, Project>)xstream.fromXML(xml);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   
			}
		});
		mnFile.add(mntmLoad);
		
		
	}

}
