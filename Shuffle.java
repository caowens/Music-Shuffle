import java.util.*;

//This class shuffles the list of songs in an random order w/no song repeating and within the time limit
public class Shuffle {
    private ArrayList<String> music;;

    //Shuffle Constructor sets the list to arrayList
    public Shuffle(ArrayList<String> music_array){
        music = music_array;
    }//end of Shuffle contructor

    public String[] Songs(int time){
        String random_song = "";
        String result ="";

        // initialize the random method
        Random rand = new Random();

        int index;
        int count = 0;
        int len = 0;
        int len_song = music.size();
        int cal_time = len_song * 4;

        if (time < cal_time){ //13
            cal_time = time;
        }

        String[] song = new String[len_song];
        Set<String> hash_set = new HashSet<String>();

        while (cal_time>=4){
            index = rand.nextInt(len_song);
            // check if the song is in the hash set
            // the goal is to avoid repeating songs
            random_song = music.get(index);
            if (hash_set.contains(random_song) == false) {
                song[count] = random_song;

                // add the song into the hashmap
                hash_set.add(random_song);
                cal_time -= 4;
                // update the constraint
                count += 1;
                len += 4;
            }//end of if statement

        }//end of while loop
        return song;
    }//end of Songs

}//end of Shuffle class

 