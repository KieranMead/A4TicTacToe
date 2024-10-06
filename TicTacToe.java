
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Container;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.Font;

/**
 * this is the class required for the tic tac toe game everything is enclosed here .
 *
 * @author (Kieran Mead)
 * @version (8/4/22)
 */
public class TicTacToe
{
    // variables to make the required panels for the GUI
    JPanel grid_panel = new JPanel();
    JButton[] buttons = new JButton[9];
    JPanel gui = new JPanel();
    JFrame frame = new JFrame("TicTacToe");
    JPanel bottomPanel = new JPanel();
    boolean player1turn;
    JTextField message, Username;
    boolean winnertrue;;
    boolean name;
    boolean startgame;
    private JButton startButton, retryButton;
    boolean validname;
    /**
     * Constructor for objects of class TicTacToe
     */
    public TicTacToe()
    {
        // initialise instance variable
        name = false;
        startgame = false;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void makeframe()
    {
        // Creates the GUI Frame with the below Values
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout (6,6));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        JMenu Menu = new JMenu("Menu");
        menubar.add(Menu);
        JMenuItem Reset = new JMenuItem("Reset");
        Menu.add(Reset);
        Reset.addActionListener( e-> reset());
        JMenuItem Quit = new JMenuItem("Quit");
        Menu.add(Quit);
        Quit.addActionListener( e-> Quit());
        grid_panel.setLayout(new GridLayout(0, 1));
        startButton = new JButton("Start");
        grid_panel.add(startButton);
        startButton.addActionListener (e -> start());
        retryButton = new JButton("Re-Play");
        grid_panel.add(retryButton);
        retryButton.addActionListener( e-> replay());
        JPanel panel1 = new JPanel();
        panel1.add(grid_panel);
        retryButton.addActionListener(e -> replay());
        contentPane.add(panel1, BorderLayout.EAST);
        gui.setLayout(new GridLayout (3, 3));
        retryButton.setEnabled(true);

        // Below Loops Make the Buttons to click for the 3x3 grid
        for(int i = 0; i < 9; i++){
            buttons[i] = new JButton();
            gui.add(buttons[i]);
        }
        for(int j = 0; j < 9; j++){
            JButton button = buttons[j];
            button.addActionListener(e -> select(button));
            retryButton.setEnabled(false);
        }

        contentPane.add(gui, BorderLayout.CENTER);
        // below Usernames sets the Textfields to the correct states
        Username = new JTextField("Please Enter Username");
        Username.setFont(new Font("SansSerif", Font.BOLD, 20));
        // lambda actionlistener to change the name field when necessary
        Username.addActionListener (e-> Name());
        // adds the username panel to the GUI
        bottomPanel.add(Username);
        contentPane.add(Username, BorderLayout.SOUTH);
        // a textfield that asks the user to enter a username
        message = new JTextField("welcome whats your name");
        message.setFont(new Font("SansSerif", Font.BOLD, 20));
        contentPane.add(message, BorderLayout.NORTH);
        frame.pack();
        // these 2 sets the size of the GUI as well as make it visible to the user
        frame.setSize(800,800);
        frame.setVisible(true);

    }
    // allows the startbutton to work when clicked
    public void start() {
        // if the username is valid the game will start
        if(name && !Username.getText().equals("")) {
            startgame = true;
            startButton.setEnabled(true);
            validname = true;
        }
        // if it is not valid the game will not start
        else  {
            startgame = false;
            message.setFont(new Font("SansSerif", Font.BOLD, 20));
            message.setText("please provide valid name before starting");
            Username.setEnabled(true);
            validname = false;
        }

    }
    // method to control when name field needs changing
    public void Name() {
        message.setFont(new Font("SansSerif", Font.BOLD, 20));
        String text = Username.getText();
        message.setText("Welcome to the Game " +text + " place a nought or cross match 3 to win ");
        name = true;
        Username.setEnabled(false);
        validname = true;
        if(Username.getText().equals("")){
            message.setText("no name detected please input username");
            validname = false;
            Username.setEnabled(true);
            name = false;
        }
    }
    // for the replay features
    public void replay() {
        validname = true;
        if( name == true ) {
            retryButton.setEnabled(false);
            startgame = true;
        }
        for(int i = 0; i < 9; i++){
            buttons[i].setText("");
            String text = Username.getText();
            if (winnertrue == true){
                buttons[i].setEnabled(true);
                buttons[i].setFont(new Font("SansSerif", Font.BOLD, 20));
                message.setText("Welcome to the Game " +text + " place a nought or cross match 3 to win ");
            }
        }
        for(int j = 0; j < 9; j++){
            buttons[j].setText("");
            buttons[j].setFont(new Font("SansSerif", Font.BOLD, 20));
        }
        startButton.setEnabled(true);
    }
    // places either X or O in the button
    public void select(JButton X) {
        if (startgame == true) {
            if(X.getText().equals("")) {
                if (player1turn) {
                    X.setText("X");
                    player1turn = false;
                    retryButton.setEnabled(true);
                    startButton.setEnabled(false);
                    winnerX();
                    X.setFont(new Font("SansSerif", Font.BOLD, 20));
                }
                else if(!player1turn){
                    X.setText("O");
                    player1turn = true;
                    retryButton.setEnabled(true);
                    startButton.setEnabled(false);
                    winnerO();
                    X.setFont(new Font("SansSerif", Font.BOLD, 20));
                }
            }

        }
    }
    // for quit menu button
    public void Quit(){
        System.exit(0);
    }
    // for reset menu button
    private void reset(){
        startgame = false;
        Username.setFont(new Font("SansSerif", Font.BOLD, 20));
        message.setFont(new Font("SansSerif", Font.BOLD, 20));
        message.setText("What is your name");
        Username.setText("Please Enter your  Username");
        Username.setEnabled(true);
        startButton.setEnabled(true);

        if( name == true ) {
            retryButton.setEnabled(false);
            startgame = true;
        }
        for(int i = 0; i < 9; i++){
            buttons[i].setText("");
            String text = Username.getText();
            if (winnertrue == true){
                buttons[i].setEnabled(true);
                buttons[i].setFont(new Font("SansSerif", Font.BOLD, 20));
                message.setText("Welcome to the Game " +text + " place a nought or cross match 3 to win ");
            }

        }
    }

    public void aiplayer(){

    }

    public void winnerX(){
        // this is for the winner if the winner has 3 X in a row which contains the combinations required
        if ((buttons[0].getText()=="X")&&
        (buttons[1].getText()=="X") &&
        (buttons[2].getText()=="X")){
            Xwinner(0,1,2); 
            winnertrue = true;
        }
        if ((buttons[3].getText()=="X")&&
        (buttons[4].getText()=="X") &&
        (buttons[5].getText()=="X")){
            Xwinner(3,4,5); 
            winnertrue = true;
        }
        if ((buttons[6].getText()=="X")&&
        (buttons[7].getText()=="X") &&
        (buttons[8].getText()=="X")){
            Xwinner(6,7,8);
            winnertrue = true;
        }
        if ((buttons[0].getText()=="X")&&
        (buttons[3].getText()=="X") &&
        (buttons[6].getText()=="X")){
            Xwinner(0,3,6); 
            winnertrue = true;
        }
        if ((buttons[1].getText()=="X")&&
        (buttons[4].getText()=="X") &&
        (buttons[7].getText()=="X")){
            Xwinner(1,4,7); 
            winnertrue = true;
        }
        if ((buttons[2].getText()=="X")&&
        (buttons[5].getText()=="X") &&
        (buttons[8].getText()=="X")){
            Xwinner(2,5,8); 
            winnertrue = true;
        }
        if ((buttons[0].getText()=="X")&&
        (buttons[4].getText()=="X") &&
        (buttons[8].getText()=="X")){
            Xwinner(0,4,8); 
            winnertrue = true;
        }
        if ((buttons[2].getText()=="X")&&
        (buttons[4].getText()=="X") &&
        (buttons[6].getText()=="X")){
            Xwinner(2,4,6); 
            winnertrue = true;
        }

    }
    // if the winner is O and contains the combinations for this to work
    public void winnerO(){
        if ((buttons[0].getText()=="O")&&
        (buttons[1].getText()=="O") &&
        (buttons[2].getText()=="O")){
            Owinner(0,1,2); 
            winnertrue = true;
        }
        if ((buttons[3].getText()=="O")&&
        (buttons[4].getText()=="O") &&
        (buttons[5].getText()=="O")){
            Owinner(3,4,5); 
            winnertrue = true;
        }
        if ((buttons[6].getText()=="O")&&
        (buttons[7].getText()=="O") &&
        (buttons[8].getText()=="O")){
            Owinner(6,7,8); 
            winnertrue = true;
        }
        if ((buttons[0].getText()=="O")&&
        (buttons[3].getText()=="O") &&
        (buttons[6].getText()=="O")){
            Owinner(0,3,6); 
            winnertrue = true;
        }
        if ((buttons[1].getText()=="O")&&
        (buttons[4].getText()=="O") &&
        (buttons[7].getText()=="O")){
            Owinner(1,4,7); 
            winnertrue = true;
        }
        if ((buttons[2].getText()=="O")&&
        (buttons[5].getText()=="O") &&
        (buttons[8].getText()=="O")){
            Owinner(2,5,8); 
            winnertrue = true;
        }
        if ((buttons[0].getText()=="O")&&
        (buttons[4].getText()=="O") &&
        (buttons[8].getText()=="O")){
            Owinner(0,4,8);
            winnertrue = true;
        }
        if ((buttons[2].getText()=="O")&&
        (buttons[4].getText()=="O") &&
        (buttons[6].getText()=="O")){
            Owinner(2,4,6); 
            winnertrue = true;
        }

    }
    // gives the message and disables buttons if the winner is X
    public void Xwinner(int a, int b, int c){
        for (int i=0; i<9;i++){
            if (winnertrue = true){
                message.setFont(new Font("SansSerif", Font.BOLD, 20));
                buttons[i].setEnabled(false);
                message.setText("X Wins");
                winnertrue = true;
            }
            else {
                winnertrue = false;
            }

        }
    }

    // gives the message and disables buttons if the winner is O
    public void Owinner(int a, int b, int c){
        for (int i=0; i<9;i++){
            if (winnertrue = true){
                message.setFont(new Font("SansSerif", Font.BOLD, 20));
                buttons[i].setEnabled(false);
                message.setText("O Wins");
                winnertrue = true;
            }
            else {
                winnertrue = false;
            }

        }
    }
    // my draw method but is not working right now
    public void nowinner(){

    }
}

