package com.yc.utils;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class StartFrame extends JFrame{
	public StartFrame(){
		//不要标题栏的修饰，主要防止一下代码抛异常
		setUndecorated(true);
		//设置窗口大小
		this.setSize(814,634);
		ImageIcon icon=new ImageIcon("./src/com/yc/images/snake.jpg");//存入||导入图片
		//显示图标
		this.setIconImage(icon.getImage());		
		//暂时去掉
		//父窗口
		//this.setFocusable(false);
		// 确保一个漂亮的外观风格
		StartFrame.setDefaultLookAndFeelDecorated(true);
		//设置窗口为不可变,为固定大小
		this.setResizable(false);
		//设置窗口居中
		this.setLocationRelativeTo(null);
		JPanel p=new JPanel();//面板
		JLabel JL=new JLabel();//标签
		ImageIcon icon2=new ImageIcon("./src/com/yc/images/background_1.png");
		icon2.setImage(icon2.getImage().getScaledInstance(800, 600, Image.SCALE_DEFAULT));
		JL.setIcon(icon2);
		p.add(JL,new Integer(Integer.MIN_VALUE));
		getContentPane().add(p);
		pack(); //窗口适应组件大小
		//设置关闭窗口是，停止运行游戏
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel jp=new JPanel();//面板
		final JButton jb1=new JButton("进入游戏");
		//设置按钮字体的颜色
		jb1.setForeground(Color.green);
		//设置位置和大小		
		jb1.setBounds( 0, 0, 35, 30);
		// 设置按钮无边框
		jb1.setBorderPainted(false);
		//去掉焦点
		jb1.setFocusPainted(false);
		//按钮设为不透明
		jb1.setContentAreaFilled(true);
		//设置背景颜色
		jb1.setBackground(Color.black);
		//设置背景图片,并图片自适应
		ImageIcon icon3=new ImageIcon("./src/com/yc/images/start.jpg");
		//final JButton jb1=new JButton(icon3);//("进入游戏");
		//jb1.setBounds(0, 0, 30, 30);
		icon3.getImage();
		Image temp=icon3.getImage().getScaledInstance(jb1.getWidth(),jb1.getHeight(),Image.SCALE_DEFAULT);
		icon3=new ImageIcon(temp);
		jb1.setIcon(icon3);
		 // 设置按钮无边框    
		jb1.setBorderPainted(false);
		//设置按钮
		final JButton jb2=new JButton("退出游戏");
		//设置位置和大小
		jb2.setBounds( 0, 0, 35, 30);
		//设置按钮背景图片
		ImageIcon icon4=new ImageIcon("./src/com/yc/images/exit.jpg");
		icon4.getImage();
		Image temp1=icon4.getImage().getScaledInstance(jb2.getWidth(),jb2.getHeight(),Image.SCALE_DEFAULT);
		icon4=new ImageIcon(temp1);
		jb2.setIcon(icon4);
		// 设置按钮无边框
		jb2.setBorderPainted(false);
		//设置按钮去焦点
		jb2.setFocusPainted(false);
		//设置按钮字体的颜色
		jb2.setForeground(Color.RED);
		//设置按钮的背景颜色
		jb2.setBackground(Color.black);
		//设置按钮背景不透明
		jb2.setContentAreaFilled(true);
		JL.setLayout(new FlowLayout());
		//按钮添加到面板
		JL.add(jb1);
        JL.add(jb2);
        
        //设置按钮监听器
        jb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	new SnakeFrame();
            }
        });
        jb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.add(jp);//窗口中添加该面板
	}

}
