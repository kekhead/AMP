package AMP;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.stage.FileChooser;

public class MenuFile
{
	private static FileChooser addFileChooser;

	private static List<File> loadedFiles;

	protected static void addFile(){
		addFileChooser = new FileChooser();

		addFileChooser.setTitle("Choose Audio File");
		addFileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        addFileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All audio", "*.*"),
                new FileChooser.ExtensionFilter("MP3", "*.mp3"),
                new FileChooser.ExtensionFilter("WAV", "*.wav"),
                new FileChooser.ExtensionFilter("FLAC", "*.flac"),
                new FileChooser.ExtensionFilter("OGG", "*.ogg"),
                new FileChooser.ExtensionFilter("AIFF", "*.ogg"),
                new FileChooser.ExtensionFilter("MONKEY'S AUDIO", "*.ape"),
                new FileChooser.ExtensionFilter("AU", "*.au")
            );

		loadedFiles = addFileChooser.showOpenMultipleDialog(MainPane.stage);

		System.out.println(loadedFiles);
	}

	protected void addFolder(){

	}

	protected void loadPlaylist(){

	}

	protected static List getLoaded(){
		return loadedFiles;
	}

}
