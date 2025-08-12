import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class GUI {
    Timer timer;
    int timeElapsed = 0;
    JLabel timerLabel;
    JLabel bombCountLabel;

    Board board;
    Game game = new Game();
    JButton[][] buttons;
    JFrame window;
    JPanel panel;

    boolean bombsPlaced = false;
    boolean gameOver = false;
    boolean flagMode = false;

    ImageIcon smile = new ImageIcon("smile.png");
    Image scaledSmile = smile.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
    JLabel restartBtn = new JLabel(smile);

    ImageIcon hint = new ImageIcon("lightbulb.png");
    Image scaledHint = hint.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
    JLabel hintBtn = new JLabel(hint);

    ImageIcon flag = new ImageIcon("flag.png");
    Image scaledFlag = flag.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
    JLabel flagBtn = new JLabel(flag);


    // Create game board (5x5 board with 5 bombs)

    public GUI(){
        int[] settings = getBoardSettings();
        int height = settings[0];
        int width = settings[1];
        int bombs = settings[2];

        this.board = new Board(height, width, bombs);
        board.createBoard();

        // Create main window
        window = new JFrame("Minesweeper");
        window.setSize(720, 720);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);

        startDisplay();
    }

    public void startDisplay(){
        this.bombsPlaced = false;
        this. gameOver = false;

        panel = new JPanel();
        panel.setLayout(new GridLayout(this.board.boardHeight, this.board.boardWidth));
        panel.setBackground(Color.BLACK);

        // Create and store buttons
        buttons = new JButton[board.boardHeight][board.boardWidth];

        // Creates the button upon calling the object
        createButtons();
        applyClicks();

        // Container holds restart button and board
        JPanel container = new JPanel(new BorderLayout());

        restartBtn.setHorizontalAlignment(SwingConstants.CENTER);
        restartBtn.setIcon(new ImageIcon(scaledSmile));

        restartBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                restartGame();
            }
        });

        hintBtn.setHorizontalAlignment(SwingConstants.CENTER);
        hintBtn.setIcon(new ImageIcon(scaledHint));

        hintBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showHint();
            }
        });

        flagBtn.setHorizontalAlignment(SwingConstants.CENTER);
        flagBtn.setIcon(new ImageIcon(scaledFlag));

        flagBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toggleFlag();
                flagBtn.setText(flagMode ? "Flag: ON" : "Flag: OFF");
            }
        });


        bombCountLabel = new JLabel("Bombs: " + board.flagCounter);
        bombCountLabel.setFont(new Font("Arial", Font.BOLD, 14));
        bombCountLabel.setHorizontalAlignment(SwingConstants.LEFT);


        window.setContentPane(container);
        timerLabel = new JLabel("Time: 0s");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel buttonGroup = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0)); // 10px gap
        buttonGroup.add(hintBtn);
        buttonGroup.add(restartBtn);
        buttonGroup.add(flagBtn);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(bombCountLabel, BorderLayout.WEST);
        topPanel.add(buttonGroup, BorderLayout.CENTER);
        topPanel.add(timerLabel, BorderLayout.EAST);
        container.add(topPanel, BorderLayout.NORTH);


        container.add(panel, BorderLayout.CENTER);


        startTimer();

        window.setVisible(true);



    }

    private int[] getBoardSettings() {
        int height = Integer.parseInt(JOptionPane.showInputDialog("Enter board height:"));
        int width = Integer.parseInt(JOptionPane.showInputDialog("Enter board width:"));
        int bombs = Integer.parseInt(JOptionPane.showInputDialog("Enter bomb count:"));
        return new int[] {height, width, bombs};
    }

    private void startTimer() {
        timeElapsed = 0;
        if (timer != null) timer.stop();

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeElapsed++;
                timerLabel.setText("Time: " + timeElapsed + "s");
            }
        });

        timer.start();
    }

    public void toggleFlag(){
        flagMode = !flagMode;

    }

    public void showHint(){
        for (int i = 0; i < board.boardHeight; i++) {
            for (int j = 0; j < board.boardWidth; j++) {
                if (board.board[i][j].isBomb()){
                    board.board[i][j].isFlagged = true;
                }
            }
        }

        updateButton();
    }

    public void restartGame(){
        ImageIcon smile = new ImageIcon("smile.png");
        Image scaled = smile.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        restartBtn.setIcon(new ImageIcon(scaled));

        if (timer != null) timer.stop();

        // Reset the board data (logic layer)
        this.board = new Board(board.boardHeight, board.boardWidth, board.bombLimit);
        this.board.createBoard();
        this.bombsPlaced = false;

        // Reset game state
        bombsPlaced = false;
        gameOver = false;

        // Remove all tiles from the panel
        panel.removeAll();

        // Rebuild the button grid
        buttons = new JButton[board.boardHeight][board.boardWidth];
        createButtons();
        applyClicks();

        // Refresh the UI
        panel.revalidate();
        panel.repaint();

        startTimer();
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
                            else if (SwingUtilities.isLeftMouseButton(e)) {
                                if (flagMode){
                                    board.flagTile(index + 1);
                                    updateButton();
                                }
                                else if(!board.board[row][col].isFlagged){
                                    if (!bombsPlaced) {
                                        board.addBombs(index + 1);
                                        bombsPlaced = true;
                                    }
                                    // Found the row and column of the clicked button
                                    board.tileInput(index + 1);
                                    String result = board.winLose();

                                    if (result.equals("Lose")) {
                                        ImageIcon dead = new ImageIcon("dead.png");
                                        Image scaled = dead.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                                        restartBtn.setIcon(new ImageIcon(scaled));

                                        disableAllButtons(); // optional method to stop further clicking
                                        if (timer != null) timer.stop();
                                        updateButton();
                                        JOptionPane.showMessageDialog(window, "You Lose! :(");
                                        break;
                                    } else if (result.equals("Win")) {
                                        ImageIcon sunglasses = new ImageIcon("sunglasses.png");
                                        Image scaled = sunglasses.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                                        restartBtn.setIcon(new ImageIcon(scaled));

                                        disableAllButtons();
                                        if (timer != null) timer.stop();
                                        updateButton();
                                        JOptionPane.showMessageDialog(window, "You Win! :)");
                                        break;
                                    }

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

    private void disableAllButtons() {
        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                buttons[row][col].setEnabled(false);
            }
        }
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
                    ImageIcon flag = new ImageIcon("flag.png");
                    Image scaledFlag = flag.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                    ImageIcon resizedIcon = new ImageIcon(scaledFlag);
                    buttons[i][j].setIcon(resizedIcon);
                }
                else if (!board.board[i][j].isFlagged){
                    buttons[i][j].setIcon(null);
                    buttons[i][j].setText(" ");
                    buttons[i][j].setBackground(Color.LIGHT_GRAY);
                }


                if (board.board[i][j].isClicked) {
                    if (board.board[i][j].isBomb()) {
                        ImageIcon bomb = new ImageIcon("bomb.png");
                        Image scaledBomb = bomb.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                        ImageIcon resizedIcon = new ImageIcon(scaledBomb);
                        buttons[i][j].setIcon(resizedIcon);

                        buttons[i][j].setBackground(Color.RED);
                    } else {
                        int count = board.checkSurroundingBombs(i, j);
                        if (count != 0){
                            buttons[i][j].setText(String.valueOf(count));
                            switch (count){
                                case 1:
                                    buttons[i][j].setForeground(Color.CYAN);
                                    break;
                                case 2:
                                    buttons[i][j].setForeground(Color.GREEN);
                                    break;
                                case 3:
                                    buttons[i][j].setForeground(Color.RED);
                                    break;
                                case 4:
                                    buttons[i][j].setForeground(Color.BLUE);
                                    break;
                                case 5:
                                    buttons[i][j].setForeground(Color.ORANGE);
                                    break;
                                case 6:
                                    buttons[i][j].setForeground(Color.PINK);
                                    break;
                                case 7:
                                    buttons[i][j].setForeground(Color.MAGENTA);
                                    break;
                                case 8:
                                    buttons[i][j].setForeground(Color.GRAY);
                                    break;
                            }
                        }
                        buttons[i][j].setBackground(Color.GRAY);
                    }
                }
            }
        }

        bombCountLabel.setText("Bombs: " + board.flagCounter);
    }

}
