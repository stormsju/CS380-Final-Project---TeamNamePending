package entity;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Picture {
    @SerializedName("id")
    private int pictureID;
    @SerializedName("image")
    private String image;
    @SerializedName("file")
    private String file;
    @SerializedName("person")
    private Person person;

}
