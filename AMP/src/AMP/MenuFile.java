package AMP;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class MenuFile
{
	private static FileChooser addFileChooser;
	private static DirectoryChooser addFolderChooser;

	private static ArrayList<File> loadedFiles = new ArrayList<File>();

	private static File loadedFolder;
		private static File[] folderFiles;

	private static ArrayList<TableNames> fileNames = new ArrayList<TableNames>();


	protected static void addFile(){
		addFileChooser = new FileChooser();

		addFileChooser.setTitle("Choose Audio File");
		addFileChooser.setInitialDirectory(new File(System.getProperty("user.home")));


		//File extension filters (audio files supported)
        addFileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All audio", "*.mp3", "*.wav","*.flac","*.ogg","*.aiff","*.ape","*.au"),
                new FileChooser.ExtensionFilter("MP3", "*.mp3"),
                new FileChooser.ExtensionFilter("WAV", "*.wav"),
                new FileChooser.ExtensionFilter("FLAC", "*.flac"),
                new FileChooser.ExtensionFilter("OGG", "*.ogg"),
                new FileChooser.ExtensionFilter("AIFF", "*.aiff"),
                new FileChooser.ExtensionFilter("MONKEY'S AUDIO", "*.ape"),
                new FileChooser.ExtensionFilter("AU", "*.au")
            );

        //Add in list
        try{
        	loadedFiles.addAll(addFileChooser.showOpenMultipleDialog(MainPane.stage));
        }
		catch(Exception ex){
			System.out.println("No file selected");
			return;
		}

        //Remove duplicates
			LinkedHashSet<File> lhs = new LinkedHashSet<File>();
			lhs.addAll(loadedFiles);

			loadedFiles.clear();
			loadedFiles.addAll(lhs);

		//Add string names
		arrayToString();
	}

	protected static void addFolder(){
		addFolderChooser = new DirectoryChooser();

		addFolderChooser.setTitle("Choose Audio File");
		addFolderChooser.setInitialDirectory(new File(System.getProperty("user.home")));

		//Open window to load folder
		loadedFolder = addFolderChooser.showDialog(MainPane.stage);

		//Add folder contents to File array

		try{
			folderFiles = loadedFolder.listFiles(new FilenameFilter(){

				@Override
				//Filter the contents (audio file supported)
				public boolean accept(File file, String name) {
					String lowercaseName = name.toLowerCase();
					if(lowercaseName.endsWith(".mp3") || lowercaseName.endsWith(".flac")
							|| lowercaseName.endsWith(".wav") || lowercaseName.endsWith(".ape")
							|| lowercaseName.endsWith(".ogg") || lowercaseName.endsWith(".aiff")
							|| lowercaseName.endsWith(".au")){
						return true;
					}
					else return false;
				}

			});
		}
		catch(Exception ex){
			System.out.println("No directory selected");
			return;
		}

		//Add files to List
		loadedFiles.addAll(Arrays.asList(folderFiles));

		//Remove duplicates to ensure only one of each file in ArrayList
			LinkedHashSet<File> lhs = new LinkedHashSet<File>();
			lhs.addAll(loadedFiles);

			loadedFiles.clear();
			loadedFiles.addAll(lhs);

		//Set string names
		arrayToString();

	}

	protected void loadPlaylist(){

	}

	protected static void arrayToString(){
		fileNames.clear();

		for(File f : loadedFiles){
			int pos = f.getName().lastIndexOf(".");

			fileNames.add(new TableNames("", f.getName().substring(0, pos), ""));
		}

	}

	protected static ArrayList<File> getLoaded(){
		return loadedFiles;
	}

	protected static ArrayList<TableNames> getLoadedNames(){
		return fileNames;
	}

}
