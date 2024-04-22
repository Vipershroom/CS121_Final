import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ToDoList extends JFrame implements ActionListener, TextListener, ItemListener {
    JLabel title = new JLabel("To Do List");
    JTextField input = new JTextField(32);
    public ToDoList() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());


        title.setFont(new Font("Arial",Font.ITALIC, 18));

        add(title);
        add(input);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

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