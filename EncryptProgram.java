import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.TextField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class EncryptProgram extends JFrame {
	
	Random random;
	Scanner sc;
	ArrayList<Character> keyList;
	ArrayList<Character> secretList;
	Character chars;
	String word;
	String response;
	char afterResponse;
	char[] letters;
	
	JScrollPane scrollPane;
	JTextArea answerArea;
	JButton newKeyButton;
	JButton getKeyButton;
	JButton encryptButton;
	JButton decryptButton;
	JButton quitButton;
	JLabel askLabel;
	
	EncryptProgram() {
		
		sc = new Scanner(System.in);
		
		this.setTitle("Encrypt Program");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setSize(500, 500);
		this.setLayout(null);
		
		
		answerArea = new JTextArea();
		answerArea.setBounds(1, 200, 479, 245);
		answerArea.setEditable(false);
		answerArea.setFont(new Font("Mv Boli", Font.BOLD, 30));
		answerArea.setText("Heeey");
		answerArea.setLineWrap(true);
		answerArea.setWrapStyleWord(true);
		
		scrollPane = new JScrollPane(answerArea);
		scrollPane.setBounds(1, 200, 479, 245);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		newKeyButton = new JButton("newKey");
		newKeyButton.setBounds(10, 75, 100, 50);
		newKeyButton.setFocusable(false);
		newKeyButton.addActionListener((e) -> {
			newKey();
			answerArea.setText("The key has been generated");
		});
		
		getKeyButton = new JButton("getKey");
		getKeyButton.setBounds(90, 75, 100, 50);
		getKeyButton.setFocusable(false);
		getKeyButton.addActionListener((e) -> {
			getKey();
			answerArea.setText("Normal List :");
		   for(int i=0; i < keyList.size(); i++) {
		   
			   answerArea.setText(answerArea.getText()+keyList.get(i).toString());
		   }
		});
		encryptButton = new JButton("Encrypt");
		encryptButton.setBounds(180, 75, 100, 50);
		encryptButton.setFocusable(false);
		
		decryptButton = new JButton("Decrypt");
		decryptButton.setBounds(270, 75, 100, 50);
		decryptButton.setFocusable(false);
		
		quitButton = new JButton("Quit");
		quitButton.setBounds(370, 75, 100, 50);
		quitButton.setFocusable(false);
		
		
		this.add(newKeyButton);
		this.add(getKeyButton);
		this.add(encryptButton);
		this.add(decryptButton);
		this.add(quitButton);
		this.add(scrollPane);
		this.add(answerArea);
		
		this.setVisible(true);
	}
	
	public void askQuestion() {
		
		while(true) {
	
	    System.out.println("*************************************************");
		System.out.println("What do you want to do ?");
		System.out.println("(N)ewKey, (G)etKey, (E)ncrypt, (D)ecrypt, (Q)uit");
		
		
		afterResponse = Character.toUpperCase(sc.nextLine().charAt(0));
		switch(afterResponse) {
		case 'N' :
			newKey();
			break;
		case 'G' :
			getKey();
			break;
		case 'E' :
			encrypt();
			break;
		case 'D' :
			decrypt();
			break;
		case 'Q' :
			quit();
			break;
		default:
			System.out.println("ANSWER NOT VALID :(, TRY AGAIN!!");
			
		}
		}
	}
	public void newKey() {
		
		chars = ' ';
		keyList = new ArrayList<Character>();
		for(int i = 32; i < 127; i++) {
			
			keyList.add(Character.valueOf(chars));
			chars++;
		}
		
		secretList = new ArrayList<Character>(keyList);
		Collections.shuffle(secretList);
		
		
		
	}
	public void getKey() {
		
		System.out.println("Normal List : ");
		for(int i = 0; i < keyList.size(); i++) {
			System.out.print(keyList.get(i));
		}
		System.out.println();
		System.out.println("Shuffled List : ");
		for(int i = 0; i < secretList.size(); i++) {
			System.out.print(secretList.get(i));
		}
		System.out.println();
	}
	public void encrypt() {
		System.out.println("Enter what you want to encrypt : ");
		
		
		word = sc.nextLine();
		
		letters = word.toCharArray();
		
		for(int i=0; i < letters.length; i++) {
			for(int j=0; j < keyList.size(); j++) {
				if(letters[i] == keyList.get(j)) {
					letters[i] = secretList.get(j);
					break;
				}
			}
		}
			System.out.println("Encrypted : ");
			for(char i : letters) {
				System.out.print(i);
			}
			System.out.println();
	}
	public void decrypt() {
		System.out.println("Enter what you want to decrypt : ");
		word = sc.nextLine();
		letters = word.toCharArray();
		
		for(int i=0; i < letters.length; i++) {
			for(int j=0; j < secretList.size(); j++) {
				
				if(letters[i] == secretList.get(j)) {
					
					letters[i] = keyList.get(j);
					break;
				}
			}
		}
		System.out.println("Decrypted : ");
		for(char i : letters) {
			System.out.print(i);
		}
		System.out.println();
	}
	public void quit() {
		System.exit(0);
	}
}