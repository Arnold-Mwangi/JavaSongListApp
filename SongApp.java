
// Name: Arnold Mwangi
// Date started: 27 April 2024
import java.io.File;
import java.util.Scanner;

class AppSong {
    public static void main(String [] args) throws Exception {

        try {

            String songList = getSongList(args);
            int numberOfSongs = countSongs(songList);

            System.out.printf("%15s", songList);
            System.out.printf("%n");

            System.out.println("Song List 1.0 - by Arnold Ward.");
            System.out.println(numberOfSongs + " songs Loaded.");

            System.out.print("\n");

            System.out.println("Menu: \n D = Display songs. \n A = Add new song \n C = complete a song \n Q = quit \n >>> This will be fun lol!!!" );

        } catch (Exception e) {
            System.out.print("An error occurred" + e.getMessage());
           
        }

    }

    public static String getSongList(String[] args) throws Exception{
        Scanner csvSongs = new Scanner(new File("songs.csv"));
        StringBuilder songList = new StringBuilder();
        csvSongs.useDelimiter(",");
        while(csvSongs.hasNextLine()){
            songList.append(csvSongs.nextLine()).append("\n");
        }

        csvSongs.close();
        return songList.toString();
    }

    public static int countSongs(String songList) {

        String[] songsArray = songList.split("\n");

        return songsArray.length;
        
    }
}
























// class SongApp {
//     public static void main(String[] args) {
     
//         try {
//             String songs = loadSongs(args);
//             int numOfSongs = countSongs(songs);
          
//             System.out.printf("%15s",songs);
//             System.out.println("\n");
//             System.out.print("Song List 1.0 - by Arnold Mwangi \n");
//             System.out.print(numOfSongs + " songs loaded. \n");
//             System.out.println("\n");
//         } catch (Exception e) {
//             System.out.println("An error occurred: " + e.getMessage());
//         }

//     }


//     public static String loadSongs(String[] args) throws Exception{
//         StringBuilder songList = new StringBuilder();
//         Scanner csv = new Scanner(new File("songs.csv"));
//         csv.useDelimiter(",");
//         while(csv.hasNextLine()){
//             songList.append(csv.nextLine()).append("\n");
//         }
//         csv.close();
//         return songList.toString();
//     }

//     public static int countSongs(String songs) {
//         String[] songsArray = songs.split("\n");
//         return songsArray.length;
//     }
// }
