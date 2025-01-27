package kadai12;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class ex12 {
  private JFrame frame;
  private JScrollPane imgScrollPane;
  private JScrollPane scrollPane2;
  private JTextField capybara;
  private JTextArea textArea;
  private ImagePanel imagePanel;
  private JButton btnNewButton1;
  private JButton btnNewButton2;
  private GrayImage imgGray;


  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          ex12 window = new ex12();
          window.frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public ex12() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame();
    frame.setBounds(100, 100, 1200, 1000);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    JPanel panel = new JPanel();
    panel.setPreferredSize(new Dimension(120, 0));
    frame.getContentPane().add(panel, BorderLayout.WEST);
    JLabel lblNewLabel = new JLabel("File Name");
    panel.add(lblNewLabel);
    
    capybara = new JTextField();
    capybara.setText("capybara.png");
    panel.add(capybara);
    capybara.setColumns(10);
    
    imgScrollPane = new JScrollPane();
    frame.getContentPane().add(imgScrollPane, BorderLayout.CENTER);
    imagePanel = new ImagePanel();
    imgScrollPane.setViewportView(imagePanel);
    
    scrollPane2 = new JScrollPane();
    frame.getContentPane().add(scrollPane2, BorderLayout.SOUTH);
    
    textArea = new JTextArea();
    scrollPane2.setViewportView(textArea);
    
    JButton btnNewButton = new JButton("Load");
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String fileName = capybara.getText();
        BufferedImage img = null;
        try {
          img = ImageIO.read(new File(fileName));
          imgGray = new GrayImage(img);
          imagePanel.setImage(imgGray);
        } catch (IOException e1) {
          
          e1.printStackTrace();
          textArea.append(e1.toString());
        } finally {         
          imgScrollPane.setViewportView(imagePanel);
        }
      }
    });
    panel.add(btnNewButton);
    
    btnNewButton1 = new JButton("Binary");
    panel.add(btnNewButton1);
    btnNewButton1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ImageFilter binaryFilter = new BinaryImageFilter();
        imgGray.applyFilter(binaryFilter);
        imagePanel.setImage(imgGray);
      }
    });
    
    btnNewButton2 = new JButton("Negative");
    panel.add(btnNewButton2);
    btnNewButton2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ImageFilter negativeFilter = new NegativeImageFilter();
        imgGray.applyFilter(negativeFilter);
        imagePanel.setImage(imgGray);
      }
    });
  }
}

