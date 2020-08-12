
import javax.swing.*;
import java.awt.*;
import static java.awt.Component.LEFT_ALIGNMENT;
import java.awt.event.*;
import java.util.Scanner;
import vn.viettuts.javaxml.DOMCreateXMLExample;
import vn.viettuts.javaxml.DOMExample;
import vn.viettuts.javaxml.Student;
public class Tra_cuu extends JDialog
                        implements ActionListener {
    private static Tra_cuu dialog;
    private static String value = "";
    

    /**
     * Set up and show the dialog.  The first Component argument
     * determines which frame the dialog depends on; it should be
     * a component in the dialog's controlling frame. The second
     * Component argument should be null if you want the dialog
     * to come up with its left corner in the center of the screen;
     * otherwise, it should be the component on top of which the
     * dialog should appear.
     */
    public static String showDialog(Component frameComp,
                                    Component locationComp,
                                    String labelText,
                                    String title,
                                    String[] possibleValues,
                                    String initialValue,
                                    String longValue) {
        Frame frame = JOptionPane.getFrameForComponent(frameComp);
        dialog = new Tra_cuu(frame,
                                locationComp,
                                labelText,
                                title,
                                possibleValues,
                                initialValue,
                                longValue);
        dialog.setVisible(true);
        return value;
    }
    private Tra_cuu(Frame frame,
                       Component locationComp,
                       String labelText,
                       String title,
                       Object[] data,
                       String initialValue,
                       String longValue) {
        super(frame, title, true);
        value=initialValue;
        //Create and initialize the buttons.
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        JTextField label = new JTextField(labelText);
        JTextArea label1 = new JTextArea();
         JButton loveButton = new JButton("Love");
         loveButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
               String textw = label.getText().toString(); 
              String text = label1.getText().toString();
              if(textw.length()!=0 && text.length()!=0){
              DOMExample DOM=new DOMExample();
              java.util.List<Student> listStudents = DOM.readListStudents("Tu_Yeu_Thich.xml");
              Student A=new Student();
              A.setTu(textw);
              A.setNghia(text);
              listStudents.add(A);
              DOMCreateXMLExample DOME=new DOMCreateXMLExample();
              if(DOME.writeListStudents(listStudents,"Tu_Yeu_Thich.xml")==1)
                  System.out.print("done");
             }
              }
        });
        
        
        
        final JButton setButton = new JButton("OK");
        setButton.setActionCommand("OK");
        setButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
              
              String textw = label.getText().toString(); 
              if(textw.length()!=0){
                 
              DOMExample DOM=new DOMExample();
              java.util.List<Student> listStudents = DOM.readListStudents(initialValue+".xml");
              for (Student student : listStudents) {
                if(textw.compareTo(student.getTu())==0)
                    {
                        label1.setText(student.toString());
                        break;
                    }
                 }

            }}
        });
        getRootPane().setDefaultButton(setButton);

        //main part of the dialog

        //Create a container so that we can add a title around
        //the scroll pane.  Can't add a title directly to the
        //scroll pane because its background would be white.
        //Lay out the label and scroll pane from top to bottom.
        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
        
        listPane.add(label);
        listPane.add(Box.createVerticalStrut(10));
        listPane.add(label1);
        listPane.add(Box.createRigidArea(new Dimension(0,5)));
        listPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        //Lay out the buttons from left to right.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(cancelButton);
        buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPane.add(setButton);
        buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPane.add(loveButton);
        //Put everything together, using the content pane's BorderLayout.
        Container contentPane = getContentPane();
        contentPane.add(listPane, BorderLayout.CENTER);
        contentPane.add(buttonPane, BorderLayout.PAGE_END);

        //Initialize values.
        pack();
        setLocationRelativeTo(locationComp);
    }
    public String getValue()
    {
        return value;
    }
    //Handle clicks on the Set and Cancel buttons.
    public void actionPerformed(ActionEvent e) {
        if ("Set".equals(e.getActionCommand())) {
            Tra_cuu.value = getValue();
        }
        Tra_cuu.dialog.setVisible(false);
    }
}
