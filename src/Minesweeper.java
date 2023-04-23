import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import sweeper.Box;
import sweeper.Coordinates;
import sweeper.Game;
import sweeper.Ranges;

public class Minesweeper extends JFrame {
    // added music to the game
    File audioFile = new File("C:\\Users\\zhana\\IdeaProjects\\test\\src\\music_game.wav");
    AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
    AudioFormat format = audioStream.getFormat();
    DataLine.Info info = new DataLine.Info(Clip.class, format);
    Clip audioClip = (Clip) AudioSystem.getLine(info);

    private Game game;
    private JPanel jpanel;
    private JLabel jlabel;

    private final int COLUMNS = 9;
    private final int ROWS = 9;
    private final int BOMBS = 10;
    private final int IMAGE_SIZE = 50;

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        new Minesweeper();
    }
    private Minesweeper() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        game = new Game(COLUMNS, ROWS, BOMBS);
        game.init();
        setImages();
        initLabel();
        initPanel();
        initFrame();
    }

    private void initFrame() throws LineUnavailableException, IOException {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Naziya's game");
        setResizable(false);
        setVisible(true);
        pack();
        audioClip.open(audioStream);
        audioClip.start();
        setLocationRelativeTo(null);
        setIconImage(getImage("main_icon"));
    }

    private void initLabel(){
        jlabel = new JLabel("Welcome to the game!", JLabel.CENTER);
        jlabel.setFont(new Font("Open Sans", Font.CENTER_BASELINE,20));
        add(jlabel, BorderLayout.NORTH);
    }

    private void initPanel(){
        jpanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                for(Coordinates c : Ranges.getAll_coordinates()){
                    g.drawImage((Image)game.getBox(c).image,
                            c.x * IMAGE_SIZE, c.y * IMAGE_SIZE,this);
                }
        }
        };
        jpanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coordinates c = new Coordinates(x, y);
                if(e.getButton() == MouseEvent.BUTTON1){
                    game.pressLeftButton(c);
                }
                if(e.getButton() == MouseEvent.BUTTON2){
                    game.init();
                }
                if(e.getButton() == MouseEvent.BUTTON3){
                    game.pressRightButton(c);
                }
                try {
                    jlabel.setText(getText());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                jpanel.repaint();
            }
        });
        jpanel.setPreferredSize(new Dimension(
                Ranges.getSize().x * IMAGE_SIZE,
                Ranges.getSize().y * IMAGE_SIZE));
        add(jpanel);
    }
    private String getText() throws IOException {
        switch (game.getGameState()){
            case PLAYED -> {
                return "Keep playing";
            }
            case BOMBED -> {
                return "You've lost. Click to try again";
            }
            case WINNER -> {
                return "Congratulations! You won!";
            }
        }
        return null;
    }
    private void setImages(){
        for(Box box : Box.values()){
            box.image = getImage(box.name().toLowerCase());
        }
    }
    private Image getImage(String name){
        String filename = "img/" + name + ".png";
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(filename));
        return imageIcon.getImage();
    }
}
