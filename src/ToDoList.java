import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class ToDoList extends JFrame implements ActionListener, TextListener, ItemListener {
    JLabel title = new JLabel("To Do List");
    JLabel title2 = new JLabel("To Do List");
    JTextField input = new JTextField(32);
    JButton enter = new JButton("Enter");
    JButton clear = new JButton("Clear");
    JButton clearAll = new JButton("Clear All");
    ArrayList<String> ToDoList = new ArrayList<String>();
    public ToDoList() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                    ToDoList.add(text);
                }
                input.setText("");
                list.removeAll();

                for (String i : ToDoList) {
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
                            ToDoList.remove(count);
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
                for (Component i : list.getComponents()) {
                    if (i instanceof JCheckBox){
                        i.setVisible(false);
                        ToDoList.removeFirst();
                        list.remove(i);
                    }
                }
                list.setAlignmentX(RIGHT_ALIGNMENT);
            }
        });
        inputPanel.add(enter);
        inputPanel.add(clear);
        inputPanel.add(clearAll);
        centerPanel.add(inputPanel, BorderLayout.NORTH);
        centerPanel.add(list, BorderLayout.CENTER);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        add(mainPanel);

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