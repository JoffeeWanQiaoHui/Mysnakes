package com.yc.login;
 
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;

import com.yc.utils.StartFrame; 
public class Login{
	//һ�����ڵĶ���
private JFrame jframe;
//�û��������ǩ
 private JLabel jlabel_user,jlabel_pwd;
 //����ʽ����
 private GridBagLayout gridbag;
 //����
 private GridBagConstraints constraints;
 //�û��ı���
 private JTextField jtfield_user;
 //�����ı���
 private JPasswordField jtfield_pwd;
 //���밴ť
 private JButton jbutton_login;
 //���
 private JPanel jpanel;
 //�û�����
 private String sb_user=new String();
 //��������
 private String sb_pwd=new String();
 public Login(){
  jframe = new JFrame();
  //�û���ǩ
  jlabel_user = new JLabel();
  //�����ǩ
  jlabel_pwd = new JLabel();
  //�û��ı���
  jtfield_user = new JTextField(4);
  //�����ı���
  jtfield_pwd = new JPasswordField(4);
  //���ֵĳ�ʼ��
  gridbag = new GridBagLayout();
  //��ӵ��밴ť
  jbutton_login = new JButton();  
  //��ʼ������ʾ����
  init();
  //���밴ť������
   jbutton_login.addActionListener(new ActionListener() {
	
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		//�û��ı�����̼���
		jtfield_user.addKeyListener(new KeyAdapter() {
			 @Override
			public void keyPressed(KeyEvent e) {
				//sb_user=jtfield_user.getText();
				jtfield_user.grabFocus();
			}
		});
		jtfield_pwd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//sb_pwd=jtfield_pwd.getText();
				jtfield_pwd.grabFocus();
			}
		});
		sb_user=jtfield_user.getText();
		sb_pwd=jtfield_pwd.getText();
		if(sb_user.equals("wan")){
			if(sb_pwd.equals("1222")){
				jframe.dispose();
				StartFrame frame=new StartFrame();
				frame.setVisible(true);
			
				java.awt.EventQueue.invokeLater(new Runnable() {
					
					public void run() {
						//��������������������Э��
						//����������������DefaultLookAndFeelDecorated����Ϊtrue
							JFrame.setDefaultLookAndFeelDecorated(true);
							JDialog.setDefaultLookAndFeelDecorated(true);
					}
				});
				new Timer(4000, new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						frame.dispose();//�رյ�һ������						
					}
				}).start();
			}
		}else{
			JOptionPane.showMessageDialog(null, "�û��������������!!!����������");
		}
	}
});
 }
 /**
  * init()��ʼ������ʾ����
  */ 
 @SuppressWarnings("serial")
private void init(){ 
  jframe.setTitle("̰���ߴ���ս");
  //����||����ͼƬ
  ImageIcon icon1=new ImageIcon("./src/com/yc/images/snake.jpg");
  //��ʾͼ��
  jframe.setIconImage(icon1.getImage()); 
  //���ô���Ϊ���ɱ�,Ϊ�̶���С
  jframe.setResizable(false);
  //���ô��ھ���
  jframe.setBounds(0, 0, 814, 634);
  jframe.setLocationRelativeTo(null);
  //���ùرմ���ʱ,�������
  jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  //�����屳��
  jpanel = new JPanel(){
   @Override
   protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    ImageIcon img = new ImageIcon("./src/com/yc/images/background_2.jpg");
    g.drawImage(img.getImage(), 0, 0,
  		800,  600, null);//������(0,0)��ʼ���л�ͼ	
   }
  };
  
  jlabel_user.setText("�û�����");
  jlabel_pwd.setText("��    �룺");
  jbutton_login.setText("��    ¼");
  //����JPanelΪ͸������ʹ��GridBagLayout���ֹ�����
  jpanel.setOpaque(true);
  //�������Ĳ��ַ�ʽΪ����ʽ
  jpanel.setLayout(gridbag);
  //�������Ĵ�С
  jpanel.setSize(800, 600);
     //�û����ı�����ʾ
     constraints = getGridBagConstraints(0,0,1,1,0,0,GridBagConstraints.CENTER,
        GridBagConstraints.NONE,new Insets(10,0,10,0),0,0);
     
     gridbag.setConstraints(jlabel_user, constraints);
     jpanel.add(jlabel_user);
  
     //�û����������ʾ
     constraints = getGridBagConstraints(1,0,1,1,0,0,GridBagConstraints.CENTER,
        GridBagConstraints.NONE,new Insets(10,0,10,0),100,0);
    
     gridbag.setConstraints(jtfield_user, constraints);
     jpanel.add(jtfield_user);
     
     //�����ı�����ʾ
     constraints = getGridBagConstraints(0,1,1,1,0,0,GridBagConstraints.CENTER,
       GridBagConstraints.NONE,new Insets(10,0,10,0),0,0);
     gridbag.setConstraints(jlabel_pwd, constraints);
     jpanel.add(jlabel_pwd);
 
     //�����������ʾ
     constraints = getGridBagConstraints(1,1,1,1,0,0,GridBagConstraints.CENTER,
       GridBagConstraints.NONE,new Insets(10,0,10,0),100,0);
 
     gridbag.setConstraints(jtfield_pwd, constraints);
     jpanel.add(jtfield_pwd);  
     //��¼��ť��ʾ
     constraints = getGridBagConstraints(1,2,1,1,0,0,GridBagConstraints.CENTER,
       GridBagConstraints.NONE,new Insets(10,0,10,0),0,0);
 
     gridbag.setConstraints(jbutton_login, constraints);
     jpanel.add(jbutton_login);
     //�������ӵ�����
     jframe.add(jpanel);
 	}
 
 private static GridBagConstraints getGridBagConstraints(int gridx,int gridy,int gridwidth,
   int gridheight,double weightx,double weighty,int anchor,int fill,Insets insets,
   int ipadx,int ipady){
 
   return new GridBagConstraints(gridx, gridy, gridwidth, gridheight, weightx, weighty, 
     anchor, fill, insets, ipadx, ipady);
   
 }
 
 public void showMe(){
  jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  jframe.setVisible(true);
 }
}

