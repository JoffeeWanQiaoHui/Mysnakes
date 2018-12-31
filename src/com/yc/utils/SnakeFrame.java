package com.yc.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class SnakeFrame extends JFrame{
	//��Ϸ���Ŀ��
	private static final int WIDTH=800;
	//����
	private static final int HEIGHT=600;
	//��Ԫ���С
	private static final int CELL = 20;
	private int dir=1;//����������ͷ�ƶ��ķ���1������-1����2������-2������
	//�洢ˮ��������
	private String [] fruitName={"apple.png","cherry.png","grape.png",
			"orange.png","peach.png","strawberry.png","tomato.png"};
	//�����洢��ͷ�Լ��ߵ�����
	private LinkedList<JLabel> bodies=new LinkedList<JLabel>();
	//�洢����������Ƭ�ε�����
	private String [] snakeBody={"body_1.png","body_2.png","body_3.png","body_4.png","body_5.png",
			"body_6.png","body_7.png","body_8.png"};
	private JLabel currentLabel; // ��ʾ��ǰ�÷�
	private JLabel maxLabel;
	private int currentScore;// ��ǰ�÷�
	private int maxScore;//��ߵ÷�
	private JLabel snakeHeader;
	private JLabel fruit;
	@SuppressWarnings("unused")
	private JLabel faultJLabel;
	//����������Ĺ���
	Random random=new Random();
	//�Խ�����г�ʼ��
	public SnakeFrame(){
		// ȷ��һ��Ư������۷��
		SnakeFrame.setDefaultLookAndFeelDecorated(true);
		//���ñ���
		setTitle("̰���ߴ���ս");
		//����ͼ��
		ImageIcon icon=new ImageIcon("./src/com/yc/images/snake.jpg");//����||����ͼƬ
		//��ʾͼ��
		this.setIconImage(icon.getImage());
		//���ô��ڵĴ�С
		this.setSize(WIDTH+4, HEIGHT+34);
		//���ô���Ϊ���ɱ�,Ϊ�̶���С
		this.setResizable(false);
		//���������������,���ǻῨ��
		//��Ϊ�Ӵ��ھ���,�Ҳ��ܳ���������
//		this.setAlwaysOnTop(true);		
		//���ô��ھ���                                                                                                                          
		this.setLocationRelativeTo(null);
		//���ùرմ����ǣ�ֹͣ������Ϸ
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//����Ϊ���Բ���
		this.setLayout(null);
		//����Ϸ�����ӵ�������
		SnakePanel snakePanel=new SnakePanel();//�������
		//����λ�úʹ�С
		//ǰ������λ��,�����������Ĵ�С,ǰ���0��0����Ӵ��ڵ���˵����꣨0,0����ʼ
		snakePanel.setBounds(0, 0, WIDTH, HEIGHT);
		//��ӵ����
		this.add(snakePanel);
		//�������趨һ�������¼�
		this.addKeyListener(new KeyAdapter() {//������
			@Override
			public void keyPressed(KeyEvent e) {
				int code=e.getKeyCode();//�õ�������Ӧ����ֵ
				switch (code) {
				case KeyEvent.VK_RIGHT:
					if(dir!=snakeDirection.LEFT)
					{
						dir=snakeDirection.RIGHT;
						break;
					}
				case KeyEvent.VK_LEFT:
					//ֻ�е�ǰ����������ʱ�ſ��Ըı��˶���������
					if(dir!=snakeDirection.RIGHT){
						dir=snakeDirection.LEFT;
						break;
					}
				case KeyEvent.VK_UP:
					if(dir!=snakeDirection.DOWN)
					{
						dir=snakeDirection.UP;
						break;
					}
				case KeyEvent.VK_DOWN:
					if(dir!=snakeDirection.UP){
						dir=snakeDirection.DOWN;
						break;
					}
				default:
					break;
				}
			}
		});
		//���ô���Ϊ�ɼ�
		this.setVisible(true);
		//�ȵ���һ��
		new Thread(new Runnable() {
			
			@Override
			public void run() {
					PlayMusic.playBGM();
			}
		}).start();;
		new Timer(60000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PlayMusic.playBGM();
			}
		}).start();
	}
	/*
	 * �����ڲ���ĺô�
	 * ����ֱ�ӷ����ⲿ������ó�Ա
	 * ��Ϸ�����(��Ϸ����)
	 */
	class SnakePanel extends JPanel{
	    private Image offScreenImage;  //ͼ�λ���		
		public SnakePanel(){
			init();//�ɼ������
			this.setSize(SnakeFrame.WIDTH,SnakeFrame.HEIGHT); 
			this.setLayout(null);
			
		}
		private void init() {
			
			//��ӵ�ǰ�÷�
			currentLabel = new JLabel("��ǰ�÷֣�"+currentScore);
			maxLabel=new JLabel("��ߵķ�: "+maxScore);
			//��ǩ��λ�ü���С
			currentLabel.setBounds(20, 20, 300, 30);
			//maxLabel.setBounds(x, y, width, height);
			maxLabel.setBounds(20, 50, 300, 30);
//			���ñ�ǩ�ı�����ɫ
//			currentLabel.setBackground(Color.green);
//			����Ϊ��͸������Ȼ���ɼ�
//			currentLabel.setOpaque(true);
			//����Ϊ͸��
			currentLabel.setOpaque(false);
			maxLabel.setOpaque(false);
			//Ϊ��ǩ��������
			Font font=new Font("����", Font.BOLD, 16);
			currentLabel.setFont(font);
			maxLabel.setFont(font);
			//Ϊ��ǩ���������ɫ
			currentLabel.setForeground(Color.blue);
			maxLabel.setForeground(Color.red);
			//��ӵ������
			this.add(currentLabel);
			this.add(maxLabel);
			//���������ͷ
			creatHeader();
			//ʹ�ö��߳�,��ֹ����
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					creatfruit();
				}
			}).start();;
			//ʹ�ö�ʱ��������ͷ�ƶ�                      
			new Timer(180, new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// ��ͷ�ƶ����߼�
					Point oldPoint=snakeHeader.getLocation();//��ͷԭ����λ��
					Point newPoint=null;//��ͷ�ƶ���ȥ��λ��
					switch (dir) {
					case snakeDirection.LEFT :
						newPoint=new Point(oldPoint.x-CELL, oldPoint.y);
						setBackgroundImage(snakeHeader, "snakehead_l.png");
						break;
					case snakeDirection.RIGHT :
						newPoint=new Point(oldPoint.x+CELL, oldPoint.y);
						setBackgroundImage(snakeHeader, "snakehead_r.png");
						break;
					case snakeDirection.UP :
						newPoint=new Point(oldPoint.x, oldPoint.y-CELL);
						setBackgroundImage(snakeHeader, "snakehead_u.png");
						break;
					case snakeDirection.DOWN :
						newPoint=new Point(oldPoint.x, oldPoint.y+CELL);
						setBackgroundImage(snakeHeader, "snakehead_d.png");
						break;
					default:
						break;
					}
					//����ͷ�ƶ����µ�λ��
					snakeHeader.setLocation(newPoint);
					//ÿ����ͷ�ƶ���
					//��������ڵ�Ҳ��������ƶ�,�����ܳ��֣�ײǽ,���߳Ե�ˮ��
					heatWall(newPoint);
					//���û��ײǽ,�ж��ܲ��ܳԵ�ˮ��
					if(snakeHeader.getLocation().equals(fruit.getLocation())){
						try {
							eatFruit();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					//���ܳ�û�гԵ�ˮ��,�ߵ����岿�ֱ��������ͷ�ƶ�
					//��Ҫ����ͷ��ԭ��λ�ô���ȥ
					move(oldPoint);
				}
			}).start();
			
		}
		/*
		 * ���ߵ����������ͷ���ƶ�����
		 */
		private void move(Point oldPoint){
			Point p=new Point();
			//���ߵĵڶ��ڿ�ʼ,ÿһ�ڶ�Ҫ����ǰһ�ڵ��ƶ�
			for(int i=1;i<bodies.size();i++){
				//�ȼ�¼��ǰһ�������λ��
				p=bodies.get(i).getLocation();
				//���ƶ���ǰһ�������λ��
				bodies.get(i).setLocation(oldPoint);
				//�ı�oldPoint��ֵΪ��ǰ�ڵ��ԭ��λ��
				oldPoint=p;
			}
		}
		/*
		 * ��ˮ���ķ���
		 */
		private void eatFruit() throws Exception{
			//���Ե���ˮ�����ת��Ϊ������һ����ɫ
			int index=random.nextInt(snakeBody.length);
			//��ˮ����ͼƬת������Ϊ���������ͼƬ
			setBackgroundImage(fruit, snakeBody[index]);
			//��ˮ����ӵ��ߵ�����
			bodies.add(fruit);
			//��������
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					PlayMusic.playEatFruit();
				}
			}).start();
			//ԭ����ˮ�����Ե���,����һ���µ�ˮ��
			//ʹ�ö��߳�,��ֹ������
//			new Thread(new Runnable() {
//				
//				@Override
//				public void run() { 
//					creatfruit();
//				}
//			}).start();
			//�ӷ�
			currentScore++;
			//���õ�ǰ�÷�
			currentLabel.setText("��ǰ�÷֣�"+currentScore);
			//������ߵķ�
			if(currentScore>maxScore){
				File file=new File("D:/max.txt");
				if(file.exists()&&file.length()==0){
					FileOutputStream fos=new FileOutputStream("d:/max.txt");
					BufferedOutputStream bos=new BufferedOutputStream(fos);
					DataOutputStream dos=new DataOutputStream(bos);
					dos.writeInt(currentScore);
					dos.close();
					FileInputStream fis=new FileInputStream("d:/max.txt");
					BufferedInputStream bis=new BufferedInputStream(fis);
					DataInputStream dis=new DataInputStream(bis);
					maxScore=dis.readInt();
					maxLabel.setText("��ߵ÷�: "+maxScore);					
				}else{
					FileInputStream fis=new FileInputStream("d:/max.txt");
					BufferedInputStream bis=new BufferedInputStream(fis);
					DataInputStream dis=new DataInputStream(bis);
					maxScore=dis.readInt();
					maxLabel.setText("��ߵ÷�: "+maxScore);	
				}
			}else{
				FileInputStream fis=new FileInputStream("d:/max.txt");
				BufferedInputStream bis=new BufferedInputStream(fis);
				DataInputStream dis=new DataInputStream(bis);
				maxScore=dis.readInt();
				maxLabel.setText("��ߵ÷�: "+maxScore);
			}
			//����lambda���ʽ
			new Thread(()->{creatfruit();}).start();			
			
		}
		
		/*
		 * �ж��Ƿ�ײǽ��
		 */
		private void heatWall(Point newPoint){
			int x= newPoint.x;
			int y=newPoint.y;
			if(x<0||x>780||y<0||y>580){
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						PlayMusic.stopBGM();
						PlayMusic.playGameOver();
					}
				}).start();
				//��Ϣ��ʾ��
				String output="��սʧ��\n\n������ս�ɣ�����";
				JOptionPane.showMessageDialog(null, output);
				System.exit(0);
				
			}
		}
		/*
		 * ������ͷ����ӵ������
		 */
		private void creatHeader(){
			snakeHeader=new JLabel();//ʵ������ͷ
			//���óߴ���ͷ
			snakeHeader.setSize(CELL, CELL);
			//����ͼƬ
			setBackgroundImage(snakeHeader, "header_r.png");
			//ȷ����ͷ��λ��
			Point p=randomPoint();
			snakeHeader.setLocation(p);
			//����ͷ��ӵ�Linkedlist��,������ͷ����ߵ�������
			bodies.add(snakeHeader);
		
			//��ӵ����
			this.add(snakeHeader);
		}
		private void creatfruit(){
			fruit=new JLabel();
			fruit.setSize(CELL, CELL);
			//���ñ���ͼ
			//�������ˮ��ͼ�������
			int index=random.nextInt(fruitName.length);
			//����ͼƬ
			setBackgroundImage(fruit, fruitName[index]);
			//�����������λ��(ˮ��������ˮ���ص�)
			//������ɵĵ���ܲ����ã�һֱ�������ɵĿ���Ϊֹ
			//�������һ����,��ȷ��������λ��,����һ����
			Point p=null;
			//��֪��ʲôʱ���ҵ�,������true
			boolean isValid= true;
			while(isValid){
				p=randomPoint();
				isValid=!isValidPoint(p);
			}
			fruit.setLocation(p);
			//��ˮ����ӵ������
			this.add(fruit);
			this.repaint();
		}
		/*
		 * �ж�������ɵ�ˮ���Ƿ����
		 */
		private boolean isValidPoint(Point p){
			//�������ѭ�����ߵ�������бȽ�
			boolean flag=true;//������������
			for(JLabel body : bodies){ //��ǿforѭ��
				if(p.equals(body))//˵���ߵ�������ˮ���غ�
				{
					flag=false;
					break;
				}
			}
			return flag;
		}
		/*
		 * ��ָ��JLabel���ñ���ͼ
		 * @param label �ƶ���ǩ���
		 * @param fileName ͼƬ�ļ�����
		 */
		private void setBackgroundImage(JLabel label,String fileName){
			ImageIcon icon=new ImageIcon("./src/com/yc/images/"+fileName);
			icon.setImage(icon.getImage().
					getScaledInstance(CELL, CELL, Image.SCALE_DEFAULT));
			label.setIcon(icon);
		}

		/*                                                                                                                                        
		 * �������һ����
		 */
		private Point randomPoint(){
			int x = random.nextInt(SnakeFrame.WIDTH/20)*20;
			int y = random.nextInt(SnakeFrame.HEIGHT/20)*20;
			return new Point(x,y);
		}
		/*����һ�����ʣ��滭������ͼƬ
		 * (non-Javadoc)
		 * @see java.awt.Container#paintComponents(java.awt.Graphics)
		 */
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			ImageIcon icon=new ImageIcon("./src/com/yc/images/background.jpg");
			g.drawImage(icon.getImage(), 0, 0,
					SnakeFrame.WIDTH,  SnakeFrame.HEIGHT, null);//������(0,0)��ʼ���л�ͼ			
		}		
		//��дupdate����ʵ��˫���壬�����˸����
		public void update(Graphics g){  
				if(offScreenImage == null)  
					//�½�һ��ͼ�񻺴�ռ�,����ͼ���СΪWIDTH*HEIGHT 
		            offScreenImage = this.createImage(WIDTH, HEIGHT);
					//�����Ļ����ù���,��gImage������ 
		            Graphics gImage = offScreenImage.getGraphics();
		            //��Ҫ���Ķ�������ͼ�񻺴�ռ�ȥ  
		            paint(gImage);
		            //Ȼ��һ������ʾ����
		            g.drawImage(offScreenImage, 0, 0, null);           
		  }
		public void animation(){
			try {
					//���»���
					update(getGraphics());
					Thread.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			update(getGraphics());
		}
		
		
		
		
	}
}
