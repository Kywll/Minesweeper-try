import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI {

    Board board;
    JButton[][] buttons;
    JFrame window;
    JPanel panel;

    boolean bombsPlaced = false;
    boolean gameOver = false;



    // Create game board (5x5 board with 5 bombs)

    public GUI(Board board){
        this.board = board;

        // Create main window
        window = new JFrame("Minesweeper");
        window.setSize(420, 450);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);

        panel = new JPanel();
        panel.setLayout(new GridLayout(this.board.boardHeight, this.board.boardWidth));
        panel.setBackground(Color.BLACK);

        // Create and store buttons
        buttons = new JButton[board.boardHeight][board.boardWidth];

        // Creates the button upon calling the object
        createButtons();
        applyClicks();

        window.add(panel);
        window.setVisible(true);

    }

    public void createButtons(){
        for (int i = 0; i < board.boardHeight; i++) {
            for (int j = 0; j < board.boardWidth; j++) {
                JButton button = new JButton();
                button.setFont(new Font("Arial", Font.BOLD, 20));
                button.setBackground(Color.LIGHT_GRAY);

                buttons[i][j] = button;
                panel.add(button);
            }
        }
    }

    public void checkForClicks(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JButton clicked = (JButton) e.getSource();


                int index = 0;
                // Search for the clicked button in the 2D array
                for (int row = 0; row < buttons.length; row++) {
                    for (int col = 0; col < buttons[0].length; col++) {
                        if (buttons[row][col] == clicked && !board.board[row][col].isClicked && !gameOver) {
                            if (SwingUtilities.isRightMouseButton(e)) {
                                board.flagTile(index + 1);
                                updateButton();
                            }
                            else if (SwingUtilities.isLeftMouseButton(e) && !board.board[row][col].isFlagged) {
                                if (!bombsPlaced) {
                                    board.addBombs(index + 1);
                                    bombsPlaced = true;
                                }
                                // Found the row and column of the clicked button
                                board.tileInput(index + 1);
                                if(board.winLose().equals("Win") || board.winLose().equals("Lose")){
                                    gameOver = true;
                                    updateButton();
                                    break;
                                }
                                updateButton();
                            }
                            return; // Exit the loop once found
                        }
                        index++;
                    }
                }
            }
        });
    }

    public void applyClicks(){
        for (int row = 0; row < board.boardHeight; row++) {
            for (int col = 0; col < board.boardWidth; col++) {
                checkForClicks(buttons[row][col]);
            }
        }
    }

    // Show bomb, number, or 0
    private void updateButton() {
        for (int i=0;i< board.boardHeight;i++){
            for (int j=0;j< board.boardWidth;j++){
                if (board.board[i][j].isFlagged){
                    buttons[i][j].setText("?");
                    buttons[i][j].setBackground(Color.BLUE);
                }
                else if (!board.board[i][j].isFlagged){
                    buttons[i][j].setText(" ");
                    buttons[i][j].setBackground(Color.LIGHT_GRAY);
                }


                if (board.board[i][j].isClicked) {
                    if (board.board[i][j].isBomb()) {
                        buttons[i][j].setText("@");
                        buttons[i][j].setBackground(Color.RED);
                    } else {
                        int count = board.checkSurroundingBombs(i, j);
                        if (count != 0){
                            buttons[i][j].setText(String.valueOf(count));
                        }
                        buttons[i][j].setBackground(Color.GRAY);
                    }
                }
            }
        }
    }



    // Show bomb, number, or 0
    private void updateButton(int row, int col) {
        Tile tile = board.board[row][col];
        JButton button = buttons[row][col];


        if (tile.isClicked) {
            if (tile.isBomb()) {
                button.setText("@");
                button.setBackground(Color.RED);
            } else {
                int count = board.checkSurroundingBombs(row, col);
                button.setText(String.valueOf(count));
                button.setBackground(Color.GRAY);
            }
        }
    }


}
