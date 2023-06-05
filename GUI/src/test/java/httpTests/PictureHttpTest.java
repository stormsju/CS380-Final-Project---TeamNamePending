package httpTests;

import entity.Picture;
import http.PictureHttp;
import org.junit.jupiter.api.Test;

import java.util.List;

class PictureHttpTest {

    @Test
    void getAll() throws Exception{
        PictureHttp pictureHttp = new PictureHttp();
        List<Picture> pictures = pictureHttp.getAll();

        for (Picture p:pictures) {
            System.out.println(p.getPerson().getId());
        }
    }
}