package admin.entity;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Comment {
    @SerializedName("comment_id")
    private int commentID;

    @SerializedName("text")
    private String text;

    @SerializedName("person_id")
    private int personID;

    @SerializedName("post_id")
    private String postID;

}
