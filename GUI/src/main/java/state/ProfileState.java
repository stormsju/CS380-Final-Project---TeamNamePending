package state;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ProfileState {
    private ImageView imgFeed;

    private Label tfProfile;

    public ImageView getImgFeed() {
        return imgFeed;
    }

    public void setImgFeed(ImageView imgFeed) {
        this.imgFeed = imgFeed;
    }

    public Label getTfProfile() {
        return tfProfile;
    }

    public void setTfProfile(Label tfProfile) {
        this.tfProfile = tfProfile;
    }
}
