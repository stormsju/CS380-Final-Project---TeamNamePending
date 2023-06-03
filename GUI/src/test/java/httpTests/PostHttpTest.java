package httpTests;

import entity.Post;
import http.PostHttp;
import org.junit.jupiter.api.Test;

import java.util.List;

class PostHttpTest {

    @Test
    void getById() throws Exception{

        PostHttp postHttp = new PostHttp();
        Post post = postHttp.getById(1);
        System.out.println(post.toString());

    }

    @Test
    void getAll() throws Exception{
        PostHttp postHttp = new PostHttp();
        List<Post> posts = postHttp.getAll();

        for (Post p : posts) {
            System.out.println(p.toString());
        }
    }

    @Test
    void getByPersonId() throws Exception{
        PostHttp postHttp = new PostHttp();
        List<Post> posts = postHttp.getByPersonId(1000);

        for (Post p : posts) {
            System.out.println(p.toString());
        }
    }
}