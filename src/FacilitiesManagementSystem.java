import javax.swing.*;

import Application.Notification.EmailData;
import Application.Notification.Notification;
import Base.Person;

import java.awt.*;
import java.awt.event.*;
import Business.*;
import Data.DatabaseManager;

public class FacilitiesManagementSystem extends JFrame {
    private JPanel taskAssignmentPanel;
    private JTextArea notificationsTextArea;
    private JPanel timeTrackingPanel;
    private JPanel taskManagementPanel;
    private JPanel taskOrganizationPanel;
    private JPanel reportGeneratorPanel;
    
    DatabaseManager databases;
    
    Task_Manager taskManager = new Task_Manager();
    public FacilitiesManagementSystem(boolean isStaff, Person user) throws Exception {
    	
    	
        setTitle("Facilities Management System");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
        
        if (isStaff) {
        notificationsTextArea = new JTextArea();
        notificationsTextArea.setEditable(false);
        JScrollPane notificationScroll = new JScrollPane(notificationsTextArea);
        tabbedPane.addTab("Notifications", null, notificationScroll, "Displays notifications");
        }
        
        taskAssignmentPanel = new JPanel();
        taskAssignmentPanel.setLayout(null);
        
        JLabel name = new JLabel("Name");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(100, 20);
        name.setLocation(100, 100);
        
        
        JTextField taskField = new JTextField();
        taskField.setSize(190, 20);
        taskField.setLocation(200, 100);
        
        JButton createTaskButton = new JButton("Assign Task");
        createTaskButton.addActionListener(e -> {
        	// Todo: Get task name, description, room, room number, priority
        	
        	// Create email point
        	EmailData emailData = new EmailData();
        	emailData.setRecipient(user);
        	emailData.setSubject("Task has been assigned");
        	emailData.setText("The task been assigned to a staff memeber. "
        			+ "You can expect your issue to be resolved in a couple of days.");
        	
        	// Create Task
        	Task task = new Task("task name", taskField.getText(), "room", "room#");
        	Notification notification;
			try {
				notification = new Notification(emailData);
			
        	task.setTask_Priority(1);
        	taskManager.addTask(task);
        	
        	databases.addItem(notification);
        	databases.addItem(task);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        	
        	}
        );
        taskAssignmentPanel.add(name);
        taskAssignmentPanel.add(taskField);
        taskAssignmentPanel.add(createTaskButton);
        tabbedPane.addTab("Task Assignment", null, taskAssignmentPanel, "Assign tasks to employees");
        
        
        if (isStaff) {
        //Task Collection
        taskManagementPanel = new JPanel();
        taskManagementPanel.setLayout(new BoxLayout(taskManagementPanel, BoxLayout.Y_AXIS));
        JList<String> taskList = new JList<>(new DefaultListModel<>());
        JScrollPane taskListScroll = new JScrollPane(taskList);
        JButton completeTaskButton = new JButton("Complete Task");
        completeTaskButton.addActionListener(e -> taskManager.completeTask(taskList.getSelectedValue()));
        taskManagementPanel.add(taskListScroll);
        taskManagementPanel.add(completeTaskButton);
        tabbedPane.addTab("Task Management", null, taskManagementPanel, "Manage tasks assigned to employees");

        }
        
        if (isStaff) {
        //Task Organizing section
        taskOrganizationPanel = new JPanel();
        taskOrganizationPanel.setLayout(new BoxLayout(taskOrganizationPanel, BoxLayout.Y_AXIS));
        String[] PriorityOptions = {"High", "Medium", "Low"};
        JComboBox<String> priorityCombo = new JComboBox<>(PriorityOptions);
        JButton organizeTaskButton = new JButton("Organize Task");
        taskOrganizationPanel.add(priorityCombo);
        taskOrganizationPanel.add(organizeTaskButton);
        tabbedPane.addTab("Task Organization", null, taskOrganizationPanel, "Organize tasks based on priority");
        }
        
        if (isStaff) {
        //Report generator section
        reportGeneratorPanel = new JPanel();
        JButton generateReportButton = new JButton("Generate Report");
        generateReportButton.addActionListener(e -> taskManager.generateReport());
        reportGeneratorPanel.add(generateReportButton);
        tabbedPane.addTab("Report Generator", null, reportGeneratorPanel, "Generate reports based on task completion");
        }
        add(tabbedPane, BorderLayout.CENTER);
        setVisible(true);
    
    }

}

