import javax.swing.*;

import Application.Notification.EmailData;
import Application.Notification.Notification;

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
    public FacilitiesManagementSystem() throws Exception {
    	
    	
        setTitle("Facilities Management System");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        notificationsTextArea = new JTextArea();
        notificationsTextArea.setEditable(false);
        JScrollPane notificationScroll = new JScrollPane(notificationsTextArea);
        tabbedPane.addTab("Notifications", null, notificationScroll, "Displays notifications");


        taskAssignmentPanel = new JPanel();
        taskAssignmentPanel.setLayout(new BoxLayout(taskAssignmentPanel, BoxLayout.Y_AXIS));
        JTextField taskField = new JTextField(20);
        JButton assignTaskButton = new JButton("Assign Task");
        assignTaskButton.addActionListener(e -> {
        	// Todo: Get task name, description, room, room number, priority
        	
        	// Create email point
        	EmailData emailData = new EmailData();
        	emailData.setRecipient(null);
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
        
        taskAssignmentPanel.add(taskField);
        taskAssignmentPanel.add(assignTaskButton);
        tabbedPane.addTab("Task Assignment", null, taskAssignmentPanel, "Assign tasks to employees");


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

        //Time Section
        timeTrackingPanel = new JPanel();
        JLabel timeLabel = new JLabel("Time: 00:00:00");
        JButton startButton = new JButton("Start");
        JButton stopButton = new JButton("Stop");
        timeTrackingPanel.add(timeLabel);
        timeTrackingPanel.add(startButton);
        timeLabel.add(stopButton);
        tabbedPane.addTab("Time Tracking", null, timeTrackingPanel, "Track employee time");

        //Task Organizing section
        taskOrganizationPanel = new JPanel();
        taskOrganizationPanel.setLayout(new BoxLayout(taskOrganizationPanel, BoxLayout.Y_AXIS));
        String[] PriorityOptions = {"High", "Medium", "Low"};
        JComboBox<String> priorityCombo = new JComboBox<>(PriorityOptions);
        JButton organizeTaskButton = new JButton("Organize Task");
        taskAssignmentPanel.add(priorityCombo);
        taskOrganizationPanel.add(organizeTaskButton);
        tabbedPane.addTab("Task Organization", null, taskOrganizationPanel, "Organize tasks based on priority");


        //Report generator section
        reportGeneratorPanel = new JPanel();
        JButton generateReportButton = new JButton("Generate Report");
        generateReportButton.addActionListener(e -> taskManager.generateReport());
        reportGeneratorPanel.add(generateReportButton);
        tabbedPane.addTab("Report Generator", null, reportGeneratorPanel, "Generate reports based on task completion");

        add(tabbedPane, BorderLayout.CENTER);
        setVisible(true);
    
    }

}

