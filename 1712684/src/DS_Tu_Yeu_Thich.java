import javax.swing.*;
import java.awt.*;
import static java.awt.Component.LEFT_ALIGNMENT;
import java.awt.event.*;
import java.util.Scanner;
import vn.viettuts.javaxml.DOMCreateXMLExample;
import vn.viettuts.javaxml.DOMExample;
import vn.viettuts.javaxml.Student;
public class DS_Tu_Yeu_Thich extends JDialog
                        implements ActionListener {
    private static DS_Tu_Yeu_Thich dialog;
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
        dialog = new DS_Tu_Yeu_Thich(frame,
                                locationComp,
                                labelText,
                                title,
                                possibleValues,
                                initialValue,
                                longValue);
        dialog.setVisible(true);
        return value;
    }
    private DS_Tu_Yeu_Thich(Frame frame,
                       Component locationComp,
                       String labelText,
                       String title,
                       Object[] data,
                       String initialValue,
                       String longValue) {
        super(frame, title, true);
        value=initialValue;
        //Create and initialize the buttons.
        
        JButton cancelButton = new JButton("Thoat");
        cancelButton.addActionListener(this);
        JTextField label = new JTextField(labelText);
        JTextArea label1 = new JTextArea();
        label1.setRows(25); 
        label1.setColumns(25); 
        label1.setWrapStyleWord(true); 
        JScrollPane sp = new JScrollPane(label1);
        sp.setBounds(10,60,780,500); 
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
         JButton loveButton = new JButton("A-Z");
         loveButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
              
              DOMExample DOM=new DOMExample();
              java.util.List<Student> listStudents = DOM.readListStudents("Tu_Yeu_Thich.xml");
              for(int i=0;i<listStudents.size();i++)
                  for(int j=i+1;j<listStudents.size()-1;j++)
                      if(listStudents.get(i).getTu().compareTo(listStudents.get(j).getTu())>0)
                      {
                          Student A=listStudents.get(i);
                          listStudents.set(i, listStudents.get(j));
                          listStudents.set(j, A);   
                      }
              for(Student student: listStudents)
              {
                 label1.setText(label1.getText().trim()+student.toString()+"\n");
              }
             
              }
        });
        
        
        
        final JButton setButton = new JButton("Z-A");
        setButton.setActionCommand("Z-A");
        setButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
                DOMExample DOM=new DOMExample();
              java.util.List<Student> listStudents = DOM.readListStudents("Tu_Yeu_Thich.xml");
              for(int i=0;i<listStudents.size();i++)
                  for(int j=i+1;j<listStudents.size()-1;j++)
                      if(listStudents.get(i).getTu().compareTo(listStudents.get(j).getTu())<0)
                      {
                          Student A=listStudents.get(i);
                          listStudents.set(i, listStudents.get(j));
                          listStudents.set(j, A);   
                      }
              for(Student student: listStudents)
              {
                 label1.setText(label1.getText().trim()+student.toString()+"\n");
              }
            }
        });
        getRootPane().setDefaultButton(setButton);

        //main part of the dialog

        //Create a container so that we can add a title around
        //the scroll pane.  Can't add a title directly to the
        //scroll pane because its background would be white.
        //Lay out the label and scroll pane from top to bottom.
        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
        
        //listPane.add(label);
        //listPane.add(Box.createVerticalStrut(10));
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
            DS_Tu_Yeu_Thich.value = getValue();
        }
        DS_Tu_Yeu_Thich.dialog.setVisible(false);
    }
}

