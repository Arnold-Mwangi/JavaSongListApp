
// Name: Arnold Mwangi
// Date started: 27 April 2024
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter; 


class AppSong {

    // Apps Entry point


    public static void main(String [] args) throws Exception {

        try {

            String songList = getSongList(args);
            int numberOfSongs = countSongs(songList);

            System.out.printf("%n");

            System.out.println("Song List 1.0 - by Arnold Ward.");
            System.out.println(numberOfSongs + " songs Loaded.");

            System.out.print("\n");

            System.out.println("Menu: \n D = Display songs. \n A = Add new song \n C = complete a song \n Q = quit \n" );
            
            // User input Scanner object
            Scanner user_input = new Scanner(System.in);
            validateInput(user_input, numberOfSongs);

        } catch (Exception e) {
            System.out.print("An error occurred" + e.getMessage());
           
        }

    }

    // User input validation
    public static void validateInput(Scanner user_input, Integer numberOfSongs) throws Exception {
        System.out.print(">>>");
        String  command;

        while(true) {
            if(user_input.hasNext()) {
                command = user_input.next();
                switch(command.toLowerCase()) {
                    case "d":                
                        displayAllSongs(user_input, numberOfSongs);
                        return;
    
                    case "a":
                        addASong(user_input, numberOfSongs);
                        return;
    
                    case "c":
                        learnSong(numberOfSongs, user_input);
                    break;

                    // case "clear":
                    //     clearCmdInterface();
                    // break;
    
                    // case "q":
                    //     returnToMainMenu();
                    // break;
    
                    default:
                        System.out.println("Wrong user input, Try again: \n >>>");
    
                }
            }

            
        }
    } 
    
    
    // Learning a song
    public static void learnSong(Integer numberOfSongs, Scanner user_input) throws Exception {
        String songs = getSongList(null);

        String[] songsArray = null;
        songsArray = songs.split("\n");
        
        System.out.println("Enter the songs number to learn");
        Scanner songNumber = new Scanner(System.in);

        while(true) {          

            int num = songNumber.nextInt();
            if (num <= 0  || num > numberOfSongs) {
                System.out.println("Song does not exist!! \nTry Again:\n>>> ");
            } else{
                //  get selected song
                String selectedSong  = songsArray[num-1];
                // split song into title and artist
                String[] songParts = selectedSong.split(",");
                System.out.println("checking: " + songParts[3]);

                if (songParts.length == 4 && songParts[3].equals("u")) {
                    System.out.println("see if chnged" + songParts[3]);
                    songParts = new String[]{songParts[0], songParts[1], songParts[2], "l"};

                    System.out.println("see if chnged" + songParts[3]);

                    songsArray[num - 1] = String.join(",", songParts);
                    for (String song: songsArray){
                        System.out.println(song+"\n");
                    }
                    
                   BufferedWriter writer = new BufferedWriter(new FileWriter("songs.csv"));
                   for (String line : songsArray){
                    writer.write(line);
                    writer.newLine();
                   }
                   writer.close();
                } else{
                    System.out.println("An error occurred: Song already learned");
                }

            }
            
            // Print the menu again
            System.out.println("Menu: \n D = Display songs. \n A = Add new song \n C = complete a song \n Q = quit \n");

            // Call the validateInput method to handle user input
            validateInput(user_input, numberOfSongs);
        }
        
    }

    // Add a song
    public static void addASong(Scanner user_input, Integer numberOfSongs) throws Exception{
        Scanner newsongString = new Scanner(System.in);
        System.out.println("Enter new song details: \n format:  \n Title, Author, Year");
        
        while(true){
            String input = newsongString.nextLine().trim();
            String[] songDetails = input.split(",");
      
            if(songDetails.length == 3) {
                WriteToSongs(input, user_input);
            } else{
                System.out.println("Incorrect number of inputs! Try Again.\n Format: Title, Author, Year\n>>>");
                System.out.println("Wrong input format! Try again:\n>>>");
            }
            newsongString.close();
            // Print the menu again
            System.out.println("Menu: \n D = Display songs. \n A = Add new song \n C = complete a song \n Q = quit \n");

            // Call the validateInput method to handle user input
            validateInput(user_input, numberOfSongs);
        }

    }

    // method to write to songs.csv
    public static void WriteToSongs(String input, Scanner user_input) throws Exception {
        try {

            // Append unlearned flag to song
            input +=",u";

            BufferedWriter writer = new BufferedWriter(new FileWriter("songs.csv", true));

            writer.write(input);
            writer.newLine();
            writer.close();

            System.out.println("New song added");

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        
    }

    // Display all songs
    public static void displayAllSongs(Scanner user_input, Integer numberOfSongs) throws Exception {
        String songs = getSongList(null);
        if (songs == null || songs.length() <= 0) {
            throw new Exception("No song in the list!");
        } else {
            System.out.println("\n" + songs);
        }
        // Print the menu again
        System.out.println("Menu: \n D = Display songs. \n A = Add new song \n C = complete a song \n Q = quit \n");

        // Call the validateInput method to handle user input
        validateInput(user_input, numberOfSongs);
    }

    //  Metgod to read csv file
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

    // method to count number of songs
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
