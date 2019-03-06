package jpgong.JDBCStudent;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Label;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class InsertFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldId;
	private JTextField textFieldAge;
	private JTextField textFieldClass;
	@SuppressWarnings("unused")
	private MainFrame mainFrame;
	private JCheckBox checkBox1;
	private JCheckBox checkBox2;
	private JCheckBox checkBox3;
	private JCheckBox checkBox4;
	private JCheckBox checkBox5;
	private JCheckBox checkBox6;
	private JTextField textFieName;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxSex;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public InsertFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		setFont(new Font("华文行楷", Font.BOLD, 16));
		setTitle("\u6DFB\u52A0\u5B66\u751F\u4FE1\u606F");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 658, 407);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnAdd = new JButton("\u6DFB\u52A0");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {    //添加学生信息事件响应
				StuDao stuDao = new StuDao();
				Student student = new Student();
				StringBuffer buffer = new StringBuffer();
				if (checkBox1.isSelected()) {        //判断复选框是否被选中
					buffer.append(checkBox1.getText() + "、");
				}
				if (checkBox2.isSelected()) {
					buffer.append(checkBox2.getText() + "、");
				}
				if (checkBox3.isSelected()) {
					buffer.append(checkBox3.getText() + "、");
				}
				if (checkBox4.isSelected()) {
					buffer.append(checkBox4.getText() + "、");
				}
				if (checkBox5.isSelected()) {
					buffer.append(checkBox5.getText() + "、");
				}
				if (checkBox6.isSelected()) {
					buffer.append(checkBox6.getText() + "、");
				}
				String hobby = buffer.toString();    //获得选中复选框中的爱好
				student.setId(Integer.parseInt(textFieldId.getText()));
				student.setName(textFieName.getText());
				student.setSex(comboBoxSex.getSelectedItem().toString());
				student.setAge(Integer.parseInt(textFieldAge.getText()));
				student.setHobby(hobby);
				student.setSclass(textFieldClass.getText());
				//判断学生信息是否添加完整
				if ((!textFieName.getText().equals(""))
						&& (!textFieldAge.getText().equals(""))
						&& (!textFieldId.getText().equals(""))
						&& (!textFieldClass.getText().equals(""))) {
					stuDao.addStudent(student);     //不为空，执行添加命令
					JOptionPane.showMessageDialog(getContentPane(), "数据添加成功！", "信息提示框", JOptionPane.WARNING_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "请将信息添加完整！", "信息提示框", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnAdd.setFont(new Font("楷体", Font.BOLD, 17));
		panel.add(btnAdd);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		panel.add(horizontalGlue);
		
		JButton btnNewButton_1 = new JButton("\u5173\u95ED");
		btnNewButton_1.addActionListener((e) ->{
			mainFrame.setVisible(true);
			this.setVisible(false);
			
			StuDao stuDao = new StuDao();
			List<Student> list = stuDao.listAllStudent();
			JTable table = mainFrame.getTable();
			DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
			defaultTableModel.setRowCount(0);      //清空表格模型中的数据
			for (int i = 0; i < list.size(); i++) {
				Student student = list.get(i);
				defaultTableModel.addRow(new Object[] {
						student.getId(),student.getName(),student.getSex(),student.getAge(),
						student.getHobby(),student.getSclass()});
			}
			table.setModel(defaultTableModel);
		});
		btnNewButton_1.setFont(new Font("楷体", Font.BOLD, 17));
		panel.add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		Label label = new Label("\u5B66\u53F7\uFF1A");
		label.setFont(new Font("华文细黑", Font.BOLD, 16));
		label.setBounds(49, 24, 50, 23);
		panel_1.add(label);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(105, 26, 429, 21);
		panel_1.add(textFieldId);
		textFieldId.setColumns(10);
		
		Label label_2 = new Label("\u6027\u522B\uFF1A");
		label_2.setFont(new Font("华文细黑", Font.BOLD, 16));
		label_2.setBounds(49, 123, 50, 23);
		panel_1.add(label_2);
		
		Label label_1 = new Label("\u5E74\u9F84\uFF1A");
		label_1.setFont(new Font("华文细黑", Font.BOLD, 16));
		label_1.setBounds(349, 117, 50, 23);
		panel_1.add(label_1);
		
		comboBoxSex = new JComboBox();
		comboBoxSex.setFont(new Font("华文细黑", Font.BOLD, 16));
		comboBoxSex.setModel(new DefaultComboBoxModel(new String[] {"\u7537", "\u5973", "\u672A\u77E5"}));
		comboBoxSex.setBounds(105, 117, 77, 29);
		panel_1.add(comboBoxSex);
		
		textFieldAge = new JTextField();
		textFieldAge.setBounds(405, 119, 129, 21);
		panel_1.add(textFieldAge);
		textFieldAge.setColumns(10);
		
		Label label_3 = new Label("\u7231\u597D\uFF1A");
		label_3.setFont(new Font("华文细黑", Font.BOLD, 16));
		label_3.setBounds(49, 169, 50, 23);
		panel_1.add(label_3);
		
		checkBox1 = new JCheckBox("\u65C5\u6E38");
		checkBox1.setFont(new Font("华文细黑", Font.BOLD, 16));
		checkBox1.setBounds(105, 169, 103, 23);
		panel_1.add(checkBox1);
		
		checkBox2 = new JCheckBox("\u770B\u4E66");
		checkBox2.setFont(new Font("华文细黑", Font.BOLD, 16));
		checkBox2.setBounds(232, 169, 103, 23);
		panel_1.add(checkBox2);
		
		checkBox3 = new JCheckBox("\u97F3\u4E50");
		checkBox3.setFont(new Font("华文细黑", Font.BOLD, 16));
		checkBox3.setBounds(388, 169, 103, 23);
		panel_1.add(checkBox3);
		
		checkBox4 = new JCheckBox("\u4E0A\u7F51");
		checkBox4.setFont(new Font("华文细黑", Font.BOLD, 16));
		checkBox4.setBounds(105, 212, 103, 23);
		panel_1.add(checkBox4);
		
		checkBox5 = new JCheckBox("\u7FBD\u6BDB\u7403");
		checkBox5.setFont(new Font("华文细黑", Font.BOLD, 16));
		checkBox5.setBounds(232, 212, 103, 23);
		panel_1.add(checkBox5);
		
		checkBox6 = new JCheckBox("\u4E52\u4E53\u7403");
		checkBox6.setFont(new Font("华文细黑", Font.BOLD, 16));
		checkBox6.setBounds(388, 212, 103, 23);
		panel_1.add(checkBox6);
		
		Label label_4 = new Label("\u73ED\u7EA7\uFF1A");
		label_4.setFont(new Font("华文细黑", Font.BOLD, 16));
		label_4.setBounds(49, 250, 50, 23);
		panel_1.add(label_4);
		
		textFieldClass = new JTextField();
		textFieldClass.setColumns(10);
		textFieldClass.setBounds(105, 250, 429, 21);
		panel_1.add(textFieldClass);
		
		Label label_5 = new Label("\u59D3\u540D\uFF1A");
		label_5.setFont(new Font("华文细黑", Font.BOLD, 16));
		label_5.setBounds(49, 72, 50, 23);
		panel_1.add(label_5);
		
		textFieName = new JTextField();
		textFieName.setColumns(10);
		textFieName.setBounds(105, 74, 429, 21);
		panel_1.add(textFieName);
	}
}
