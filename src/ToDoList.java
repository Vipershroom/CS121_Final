import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.util.*;
public class ToDoList extends JFrame implements ActionListener, TextListener, ItemListener {
    JLabel title2 = new JLabel("To Do List");
    JTextField input = new JTextField(32);
    JButton enter = new JButton("Enter");
    JButton clear = new JButton("Clear");
    JButton clearAll = new JButton("Clear All");
    JButton add1 = new JButton("+");
    JButton add2 = new JButton("+");
    ArrayList<String> ToDoList = new ArrayList<String>();
    ArrayList<String> ToDoList2 = new ArrayList<String>();
    ArrayList<String> ToDoList3 = new ArrayList<String>();
    public ToDoList() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 65, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JTextField textbox = new JTextField(32);
        mainPanel.add(genTodo(textbox, input, enter, clear, clearAll));
        JPanel new_panel = genTodo(new JTextField(32),new JTextField(32),new JButton("Enter"),new JButton("Clear"),new JButton("Clear All"));
        JPanel new_panel2 = genTodo(new JTextField(32),new JTextField(32),new JButton("Enter"),new JButton("Clear"),new JButton("Clear All"));
        new_panel.setVisible(false);
        new_panel2.setVisible(false);

        add1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add1.setVisible(false);
                new_panel.setVisible(true);
            }
        });

        add2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add2.setVisible(false);
                new_panel2.setVisible(true);
            }
        });
        mainPanel.add(add1);
        mainPanel.add(new_panel);
        mainPanel.add(add2);
        mainPanel.add(new_panel2);
        add(mainPanel);

        JPanel buttons = new JPanel();
        JButton save = new JButton("Save");

        HashMap<String, ArrayList<String>> listContents = new HashMap<String, ArrayList<String>>();


        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.setVisible(true);
                ArrayList<String> items = new ArrayList<String>();
                for (Component i: mainPanel.getComponents()) {
                    if (i instanceof JPanel && ((JPanel) i).getLayout() instanceof BorderLayout) {
                        for (Component k: ((JPanel) i).getComponents()) {

                            String title = "";
                            if (k instanceof JTextField) {
                                title = ((JTextField) k).getText();
                            }
                            if (k instanceof JPanel) {
                                for (Component m: ((JPanel) k).getComponents()) {
                                    if (m instanceof JPanel) {
                                        for (Component j: ((JPanel) m).getComponents()) {
                                            if (j instanceof JCheckBox) {
                                                System.out.println(((JCheckBox) j).getText());
                                                items.add(((JCheckBox) j).getText());
                                            }
                                        }
                                    }
                                }
                            }
                            System.out.println(items);

                            if (title.equals("")) {
                                title = Math.floor((Math.random() * (999999 - 100000))) + "";
                            }
                            listContents.remove(title);
                            listContents.put(title, items);
                            System.out.println(listContents);
                        }
                    }
                }
                HashMap<String, ArrayList<String>> temp = new HashMap<>();
                for (String i : listContents.keySet()) {
                    try {
                        Double.parseDouble(i);
                    } catch (NumberFormatException f) {
                        temp.put(i,listContents.get(i));
                    }
                }
                System.out.println(temp);
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showSaveDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {
                    File fi = fileChooser.getSelectedFile();
                    try {
                        FileWriter fw = new FileWriter(fi.getPath());
                        StringBuilder content = new StringBuilder();
                        for (String k : temp.keySet()) {
                            content.append(k);
                            content.append("[");
                            for (String i : temp.get(k)) {
                                content.append(i + " , ");
                            }
                            content.append("]");
                            content.append("\n");
                        }
                        fw.write(String.valueOf(content));
                        fw.flush();
                    } catch (Exception e2) {
                        JOptionPane.showMessageDialog(null, e2.getMessage());
                    }
                }
            }
        });
        buttons.add(save);
        mainPanel.add(buttons);

    }
    public JPanel genTodo(JTextField title, JTextField input, JButton enter, JButton clear, JButton clearAll) {
        final ArrayList<String>[] ToDoList = new ArrayList[]{new ArrayList<String>()};
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        title.setFont(new Font("Arial",Font.ITALIC, 18));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        mainPanel.add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.add(input);
        JPanel list = new JPanel();
        list.setLayout(new BoxLayout(list,BoxLayout.Y_AXIS));
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String text = input.getText();
                // Check for empty or whitespace characters
                if (!text.matches("^$") && !text.matches("^\s+$")) {
                    ToDoList[0].add(text);
                }
                input.setText("");
                list.removeAll();

                for (String i : ToDoList[0]) {
                    JCheckBox label = new JCheckBox("• " + i);
                    // grays out text if it is checked
                    label.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                            int select = e.getStateChange();
                            if (select == ItemEvent.SELECTED){
                                label.setForeground(Color.GRAY);
                            }
                            if (select == ItemEvent.DESELECTED){
                                label.setForeground(Color.BLACK);
                            }
                        }
                    });
                    label.setAlignmentX(Component.CENTER_ALIGNMENT);
                    label.setAlignmentX(Component.CENTER_ALIGNMENT);
                    list.add(label);

                }
                list.setAlignmentX(RIGHT_ALIGNMENT);
                setVisible(true);
            }
        });
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count = -1;
                for (Component i : list.getComponents()) {
                    if (i instanceof JCheckBox){
                        count++;
                        if (((JCheckBox) i).isSelected()){
                            i.setVisible(false);
                            list.remove(i);
                            ToDoList[0].remove(count);
                            count--;
                            System.out.println("removed");
                        }
                    }
                }
                list.setAlignmentX(RIGHT_ALIGNMENT);
            }
        });
        clearAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ToDoList[0] = new ArrayList<String>();
                list.removeAll();
                list.setVisible(false);
                list.setVisible(true);
                list.setAlignmentX(RIGHT_ALIGNMENT);
            }
        });
        inputPanel.add(enter);
        inputPanel.add(clear);
        inputPanel.add(clearAll);
        centerPanel.add(inputPanel, BorderLayout.NORTH);
        centerPanel.add(list, BorderLayout.CENTER);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        return (mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Hi");
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        System.out.println("aaaa");
    }

    @Override
    public void textValueChanged(TextEvent e) {

    }

    public static void main(String[] args) {
        final int FRAME_WIDTH = 640;
        final int FRAME_HEIGHT = 600;
        ToDoList i = new ToDoList();
        i.setSize(FRAME_WIDTH,FRAME_HEIGHT);
        i.setVisible(true);
    }
}