package AMP;

import java.io.File;
import java.util.Map;

import javafx.scene.control.Slider;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javazoom.jlgui.basicplayer.BasicPlayerListener;

public class AudioControl implements BasicPlayerListener {

	protected boolean isPlaying;
	protected boolean isPaused;
	protected double volume;
	protected int time;
	protected boolean isShuffle;
	protected boolean isRepeat;

	private BasicPlayer player = new BasicPlayer();
	private BasicController control;


	public static void main(String []args){



	}
	protected BasicController playSelected(File filename, Slider sdVolume){

		control = (BasicController) player;

		player.addBasicPlayerListener(this);

		try{
			if(player.getStatus() == BasicPlayer.STOPPED || player.getStatus() == BasicPlayer.UNKNOWN){

			System.out.println("song is not playing, starting song");

			control.open(filename);
			control.setGain(sdVolume.getValue());
			control.play();
			isPlaying = true;
			}
			else if (player.getStatus() == BasicPlayer.PLAYING){

			System.out.println("song is playing, starting new song");

			control.stop();
			control.open(filename);
			control.setGain(sdVolume.getValue());
			control.play();
			isPlaying = true;
			}
			else if (player.getStatus() == BasicPlayer.PAUSED){

			System.out.println("song is paused, resuming");

			control.resume();

			}
		}
		catch(BasicPlayerException e){
			e.printStackTrace();
		}
		return control;

	}

	protected void stopCurrent(){

	try {
		if(player.getStatus() == BasicPlayer.PAUSED || player.getStatus() == BasicPlayer.PLAYING){

		System.out.println("stopping...");
		control.stop();

		}

		else{
		System.out.println("cannot stop");
		}
	} catch (BasicPlayerException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}

	protected void pauseCurrent(){
		try {
		if(player.getStatus() == BasicPlayer.PLAYING){
		System.out.println("Pausing current song");

		control.pause();

		}
		else{

		System.out.println("cannot pause song");

		}

		} catch (BasicPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void nextPlay(File filename, Slider sdVolume){

		try{

		if (player.getStatus() == BasicPlayer.PLAYING  || player.getStatus() == BasicPlayer.PAUSED){


		System.out.println("song is playing, starting new song");

		control.stop();
		control.open(filename);
		control.setGain(sdVolume.getValue());
		control.play();

		}
		else if (player.getStatus() == BasicPlayer.STOPPED || player.getStatus() == BasicPlayer.UNKNOWN){


			System.out.println("song is playing, starting new song");

			control.open(filename);
			control.setGain(sdVolume.getValue());
			control.play();

			}
		}
		catch(BasicPlayerException e){
			e.printStackTrace();
		}
			}

	protected void shuffleOn(){

	}

	protected void repeatOn(File filename , Slider sdVolume){

	try{
		control.stop();
		control.open(filename);
		control.setGain(sdVolume.getValue());
		control.play();
	}
	catch(BasicPlayerException e){
		e.printStackTrace();
	}

	}

	@Override
	public void opened(Object arg0, Map arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void progress(int arg0, long arg1, byte[] arg2, Map arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setController(BasicController arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stateUpdated(BasicPlayerEvent arg0) {
		// TODO Auto-generated method stub

	}

}
