package blatt4.aufgabe;

import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

@SuppressWarnings("serial")
public class LittleGUI extends JPanel implements ItemListener, Observer {

	private Process[] processes;
	private JCheckBox[] boxes;

    public LittleGUI(Process[] processes) {
        super(new BorderLayout());

        this.processes = processes;
        this.boxes = new JCheckBox[10];
        JPanel checkPanel = new JPanel(new GridLayout(1, 0));
 
        //Create the check boxes.
        for (int i = 0; i < 10; i++) {
        	this.boxes[i] = new JCheckBox("Prozess " + (i + 1));
        	if (i < 8) {
        		this.boxes[i].setSelected(true);
        	} else {
        		this.boxes[i].setSelected(false);
        	}
        	this.boxes[i].addItemListener(this);
        	checkPanel.add(this.boxes[i]);
        }
 
        add(checkPanel, BorderLayout.LINE_START);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        for (Process proc : this.processes) {
        	proc.addObserver(this);
        }
    }
 
    /** Listens to the check boxes. */
    public void itemStateChanged(ItemEvent e) {
    	int i = 0;
    	Process isActive = null;

    	for (JCheckBox box : this.boxes) {
    		if (box.isSelected()) {
    			this.processes[i].setActive(true);
    			isActive = this.processes[i];
    		} else {
    			this.processes[i].setActive(false);
    		}
    		i++;
    	}

    	isActive.startElection();
    }
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI(Process[] processes) {
        //Create and set up the window.
        JFrame frame = new JFrame("Bully Algorithm");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        JComponent newContentPane = new LittleGUI(processes);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void showGUI(final Process[] processes) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(processes);
            }
        });
    }

	public void update(Observable o, Object arg) {
		for (JCheckBox box : this.boxes) {
			box.setForeground(Color.BLACK);
		}
		this.boxes[(Integer) arg].setForeground(Color.GREEN);
	}
}