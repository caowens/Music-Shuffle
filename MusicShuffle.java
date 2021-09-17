import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.PrivateKey;
import java.util.*;
import java.util.List;

//This class displays a gui that allows the user to recieve a shuffled playist based on the information they provide 
//Each song is 4 minutes long
public class MusicShuffle extends JFrame {
 //Instance Fields
  private final int WINDOW_WIDTH = 250;
  private final int WINDOW_HEIGHT = 350;

  //keeping up w/number of times the button is being clicked
  private static int count = 0;

  private JLabel timeDurationTitle;
  private JLabel playlistTitle;
  private JLabel minutesLabel;
  private JLabel playlistOutputLabel;
  private JLabel playlistNewLabel;

  private JButton shuffleButton;
  private JTextField timeTextField;
  private JScrollPane scrollPane;

  private List<String> selectedSongs;
  private JList <String> selectedValues, playlist;
  private JList playlistOutputList;

  private JPanel panel, panel2, panel3, panel4;

//======================================================

//MusicShuffle Constructor
  public MusicShuffle() {
    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    setTitle("Music Shuffle");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    buildPanel();

    setLayout(new BorderLayout());

    add(panel, BorderLayout.NORTH);
    add(panel2, BorderLayout.CENTER);
    add(panel3, BorderLayout.SOUTH);
    add(panel4, BorderLayout.EAST);
    pack();
    setVisible(true);
  }// End MusicShuffle Constructor

//=======================================================

//cosntructs the panels and the componenets needed to excute the task
   private void buildPanel() {
    //Time Duration panel
    panel = new JPanel();
    panel.setBackground(Color.BLACK);

    timeDurationTitle = new JLabel("Listening Time: ");
    timeDurationTitle.setForeground(Color.MAGENTA);
    timeTextField = new JTextField(3);
    timeTextField.setEditable(true);
    timeTextField.setBackground(Color.BLACK);
    timeTextField.setForeground(Color.WHITE);
    minutesLabel = new JLabel("minutes");
    minutesLabel.setForeground(Color.MAGENTA);

    panel.add(timeDurationTitle);
    panel.add(timeTextField);
    panel.add(minutesLabel);


   //Playlist Panel
    panel2 = new JPanel();
    panel2.setLayout(new BorderLayout());
    panel2.setBackground(Color.BLACK);

    // List of 20 Songs
    playlistTitle = new JLabel("My Playlist");
    playlistTitle.setForeground(Color.MAGENTA);
    String[] songs =  {"Montero by Lil Nas X",
                      "Rapstar by Polo G",
                      "Leave The Door Open by Bruno Mars",
                      "Peaches by Justin Bieber",
                      "Save Your Tears by The Weekend",
                      "Levitating by Dua Lipa",
                      "Kiss Me More by Doja Cat",
                      "Up by Cardi B",
                      "Drivers License by Olivia Rodrigo",
                      "Astronaut In The Ocean by Masked Wolf",
                      "Blinding Lights by The Weeknd",
                      "Beat Box by SpotemGottem",
                      "Calling My Phone by Lil Tjay",
                      "What You Know Bout Love by Pop Smoke",
                      "Deja Vu by Olivia Rodrigo",
                      "Ruff Ryders' Anthem by DMX",
                      "Best Friend by Saweetie",
                      "Mood by 24kGoldn",
                      "The Good Ones by Gabby Barrett",
                      "Back In Blood by Pooh Shiesty"};

    playlist = new JList<String>(songs);
    playlist.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    playlist.setVisibleRowCount(5);
    
    scrollPane = new JScrollPane(playlist);
    scrollPane.setBackground(Color.BLACK);
    playlist.setBackground(Color.BLACK);
    playlist.setForeground(Color.WHITE);
    playlist.setSelectionForeground(Color.MAGENTA);
    playlist.setSelectionBackground(Color.BLACK);
  
    panel2.add(playlistTitle, BorderLayout.NORTH);
    panel2.add(scrollPane, BorderLayout.CENTER);

   //Shuffle Results panel 
    panel3 = new JPanel();
    panel3.setBackground(Color.BLACK);

    shuffleButton = new JButton("Shuffle");
    shuffleButton.setForeground(Color.MAGENTA);
    shuffleButton.setBackground(Color.BLACK);
    shuffleButton.addActionListener(new ButtonClickListener());
 
   //Instruction labels for users
    playlistOutputLabel = new JLabel("Press for new playlist");
    playlistOutputLabel.setForeground(Color.MAGENTA);
    playlistNewLabel = new JLabel("Press again to start over");
    playlistNewLabel.setForeground(Color.MAGENTA);
    playlistNewLabel.setVisible(false);

    ImageIcon shuffleImg = new ImageIcon("newMusicImg.png");
    JLabel shuffleImgLabel = new JLabel(shuffleImg);

    panel3.add(shuffleButton);
    panel3.add(playlistOutputLabel);
    panel3.add(playlistNewLabel);

    panel4 = new JPanel();
    panel4.setBackground(Color.BLACK);

    panel4.add(shuffleImgLabel);
  }//end of buildPanel

//=======================================================

// class for listener
// what happens when the user clicks on a song
  private void shuffleSelectedItems(){

    //getting the provided information from the user
    selectedSongs =  playlist.getSelectedValuesList();
    int time = Integer.parseInt(timeTextField.getText());

    // declare the the Shuffle class and pass in the array of selected songs into the Constructor
    // the shuffle Constructor takes in ArrayList
    Shuffle shuffleSongs = new Shuffle((ArrayList<String>) selectedSongs);
  
    // create text field assign to shuffleSongs.Songs(time)
    String[] values = shuffleSongs.Songs(time); 
 
    playlistOutputList = new JList<String>(values);
    playlistOutputList.setBackground(Color.BLACK);
    playlistOutputList.setForeground(Color.WHITE);
    playlistOutputList.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
    playlistOutputLabel.setVisible(false);
    
    panel3.add(playlistOutputList);    
  }//end of shuffleSelctedItems

//=======================================================
  
//Excutes the desired and expected actions when shuffle is pressed
  private class ButtonClickListener implements ActionListener{
    public void actionPerformed(ActionEvent buttonClick){

      //Depending on the # of times the shuffle button has been clicked depends on if a output will be displayed or not
      if(count % 2 != 0){
        playlist.clearSelection();
        timeTextField.setText("");
        playlistOutputList.setVisible(false);
        playlistNewLabel.setVisible(false);
        playlistOutputLabel.setVisible(true);
      }
      else{
        playlistNewLabel.setVisible(true);
        shuffleSelectedItems();  
      } 
      count ++;      
    }//end of actionPerformed
  }//end of ButtonClickListener
    
}//end of MusicShuffle class
