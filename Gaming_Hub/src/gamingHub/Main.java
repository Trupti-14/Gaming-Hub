package gamingHub ; 
import java.util.*; 
 
class Player  
{ 
    String Username; 
    String EmailID; 
    String Password; 
    long phoneNo; 
    int score; 
     
   //ArrayList to store Players information. 
    static ArrayList<Player> PlayerInformation = new ArrayList<>(); 
    static Scanner sc = new Scanner(System.in); 
 
    // Constructor Overloading. 
    Player() 
    { 
         
    } 
     
    Player(String Username, String EmailID, String Password)  
    { 
        this.Username = Username; 
        this.EmailID = EmailID; 
        this.Password = Password; 
        this.score = 0; 
    } 
 
    Player(String Username, long phoneNo, String Password)  
    { 
        this.Username = Username; 
        this.phoneNo = phoneNo; 
        this.Password = Password; 
        this.score = 0; 
    } 
 
    //Method to increase score as player wins games. 
      void increaseScore(int points)  
    { 
        score += points; 
    } 
 
    //Methods to show rewards. 
    void showReward()  
    { 
        String reward; 
        if (score >= 20) reward = "\ud83c\udfc6 Legend"; 
        else if (score >= 15) reward = "\ud83e\udd47 Gold Player"; 
        else if (score >= 10) reward = "\ud83e\udd48 Silver Player"; 
        else if (score >= 5) reward = "\ud83e\udd49 Bronze Player"; 
        else reward = "\ud83d\udd30 Beginner"; 
        System.out.println("Reward: " + reward); 
    } 
     
    //Method to take Information of player. 
    void settings()  
    { 
            System.out.println("CREATE YOUR PROFILE\n"); 
 
            int method = 0; 
            while (true)  
            { 
                try { 
                    System.out.println("Create account using : \n1] EmailID.  \n2] Phone Number."); 
                    System.out.print("Enter Here : "); 
                    method = sc.nextInt(); 
 
                    if (method == 1 || method == 2)  
                    { 
                        break; 
                    }  
                    else  
                    { 
                        System.out.println("\nInvalid choice. Please enter 1 or 2."); 
                    } 
                }  
                catch (InputMismatchException e)  
                { 
                    System.out.println("Invalid input! Please enter numeric digits only.\n"); 
                    sc.nextLine(); // clear the invalid input 
                } 
            } 
             
            if (method == 1)  
            { 
                System.out.print("\nEnter your Email ID: "); 
                EmailID = sc.next(); 
            }  
            else  
            { 
                while (true)  
                { 
                    try  
                    { 
                        System.out.print("\nEnter your Phone Number: "); 
                        phoneNo = sc.nextLong(); 
 
                        if (phoneNo >= 1000000000L && phoneNo <= 9999999999L)  
                        { 
                            break; // Valid 10-digit number 
                        }  
                        else  
                        { 
                            System.out.println("Phone number must be exactly 10 digits. Try again."); 
                        } 
                    }  
                    catch (InputMismatchException e)  
                    { 
                        System.out.println("Invalid input! Please enter numeric digits only."); 
                        sc.nextLine();  
                    } 
                } 
            }      
         
 
        //String password; 
        while (true)  
        { 
            System.out.print("\nSet Password : "); 
            Password = sc.next(); 
            System.out.print("\nConfirm Password : "); 
            String password = sc.next(); 
            if (Password.equals(password)) 
            { 
                break; 
            } 
             
            System.out.println("Passwords do not match! Try again."); 
        } 
 
        System.out.print("\nSet Username: "); 
        Username = sc.next(); 
 
        if (method == 1) 
        { 
            PlayerInformation.add(new Player(Username, EmailID , Password)); // Email 
        } 
        else 
        { 
            PlayerInformation.add(new Player(Username, phoneNo, Password)); // Phone 
        } 
         
        System.out.println("\nAccount created successfully!"); 
  
    } 
    
    //Display profile of player. 
    void showProfile()  
    { 
        System.out.println("------------------------------"); 
        System.out.println("Username: " + Username); 
        System.out.println("Score: " + score); 
        showReward(); 
        System.out.println("------------------------------"); 
    } 
} 
 
//Created class to display personal information of player. 
class Storage extends Player 
{ 
    Storage() 
    { 
        super(); 
    } 
     
    @Override 
    protected void settings() 
    { 
        for( Player p : PlayerInformation) 
        { 
        System.out.println("------------------------------"); 
        System.out.println("USER DATA STORAGE : "); 
        System.out.println("------------------------------"); 
        System.out.println(); 
         
         
        System.out.println("EmailID : "+p.EmailID); 
        System.out.println("phoneNo : "+p.phoneNo); 
         
             
        System.out.println("Password : "+p.Password); 
        System.out.println("Username : "+p.Username); 
        System.out.println("------------------------------"); 
        } 
    } 
} 
 
interface Game 
{ 
    public void Play(Player player); 
} 
 
//Code implementation of Stone Paper Scissors. 
class StonePaperScissors implements Game  
 { 
    Scanner scanner = new Scanner(System.in); 
  
    //Abstract method implementation. 
    public void Play(Player player)  
    { 
        System.out.println("\nPlaying Stone Paper Scissors...🪨 📜 ✂"); 
 
        for (int i = 0; i < 3; i++) 
        { 
            System.out.print("\nEnter your choice (Stone 🪨, Paper 📜, Scissors ✂): "); 
            String userChoice = scanner.next(); 
            String[] options = {"Stone", "Paper", "Scissors"};//Array. 
 
            if (!userChoice.equalsIgnoreCase("Stone") && !userChoice.equalsIgnoreCase("Paper") && !userChoice.equalsIgnoreCase("Scissors"))  
            { 
                System.out.println("Invalid input! Please choose Stone, Paper, or Scissors."); 
                i--; // to not count this as a valid turn 
                continue; 
            } 
 
            String computerChoice = options[new Random().nextInt(3)];//To generate random number out of 3. 
            System.out.println("Computer choose: " + computerChoice); 
             
            if (userChoice.equalsIgnoreCase(computerChoice))  
            { 
                System.out.println("It's a tie :|"); 
            }  
            else if ((userChoice.equalsIgnoreCase("Stone") && computerChoice.equals("Scissors")) || 
                       (userChoice.equalsIgnoreCase("Paper") && computerChoice.equals("Stone")) || 
                       (userChoice.equalsIgnoreCase("Scissors") && computerChoice.equals("Paper")))  
            { 
                System.out.println("You win! 😊"); 
                player.increaseScore(2); 
            }  
            else  
            { 
                System.out.println("You lose 😞"); 
            } 
        } 
    } 
 
} 
   
//Code implementation of Tic-Tac-Toe. 
 
// TicTacToe game class implementing the Game interface 
class TicTacToe implements Game { 
     
    Scanner scanner = new Scanner(System.in); 
    char[][] board;          // 2D array to represent the game board 
    char playerMark, AIMark; // Variables to store player's and computer's marks (X or O) 
 
    // Constructor to initialize the game board 
    public TicTacToe() { 
        board = new char[3][3]; 
        initBoard(); // Fill the board with empty spaces 
    } 
 
    // Main method to start and play the TicTacToe game 
    public void Play(Player player) 
     { 
        getPlayerMark(); // Ask the player to choose X or O 
 
        for (int turn = 0; turn < 9; turn++) { // Max 9 moves in a TicTacToe game 
            if (turn % 2 == 0) { 
                System.out.println("\nYour Turn:"); 
                playerMove(); // Player plays on even turns 
            } else { 
                System.out.println("\nComputer's Turn:"); 
                getAIMove(); // Computer plays on odd turns 
            } 
 
            displayBoard(); // Show the board after each move 
 
            // Check if someone has won after every move 
            if (checkRowWin() || checkcolWin() || checkdiagWin()) { 
                System.out.println(); 
                if (turn % 2 == 0) { 
                    System.out.println("🎉 Boom! You outsmarted the computer 🔥"); 
                    player.increaseScore(2); 
                } else { 
                    System.out.println("💔 Oops! The computer flexed its virtual muscles this time. 💪"); 
                } 
                return; // End the game after a win 
            } 
        } 
 
        // If all 9 turns are played and no one wins, it's a draw 
        System.out.println("It's a draw! 🤝"); 
    } 
 
    // Method to fill the board with empty spaces initially 
    void initBoard() { 
        for (int i = 0; i < 3; i++) 
            for (int j = 0; j < 3; j++) 
                board[i][j] = ' '; 
    } 
 
    // Method to print the current state of the game board 
    void displayBoard() { 
        System.out.println("+---+---+---+"); 
        for (int i = 0; i < 3; i++) { 
            System.out.print("| "); 
            for (int j = 0; j < 3; j++) { 
                System.out.print(board[i][j] + " | "); 
            } 
            System.out.println(); 
            System.out.println("+---+---+---+"); 
        } 
    } 
 
    // Method to check if any column has all same marks (win condition) 
    boolean checkcolWin() { 
        for (int j = 0; j < 3; j++) 
            if (board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != ' ') 
                return true; 
        return false; 
    } 
 
    // Method to check if any row has all same marks (win condition) 
    boolean checkRowWin() { 
        for (int i = 0; i < 3; i++) 
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') 
                return true; 
        return false; 
    } 
 
    // Method to check if diagonals have all same marks (win condition) 
    boolean checkdiagWin() { 
        return (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') || 
               (board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[2][0] != ' '); } 
 
    // Places the mark (X or O) at the specified row and column 
    void placeMark(int row, int column, char mark) { 
        try { 
            if (row < 0 || row > 2 || column < 0 || column > 2) 
                throw new IllegalArgumentException("Invalid position! Row and column must be between 0 and 2."); 
 
            if (board[row][column] != ' ') 
                throw new IllegalArgumentException("This position is already taken! Try a different one."); 
 
            board[row][column] = mark; // Place the mark on board 
 
        } catch (IllegalArgumentException e) { 
            System.out.println("❌ Error: " + e.getMessage()); 
        } 
    } 
 
    // Ask the user to choose X or O and assign opposite mark to computer 
    void getPlayerMark() { 
        System.out.print("Choose your mark from (X/O): "); 
        playerMark = scanner.nextLine().toUpperCase().charAt(0); 
 
        if (playerMark == 'X') 
            AIMark = 'O'; 
        else if (playerMark == 'O') 
            AIMark = 'X'; 
        else { 
            System.out.println("Invalid sign chosen!! Defaulting to X."); 
            playerMark = 'X'; 
            AIMark = 'O'; 
        } 
 
        System.out.println("You chose : " + playerMark); 
    } 
 
    // Takes input from player for row and column and places the mark if valid 
    void playerMove() { 
        int row, column; 
        do { 
            System.out.print("Enter row and column (0-2): "); 
            row = scanner.nextInt(); 
            column = scanner.nextInt(); 
            System.out.println(); 
        } while (!isValidMove(row, column)); 
 
        placeMark(row, column, playerMark); // Place player's mark on board 
    } 
 
    // Generates random valid move for the computer and places its mark 
    void getAIMove() { 
        Random r = new Random(); 
        int row, col; 
        do { 
            row = r.nextInt(3); 
            col = r.nextInt(3); 
        } while (!isValidMove(row, col)); 
 
        System.out.println("Computer chose position: " + row + " " + col + "\n"); 
        placeMark(row, col, AIMark); // Place computer's mark on board 
    } 
 
    // Checks if the move is valid (i.e., within board and not already taken) 
    boolean isValidMove(int row, int column) { 
        return row >= 0 && row < 3 && column >= 0 && column < 3 && board[row][column] == ' '; 
    } 
} 
 
  class WordScramble implements Game 
  { 
         Scanner scanner = new Scanner(System.in); 
          
        public void Play(Player player)  
        { 
            System.out.println("\nPlaying Word Scramble..."); 
             
            String[] words = {"apple", "banana", "computer", "java", "gamer"}; 
            String word = words[new Random().nextInt(words.length)]; 
             
            List<Character> chars = new ArrayList<>(); 
             
            for (char c : word.toCharArray())  
            { 
                chars.add(c); 
            } 
             
            Collections.shuffle(chars);// 
 
            System.out.print("\nGuess the word: "); 
            for (char c : chars)  
            { 
            System.out.print(c); 
            } 
             
            System.out.println(); 
 
            Scanner scanner = new Scanner(System.in); 
            System.out.print("Your guess: "); 
            String guess = scanner.nextLine(); 
 
            if (guess.equalsIgnoreCase(word))  
            { 
                System.out.println("Correct Answer ✅ "); 
                player.increaseScore(2); 
            }  
            else  
            { 
                System.out.println("Wrong Answer ❌ \nThe word was: " + word); 
            } 
        } 
    } 
   
//Code implementation of Hangman. 
  class Hangman implements Game  
  { 
        final String[] WORDS = {"elephant", "notebook", "science", "football"}; 
        final int MAX_LIVES = 6;//Number of chances given to player. 
        Scanner scanner = new Scanner(System.in); 
         
      //Abstract method implementation. 
        public void Play(Player player)  
        { 
            System.out.println("\n=== HANGMAN ==="); 
             
            String word = WORDS[new Random().nextInt(WORDS.length)];//Choosing random word from above array. 
             
            char[] guessedLetters = new char[word.length()];//Stored length of choosed letter. 
             
            // Initialize guessedLetters with underscores 
            for (int i = 0; i < guessedLetters.length; i++)  
            { 
                guessedLetters[i] = '_'; 
            } 
             
            int livesLeft = MAX_LIVES; // To give total number of chances. 
 
            while (livesLeft > 0 && new String(guessedLetters).contains("_"))  
            { 
                System.out.println("\nWord: " + joinWithSpaces(guessedLetters)); 
                System.out.println("Lives left: " + livesLeft); 
                System.out.print("Guess a letter: "); 
                char guess = scanner.next().toLowerCase().charAt(0); 
                 
                boolean correctGuess = false; 
                for (int i = 0; i < word.length(); i++)  
                { 
                    if (word.charAt(i) == guess)  
                    { 
                        guessedLetters[i] = guess; 
                        correctGuess = true; 
                    } 
                } 
                 
                if (!correctGuess)  
                { 
                    livesLeft--; 
                } 
            } 
 
            if (new String(guessedLetters).equals(word))  
            { 
                System.out.println("\nYou won! ✅ The word was: " + word); 
                player.increaseScore(3); 
            }  
            else  
            { 
                System.out.println("\nGame over! ❌ The word was: " + word); 
            } 
        } 
         
        String joinWithSpaces(char[] letters)  
        { 
            StringBuilder result = new StringBuilder(); 
            for (char c : letters)  
            { 
                result.append(c).append(" "); 
            } 
            return result.toString().trim(); 
        } 
    } 
   
//Code implementation of Treasure hunt. 
 class TreasureHuntGame implements Game  
 { 
    final int SIZE = 5; 
    char[][] Map = new char[SIZE][SIZE]; 
    int playerX = 0, playerY = 0; 
    Scanner scanner = new Scanner(System.in); 
     
    //ArrayList to store list of completed tasks. 
    ArrayList <String> completedTasks = new ArrayList<>(); 
     
    //Hashmap to create connectivity between place and task. 
    Map<String, Task> tasks = new HashMap<>(); 
 
   //Abstract method implementation. 
    public void Play(Player player)  
    { 
        initMap(); 
        setupTasks(); 
 
        System.out.println("═══════════════════════════════════════════════════════════════════════════════════"); 
        System.out.println("                         🪙 WELCOME TO TREASURE HUNT 🪙 "); 
        System.out.println("═══════════════════════════════════════════════════════════════════════════════════"); 
 
        printInstructions(); 
 
        while(true)  
        { 
            System.out.println("\nYou are at (" + playerX + ", " + playerY + ")"); 
            String location = playerX + "," + playerY; 
 
            // Check if player reached treasure 
            if (location.equals("4,4"))  
            { 
                if (tasks.containsKey(location) && !completedTasks.contains(location))  
                { 
                    tasks.get(location).game(); 
                } 
                System.out.println("\n🎉 Congratulations! You found the treasure! 🎉"); 
                printMap(); 
                break;  // Exit the game loop immediately after treasure is found 
            } 
 
            if (tasks.containsKey(location) && !completedTasks.contains(location))  
            { 
                tasks.get(location).game(); 
            } 
             
            System.out.println(); 
            printMap(); 
            System.out.print("Enter direction (north, south, east, west) : "); 
            String input = scanner.nextLine().trim().toLowerCase(); 
            movePlayer(input); 
        } 
      
    } 
 
    //Map initialization. 
    void initMap()  
    { 
        // Fill the matrix with '.' 
        for (int i = 0; i < SIZE; i++)  
        { 
            for (int j = 0; j < SIZE; j++)  
            { 
                Map[i][j] = '.';  
            } 
        } 
        // Optional: Set special start and target positions 
        Map[0][0] = 'S'; // Start 
        Map[4][4] = 'T'; // Target        
    } 
  
    //Printing in the form of matrix. 
    void printMap() 
    { 
         for (int i = 0; i < SIZE; i++)  
         { 
             for (int j = 0; j < SIZE; j++)  
             { 
                 if (i == playerX && j == playerY) //(X,Y) i.e place of player. 
                 { 
                     System.out.print("X "); 
                 }  
                 else  
                 { 
                     System.out.print(Map[i][j] + " "); 
                 } 
             } 
             System.out.println(); // Move to next row 
         } 
         System.out.println(); 
    } 
 
    //Method to Set tasks. 
    void setupTasks()  
    { 
        tasks.put("0,1", new Task("0,1", "Odd One Out: Cat, Dog, Cow, Bat", "bat")); 
        tasks.put("0,2", new Task("0,2", "Unscramble: 'PAPLE'", "apple")); 
        tasks.put("0,3", new Task("0,3", "What is 6+3 if 2+3=10 and 8+4=16?", "13")); 
        tasks.put("0,4", new Task("0,4", "Unscramble: DROWSSAP", "password")); 
        tasks.put("1,4", new Task("1,4", "What has keys but can't open locks?", "piano")); 
        tasks.put("2,4", new Task("2,4", "Find a 5-letter word from TEACHER", "cheer")); 
        tasks.put("3,4", new Task("3,4", "Sequence: 2, 6, 12, 20, ?", "30")); 
        tasks.put("1,1", new Task("1,1", "Riddle: 'I speak without a mouth and hear without ears.'", "echo")); 
        tasks.put("2,2", new Task("2,2", "What is 7+2 if 2+3=10 and 8+4=96?", "63")); 
        tasks.put("3,3", new Task("3,3", "Find a 5-letter word from 'RESTAURANT'", "start")); 
        tasks.put("4,1", new Task("4,1", "Anagram with a Clue: Rearrange “ENLIST” to something quiet", "silent")); 
        tasks.put("4,2", new Task("4,2", "Math Twist: If 3+2 = 25 and 4+3 = 37, then 5+4 = ?", "49")); 
        tasks.put("4,3", new Task("4,3", "Unscramble: “TRAPNE”", "parent")); 
        tasks.put("1,0", new Task("1,0", "No Task :)", "")); 
        tasks.put("2,0", new Task("2,0", "You see me once in June, twice in November, and not at all in May. What am I?", "e")); 
        tasks.put("3,0", new Task("3,0", "Odd One Out: Circle, Triangle, Cube, Square", "circle")); 
        tasks.put("4,0", new Task("4,0", "No Task :)", "")); 
        tasks.put("1,2", new Task("1,2", "No Task :)", "")); 
        tasks.put("1,3", new Task("1,3", "Number Series: 5, 10, 20, 40, ?", "80")); 
        tasks.put("2,3", new Task("2,3", "No Task :)", "")); 
        tasks.put("3,2", new Task("3,2", "Find a word from EXPLORATION (8-letter)", "potential")); 
        tasks.put("2,1", new Task("2,1", "No Task :)", "")); 
        tasks.put("3,1", new Task("3,1", "Unscramble: NATIP", "paint")); 
        tasks.put("4,4", new Task("4,4", "Enter Treasure", "Treasure")); 
    } 
 
    //Method to change the place according to input. 
    void movePlayer(String direction)  
    { 
        int newX = playerX, newY = playerY; 
 
        switch (direction) { 
            case "north": newX--; break; 
            case "south": newX++; break; 
            case "east":  newY++; break; 
            case "west":  newY--; break; 
            default: 
                System.out.println("\nInvalid direction."); 
                return; 
        } 
 
        if (newX >= 0 && newX < SIZE && newY >= 0 && newY < SIZE)  
        { 
            playerX = newX; 
            playerY = newY; 
        }  
        else  
        { 
            System.out.println("\nCan't move outside the map!"); 
        } 
    } 
 
    //Instruction given to player before playing treasure hunt only. 
    private static void printInstructions()  
    { 
        System.out.println("Instructions:"); 
        System.out.println("1. Start is marked as 'S', treasure as 'T'."); 
        System.out.println("2. Move using: north, south, east, west."); 
        System.out.println("3. Type 'map' anytime to view the map."); 
        System.out.println("4. Solve tasks to progress."); 
        System.out.println("5. Some spots have no tasks.\n"); 
        System.out.println("6. As last game if you reach upto treasure game will stop.\n"); 
         
         
    } 
} 
 
//Child class of TreasureHuntGame for set task at particular position. 
 class Task extends TreasureHuntGame  
 { 
     String place; 
     String task; 
     String answer; 
 
     //Constructor overloading. 
     Task()  
     { 
        super(); 
     } 
 
     Task(String place, String task, String answer)  
     { 
         this.place = place; 
         this.task = task; 
         this.answer = answer; 
     } 
      
     //Taking input for task from user. 
     void game()  
     { 
         try  
         { 
             if (task.equalsIgnoreCase("No Task :)"))  
             { 
                 System.out.println("No task at this location. Moving ahead."); 
                 completedTasks.add(place); 
                 return; 
             } 
 
         } 
         catch (NullPointerException e)  
         { 
             completedTasks.add(place); 
             return; 
         } 
 
         while (true)  
         { 
             System.out.println("Task: " + task); 
             System.out.print("Your answer: "); 
             String input = scanner.nextLine().trim().toLowerCase(); 
 
             if (input.equals(answer.toLowerCase()))  
             { 
                 System.out.println("✅ Correct answer! Moving ahead.\n"); 
                 completedTasks.add(place); 
                 break; 
             }  
             else  
             { 
                 System.out.println("❌ Wrong answer. Please try again.\n"); 
             } 
         } 
     } 
 } 
 
//Main class. 
public class Main { 
 
    static Scanner scanner = new Scanner(System.in); 
 
    public static void main(String[] args)  
    { 
         
        String Fire = new String(Character.toChars(0x1F525)); 
        Player player = new Player(); 
 
        int choice; 
         
        System.out.println(); 
        System.out.println("═══════════════════════════════════════════════════════════════════════════════════"); 
        System.out.println("                         "+Fire+" WELCOME TO THE GAMING HUB "+Fire+"                      "); 
        System.out.println("═══════════════════════════════════════════════════════════════════════════════════"); 
        System.out.println(); 
         
        System.out.println(); 
        System.out.println(); 
        player.settings(); 
 
        do { 
             
            System.out.println(); 
            System.out.println(); 
            System.out.println("------------------------------"); 
            System.out.println("PLAYER PROFILE 🧑 : "); 
            player.showProfile(); 
             
            System.out.println("\nChoose a game you want to play:"); 
                System.out.println("╔═══════════════════════════════════════════════════╗"); 
                System.out.println("║1. Stone Paper Scissors                            ║"); 
                System.out.println("║2. Tic-Tac Toe                                     ║"); 
                System.out.println("║3. Word Scramble                                   ║"); 
            if (player.score > 5) { 
                System.out.println("║4. Hangman (Unlocked!)                             ║"); 
            } else { 
                System.out.println("║4. Hangman (Locked - Unlock at Score > 5)          ║"); 
            } 
            if(player.score > 8) { 
                System.out.println("║5. Treasure Hunt (Unlocked!)                       ║"); 
                } else { 
                System.out.println("║5. Treasure Hunt (Locked - Unlock at Score > 8)    ║"); 
                   }      
                System.out.println("║6. Personal Data.                                  ║"); 
                System.out.println("║0. Exit                                            ║"); 
                System.out.println("╚═══════════════════════════════════════════════════╝"); 
      
                while (true)  
                { 
                    System.out.print("\nEnter your choice: "); 
                    String input = scanner.nextLine(); 
                    try { 
                        choice = Integer.parseInt(input); 
                        break; 
                    }  
                    catch (NumberFormatException e)  
                    { 
                        System.out.println("Invalid input! Please enter a number."); 
                    } 
                } 
 
            switch (choice)  
            { 
                case 1://stone paper scissor 
                    StonePaperScissors game = new StonePaperScissors(); 
                    game.Play(player); 
                    break; 
 
                case 2: 
                    TicTacToe tictactoe = new TicTacToe(); 
                    tictactoe.Play(player); 
                    break; 
                     
                case 3://scramble words 
                    WordScramble wordscramble = new WordScramble(); 
                    wordscramble.Play(player); 
                    break; 
 
                case 4: 
                     
                    Hangman hangman = new Hangman(); 
                    if (player.score > 5)  
                    { 
                        hangman.Play(player); 
                    }  
                    else  
                    { 
                        System.out.println("Hangman is locked. Increase your score to unlock."); 
                    } 
                    break; 
                     
                case 5://treasure hunt. 
               
                if (player.score > 8 ) 
                {        
                    TreasureHuntGame Tgame = new TreasureHuntGame(); 
                    Tgame.Play(player); 
                } 
                else 
                { 
                    System.out.println("Treasure Hunt is locked. Increase your score to unlock."); 
                } 
                     
                    break; 
                     
                case 6 : 
                     
                    System.out.println("\nPersonal information of player: ");  
                        Storage storage = new Storage(); 
                        storage.settings();      
                         
                     
                    break ; 
                     
                case 0: 
                    System.out.println(); 
                    System.out.println("Thanks for playing 🙏🏻"); 
                    player.showProfile(); 
                    System.out.println("\nEXITING FROM GAMING HUB…"); 
                    break; 
                     
                default: 
                    System.out.println(); 
                    System.out.println("Invalid choice. Try again."); 
            } 
 
        } while (choice != 0); 
    } 
} 
