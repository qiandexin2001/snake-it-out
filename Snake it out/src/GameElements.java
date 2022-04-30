
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.*;
import java.awt.Color;

public class GameElements {
	
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void createPopUp(String[] msg) {
		
		JFrame frame = new JFrame("Oops! Almost there!");
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		Font font = new Font("Arial", Font.BOLD, 20);
		 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(200,100,760,533);
		
		Container container = frame.getContentPane();
		container.setLayout(null);
		
		JLabel title = new JLabel();
		title.setBackground(Color.ORANGE);
		title.setText("Quiz Time! Will you survive?");
		title.setBounds(229,24,300,45);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(font);
		

		JTextArea question = new JTextArea(msg[0]);
		question.setBackground(Color.GRAY);
		question.setWrapStyleWord(true);
		question.setLineWrap(true);
		question.setBounds(58, 96 , 631, 195);
		question.setFont(new Font("Arial", Font.PLAIN, 20));
		
		JLabel answer = new JLabel("Answer: ");
		answer.setHorizontalAlignment(SwingConstants.CENTER);
		answer.setFont(new Font("Arial", Font.PLAIN, 15));
		answer.setBounds(58, 319 , 78, 45);
		
		JTextField answerField = new JTextField(100);
		answerField.setBounds(58,375,631,36);
		
		
		container.add(title);
		container.add(question);
		container.add(answer);
		container.add(answerField);
		
		JButton btnOk = new JButton("ok");
		btnOk.setFont(new Font("Arial", Font.PLAIN, 15));
		answerField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String result =answerField.getText();
				
				if(msg[1].equalsIgnoreCase(result)) {
					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));		//dummy values
				}else {
					System.out.println("Lose");
				}
			}
		});
		
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String result =answerField.getText();
				
				if(msg[1].equalsIgnoreCase(result)) {
					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				
					//dummy values
				}else {
					System.out.println("Lose");
				}
			}
		});
		
		btnOk.setBounds(340, 431, 98, 36);
		frame.getContentPane().add(btnOk);
		
		
		
		frame.setVisible(true);
		
	
	}
	
	
	
}

