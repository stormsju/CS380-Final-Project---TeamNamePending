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

public class PostPicture {
    @SerializedName("picture_id")
    private int pictureID;
    @SerializedName("post_id")
    private int postID;

}
