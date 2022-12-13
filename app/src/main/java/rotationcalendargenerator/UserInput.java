package rotationcalendargenerator;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class UserInput implements Runnable {
    private Thread t;
    private String threadName;

    public UserInput(String name) {
        threadName = name;
    }

    private JButton nextbutton = new JButton("Next");
    private LocalDate startdate, enddate;
    private int framey = 0;

    @Override
    public void run() throws NumberFormatException {
        nextbutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (t) {
                    t.interrupt();
                }
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        make1();
        frame.getContentPane().removeAll();
        make2();
        frame.getContentPane().removeAll();
        framey = 0;
        make3();

        frame.dispose();
        WriteFile writeFile = new WriteFile();
        writeFile.start();
    }

    private JFrame frame = new JFrame(Main.TITLE);

    private JLabel label1n = new JLabel("Arrangement 1 Name");
    private JTextField field1n = new JTextField();
    private JLabel label1l = new JLabel("Arrangement 1 location");
    private JTextField field1l = new JTextField();

    private JLabel label2n = new JLabel("Arrangement 2 Name");
    private JTextField field2n = new JTextField();
    private JLabel label2l = new JLabel("Arrangement 2 location");
    private JTextField field2l = new JTextField();

    private JLabel label3n = new JLabel("Arrangement 3 Name");
    private JTextField field3n = new JTextField();
    private JLabel label3l = new JLabel("Arrangement 3 location");
    private JTextField field3l = new JTextField();

    private JLabel label4n = new JLabel("Arrangement 4 Name");
    private JTextField field4n = new JTextField();
    private JLabel label4l = new JLabel("Arrangement 4 location");
    private JTextField field4l = new JTextField();

    private JLabel labelstart = new JLabel("Term Start Date");
    private JFormattedTextField fieldstart = new JFormattedTextField();

    private JLabel labelend = new JLabel("Term End Date");
    private JFormattedTextField fieldend = new JFormattedTextField();

    private void make1() {
        label1n.setBounds(0, framey, 200, 30);
        field1n.setBounds(200, framey, 200, 30);
        framey += 30;
        label1l.setBounds(0, framey, 200, 30);
        field1l.setBounds(200, framey, 200, 30);
        framey += 30;
        

        label2n.setBounds(0, framey, 200, 30);
        field2n.setBounds(200, framey, 200, 30);
        framey += 30;
        label2l.setBounds(0, framey, 200, 30);
        field2l.setBounds(200, framey, 200, 30);
        framey += 30;


        label3n.setBounds(0, framey, 200, 30);
        field3n.setBounds(200, framey, 200, 30);
        framey += 30;
        label3l.setBounds(0, framey, 200, 30);
        field3l.setBounds(200, framey, 200, 30);
        framey += 30;
        

        label4n.setBounds(0, framey, 200, 30);
        field4n.setBounds(200, framey, 200, 30);
        framey += 30;
        label4l.setBounds(0, framey, 200, 30);
        field4l.setBounds(200, framey, 200, 30);
        framey += 30;

        labelstart.setBounds(0, framey, 200, 30);
        fieldstart.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}

            public void keyTyped(KeyEvent e) {
              char c = e.getKeyChar();
              if (!((c >= '0') && (c <= '9') ||
                 (c == KeyEvent.VK_BACK_SPACE) ||
                 (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SLASH)))        
              {
                JOptionPane.showMessageDialog(null, "Please Enter Valid");
                e.consume();
              }
            }
          });
        fieldstart.setBounds(200, framey, 200, 30);
        framey += 30;

        labelend.setBounds(0, framey, 200, 30);
        fieldend.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}

            public void keyTyped(KeyEvent e) {
              char c = e.getKeyChar();
              if (!((c >= '0') && (c <= '9') ||
                 (c == KeyEvent.VK_BACK_SPACE) ||
                 (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SLASH)))        
              {
                JOptionPane.showMessageDialog(null, "Please Enter Valid");
                e.consume();
              }
            }
          });
        fieldend.setBounds(200, framey, 200, 30);
        framey += 30;
        
        
        

        frame.getContentPane().add(label1n);
        frame.getContentPane().add(field1n);
        frame.getContentPane().add(label1l);
        frame.getContentPane().add(field1l);

        frame.getContentPane().add(label2n);
        frame.getContentPane().add(field2n);
        frame.getContentPane().add(label2l);
        frame.getContentPane().add(field2l);

        frame.getContentPane().add(label3n);
        frame.getContentPane().add(field3n);
        frame.getContentPane().add(label3l);
        frame.getContentPane().add(field3l);

        frame.getContentPane().add(label4n);
        frame.getContentPane().add(field4n);
        frame.getContentPane().add(label4l);
        frame.getContentPane().add(field4l);

        frame.getContentPane().add(labelstart);
        frame.getContentPane().add(fieldstart);

        frame.getContentPane().add(labelend);
        frame.getContentPane().add(fieldend);
        

        nextbutton.setBounds(300, framey, 100, 30);

        framey += 30;

        frame.getContentPane().add(nextbutton);


        frame.getContentPane().add(new JLabel());

        frame.setResizable(false);
        framey += 30;
        frame.setSize(400 + Main.ROOM, framey + Main.ROOM);

        show1();
    }

    private void show1() {
        frame.setVisible(true);

        synchronized (t) {
            try {
                t.wait();
            } catch (InterruptedException e) {}
        }

        check1();
    }

    private void check1() {
        frame.setVisible(false);

        int[] startdateint = {0, 0, 0}, enddateint = {0, 0, 0};
        try {
            startdateint = Arrays.stream(fieldstart.getText().split("/")).mapToInt(Integer::parseInt).toArray(); 
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Incorrect Starting Date", Main.TITLE, JOptionPane.ERROR_MESSAGE);
            show1();
        }
        try {
            enddateint = Arrays.stream(fieldend.getText().split("/")).mapToInt(Integer::parseInt).toArray();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Incorrect Ending Date", Main.TITLE, JOptionPane.ERROR_MESSAGE);
            show1();
        }

        LocalDate startdate = LocalDate.of(1970, 1, 1), enddate = LocalDate.of(1970, 1, 1);
        try {
            startdate = LocalDate.of(startdateint[0], startdateint[1], startdateint[2]);
            if (startdate.getDayOfWeek().getValue() == 6)
                startdate = startdate.plusDays(2);
            if (startdate.getDayOfWeek().getValue() == 7)
                startdate = startdate.plusDays(1);
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Incorrect Starting Date", Main.TITLE, JOptionPane.ERROR_MESSAGE);
            show1();
        }

        try {
            enddate = LocalDate.of(enddateint[0], enddateint[1], enddateint[2]);
            if (enddate.getDayOfWeek().getValue() == 6)
                enddate = enddate.minusDays(1);
            if (enddate.getDayOfWeek().getValue() == 7)
                enddate = enddate.minusDays(2);
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Incorrect Ending Date", Main.TITLE, JOptionPane.ERROR_MESSAGE);
            show1();
        }
        this.startdate = startdate;
        Main.startdate = startdate;
        this.enddate = enddate;

        try {
            Main.arrNames[0] = field1n.getText();
        } catch (NullPointerException e) {
            Main.arrNames[0] = "";
        }
        try {
            Main.arrNames[1] = field2n.getText();
        } catch (NullPointerException e) {
            Main.arrNames[1] = "";
        }
        try {
            Main.arrNames[2] = field3n.getText();
        } catch (NullPointerException e) {
            Main.arrNames[2] = "";
        }
        try {
            Main.arrNames[3] = field4n.getText();
        } catch (NullPointerException e) {
            Main.arrNames[3] = "";
        }

        try {
            Main.arrLocations[0] = field1l.getText();
        } catch (NullPointerException e) {
            Main.arrLocations[0] = "";
        }
        try {
            Main.arrLocations[1] = field2l.getText();
        } catch (NullPointerException e) {
            Main.arrLocations[1] = "";
        }
        try {
            Main.arrLocations[2] = field3l.getText();
        } catch (NullPointerException e) {
            Main.arrLocations[2] = "";
        }
        try {
            Main.arrLocations[3] = field4l.getText();
        } catch (NullPointerException e) {
            Main.arrLocations[3] = "";
        }

        Main.daystorepeat = (int) ChronoUnit.DAYS.between(startdate, enddate);
    }


    private JCheckBox[] boxes;
    private int totaldays; 
    private JLabel[] weekdays = {
        new JLabel("Sun"), 
        new JLabel("Mon"), 
        new JLabel("Tue"), 
        new JLabel("Wed"), 
        new JLabel("Thu"), 
        new JLabel("Fri"), 
        new JLabel("Sat")
    };

    private JLabel prompt;

    private void make2() {
        for (int i = 0; ChronoUnit.DAYS.between(startdate.plusDays(i), enddate) != 0; i++) {
            if (startdate.plusDays(i).getDayOfWeek().getValue() != 6 && startdate.plusDays(i).getDayOfWeek().getValue() != 7)
                totaldays++;
        }

        totaldays = (int) ChronoUnit.DAYS.between(startdate, enddate);

        boxes = new JCheckBox[totaldays];

        prompt = new JLabel("Uncheck Holidays");
        prompt.setBounds(0, 0, 200, 30);

        frame.getContentPane().add(prompt);

        for (int i = 0; i < 7; i++) {
            weekdays[i].setBounds(i * 50, 30, 50, 30);
            frame.getContentPane().add(weekdays[i]);
        }

        int l = 2;

        for (int i = 0, k = startdate.getDayOfWeek().getValue(); i < totaldays; i++) {
            if (k != 6 && k != 7) {
                boxes[i] = new JCheckBox("", true);
                boxes[i].setBounds(k * 50, l * 30, 25, 25);
                frame.getContentPane().add(boxes[i]);
            } else {
                boxes[i] = new JCheckBox("", false);
            }
            if (k < 7)
                k++;
            else {
                k = 1;
                l++;
            }
        }
        l++;

        nextbutton.setBounds(250, l * 30, 100, 30);
        frame.getContentPane().add(nextbutton);
        l+=2;


        frame.setResizable(false);
        frame.setSize(350 + Main.ROOM, l * 30 + Main.ROOM);

        frame.getContentPane().add(new JLabel());

        show2();
    }

    private void show2() {
        frame.setVisible(true);

        synchronized (t) {
            try {
                t.wait();
            } catch (InterruptedException e) {}
        }

        check2();
    }

    private void check2() {
        frame.setVisible(false);
        for (int i = 0; i < totaldays; i++) 
            if (!boxes[i].isSelected()) 
                Main.skipDates.add(startdate.plusDays(i));
    }

    private JLabel specialRules = new JLabel("Any special rules for the arrangements?");

    private JLabel[] ruleArrNames = new JLabel[4];

    private JComboBox<String>[] ruleBoxes = new JComboBox[4];

    private String[] rules = {"Every Weekday", "Odd Rotation Days Only", "Even Rotation Days Only"};

    private void make3() {
        specialRules.setBounds(0, 0, 300, 30);
        framey += 30;
        frame.getContentPane().add(specialRules);
        for (int i = 0; i < 4; i++) {
            ruleBoxes[i] = new JComboBox<String>(rules);
            ruleBoxes[i].setSelectedIndex(0);
            if (!Main.arrNames[i].equals("")) {
                ruleArrNames[i] = new JLabel(Main.arrNames[i]);
                ruleArrNames[i].setBounds(0, framey, 300, 30);
                ruleBoxes[i].setBounds(300, framey, 300, 30);
                frame.getContentPane().add(ruleArrNames[i]);
                frame.getContentPane().add(ruleBoxes[i]);
                framey += 30;
            }
        }
        nextbutton.setBounds(500, framey, 100, 30);
        framey += 60;
        frame.getContentPane().add(nextbutton);

        frame.getContentPane().add(new JLabel());
        
        frame.setSize(600 + Main.ROOM, framey + Main.ROOM);
        
        show3();
    }

    private void show3() {
        frame.setVisible(true);

        synchronized(t) {
            try {
                t.wait();
            } catch (InterruptedException e) {}
        }

        check3();
    }

    private void check3() {
        for (int i = 0; i < 4; i++) {
            Main.frequencyID[i] = ruleBoxes[i].getSelectedIndex();
        }
    }


    
    public void start() {
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }
}
