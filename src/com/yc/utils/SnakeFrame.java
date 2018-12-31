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
	//游戏面板的宽度
	private static final int WIDTH=800;
	//长度
	private static final int HEIGHT=600;
	//单元格大小
	private static final int CELL = 20;
	private int dir=1;//用来控制蛇头移动的方向1：向右-1向左2：向上-2：向下
	//存储水果的数组
	private String [] fruitName={"apple.png","cherry.png","grape.png",
			"orange.png","peach.png","strawberry.png","tomato.png"};
	//用来存储蛇头以及蛇的身体
	private LinkedList<JLabel> bodies=new LinkedList<JLabel>();
	//存储各种蛇身体片段的数组
	private String [] snakeBody={"body_1.png","body_2.png","body_3.png","body_4.png","body_5.png",
			"body_6.png","body_7.png","body_8.png"};
	private JLabel currentLabel; // 显示当前得分
	private JLabel maxLabel;
	private int currentScore;// 当前得分
	private int maxScore;//最高得分
	private JLabel snakeHeader;
	private JLabel fruit;
	@SuppressWarnings("unused")
	private JLabel faultJLabel;
	//生成随机数的工具
	Random random=new Random();
	//对界面进行初始化
	public SnakeFrame(){
		// 确保一个漂亮的外观风格
		SnakeFrame.setDefaultLookAndFeelDecorated(true);
		//设置标题
		setTitle("贪吃蛇大作战");
		//设置图标
		ImageIcon icon=new ImageIcon("./src/com/yc/images/snake.jpg");//存入||导入图片
		//显示图标
		this.setIconImage(icon.getImage());
		//设置窗口的大小
		this.setSize(WIDTH+4, HEIGHT+34);
		//设置窗口为不可变,为固定大小
		this.setResizable(false);
		//加了这个程序运行,但是会卡死
		//设为子窗口居上,且不能超做父窗口
//		this.setAlwaysOnTop(true);		
		//设置窗口居中                                                                                                                          
		this.setLocationRelativeTo(null);
		//设置关闭窗口是，停止运行游戏
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置为绝对布局
		this.setLayout(null);
		//将游戏面板添加到窗口中
		SnakePanel snakePanel=new SnakePanel();//创建面板
		//控制位置和大小
		//前面设置位置,后面设置面板的大小,前面的0和0代表从窗口的左端点坐标（0,0）开始
		snakePanel.setBounds(0, 0, WIDTH, HEIGHT);
		//添加到面板
		this.add(snakePanel);
		//给窗口设定一个监听事件
		this.addKeyListener(new KeyAdapter() {//适配器
			@Override
			public void keyPressed(KeyEvent e) {
				int code=e.getKeyCode();//得到按键对应的码值
				switch (code) {
				case KeyEvent.VK_RIGHT:
					if(dir!=snakeDirection.LEFT)
					{
						dir=snakeDirection.RIGHT;
						break;
					}
				case KeyEvent.VK_LEFT:
					//只有当前方向不是向右时才可以改变运动方向向左
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
		//设置窗口为可见
		this.setVisible(true);
		//先调用一次
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
	 * 采用内部类的好处
	 * 可以直接访问外部类的所用成员
	 * 游戏主面板(游戏区域)
	 */
	class SnakePanel extends JPanel{
	    private Image offScreenImage;  //图形缓存		
		public SnakePanel(){
			init();//可见的面板
			this.setSize(SnakeFrame.WIDTH,SnakeFrame.HEIGHT); 
			this.setLayout(null);
			
		}
		private void init() {
			
			//添加当前得分
			currentLabel = new JLabel("当前得分："+currentScore);
			maxLabel=new JLabel("最高的分: "+maxScore);
			//标签的位置及大小
			currentLabel.setBounds(20, 20, 300, 30);
			//maxLabel.setBounds(x, y, width, height);
			maxLabel.setBounds(20, 50, 300, 30);
//			设置标签的背景颜色
//			currentLabel.setBackground(Color.green);
//			设置为不透明，不然不可见
//			currentLabel.setOpaque(true);
			//设置为透明
			currentLabel.setOpaque(false);
			maxLabel.setOpaque(false);
			//为标签设置字体
			Font font=new Font("宋体", Font.BOLD, 16);
			currentLabel.setFont(font);
			maxLabel.setFont(font);
			//为标签文字添加颜色
			currentLabel.setForeground(Color.blue);
			maxLabel.setForeground(Color.red);
			//添加到面板中
			this.add(currentLabel);
			this.add(maxLabel);
			//随机生成蛇头
			creatHeader();
			//使用多线程,防止卡死
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					creatfruit();
				}
			}).start();;
			//使用定时任务让蛇头移动                      
			new Timer(180, new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// 蛇头移动的逻辑
					Point oldPoint=snakeHeader.getLocation();//蛇头原来的位置
					Point newPoint=null;//蛇头移动过去的位置
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
					//将蛇头移动到新的位置
					snakeHeader.setLocation(newPoint);
					//每次蛇头移动后
					//其他身体节点也必须跟着移动,都可能出现：撞墙,或者吃到水果
					heatWall(newPoint);
					//如果没有撞墙,判断能不能吃到水果
					if(snakeHeader.getLocation().equals(fruit.getLocation())){
						try {
							eatFruit();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					//不管吃没有吃到水果,蛇的身体部分必须跟着蛇头移动
					//需要将蛇头的原来位置传过去
					move(oldPoint);
				}
			}).start();
			
		}
		/*
		 * 让蛇的身体跟随蛇头的移动方向
		 */
		private void move(Point oldPoint){
			Point p=new Point();
			//从蛇的第二节开始,每一节都要跟随前一节的移动
			for(int i=1;i<bodies.size();i++){
				//先记录下前一节身体的位置
				p=bodies.get(i).getLocation();
				//再移动到前一节身体的位置
				bodies.get(i).setLocation(oldPoint);
				//改变oldPoint的值为当前节点的原来位置
				oldPoint=p;
			}
		}
		/*
		 * 吃水果的方法
		 */
		private void eatFruit() throws Exception{
			//将吃掉的水果随机转化为蛇身体一种颜色
			int index=random.nextInt(snakeBody.length);
			//将水果的图片转化设置为成蛇身体的图片
			setBackgroundImage(fruit, snakeBody[index]);
			//将水果添加到蛇的身体
			bodies.add(fruit);
			//播放音乐
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					PlayMusic.playEatFruit();
				}
			}).start();
			//原来的水果被吃掉了,创建一个新的水果
			//使用多线程,防止程序卡死
//			new Thread(new Runnable() {
//				
//				@Override
//				public void run() { 
//					creatfruit();
//				}
//			}).start();
			//加分
			currentScore++;
			//设置当前得分
			currentLabel.setText("当前得分："+currentScore);
			//设置最高的分
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
					maxLabel.setText("最高得分: "+maxScore);					
				}else{
					FileInputStream fis=new FileInputStream("d:/max.txt");
					BufferedInputStream bis=new BufferedInputStream(fis);
					DataInputStream dis=new DataInputStream(bis);
					maxScore=dis.readInt();
					maxLabel.setText("最高得分: "+maxScore);	
				}
			}else{
				FileInputStream fis=new FileInputStream("d:/max.txt");
				BufferedInputStream bis=new BufferedInputStream(fis);
				DataInputStream dis=new DataInputStream(bis);
				maxScore=dis.readInt();
				maxLabel.setText("最高得分: "+maxScore);
			}
			//这是lambda表达式
			new Thread(()->{creatfruit();}).start();			
			
		}
		
		/*
		 * 判断是否撞墙了
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
				//消息提示框
				String output="挑战失败\n\n继续挑战吧！！！";
				JOptionPane.showMessageDialog(null, output);
				System.exit(0);
				
			}
		}
		/*
		 * 创建蛇头并添加到面板中
		 */
		private void creatHeader(){
			snakeHeader=new JLabel();//实例化蛇头
			//设置尺寸蛇头
			snakeHeader.setSize(CELL, CELL);
			//设置图片
			setBackgroundImage(snakeHeader, "header_r.png");
			//确定蛇头的位置
			Point p=randomPoint();
			snakeHeader.setLocation(p);
			//将蛇头添加到Linkedlist中,即将蛇头添加蛇的身体中
			bodies.add(snakeHeader);
		
			//添加到面板
			this.add(snakeHeader);
		}
		private void creatfruit(){
			fruit=new JLabel();
			fruit.setSize(CELL, CELL);
			//设置背景图
			//随机生成水果图标的索引
			int index=random.nextInt(fruitName.length);
			//设置图片
			setBackgroundImage(fruit, fruitName[index]);
			//设置随机生成位置(水果不能与水果重叠)
			//随机生成的点可能不可用，一直到点生成的可用为止
			//随机生成一个点,不确定这个点的位置,先设一个点
			Point p=null;
			//不知到什么时候找到,所以用true
			boolean isValid= true;
			while(isValid){
				p=randomPoint();
				isValid=!isValidPoint(p);
			}
			fruit.setLocation(p);
			//将水果添加到面板里
			this.add(fruit);
			this.repaint();
		}
		/*
		 * 判断随机生成的水果是否可用
		 */
		private boolean isValidPoint(Point p){
			//将这个点循环与蛇的身体进行比较
			boolean flag=true;//假设这个点可用
			for(JLabel body : bodies){ //加强for循环
				if(p.equals(body))//说明蛇的身体与水果重合
				{
					flag=false;
					break;
				}
			}
			return flag;
		}
		/*
		 * 给指定JLabel设置背景图
		 * @param label 制定标签组件
		 * @param fileName 图片文件名称
		 */
		private void setBackgroundImage(JLabel label,String fileName){
			ImageIcon icon=new ImageIcon("./src/com/yc/images/"+fileName);
			icon.setImage(icon.getImage().
					getScaledInstance(CELL, CELL, Image.SCALE_DEFAULT));
			label.setIcon(icon);
		}

		/*                                                                                                                                        
		 * 随机生成一个点
		 */
		private Point randomPoint(){
			int x = random.nextInt(SnakeFrame.WIDTH/20)*20;
			int y = random.nextInt(SnakeFrame.HEIGHT/20)*20;
			return new Point(x,y);
		}
		/*生成一个画笔，绘画出背景图片
		 * (non-Javadoc)
		 * @see java.awt.Container#paintComponents(java.awt.Graphics)
		 */
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			ImageIcon icon=new ImageIcon("./src/com/yc/images/background.jpg");
			g.drawImage(icon.getImage(), 0, 0,
					SnakeFrame.WIDTH,  SnakeFrame.HEIGHT, null);//从坐标(0,0)开始进行绘图			
		}		
		//重写update方法实现双缓冲，解决闪烁问题
		public void update(Graphics g){  
				if(offScreenImage == null)  
					//新建一个图像缓存空间,这里图像大小为WIDTH*HEIGHT 
		            offScreenImage = this.createImage(WIDTH, HEIGHT);
					//把它的画笔拿过来,给gImage保存着 
		            Graphics gImage = offScreenImage.getGraphics();
		            //将要画的东西画到图像缓存空间去  
		            paint(gImage);
		            //然后一次性显示出来
		            g.drawImage(offScreenImage, 0, 0, null);           
		  }
		public void animation(){
			try {
					//更新画面
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
