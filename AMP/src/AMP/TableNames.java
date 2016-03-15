package AMP;

import javafx.beans.property.SimpleStringProperty;

public class TableNames
{
    private final SimpleStringProperty playing;
    private final SimpleStringProperty title;
    private final SimpleStringProperty duration;

    public TableNames(String playing, String title, String duration) {
        this.playing = new SimpleStringProperty(playing);
        this.title = new SimpleStringProperty(title);
        this.duration = new SimpleStringProperty(duration);
    }

    public String getPlaying() {
        return playing.get();
    }
    public void setPlaying(String play) {
        playing.set(play);
    }

    public String getTitle() {
        return title.get();
    }
    public void setTitle(String tit) {
        title.set(tit);
    }

    public String getDuration() {
        return duration.get();
    }
    public void setDuration(String dur) {
        duration.set(dur);
    }
}
