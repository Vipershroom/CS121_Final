import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class ToDoList extends JFrame implements ActionListener, TextListener, ItemListener {
    JLabel title = new JLabel("To Do List");
    JLabel title2 = new JLabel("To Do List");
    JTextField input = new JTextField(32);
    JButton enter = new JButton("Enter");
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
                    label.setAlignmentX(Component.CENTER_ALIGNMENT);
                    label.setAlignmentX(Component.CENTER_ALIGNMENT);
                    list.add(label);

                }
                list.setAlignmentX(RIGHT_ALIGNMENT);
                setVisible(true);
            }
        });
        inputPanel.add(enter);
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

    }

    @Override
    public void textValueChanged(TextEvent e) {

    }

    public static void main(String[] args) {
        final int FRAME_WIDTH = 500;
        final int FRAME_HEIGHT = 600;
        ToDoList i = new ToDoList();
        i.setSize(FRAME_WIDTH,FRAME_HEIGHT);
        i.setVisible(true);
    }
}