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

	private static BasicPlayer player = new BasicPlayer();
	private BasicController control;

	protected BasicController playSelected(File filename, Slider sdVolume){

		control = (BasicController) player;

		player.addBasicPlayerListener(this);

		try{
			if(player.getStatus() == BasicPlayer.STOPPED || player.getStatus() == BasicPlayer.UNKNOWN){

			System.out.println("song is not playing, starting song");

			control.open(filename);
			control.setGain(100);
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

	protected static BasicPlayer getPlayer(){
		return player;

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
	public void opened(Object stream, Map properties) {
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
