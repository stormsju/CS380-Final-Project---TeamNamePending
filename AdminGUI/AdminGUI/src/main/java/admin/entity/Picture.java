package admin.entity;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.sql.Blob;
import java.util.List;

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

    public int getPictureID() {
        return pictureID;
    }

    public Blob getPictureData() {
        return pictureData;
    }

    public String getFile() {
        return file;
    }

    public int getPersonID() {
        return personID;
    }
}
