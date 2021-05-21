
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Guesser extends JFrame  {
    private JButton start_game, play_again, guess, give_up;
    private JPanel main_panel, panel1, panel2;
    private JTextField pole1;
    private JLabel Label1, Prompt;
    private int number;
    private GridBagConstraints c, a;

    private Font Font1;

    Guesser() {
        super("Guesser");
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
        setSize(new Dimension(dim.width / 4, dim.height / 4));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




        start_game = new JButton("Start Game");
        start_game.setPreferredSize(new Dimension(100,40));
        play_again = new JButton("Play Again");
        give_up = new JButton("Give Up");
        guess = new JButton("Guess");
        main_panel = new JPanel();
        Prompt = new JLabel("Enter a number from the range 1-10: ");
        Prompt.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.Y_AXIS));
        pole1 = new JTextField(3);


        c = new GridBagConstraints();
        a = new GridBagConstraints();
        Label1 = new JLabel("");
        Label1.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));


        Font1 = new Font("SansSerif", Font.BOLD, 20);
        pole1.setFont(Font1);
        pole1.setHorizontalAlignment(JTextField.CENTER);
        panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
        panel1.add(start_game);
        main_panel.add(panel2);
        main_panel.add(panel1);
        add(main_panel);



        start_game.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae) {

                try {
                    if ((ae.getActionCommand().equals("Guess")) && (Integer.parseInt(pole1.getText()) > 10 || Integer.parseInt(pole1.getText()) < 1))
                        throw new InAppropriateNumber();



                    if (ae.getActionCommand().equals("Start Game")) {

                        panel2.remove(Label1);
                        panel2.add(Prompt, a);
                        panel2.add(pole1);
                        panel1.add(guess);
                        panel1.add(give_up);
                        panel1.remove(start_game);
                        pole1.setText("");
                        revalidate();
                        repaint();
                        generate_number();
                        guess.addActionListener(this);
                        pole1.addActionListener(this);
                        give_up.addActionListener(this);
                        play_again.addActionListener(this);
                    }


                    if (ae.getActionCommand().equals("Give Up")) {

                        panel1.remove(guess);
                        panel1.remove(give_up);
                        panel1.add(play_again);
                        Label1.setText("The number that you were supposed to guess is "+number);
                        panel2.add(Label1);
                        panel2.remove(pole1);
                        panel2.remove(Prompt);
                        revalidate();
                        repaint();
                    }

                    if (ae.getActionCommand().equals("Play Again")) {
                        panel1.remove(play_again);
                        panel1.add(start_game);
                        panel2.removeAll();
                        revalidate();
                        repaint();
                    }


                    if (ae.getActionCommand().equals("Guess") && Integer.parseInt(pole1.getText()) == number) {
                        Label1.setText("You've just won, congratulations! Your Lucky number is " + number);
                        panel2.removeAll();
                        panel2.add(Label1);
                        panel1.add(play_again);
                        panel1.remove(guess);
                        panel1.remove(give_up);
                        revalidate();
                        repaint();
                    }



                    if (ae.getActionCommand().equals("Guess") && Integer.parseInt(pole1.getText()) != number && Integer.parseInt(pole1.getText()) <= 10 && Integer.parseInt(pole1.getText()) >= 1) {

                        Label1.setText("You have not guessed, try again ");
                        panel2.add(Label1);
                        a.insets = new Insets(10, 0, 0, 0);
                        a.gridx = 0;
                        a.gridy = 2;
                        revalidate();
                        repaint();
                    }
                }



                catch (InAppropriateNumber e)
                {
                    e.WrongNumber();

                }

                catch (NumberFormatException e)
                {

                    Label1.setText("Fill out the field ");
                    panel2.add(Label1);
                    repaint();
                    revalidate();
                }


            }});
    }





    void generate_number()
    {
        Random rand = new Random();
        number = rand.nextInt(10) + 1;
    }


    class InAppropriateNumber extends Exception {
        void WrongNumber() {

            c.insets = new Insets(10,0,0,0);
            c.gridx = 0;
            c.gridy = 2;

            panel2.add(Label1);
            Label1.setText("Pass number from range 1-10!");
            panel2.revalidate();
            panel2.repaint();

            }


        }



    public static void main(String[] args){

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Guesser();

            }
        });




    }
}
