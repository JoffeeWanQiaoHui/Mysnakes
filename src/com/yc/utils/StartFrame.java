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
		//��Ҫ�����������Σ���Ҫ��ֹһ�´������쳣
		setUndecorated(true);
		//���ô��ڴ�С
		this.setSize(814,634);
		ImageIcon icon=new ImageIcon("./src/com/yc/images/snake.jpg");//����||����ͼƬ
		//��ʾͼ��
		this.setIconImage(icon.getImage());		
		//��ʱȥ��
		//������
		//this.setFocusable(false);
		// ȷ��һ��Ư������۷��
		StartFrame.setDefaultLookAndFeelDecorated(true);
		//���ô���Ϊ���ɱ�,Ϊ�̶���С
		this.setResizable(false);
		//���ô��ھ���
		this.setLocationRelativeTo(null);
		JPanel p=new JPanel();//���
		JLabel JL=new JLabel();//��ǩ
		ImageIcon icon2=new ImageIcon("./src/com/yc/images/background_1.png");
		icon2.setImage(icon2.getImage().getScaledInstance(800, 600, Image.SCALE_DEFAULT));
		JL.setIcon(icon2);
		p.add(JL,new Integer(Integer.MIN_VALUE));
		getContentPane().add(p);
		pack(); //������Ӧ�����С
		//���ùرմ����ǣ�ֹͣ������Ϸ
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel jp=new JPanel();//���
		final JButton jb1=new JButton("������Ϸ");
		//���ð�ť�������ɫ
		jb1.setForeground(Color.green);
		//����λ�úʹ�С		
		jb1.setBounds( 0, 0, 35, 30);
		// ���ð�ť�ޱ߿�
		jb1.setBorderPainted(false);
		//ȥ������
		jb1.setFocusPainted(false);
		//��ť��Ϊ��͸��
		jb1.setContentAreaFilled(true);
		//���ñ�����ɫ
		jb1.setBackground(Color.black);
		//���ñ���ͼƬ,��ͼƬ����Ӧ
		ImageIcon icon3=new ImageIcon("./src/com/yc/images/start.jpg");
		//final JButton jb1=new JButton(icon3);//("������Ϸ");
		//jb1.setBounds(0, 0, 30, 30);
		icon3.getImage();
		Image temp=icon3.getImage().getScaledInstance(jb1.getWidth(),jb1.getHeight(),Image.SCALE_DEFAULT);
		icon3=new ImageIcon(temp);
		jb1.setIcon(icon3);
		 // ���ð�ť�ޱ߿�    
		jb1.setBorderPainted(false);
		//���ð�ť
		final JButton jb2=new JButton("�˳���Ϸ");
		//����λ�úʹ�С
		jb2.setBounds( 0, 0, 35, 30);
		//���ð�ť����ͼƬ
		ImageIcon icon4=new ImageIcon("./src/com/yc/images/exit.jpg");
		icon4.getImage();
		Image temp1=icon4.getImage().getScaledInstance(jb2.getWidth(),jb2.getHeight(),Image.SCALE_DEFAULT);
		icon4=new ImageIcon(temp1);
		jb2.setIcon(icon4);
		// ���ð�ť�ޱ߿�
		jb2.setBorderPainted(false);
		//���ð�ťȥ����
		jb2.setFocusPainted(false);
		//���ð�ť�������ɫ
		jb2.setForeground(Color.RED);
		//���ð�ť�ı�����ɫ
		jb2.setBackground(Color.black);
		//���ð�ť������͸��
		jb2.setContentAreaFilled(true);
		JL.setLayout(new FlowLayout());
		//��ť��ӵ����
		JL.add(jb1);
        JL.add(jb2);
        
        //���ð�ť������
        jb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	new SnakeFrame();
            }
        });
        jb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.add(jp);//��������Ӹ����
	}

}
