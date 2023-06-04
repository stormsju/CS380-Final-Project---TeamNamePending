package httpTests;

import entity.Picture;
import http.PictureHttp;
import org.junit.jupiter.api.Test;

import java.util.List;

class PictureHttpTest {

    @Test
    void getByPerson() throws Exception{
        PictureHttp pictureHttp = new PictureHttp();
        List<Picture> pictures = pictureHttp.getByPerson(1000);

        System.out.println(pictures.toString());
    }
}