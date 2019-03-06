package jpgong.fileProcess;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.Panel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Component;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class FileEditor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5;
	private JPanel contentPane;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileEditor frame = new FileEditor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FileEditor() {
		setFont(new Font("华文行楷", Font.BOLD, 15));
		setTitle("FileEditor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		Panel panel = new Panel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnWriter = new JButton("\u5199\u5165\u6587\u672C");
		btnWriter.addActionListener(new ActionListener() {
			@SuppressWarnings("null")
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int select = fileChooser.showSaveDialog(FileEditor.this);
				String fileName = null;
				if (select == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();   //如果这里没有获取任何的文件，下面的getName（）会放回手输入的文件名
					fileName = fileChooser.getName(file);
					if (fileName != null || fileName.trim().length() > 0) {
						if (file.isFile()) {
							fileName = file.getName();
						}
						file = fileChooser.getCurrentDirectory();
						file = new File(file.getPath().concat(File.separator).concat(fileName));
						if (file.exists()) {    //若存在，询问是否要覆盖
							int i = JOptionPane.showConfirmDialog(FileEditor.this, "该文件已存在，确定要覆盖吗？");
							if (i == JOptionPane.YES_OPTION) {
								
							}else {
								return;
							}
						}
						try {
							file.createNewFile();
							FileWriter fileWriter = new FileWriter(file);
							String s = textArea.getText();
							fileWriter.write(s);
							fileWriter.close();
							JOptionPane.showMessageDialog(FileEditor.this, "保存成功", "成功", JOptionPane.PLAIN_MESSAGE);
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(FileEditor.this, "出错：" + e1.getMessage());
							return;
						}
					}else {
						JOptionPane.showMessageDialog(FileEditor.this, "文件名为空", "警告", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
			}
		});
		btnWriter.setFont(new Font("楷体", Font.BOLD, 15));
		panel.add(btnWriter);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		panel.add(horizontalGlue);
		
		JButton btnReade = new JButton("\u8BFB\u53D6\u6587\u672C");
		btnReade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.showOpenDialog(FileEditor.this);
				
				try {
					FileReader fileReader = new FileReader(fileChooser.getSelectedFile());
					textArea.setText("");
					char[] buf = new char[1024];
					int len = fileReader.read(buf);
					textArea.setText(new String(buf, 0, len));
					fileReader.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnReade.setFont(new Font("楷体", Font.BOLD, 15));
		panel.add(btnReade);
		
		JButton button = new JButton("\u6E05\u7A7A\u6587\u672C\u533A\u57DF");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});
		button.setFont(new Font("楷体", Font.BOLD, 15));
		panel.add(button);
		
		textArea = new JTextArea();
		contentPane.add(textArea, BorderLayout.CENTER);
	}

}
