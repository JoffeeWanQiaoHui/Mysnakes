package com.yc.utils;
import java.applet.Applet;
//�����������ֵĹ�����
import java.applet.AudioClip;
import java.net.URL;
public class PlayMusic {
	//��������
	private static AudioClip bgmAudio;
	//��������
	private static AudioClip overAudio;
	//�Ե�ˮ��ʱ������
	private static AudioClip lifeAudio;
	public static void playBGM(){
		//��ȡ�����ļ���·��
		URL url=PlayMusic.class.getResource("../music/bg.wav");
		bgmAudio=Applet.newAudioClip(url);
		bgmAudio.play();
	}
	public static void stopBGM(){
		bgmAudio.stop();
	}
	/*
	 * ����gameover��Ч
	 */
	public static void playGameOver(){
		//��ȡ�����ļ���·��
		URL url=PlayMusic.class.getResource("../music/over.wav");
		//����������������
		overAudio=Applet.newAudioClip(url);
		//�������ּ��� 
		overAudio.play();
	}
	/*
	 * �Ե�ˮ��ʱ����Ч
	 */
	public static void playEatFruit(){
		//��ȡ�����ļ���·��
		URL url=PlayMusic.class.getResource("../music/eat.wav");
		//�������ּ�������
		lifeAudio=Applet.newAudioClip(url);
		//�������ּ���
		lifeAudio.play();
	}
}
