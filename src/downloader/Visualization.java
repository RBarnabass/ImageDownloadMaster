package downloader;

import javax.swing.*;
import java.awt.*;

public class Visualization extends JFrame {

    JTextField text = new JTextField("https://github.com",20);
    JButton button = new JButton("Start");
    JLabel label = new JLabel();
    ImageSearcher imageSearcher = new ImageSearcher();
    IOEngine ioEngine = new IOEngine();

    Visualization() {

        setTitle(" Image Download Master ");
        setVisible(true);
        setSize(400, 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        text.addActionListener((event) -> text.getText());

        button.addActionListener((event) -> {
            String str = text.getText();
            imageSearcher.getImage(ioEngine.downLoadPicture(str));
            label.setText("Downloading was successfully done");
        });

        setLayout(new FlowLayout());
        add(text);
        add(button);
        add(label);
    }
}

