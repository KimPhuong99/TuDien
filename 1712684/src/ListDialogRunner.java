import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * A 1.4 application that brings up a ListDialog.
 */
public class ListDialogRunner {
    static JFrame frame;
    static String[] names = {"Anh_Viet", "Viet_Anh"};

    public static JPanel createUI() {
        //Create the labels.
        JLabel intro = new JLabel("MY DICTIONARY:");
        final JLabel name = new JLabel(names[1]);
        intro.setLabelFor(name);

        //Use a wacky font if it exists. If not, this falls
        //back to a font we know exists.
        name.setFont(getAFont());

        //Create the button.
        final JButton button1 = new JButton("Thay đổi ngôn ngữ");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedName = ListDialog.showDialog(
                                        frame,
                                        button1,
                                        "Baby names ending in O:",
                                        "Name Chooser",
                                        names,
                                        name.getText(),
                                        "Cosmo  ");
                name.setText(selectedName);
            }
        });
        final JButton button2 = new JButton("Tra cứu từ");
        button2.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
                String selectedName = Tra_cuu.showDialog(
                                        frame,
                                        button2,
                                        "",
                                        "TRA CỨU",
                                        names,
                                        name.getText(),
                                        "Cosmo  ");
                name.setText(selectedName);
            }
        });
         final JButton button3 = new JButton("Thêm từ");
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedName = Them_Tu.showDialog(
                                        frame,
                                        button3,
                                        "",
                                        "Them tu",
                                        names,
                                        name.getText(),
                                        "Cosmo  ");
                name.setText(selectedName);
            }
        });
           final JButton button4 = new JButton("Danh sách từ yêu thích");
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedName = DS_Tu_Yeu_Thich.showDialog(
                                        frame,
                                        button4,
                                        "",
                                        "Danh sách từ yêu thích",
                                        names,
                                        name.getText(),
                                        "Cosmo  ");
                name.setText(selectedName);
            }
        });
        //Create the panel we'll return and set up the layout.
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,
                                      BoxLayout.PAGE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,10,20));
        intro.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        name.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        button1.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        button2.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        button3.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        button4.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        //Add the labels to the content pane.
        panel.add(intro);
        panel.add(Box.createVerticalStrut(10)); //extra space
        panel.add(name);

        //Add a vertical spacer that also guarantees us a minimum width:
        panel.add(Box.createRigidArea(new Dimension(150,50)));

        //Add the button.
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        return panel;
    }

    /**
     * Finds a cursive font to use, or falls back to using
     * an italic serif font.
     */
    protected static Font getAFont() {
        //initial strings of desired fonts
        String[] desiredFonts =
            {"French Script", "FrenchScript", "Script"};

        String[] existingFamilyNames = null; //installed fonts
        String fontName = null;        //font we'll use

        //Search for all installed font families.  The first
        //call may take a while on some systems with hundreds of
        //installed fonts, so if possible execute it in idle time,
        //and certainly not in a place that delays painting of
        //the UI (for example, when bringing up a menu).
        //
        //In systems with malformed fonts, this code might cause
        //serious problems; use the latest JRE in this case. (You'll
        //see the same problems if you use Swing's HTML support or
        //anything else that searches for all fonts.)  If this call
        //causes problems for you under the latest JRE, please let
        //us know:
        //http://java.sun.com/docs/books/tutorial/forms/sendusmail.html
        GraphicsEnvironment ge =
            GraphicsEnvironment.getLocalGraphicsEnvironment();
        if (ge != null) {
            existingFamilyNames = ge.getAvailableFontFamilyNames();
        }

        //See if there's one we like.
        if ((existingFamilyNames != null) && (desiredFonts != null)) {
            int i = 0;
            while ((fontName == null) && (i < desiredFonts.length)) {

                //Look for a font whose name starts with desiredFonts[i].
                int j = 0;
                while ((fontName == null) && (j < existingFamilyNames.length)) {
                    if (existingFamilyNames[j].startsWith(desiredFonts[i])) {

                        //We've found a match.  Test whether it can display 
                        //the Latin character 'A'.  (You might test for
                        //a different character if you're using a different
                        //language.)
                        Font f = new Font(existingFamilyNames[j],
                                          Font.PLAIN, 1);
                        if (f.canDisplay('A')) {
                            fontName = existingFamilyNames[j];
                            System.out.println("Using font: " + fontName);
                        }
                    }

                    j++; //Look at next existing font name.
                }
                i++;     //Look for next desired font.
            }
        }

        //Return a valid Font.
        if (fontName != null) {
            return new Font(fontName, Font.PLAIN, 36);
        } else {
            return new Font("Serif", Font.ITALIC, 36);
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        frame = new JFrame("MENU");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = createUI();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}

