import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Business.*;
import Notification.*;

public class FacilitiesManagementSystem extends JFrame {
    private JPanel taskAssignmentPanel;
    private JTextArea notificationsTextArea;
    private JPanel timeTrackingPanel;
    private JPanel taskManagementPanel;
    private JPanel taskOrganizationPanel;
    private JPanel reportGeneratorPanel;

    public FacilitiesManagementSystem() {
        setTitle("Facilities Management System");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        notificationsTextArea = new JTextArea();
        notificationsTextArea.setEditable(false);
        JScrollPane notificationScroll = new JScrollPane(notificationsTextArea);
        tabbedPane.addTab("Notifications", null, notificationScroll, "Displays notifications");


        taskAssignmentPanel = new Jpanel();
        taskAssignmentPanel.setLayout(new BoxLayout(taskAssignmentPanel, BoxLayout.Y_AXIS));
        JTextField taskField = new JTextField(20);
        JButton assignTaskButton = new JButton("Assign Task");
        assignTaskButton.addActionListener(e -> {assignTask(taskField.getText());});
        taskAssignmentPanel.add(taskField);
        taskAssignmentPanel.add(assignTaskButton);
        tabbedPane.addTab("Task Assignment", null, taskAssignmentPanel, "Assign tasks to employees");


        //Task Collection
        taskManagementPanel = new Jpanel();
        taskManagementPanel.setLayout(new BoxLayout(taskManagementPanel, BoxLayout.Y_AXIS));
        JList<String> taskList = new JList<>(new DefaultListModel<>());
        JScrollPane taskListScroll = new JScrollPane(taskList);
        JButton completeTaskButton = new JButton("Complete Task");
        completeTaskButton.addActionListener(e -> completeTaskButton(taskList.getSelectedValue()));
        taskManagementPanel.add(taskListScroll);
        taskManagementPanel.add(completeTaskButton);
        tabbedPane.addTab("Task Management", null, taskManagementPanel, "Manage tasks assigned to employees");

        //Time Section
        timeTrackingPanel = new Jpanel();
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
        generateReportButton.addActionListener(e -> generateReport());
        reportGeneratorPanel.add(generateReportButton);
        tabbedPane.addTab("Report Generator", null, reportGeneratorPanel, "Generate reports based on task completion");

        add(tabbedPane, BorderLayout.CENTER);
    
    }

}

