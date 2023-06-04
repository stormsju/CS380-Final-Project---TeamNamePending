package state;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class HomeState {

    private static ImageView imgFeed;
    private static Label tfCaption;

    public ImageView getImgFeed() {
        return imgFeed;
    }

    public void setImgFeed(ImageView imgFeed) {
        this.imgFeed = imgFeed;
    }

    public Label getTfCaption() {
        return tfCaption;
    }

    public void setTfCaption(Label tfCaption) {
        this.tfCaption = tfCaption;
    }
}