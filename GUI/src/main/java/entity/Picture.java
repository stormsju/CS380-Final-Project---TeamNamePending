package entity;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Blob;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Picture {
    @SerializedName("picture_id")
    private int pictureID;
    @SerializedName("picture_data")
    private Blob pictureData;
    @SerializedName("file")
    private String file;
    @SerializedName("personID")
    private int personID;
}
