package AMP;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class MainPane extends Application {
	protected static Stage stage;

	private BorderPane mnPane;

	private VBox topBox;
		private Pane topPane; //Top control pane
		private Pane spectrumAnim;
			private Rectangle placeholder;

	private MenuBar topMenu; //MenuBar and menus.
		private Menu menuFile, menuView, menuAbout;
			private MenuItem addFile, addFolder, loadPlaylist;

			private MenuItem loadPreset, viewSpectrum, viewOsci;
			private CheckMenuItem disableAnimLogo;
			private MenuItem displayAbout;

	private HBox audioControl; //HBox for button and slider HBox
		private HBox buttonControl;
		private HBox sliderControl; //Slider HBox

		private Button btPlay; private Button btStop; private Button btPause;
		private Button btPrev; private Button btNext;
		private Button btShuffle; private Button btRepeat;

		private ImageView btPlayImg; private ImageView btStopImg; private ImageView btPauseImg;
		private ImageView btPrevImg; private ImageView btNextImg;
		private ImageView btShuffleImg; private ImageView btRepeatImg;

		private Slider sdVolume; private Slider sdSeek; //Volume and seek sliders

	private TableView<TableNames> libraryView;
		private TableColumn playingColumn;
		private TableColumn titleColumn;
		private TableColumn durationColumn;

	private VBox leftBox;
		Image coverImage;
		ImageView coverImgView;
			Rectangle imgBox;

		ListView infoView;

	private ArrayList<File> loadedFiles = new ArrayList<File>();

	private AudioControl controlClass = new AudioControl();
	private BasicController control;
	private BasicPlayer player;
	
	private MenuBar RightMenu;
		private MenuItem Remove, Properties;


	@Override
	public void start(Stage primaryStage) {
			stage = primaryStage;

			mnPane = new BorderPane(); // Main pane

			topBox = new VBox(); //Top box create
				topPane = new Pane(); //Top Pane create
				spectrumAnim = new Pane(); //Top spectrum animation pane.

			//Top menu (File, View, About)

			topMenu = new MenuBar();
				menuFile = new Menu("File");
					addFile = new MenuItem("Add File");

					//Add file button action

						addFile.setOnAction(e->{

							MenuFile.addFile();
							libraryView.setItems(FXCollections.observableArrayList(MenuFile.getLoadedNames()));
							loadedFiles = MenuFile.getLoaded();
						});
					addFolder = new MenuItem("Add Folder");
					//Add folder button action
						addFolder.setOnAction(e->{
							MenuFile.addFolder();
							libraryView.setItems(FXCollections.observableArrayList(MenuFile.getLoadedNames()));
							loadedFiles = MenuFile.getLoaded();

							System.out.println(loadedFiles);
						});

					loadPlaylist = new MenuItem("Load Playlist");
						loadPlaylist.setOnAction(e->{
							//TODO Playlist implementation
						});

				menuFile.getItems().addAll(addFile, addFolder, loadPlaylist);

				menuView = new Menu("View");
					loadPreset = new MenuItem("Load preset..");
					viewSpectrum = new MenuItem("View Spectrum");
					viewOsci = new MenuItem("View Oscilloscope");
					disableAnimLogo = new CheckMenuItem("Animated Logo");
						disableAnimLogo.setSelected(true);

				menuView.getItems().addAll(loadPreset, viewSpectrum, viewOsci, disableAnimLogo);

				menuAbout = new Menu("About");
					displayAbout = new MenuItem("About");

				menuAbout.getItems().add(displayAbout);

			topMenu.prefWidthProperty().bind(primaryStage.widthProperty());

			topMenu.getMenus().addAll(menuFile, menuView, menuAbout); //Add the menus to the MenuBar


			audioControl = new HBox(); //Button HBox and Slider HBox

			buttonControl = new HBox();
			sliderControl = new HBox();

			//Buttons and Tooltips
				btPlay = new Button(); btPlay.setTooltip(new Tooltip("Play"));
					btPlayImg = new ImageView("resources/buttons/play.png");
						btPlayImg.setFitHeight(27); btPlayImg.setFitWidth(27);
						btPlay.setGraphic(btPlayImg);

				btStop = new Button(); btStop.setTooltip(new Tooltip("Stop"));
					btStopImg = new ImageView("resources/buttons/stop.png");
						btStopImg.setFitHeight(27); btStopImg.setFitWidth(27);
						btStop.setGraphic(btStopImg);

				btPause = new Button();  btPause.setTooltip(new Tooltip("Pause"));
					btPauseImg = new ImageView("resources/buttons/pause.png");
						btPauseImg.setFitHeight(27); btPauseImg.setFitWidth(27);
						btPause.setGraphic(btPauseImg);

				btPrev = new Button(); btPrev.setTooltip(new Tooltip("Previous"));
					btPrevImg = new ImageView("resources/buttons/previous.png");
						btPrevImg.setFitHeight(27); btPrevImg.setFitWidth(27);
						btPrev.setGraphic(btPrevImg);

				btNext = new Button();  btNext.setTooltip(new Tooltip("Next"));
					btNextImg = new ImageView("resources/buttons/next.png");
						btNextImg.setFitHeight(27); btNextImg.setFitWidth(27);
						btNext.setGraphic(btNextImg);

				btShuffle = new Button(); btShuffle.setTooltip(new Tooltip("Shuffle"));
					btShuffleImg = new ImageView("resources/buttons/shuffle.png");
						btShuffleImg.setFitHeight(27); btShuffleImg.setFitWidth(27);
						btShuffle.setGraphic(btShuffleImg);

				btRepeat = new Button(); btRepeat.setTooltip(new Tooltip("Repeat"));
					btRepeatImg = new ImageView("resources/buttons/repeat.png");
						btRepeatImg.setFitHeight(27); btRepeatImg.setFitWidth(27);
						btRepeat.setGraphic(btRepeatImg);


			//Sliders
				sdVolume = new Slider(0,1,1); sdVolume.setPadding(new Insets(0,10,0,5));
					sdVolume.setPrefWidth(175);
					sdVolume.setValue(1); sdVolume.setMax(1);

					sdVolume.setBlockIncrement(0.02);

					sdVolume.valueProperty().addListener(e->{
						try {
							control.setGain(sdVolume.getValue());
							System.out.println(player.getGainValue());
						} catch (Exception e1) {
							if(!controlClass.isPlaying)
								return;
							else e1.printStackTrace();
						}
					});

				sdSeek = new Slider(); sdSeek.setPadding(new Insets(0,0,0,10));
					sdSeek.setPrefWidth(200);
					
					sdSeek.valueProperty().addListener(e->{
						try{
							player = AudioControl.getPlayer();
							long x = (long) sdVolume.getValue();
							player.seek(x);
							
						}catch (Exception e1) {
							if(!controlClass.isPlaying)
								return;
							else e1.printStackTrace();
						}
						
					});
				
			//Add sliders to HBox
			sliderControl.getChildren().addAll(sdVolume, sdSeek);
			sliderControl.setAlignment(Pos.CENTER);


			//Add elements to the HBox.
			buttonControl.getChildren().addAll(btStop, btPause, btPlay, btPrev, btNext, btShuffle, btRepeat);
			buttonControl.setPadding(new Insets(0,0,0,75));


			audioControl.getChildren().addAll(buttonControl, sliderControl);
			audioControl.setPadding(new Insets(0,175,0,0));

			audioControl.setAlignment(Pos.TOP_RIGHT);
			audioControl.setLayoutX(150);

			//Bind the top bar to the stage.
			audioControl.prefWidthProperty().bind(primaryStage.widthProperty());

			//Create placeholder rectangle

			placeholder = new Rectangle(950, 55);
				placeholder.setFill(Color.WHITE);
				placeholder.widthProperty().bind(primaryStage.widthProperty());

				 topMenu.setPrefHeight(35);

			//Add MenuBar and HBox to the pane.
			topPane.getChildren().addAll(topMenu, audioControl);



			spectrumAnim.getChildren().add(placeholder);

			topBox.getChildren().addAll(topPane, spectrumAnim);


			//Table View for library.

			libraryView = new TableView<TableNames>();
			libraryView.setEditable(false);

			//Max width

				//Create columns
				playingColumn = new TableColumn("Playing");
					playingColumn.setMinWidth(Control.USE_PREF_SIZE);

				playingColumn.setStyle("-fx-alignment: BASELINE_CENTER");

				playingColumn.setCellValueFactory(
					    new PropertyValueFactory<TableNames,String>("playing")
				);

				titleColumn = new TableColumn("Title");

					titleColumn.setMinWidth(435);
					titleColumn.setPrefWidth(440);
					titleColumn.prefWidthProperty().bind(libraryView.widthProperty().subtract(160));

				titleColumn.setCellValueFactory(
					    new PropertyValueFactory<TableNames,String>("title")
				);

				durationColumn = new TableColumn("Duration");
					durationColumn.setMinWidth(Control.USE_PREF_SIZE);

				durationColumn.setStyle("-fx-alignment: BASELINE_RIGHT");

				durationColumn.setCellValueFactory(
					    new PropertyValueFactory<TableNames,String>("duration")
				);


			//Add columns and empty placeholder.
			libraryView.getColumns().addAll(playingColumn, titleColumn, durationColumn);
			libraryView.setPlaceholder(new Text(""));


			//Image and Info
			leftBox = new VBox();
				imgBox = new Rectangle(312,312);
					imgBox.setFill(Color.WHITE); imgBox.setStroke(Color.LIGHTGREY);

				coverImgView = new ImageView();
				infoView = new ListView<String>();
					infoView.setPrefHeight(848);


			leftBox.getChildren().addAll(imgBox, infoView);

			//Add to main pane BorderPane
			mnPane.setTop(topBox);
			mnPane.setCenter(libraryView); mnPane.setAlignment(libraryView, Pos.CENTER_RIGHT);
			mnPane.setLeft(leftBox);

			//Launch settings.
			Scene scene = new Scene(mnPane, audioControl.getPrefWidth(), libraryView.getHeight());

			scene.getStylesheets().add("resources/style.css");

			primaryStage.setTitle("AMP");
			primaryStage.setScene(scene);

			primaryStage.widthProperty().addListener(e->{
				sliderControl.setPadding(new Insets(0,0,2,primaryStage.getWidth()/6));
			});


				primaryStage.setMinWidth(1050);
				primaryStage.setMaxHeight(848); primaryStage.setMinHeight(750);

			primaryStage.show();

			primaryStage.setOnCloseRequest(e->{
					try {
						System.exit(0);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			});

			btPlay.setOnAction(e->{
				player = AudioControl.getPlayer();
				try {
					if(player.getStatus() == BasicPlayer.PAUSED){
							control.resume();
					}
					else if(player.getStatus() == BasicPlayer.PLAYING || player.getStatus() == BasicPlayer.PAUSED){
						control = controlClass.playSelected
								(loadedFiles.get(libraryView.getSelectionModel().getSelectedIndex()), sdVolume);
						
							control.play();
					}
					else if(player.getStatus() == BasicPlayer.STOPPED || player.getStatus() == BasicPlayer.UNKNOWN){
						if(libraryView.getItems().isEmpty() || libraryView.getSelectionModel().getSelectedIndex() == -1){
							return;
						}
						else {
							control = controlClass.playSelected
								(loadedFiles.get(libraryView.getSelectionModel().getSelectedIndex()), sdVolume);
					
							control.play();
							
							
						}
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			});
			btStop.setOnAction(e->{
				try{
					player = AudioControl.getPlayer();
					if(player == null){ return;}

					else if (player.getStatus() == BasicPlayer.PLAYING){
						control.stop();
					}
				}
				catch(Exception e2){
					e2.printStackTrace();
				}
			});

			btPause.setOnAction(e->{
				try {
					player = AudioControl.getPlayer();
					if(player == null){ return;}

					else if(player.getStatus() == BasicPlayer.PLAYING){
						control.pause();
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			
			btNext.setOnAction(e->{
				try{
					player = AudioControl.getPlayer();
					if(player == null){ return;}
					
					if(player.getStatus() == BasicPlayer.PLAYING && libraryView.getSelectionModel().getSelectedIndex() 
							!= loadedFiles.size()-1 || player.getStatus() == BasicPlayer.PAUSED && libraryView.getSelectionModel().getSelectedIndex() 
									!= loadedFiles.size()-1
							|| player.getStatus() == BasicPlayer.STOPPED && libraryView.getSelectionModel().getSelectedIndex() 
							!= loadedFiles.size()-1){
					
						control = controlClass.nextPlay
								(loadedFiles.get(libraryView.getSelectionModel().getSelectedIndex() + 1), sdVolume);
												control.play();
						libraryView.getSelectionModel().select(libraryView.getSelectionModel().getSelectedIndex() + 1);
						
					}
					
				}
				catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});

			btPrev.setOnAction(e->{
				try{
					player = AudioControl.getPlayer();
					if(player == null){ return;}
					
					if(player.getStatus() == BasicPlayer.PLAYING && libraryView.getSelectionModel().getSelectedIndex() 
							!= 0|| player.getStatus() == BasicPlayer.PAUSED && libraryView.getSelectionModel().getSelectedIndex() 
									!= 0
							|| player.getStatus() == BasicPlayer.STOPPED && libraryView.getSelectionModel().getSelectedIndex() 
							!= 0){
					
						control = controlClass.nextPlay
								(loadedFiles.get(libraryView.getSelectionModel().getSelectedIndex() - 1), sdVolume);
						
						libraryView.getSelectionModel().select(libraryView.getSelectionModel().getSelectedIndex() - 1);
						
							control.play();
						
					}
					
				}
				catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			
			btRepeat.setOnAction(e->{
				try{
					player = AudioControl.getPlayer();
					if(player == null){ return;}
					
					if(player.getStatus() == BasicPlayer.PLAYING || player.getStatus() == BasicPlayer.PAUSED
							|| player.getStatus() == BasicPlayer.STOPPED ){
					
						control = controlClass.repeatOn
								(loadedFiles.get(libraryView.getSelectionModel().getSelectedIndex()), sdVolume);
						
							control.play();
						
					}
					
				}
				catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			
			btShuffle.setOnAction(e->{
				try{
					player = AudioControl.getPlayer();
					if(player == null){ return;}
					
					if(player.getStatus() == BasicPlayer.PLAYING || player.getStatus() == BasicPlayer.PAUSED
							|| player.getStatus() == BasicPlayer.STOPPED ){
						
						Random rand = new Random();
						int value = rand.nextInt(loadedFiles.size());
						
						control = controlClass.shuffleOn
								(loadedFiles.get(value), sdVolume);
						
						libraryView.getSelectionModel().select(value);
						
						control.play();
						
					}
					
				}
				catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
	        libraryView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
	        	//Get file name without extension
		          //  if (libraryView.getSelectionModel().getSelectedItem() != null) {

					//	File file = loadedFiles.get(libraryView.getSelectionModel().getSelectedIndex());
					//	int pos = file.getName().lastIndexOf(".");
						//System.out.println(file.getName().substring(0, pos));
		           // }
	        });
	        
	        libraryView.setRowFactory( tv -> {
		          TableRow<TableNames> row = new TableRow<TableNames>();
		          row.setOnMouseClicked(event -> {
		        try {
		              if (event.getButton() == MouseButton.SECONDARY && (! row.isEmpty()) ) {
		            	  
		            	  MenuBar infomain = new MenuBar();
							Menu info = new Menu();
							MenuItem info2 = new MenuItem(), remove = new MenuItem();
							info.getItems().addAll(info2,remove);
							infomain.getMenus().add(info);
							
						Pane paneInfo = new Pane();
						paneInfo.getChildren().add(infomain);
						
						paneInfo.setLayoutX(event.getX());
						paneInfo.setLayoutY(event.getY());
							
							}
		             
		        		} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
		               });
		          return row ;
		      });

		

	      libraryView.setRowFactory( tv -> {
	          TableRow<TableNames> row = new TableRow<TableNames>();
	          row.setOnMouseClicked(event -> {
	        try {
	              if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
						control = controlClass.playSelected
								(loadedFiles.get(libraryView.getSelectionModel().getSelectedIndex()), sdVolume);
		
								control.play();
							}
	             
	        		} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
	              
	          });
	          return row ;
	      });

	}

	public static void main(String[] args) {
		launch(args);
	}
}
