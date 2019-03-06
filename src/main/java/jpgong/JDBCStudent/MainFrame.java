package jpgong.JDBCStudent;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Label;
import java.util.List;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel defaultTableModel;
	private StuDao stuDao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setFont(new Font("����", Font.BOLD, 14));
		setTitle("\u4FE1\u606F\u7BA1\u7406");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 703, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnAdd = new JButton("\u6DFB\u52A0");
		btnAdd.addActionListener((e) ->{    //��Ӱ�ť��Ӧ�¼�
				InsertFrame insertFrame = new InsertFrame(this);
				//ʹ���ڿɼ�
				insertFrame.setVisible(true);
				insertFrame.setSize(new Dimension(700,600));
				this.setVisible(false);
		});
		
		btnAdd.setFont(new Font("����", Font.BOLD, 16));
		panel.add(btnAdd);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		panel.add(horizontalGlue);
		
		JButton btnUpdate = new JButton("\u4FEE\u6539");
		btnUpdate.addActionListener((e) ->{    //�޸İ�ť��Ӧ�¼�
			//���Jtable��ѡ���е�ѧ����Ϣ
			int row = table.getSelectedRow();
			int id = (int) table.getValueAt(row, 0);
			String name = table.getValueAt(row, 1).toString();
			String sex = table.getValueAt(row, 2).toString();
			int age = (int) table.getValueAt(row, 3);
			String hobby = table.getValueAt(row, 4).toString();
			String sclass = table.getValueAt(row, 5).toString();
			
			Student student = new Student();
			student.setId(id);
			student.setName(name);
			student.setAge(age);
			student.setSex(sex);
			student.setHobby(hobby);
			student.setSclass(sclass);
			
			UpdateFrame updateFrame = new UpdateFrame(this,student);
			//ʹ���ڿɼ�
			updateFrame.setVisible(true);
			updateFrame.setSize(new Dimension(700,600));
			this.setVisible(false);
		});
		btnUpdate.setFont(new Font("����", Font.BOLD, 16));
		panel.add(btnUpdate);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		panel.add(horizontalGlue_1);
		
		JButton btnDelete = new JButton("\u5220\u9664");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {   //ִ��ɾ��ѧ����Ϣ����
				//���Jtable��ѡ���е�ѧ��ѧ����Ϣ
				int row = table.getSelectedRow();
				int id = (int) table.getValueAt(row, 0);
				int result = JOptionPane.showConfirmDialog(getContentPane(), "ȷ��ɾ����", "ɾ���Ի���", JOptionPane.YES_NO_CANCEL_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					stuDao.deleteStudent(id);
					JOptionPane.showMessageDialog(getContentPane(), "ɾ���ɹ���", "��ʾ�Ի���", JOptionPane.WARNING_MESSAGE);
				}
				//����Jtable�е�����
				List<Student> list = stuDao.listAllStudent();
				defaultTableModel.setRowCount(0);      //��ձ��ģ���е�����
				for (int i = 0; i < list.size(); i++) {
					Student student = list.get(i);
					defaultTableModel.addRow(new Object[] {
							student.getId(),student.getName(),student.getSex(),student.getAge(),
							student.getHobby(),student.getSclass()});
				}
				table.setModel(defaultTableModel);
			}
		});
		btnDelete.setFont(new Font("����", Font.BOLD, 16));
		panel.add(btnDelete);
		
		Component horizontalGlue_2 = Box.createHorizontalGlue();
		panel.add(horizontalGlue_2);
		
		JButton btnClose = new JButton("\u5173\u95ED");
		btnClose.addActionListener((e) ->{    //�رմ���
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			System.exit(0);
		});
		btnClose.setFont(new Font("����", Font.BOLD, 16));
		panel.add(btnClose);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		Label label = new Label("\u5B66\u751F\u4FE1\u606F");
		label.setForeground(Color.RED);
		label.setFont(new Font("�����п�", Font.BOLD, 24));
		panel_1.add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setFont(new Font("����", Font.BOLD, 17));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u5B66\u53F7", "\u59D3\u540D", "\u6027\u522B", "\u5E74\u9F84", "\u7231\u597D", "\u73ED\u7EA7"
			}
		));
		scrollPane.setViewportView(table);
		stuDao = new StuDao();
		List<Student> list = stuDao.listAllStudent();
		defaultTableModel=((DefaultTableModel) table.getModel());
		defaultTableModel.setRowCount(0);      //��ձ��ģ���е�����
		for (int i = 0; i < list.size(); i++) {
			Student student = list.get(i);
			defaultTableModel.addRow(new Object[] {
					student.getId(),student.getName(),student.getSex(),student.getAge(),
					student.getHobby(),student.getSclass()});
		}
		table.setModel(defaultTableModel);
	}

	public JTable getTable() {
		return table;
	}
	



}
