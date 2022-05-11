import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math.*;

public class PaintProgram implements ActionListener {
    JFrame frame;
    DrawingPanel dPanel;
    JPanel buttonPanel,colorPanel;
    JTextField redField, blueField, greenField;
    JLabel redLabel, blueLabel, greenLabel;
    JButton pencilButton, eraserButton, blackButton, blueButton, greenButton, redButton, clearButton, sprayButton, rectangleButton, colorPickerButton, customColorButton;

    // This is the PaintProgram constructor which sets up the JFrame and all other components and containers

    public PaintProgram() {
        // Set up JFrame using BorderLayout
        frame = new JFrame("Paint Program");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and add DrawingPanel to CENTER
        dPanel = new DrawingPanel();
        frame.add(dPanel);

        // Create buttonPanel and buttons
        buttonPanel = new JPanel();
        frame.add(buttonPanel, BorderLayout.SOUTH);

        pencilButton = new JButton("Pencil");
        pencilButton.addActionListener(this);
        buttonPanel.add(pencilButton);

        eraserButton = new JButton("Eraser");
        eraserButton.addActionListener(this);
        buttonPanel.add(eraserButton);

        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        buttonPanel.add(clearButton);
        sprayButton = new JButton("Spray");
        sprayButton.addActionListener(this);
        buttonPanel.add(sprayButton);
        rectangleButton = new JButton("Rectangle");
        rectangleButton.addActionListener(this);
        buttonPanel.add(rectangleButton);
        colorPickerButton = new JButton("Color Picker");
        colorPickerButton.addActionListener(this);
        buttonPanel.add(colorPickerButton);

        colorPanel = new JPanel();
        BoxLayout layout1 = new BoxLayout(colorPanel, BoxLayout.Y_AXIS);
        colorPanel.setLayout(layout1);
        frame.add(colorPanel, BorderLayout.EAST);

        blackButton = new JButton("Black");
        blackButton.addActionListener(this);
        colorPanel.add(blackButton);
        blueButton = new JButton("Blue");
        blueButton.addActionListener(this);
        colorPanel.add(blueButton);
        greenButton = new JButton("Green");
        greenButton.addActionListener(this);
        colorPanel.add(greenButton);
        redButton = new JButton("Red");
        redButton.addActionListener(this);
        colorPanel.add(redButton);
        redLabel=new JLabel("r = ");
        colorPanel.add(redLabel);
        redField=new JTextField(10);
        colorPanel.add(redField);
        greenLabel=new JLabel("g = ");
        colorPanel.add(greenLabel);
        greenField=new JTextField(10);
        colorPanel.add(greenField);
        blueLabel=new JLabel("b = ");
        colorPanel.add(blueLabel);
        blueField=new JTextField(10);
        colorPanel.add(blueField);
        customColorButton = new JButton("Custom");
        customColorButton.addActionListener(this);
        colorPanel.add(customColorButton);

        // Set the size and set the visibility
        frame.pack();
        frame.setVisible(true);
    }

    // This the code that is called when any button is pressed

    public void actionPerformed(ActionEvent ae) {
        // If pencilButton is pressed, set drawingPanel mode to "Pencil"
        if (ae.getActionCommand().equals("Pencil")) {
            dPanel.setMode("Pencil");
        }
        if (ae.getActionCommand().equals("Eraser")) {
            dPanel.setMode("Eraser");
        }
        if (ae.getActionCommand().equals("Black")) {
            dPanel.setColor(Color.BLACK);
        }
        if (ae.getActionCommand().equals("Blue")) {
            dPanel.setColor(Color.BLUE);
        }
        if (ae.getActionCommand().equals("Green")) {
            dPanel.setColor(Color.GREEN);
        }
        if (ae.getActionCommand().equals("Red")) {
            dPanel.setColor(Color.RED);
        }
        if (ae.getActionCommand().equals("Clear")) {
            dPanel.clear();

        }
        if (ae.getActionCommand().equals("Spray")) {
            dPanel.setMode("Spray");

        }
        if (ae.getActionCommand().equals("Color Picker")) {
            dPanel.setMode("Color Picker");

        }
        if (ae.getActionCommand().equals("Rectangle")) {
            dPanel.setMode("Rectangle");

        }
        if (ae.getActionCommand().equals("Custom")) {
            Color c = new Color(Integer.parseInt(redField.getText()), Integer.parseInt(greenField.getText()), Integer.parseInt(blueField.getText()));
            dPanel.setColor(c);

        }
    }

    // Main method just creates a PaintProgram object
    public static void main(String[] args) {
        PaintProgram x = new PaintProgram();
    }

    class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener {
        // DrawingPanel has the following instance variables:

        // A 2D array which stores whether or not each pixel should be painted

        private boolean[][] isPainted;

        // A 2D array which stores the Java Colors of each pixel

        private Color[][] colors;

        // The mode is a String that we can use to keep track of what should happen if the user presses the mouse

        private String mode;

        // This keeps track of the current selected color

        private Color color;

        private boolean rectClick=false;
        private Color rectColor;
        private int rectX;
        private int rectY;

        // These are constant values
        private static final int WIDTH = 500;
        private static final int HEIGHT = 500;

        // Constructor sets up DrawingPanel

        public DrawingPanel() {
            // Set background color
            setBackground(Color.WHITE);

            // Add mouse listeners
            addMouseListener(this);
            addMouseMotionListener(this);

            // Initialize instance variables
            isPainted = new boolean[WIDTH][HEIGHT];
            colors = new Color[WIDTH][HEIGHT];
            mode = "Pencil";
            color = Color.BLACK;
        }

        // Can be called to change the current mode of the drawing panel

        public void setMode(String mode) {
            this.mode = mode;
        }

        // Can be called to change the current color of the drawing panel

        public void setColor(Color color) {
            this.color = color;
        }



        public void clear(){
            for(int i=0;i<isPainted.length;i++){
                for(int j=0;j<isPainted[0].length;j++){
                    isPainted[i][j]=false;
                    repaint();
                }
            }
        }

        // Sets the size of the DrawingPanel, so frame.pack() considers its preferred size when sizing the JFrame

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(WIDTH, HEIGHT);
        }

        // This is the method that draws everything

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Loop through the 2D array and draw a 1x1 rectangle on each pixel that is currently painted
            for (int x = 0; x < WIDTH; x++) {
                for (int y = 0; y < HEIGHT; y++) {
                    if (isPainted[x][y]) {


                        g.setColor(colors[x][y]);
                        g.fillRect(x, y, 1, 1);

                    }
                }
            }
        }

        // MouseListener methods
        public void drawing(int x, int y, String mode){
            if (mode.equals("Pencil")) {
                // Check that mouse is in bounds of panel
                if (x >= 0 && x < WIDTH &&
                        y >= 0 && y < HEIGHT) {
                    isPainted[x][y] = true;
                    colors[x][y]=color;
                }
            }
            else if (mode.equals("Eraser")){
                if (x >= 20 && x < WIDTH-20 && y >= 20 && y < HEIGHT-20) {
                    for(int i=1;i<=40;i++){
                        for(int j=1;j<=40;j++) {
                            isPainted[x- 20 + i][y - 20 + j] = false;
                        }
                    }
                }
            }
            else if (mode.equals("Spray")){
                if (x >= 20 && x < WIDTH-20 && y >= 20 && y < HEIGHT-20) {
                    for(int i=0;i<20;i++){
                        int newX=x-20+(int)(Math.random()*40+1);
                        int newY=y-20+(int)(Math.random()*40+1);
                        isPainted[newX][newY] = true;
                        colors[newX][newY]=color;
                    }
                }
            }
            else if (mode.equals("Color Picker")){
                if (x >= 0 && x < WIDTH &&
                        y >= 0 && y < HEIGHT) {
                    if(colors[x][y]!=null){
                        color=colors[x][y];
                    }
                }
            }
            else if (mode.equals("Rectangle")&&! rectClick){
                if (x >= 0 && x < WIDTH &&
                        y >= 0 && y < HEIGHT) {
                    rectColor=color;
                    rectX=x;
                    rectY=y;
                    rectClick=true;
                }
            }

            repaint();
        }

        public void mousePressed(MouseEvent e) {
            // Check the current mode
            int x=e.getX();
            int y=e.getY();
            if (mode.equals("Pencil")) {
                drawing(x,y, "Pencil");
            }
            else if (mode.equals("Eraser")){
                drawing(x,y, "Eraser");
            }
            else if (mode.equals("Spray")){
                drawing(x,y, "Spray");
            }
            else if (mode.equals("Color Picker")){
                drawing(x,y, "Color Picker");
            }
            else if (mode.equals("Rectangle")&&! rectClick){
                drawing(x,y, "Rectangle");
            }

        }

        public void mouseDragged(MouseEvent e) {
            mousePressed(e);
        }

        public void mouseReleased(MouseEvent e) {
            if(rectClick){
                rectClick=false;
                int currentX=e.getX();
                int currentY=e.getY();
                for(int i=Math.min(rectX,currentX);i<Math.max(rectX,currentX);i++){
                    for(int j=Math.min(rectY,currentY);j<Math.max(rectY,currentY);j++){
                        isPainted[i][j]=true;
                        colors[i][j]=rectColor;
                    }
                }
            }
            repaint();
        }

        public void mouseEntered(MouseEvent e) {
            // This method is intentionally blank
        }

        public void mouseExited(MouseEvent e) {
            // This method is intentionally blank
        }

        public void mouseClicked(MouseEvent e) {
            // This method is intentionally blank
        }

        public void mouseMoved(MouseEvent e) {
            // This method is intentionally blank
        }
    }
}
