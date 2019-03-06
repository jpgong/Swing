package jpgong.fileProcess;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Label;
import java.awt.Component;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

/**
 * 在该类中，根据用户输入的扩展名，列出指定文件夹内该类型文件的文件名、文件大小和修改时间
 * @author jpgong
 *
 */
@SuppressWarnings("serial")
public class FileList extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldType;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileList frame = new FileList();
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
	public FileList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 589, 312);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		Label label = new Label("\u8F93\u5165\u6587\u4EF6\u6269\u5C55\u540D\uFF1A");
		label.setFont(new Font("楷体", Font.BOLD, 17));
		panel.add(label);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		panel.add(horizontalGlue);
		
		textFieldType = new JTextField();
		panel.add(textFieldType);
		textFieldType.setColumns(10);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		panel.add(horizontalGlue_1);
		
		JButton btnChoose = new JButton("\u9009\u62E9\u6587\u4EF6\u5939");
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final String fileType = textFieldType.getText();
				if (fileType.isEmpty()) {
					JOptionPane.showMessageDialog(FileList.this, "请输入文件类型！","警告", JOptionPane.WARNING_MESSAGE);
					return;
				}
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);     //设置只能选择文件夹
				fileChooser.setMultiSelectionEnabled(false);   //禁止选择多个文件夹
				int result = fileChooser.showOpenDialog(FileList.this);    //打开文件选择器
				if (result == JFileChooser.APPROVE_OPTION) {
					File[] listFiles = fileChooser.getSelectedFile().listFiles(new FileFilter() {
						
						@Override
						public boolean accept(File pathname) {
							if (pathname.getName().endsWith(fileType)) {
								return true;
							}else {
								return false;
							}
						}
					});           //获得符合条件的文件
					DefaultTableModel model = (DefaultTableModel) table.getModel();    //获得默认表格模式
					//指定日期格式
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					for (File file : listFiles) {
						String name = file.getName();
						long size = file.length()/1024;
						String modifyDate = format.format(new Date(file.lastModified()));
						model.addRow(new String[] {name,""+ size + "KB",modifyDate});
					}
					table.setModel(model);
				}
			}
		});
		btnChoose.setFont(new Font("华文行楷", Font.BOLD, 16));
		panel.add(btnChoose);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		DefaultTableModel tModel = new DefaultTableModel(new String[] {"文件名", "文件大小", "修改时间"}, 0);
		table.setModel(tModel);
		scrollPane.setColumnHeaderView(table);
	}

}
