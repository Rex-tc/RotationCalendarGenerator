package rotationcalendargenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class WriteFile {

    private File file;

    private void askForSaveLocation() {
        JFileChooser fc = new JFileChooser();
        JPanel panel = new JPanel();

        panel.setVisible(true);
        fc.setFileFilter(new FileNameExtensionFilter(".ics Files Only", "ics"));
        fc.setDialogType(JFileChooser.SAVE_DIALOG);
        fc.setSelectedFile(new File("TermSchedule.ics"));

        if (fc.showSaveDialog(panel) == JFileChooser.APPROVE_OPTION) {
            String fileName = fc.getSelectedFile().getAbsolutePath();

            if (!fileName.endsWith(".ics"))
                fileName += ".ics";
            
            file = new File(fileName);


        } else {
            JOptionPane.showMessageDialog(null, "Cancelled");
            System.exit(0);
        }
    }

    private void startWriting() {
        LocalDate startdate = Main.startdate;
        int rotation = 0, temp;
        String currentdate;
        boolean x = false;
        FileWriter writer;
        try {
            writer = new FileWriter(file);
            writer.write("BEGIN:VCALENDAR\nVERSION:2.0\nPRODID:Rex_tc_With_a_Laptop_Limited\n");
            for (int i = 0; i < Main.daystorepeat; i++) {
                for (int k = 0; k < Main.skipDates.size(); k++) {
                    if (Main.skipDates.get(k).equals(startdate.plusDays(i))) {
                        x = true;
                        continue;
                    }
                }
                if (x) {
                    x = false;
                    continue;
                }

                currentdate = startdate.plusDays(i).toString().replaceAll("-", "");

                temp = rotation;

                if (!Main.arrNames[temp].equals("")) 
                    if (Main.frequencyID[temp] == 0 || (Main.frequencyID[temp] == 1 && rotation % 2 == 0) || (Main.frequencyID[temp] == 2 && rotation % 2 == 1))
                        writer.write("BEGIN:VEVENT\nDTSTART;TZID=America/New_York:" + currentdate + "T081500\n" + "SUMMARY:" + Main.arrNames[temp] + "\nLOCATION:" + Main.arrLocations[temp] + "\nUID:" + UUID.randomUUID() + "\nDURATION:PT1H15M\nEND:VEVENT\n");

                temp = incrementRotation(temp);

                if (!Main.arrNames[temp].equals("")) 
                    if (Main.frequencyID[temp] == 0 || (Main.frequencyID[temp] == 1 && rotation % 2 == 0) || (Main.frequencyID[temp] == 2 && rotation % 2 == 1))
                        writer.write("BEGIN:VEVENT\nDTSTART;TZID=America/New_York:" + currentdate + "T102000\n" + "SUMMARY:" + Main.arrNames[temp] + "\nLOCATION:" + Main.arrLocations[temp] + "\nUID:" + UUID.randomUUID() + "\nDURATION:PT1H15M\nEND:VEVENT\n");

                temp = incrementRotation(temp);

                if (!Main.arrNames[temp].equals("")) 
                    if (Main.frequencyID[temp] == 0 || (Main.frequencyID[temp] == 1 && rotation % 2 == 0) || (Main.frequencyID[temp] == 2 && rotation % 2 == 1))
                        writer.write("BEGIN:VEVENT\nDTSTART;TZID=America/New_York:" + currentdate + "T123500\n" + "SUMMARY:" + Main.arrNames[temp] + "\nLOCATION:" + Main.arrLocations[temp] + "\nUID:" + UUID.randomUUID() + "\nDURATION:PT1H15M\nEND:VEVENT\n");
    
                temp = incrementRotation(temp);

                if (!Main.arrNames[temp].equals("")) 
                    if (Main.frequencyID[temp] == 0 || (Main.frequencyID[temp] == 1 && rotation % 2 == 0) || (Main.frequencyID[temp] == 2 && rotation % 2 == 1))
                        writer.write("BEGIN:VEVENT\nDTSTART;TZID=America/New_York:" + currentdate + "T140000\n" + "SUMMARY:" + Main.arrNames[temp] + "\nLOCATION:" + Main.arrLocations[temp] + "\nUID:" + UUID.randomUUID() + "\nDURATION:PT1H15M\nEND:VEVENT\n");
    


                rotation = incrementRotation(rotation);
            }
            writer.write("END:VCALENDAR");
            writer.close();
            System.exit(0);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "File Cannot be Written To", Main.TITLE, JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }   
    }

    private static int incrementRotation(int rotation) {
        return (rotation < 3)? rotation + 1 : 0;
    }

    public void start() {
        askForSaveLocation();
        startWriting();
    }
}
