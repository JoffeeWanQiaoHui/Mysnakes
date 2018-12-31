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
	//一个窗口的定义
private JFrame jframe;
//用户和密码标签
 private JLabel jlabel_user,jlabel_pwd;
 //网格式布局
 private GridBagLayout gridbag;
 //限制
 private GridBagConstraints constraints;
 //用户文本框
 private JTextField jtfield_user;
 //密码文本框
 private JPasswordField jtfield_pwd;
 //登入按钮
 private JButton jbutton_login;
 //面板
 private JPanel jpanel;
 //用户输入
 private String sb_user=new String();
 //密码输入
 private String sb_pwd=new String();
 public Login(){
  jframe = new JFrame();
  //用户标签
  jlabel_user = new JLabel();
  //密码标签
  jlabel_pwd = new JLabel();
  //用户文本框
  jtfield_user = new JTextField(4);
  //密码文本框
  jtfield_pwd = new JPasswordField(4);
  //布局的初始化
  gridbag = new GridBagLayout();
  //添加登入按钮
  jbutton_login = new JButton();  
  //初始化并显示界面
  init();
  //登入按钮监听器
   jbutton_login.addActionListener(new ActionListener() {
	
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		//用户文本框键盘监听
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
						//另外如果想让整体界面变得协调
						//最好设置容器窗体的DefaultLookAndFeelDecorated属性为true
							JFrame.setDefaultLookAndFeelDecorated(true);
							JDialog.setDefaultLookAndFeelDecorated(true);
					}
				});
				new Timer(4000, new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						frame.dispose();//关闭第一个窗口						
					}
				}).start();
			}
		}else{
			JOptionPane.showMessageDialog(null, "用户名或者密码错误!!!请重新输入");
		}
	}
});
 }
 /**
  * init()初始化并显示界面
  */ 
 @SuppressWarnings("serial")
private void init(){ 
  jframe.setTitle("贪吃蛇大作战");
  //存入||导入图片
  ImageIcon icon1=new ImageIcon("./src/com/yc/images/snake.jpg");
  //显示图标
  jframe.setIconImage(icon1.getImage()); 
  //设置窗口为不可变,为固定大小
  jframe.setResizable(false);
  //设置窗口居中
  jframe.setBounds(0, 0, 814, 634);
  jframe.setLocationRelativeTo(null);
  //设置关闭窗口时,程序结束
  jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  //添加面板背景
  jpanel = new JPanel(){
   @Override
   protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    ImageIcon img = new ImageIcon("./src/com/yc/images/background_2.jpg");
    g.drawImage(img.getImage(), 0, 0,
  		800,  600, null);//从坐标(0,0)开始进行绘图	
   }
  };
  
  jlabel_user.setText("用户名：");
  jlabel_pwd.setText("密    码：");
  jbutton_login.setText("登    录");
  //设置JPanel为透明，且使用GridBagLayout布局管理器
  jpanel.setOpaque(true);
  //设置面板的布局方式为网格式
  jpanel.setLayout(gridbag);
  //设置面板的大小
  jpanel.setSize(800, 600);
     //用户名文本框显示
     constraints = getGridBagConstraints(0,0,1,1,0,0,GridBagConstraints.CENTER,
        GridBagConstraints.NONE,new Insets(10,0,10,0),0,0);
     
     gridbag.setConstraints(jlabel_user, constraints);
     jpanel.add(jlabel_user);
  
     //用户名输入框显示
     constraints = getGridBagConstraints(1,0,1,1,0,0,GridBagConstraints.CENTER,
        GridBagConstraints.NONE,new Insets(10,0,10,0),100,0);
    
     gridbag.setConstraints(jtfield_user, constraints);
     jpanel.add(jtfield_user);
     
     //密码文本框显示
     constraints = getGridBagConstraints(0,1,1,1,0,0,GridBagConstraints.CENTER,
       GridBagConstraints.NONE,new Insets(10,0,10,0),0,0);
     gridbag.setConstraints(jlabel_pwd, constraints);
     jpanel.add(jlabel_pwd);
 
     //密码输入框显示
     constraints = getGridBagConstraints(1,1,1,1,0,0,GridBagConstraints.CENTER,
       GridBagConstraints.NONE,new Insets(10,0,10,0),100,0);
 
     gridbag.setConstraints(jtfield_pwd, constraints);
     jpanel.add(jtfield_pwd);  
     //登录按钮显示
     constraints = getGridBagConstraints(1,2,1,1,0,0,GridBagConstraints.CENTER,
       GridBagConstraints.NONE,new Insets(10,0,10,0),0,0);
 
     gridbag.setConstraints(jbutton_login, constraints);
     jpanel.add(jbutton_login);
     //将面板添加到窗口
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

