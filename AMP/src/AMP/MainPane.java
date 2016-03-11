package AMP;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class MainPane extends Application {
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

	private TableView libraryView;
		private TableColumn playingColumn;
		private TableColumn titleColumn;
		private TableColumn durationColumn;

	private VBox leftBox;
		Image coverImage;
		ImageView coverImgView;
			Rectangle imgBox;

		ListView infoView;


	@Override
	public void start(Stage primaryStage) {
			mnPane = new BorderPane(); // Main pane

			topBox = new VBox(); //Top box create
				topPane = new Pane(); //Top Pane create
				spectrumAnim = new Pane(); //Top spectrum animation pane.

			//Top menu (File, View, About)

			topMenu = new MenuBar();
				menuFile = new Menu("File");
					addFile = new MenuItem("Add File"); addFolder = new MenuItem("Add Folder"); loadPlaylist = new MenuItem("Load Playlist");
				menuFile.getItems().addAll(addFile, addFolder, loadPlaylist);

				menuView = new Menu("View");
					loadPreset = new MenuItem("Load preset.."); viewSpectrum = new MenuItem("View Spectrum"); viewOsci = new MenuItem("View Oscilloscope");
					disableAnimLogo = new CheckMenuItem("Animated Logo"); disableAnimLogo.setSelected(true);
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
						btPlayImg.setFitHeight(23); btPlayImg.setFitWidth(23);
						btPlay.setGraphic(btPlayImg);

				btStop = new Button(); btStop.setTooltip(new Tooltip("Stop"));
					btStopImg = new ImageView("resources/buttons/stop.png");
						btStopImg.setFitHeight(23); btStopImg.setFitWidth(23);
						btStop.setGraphic(btStopImg);

				btPause = new Button();  btPause.setTooltip(new Tooltip("Pause"));
					btPauseImg = new ImageView("resources/buttons/pause.png");
						btPauseImg.setFitHeight(23); btPauseImg.setFitWidth(23);
						btPause.setGraphic(btPauseImg);

				btPrev = new Button(); btPrev.setTooltip(new Tooltip("Previous"));
					btPrevImg = new ImageView("resources/buttons/previous.png");
						btPrevImg.setFitHeight(23); btPrevImg.setFitWidth(23);
						btPrev.setGraphic(btPrevImg);

				btNext = new Button();  btNext.setTooltip(new Tooltip("Next"));
					btNextImg = new ImageView("resources/buttons/next.png");
						btNextImg.setFitHeight(23); btNextImg.setFitWidth(23);
						btNext.setGraphic(btNextImg);

				btShuffle = new Button(); btShuffle.setTooltip(new Tooltip("Shuffle"));
					btShuffleImg = new ImageView("resources/buttons/play.png");
						btShuffleImg.setFitHeight(23); btShuffleImg.setFitWidth(23);
						btShuffle.setGraphic(btShuffleImg);

				btRepeat = new Button(); btRepeat.setTooltip(new Tooltip("Repeat"));
					btRepeatImg = new ImageView("resources/buttons/repeat.png");
						btRepeatImg.setFitHeight(23); btRepeatImg.setFitWidth(23);
						btRepeat.setGraphic(btRepeatImg);


			//Sliders
				sdVolume = new Slider(0,100,100); sdVolume.setPadding(new Insets(0,10,0,5));
					sdVolume.setPrefWidth(175);
				sdSeek = new Slider(); sdSeek.setPadding(new Insets(0,0,0,10));
					sdSeek.setPrefWidth(200);

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

			//Add MenuBar and HBox to the pane.
			topPane.getChildren().addAll(topMenu, audioControl);



			spectrumAnim.getChildren().add(placeholder);

			topBox.getChildren().addAll(topPane, spectrumAnim);


			//Table View for library.

			libraryView = new TableView();
			libraryView.setEditable(false);

			//Max width

				//Create columns
				playingColumn = new TableColumn("Playing");
					playingColumn.setMinWidth(Control.USE_PREF_SIZE);

				titleColumn = new TableColumn("Title");
					titleColumn.setMinWidth(435);
					titleColumn.setPrefWidth(440);
					titleColumn.prefWidthProperty().bind(libraryView.widthProperty().subtract(160));
				durationColumn = new TableColumn("Duration");
					durationColumn.setMinWidth(Control.USE_PREF_SIZE);


			//Add columns and empty placeholder.
			libraryView.getColumns().addAll(playingColumn, titleColumn, durationColumn);
			libraryView.setPlaceholder(new Text(""));


			//Image and Info
			leftBox = new VBox();
				imgBox = new Rectangle(312,312);
					imgBox.setFill(Color.WHITE); imgBox.setStroke(Color.LIGHTGREY);

				coverImgView = new ImageView();
				infoView = new ListView();


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

	}

	public static void main(String[] args) {
		launch(args);
	}
}
