package com.yc.utils;
import java.applet.Applet;
//用来播放音乐的工具类
import java.applet.AudioClip;
import java.net.URL;
public class PlayMusic {
	//背景音乐
	private static AudioClip bgmAudio;
	//结束音乐
	private static AudioClip overAudio;
	//吃到水果时的音乐
	private static AudioClip lifeAudio;
	public static void playBGM(){
		//获取音乐文件的路径
		URL url=PlayMusic.class.getResource("../music/bg.wav");
		bgmAudio=Applet.newAudioClip(url);
		bgmAudio.play();
	}
	public static void stopBGM(){
		bgmAudio.stop();
	}
	/*
	 * 播放gameover音效
	 */
	public static void playGameOver(){
		//获取音乐文件的路径
		URL url=PlayMusic.class.getResource("../music/over.wav");
		//创建声音剪辑对象
		overAudio=Applet.newAudioClip(url);
		//播放音乐即可 
		overAudio.play();
	}
	/*
	 * 吃到水果时的音效
	 */
	public static void playEatFruit(){
		//获取音乐文件的路径
		URL url=PlayMusic.class.getResource("../music/eat.wav");
		//创建音乐剪辑对象
		lifeAudio=Applet.newAudioClip(url);
		//播放音乐即可
		lifeAudio.play();
	}
}
