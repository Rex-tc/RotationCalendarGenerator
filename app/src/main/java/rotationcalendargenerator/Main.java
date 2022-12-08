/**
 * If you were expecting nice looking code, look away
 * I don't know what you are expecting, but it's probably wrong
 */
package rotationcalendargenerator;

import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentListener;
import java.awt.BorderLayout;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main implements ActionListener, ComponentListener {
    //TODO: VERSION OF THIS PIECE OF CRAP
    public static final String VERSION = "2.0.3";

    public static final String[] CHANGELOG = {
        "removed Main.getVersion()"
    };

    public static final int ROOM = 20;


    public static final String TITLE = "Rotation Calendar Generator";

    public static String[] arrNames, arrLocations;
    public static int daystorepeat;
    public static ArrayList<LocalDate> skipDates = new ArrayList<>();
    public static int[] frequencyID = new int[4];
    public static LocalDate startdate;

    
    public static void main(String[] args) {
        arrNames = new String[4];
        arrLocations = new String[4];

        new Main().make();
    }

    private JFrame frame = new JFrame("insert title here");
    private JLabel mainLabel = new JLabel("Rotation Calendar Generator Thing");
    private JLabel howLabel = new JLabel("<html><h1>How do i use this thing?</h1><br />so you enter in all the data that it asks you (leave the arrangement name blank if it's free), then you open the .ics file it generates.<br />it will import the schedule to your calendar<br /><br /><b>Disclaimer</b><br />This tool will only generate a .ics file for <i>the main academical schedule</i>.<br />I have no clubs so i don't know how those work, and for the others like<br />collection, meeting for worship, and assembly, they follow simple repeating<br />patterns and so is easy to just add as a repeating event in your calendar app<br /><br />This tool mainly tackles the inconvenience that is the rotating term schedule.<br />This also <b>DOES NOT GENERATE REMINDERS</b>, that is impossible with .ics files and i don't feel like coding a plugin and/or learning a new language to interface with iCal or Outlook<br /><br />Also, if you have a class that you only have to go to on even or odd rotation days, this has that option, but<br /><b>If you have two classes in the same arrangement</b> with one being odd rotation and one being even rotation, you will have to generate the .ics file twice, once with one class and your other normal arrangements, one with the other(with the rotation options reversed obviously).<br />i've only seen this type of weird schedule once, and it was a system error, but here's how to do it anyways</html>");

    private JLabel aboutLabel = new JLabel("<html><h1>About</h1><br />Hi, i'm Rex_tc<br />I got tired of having to manually add the term schedule to my calendar<br />You will never know what the \"tc\" part stands for bc i forgot too</html>");

    private JButton startButton = new JButton("Start");
    private JButton howButton = new JButton("How?");
    private JButton aboutButton = new JButton("About");
    private JButton backButton = new JButton("Back");

    private JLabel versionLabel = new JLabel("Ver: " + Main.VERSION);
    private JButton changelogButton = new JButton("Changelog");
    private JLabel changelogLabel = new JLabel();

    private JLabel dud = new JLabel("");

    private void make() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startButton.addActionListener(this);
        howButton.addActionListener(this);
        aboutButton.addActionListener(this);
        backButton.addActionListener(this);
        changelogButton.addActionListener(this);

        frame.addComponentListener(this);
        frame.setResizable(false);

        String temp = "<html><h1>Changelog</h1>" + Main.VERSION;
        for (int i = 0; i < Main.CHANGELOG.length; i++) {
            temp += "<br />•" + Main.CHANGELOG[i];
        }
        temp += "</html>";

        changelogLabel.setText(temp);

        mainLabel.setBounds(0, 0, 250, 30);
        startButton.setBounds(50, 30, 100, 30);
        howButton.setBounds(50, 60, 100, 30);
        aboutButton.setBounds(50, 90, 100, 30);

        howLabel.setBounds(0, 0, 500, 450);

        aboutLabel.setBounds(0, 0, 400, 200);

        changelogButton.setBounds(0, 200, 100, 30);


        versionLabel.setBounds(100, 200, 100, 30);

        changelogLabel.setBounds(0, 0, 400, Main.CHANGELOG.length * 40 + 50);


        mainMenu();
        frame.setVisible(true);
    }

    private void mainMenu() {
        frame.getContentPane().removeAll();


        frame.getContentPane().add(mainLabel);
        frame.getContentPane().add(startButton);
        frame.getContentPane().add(howButton);
        frame.getContentPane().add(aboutButton);
        frame.getContentPane().add(dud);

        frame.setSize(230 + Main.ROOM, 160 + Main.ROOM);
    }

    private void howMenu() {
        frame.getContentPane().removeAll();

        backButton.setBounds(400, 430, 100, 30);
        frame.getContentPane().add(howLabel);
        frame.getContentPane().add(backButton);
        frame.getContentPane().add(dud);

        frame.setSize(500 + Main.ROOM, 500 + Main.ROOM);
    }

    private void aboutMenu() {
        frame.getContentPane().removeAll();
        backButton.setBounds(300, 200, 100, 30);
        
        frame.getContentPane().add(aboutLabel);
        frame.getContentPane().add(versionLabel);
        frame.getContentPane().add(backButton);
        frame.getContentPane().add(changelogButton);
        frame.getContentPane().add(dud);

        frame.setSize(400 + Main.ROOM, 300 + Main.ROOM);
    }

    private void changelogMenu() {
        frame.getContentPane().removeAll();

        frame.getContentPane().add(backButton, BorderLayout.PAGE_END);

        frame.getContentPane().add(changelogLabel);
        frame.getContentPane().add(dud);

        frame.setResizable(true);
        frame.setSize(400 + Main.ROOM, Main.CHANGELOG.length * 30 + 140 + Main.ROOM);

    }


    private void start() {
        frame.dispose();
        UserInput input = new UserInput("input");
        input.start();
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setResizable(false);

        if (e.getSource() == startButton)
            start();
        if (e.getSource() == backButton)
            mainMenu();
        if (e.getSource() == howButton)
            howMenu();
        if (e.getSource() == aboutButton)
            aboutMenu();
        if (e.getSource() == changelogButton)
            changelogMenu();
    }

    @Override
    public void componentResized(ComponentEvent e) {
        changelogLabel.setBounds(0, 0, e.getComponent().getWidth() - ROOM, e.getComponent().getHeight() - ROOM); 
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        
    }

    @Override
    public void componentShown(ComponentEvent e) {
        
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        
    }
}
