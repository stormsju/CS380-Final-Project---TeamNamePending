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
    private String status;

    public int getPictureID() {
        return pictureID;
    }

    public void setPictureID(int pictureID) {
        this.pictureID = pictureID;
    }

    public Blob getPictureData() {
        return pictureData;
    }

    public void setPictureData(Blob pictureData) {
        this.pictureData = pictureData;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public String getStatus(){ return status; }
    public void setStatus(String status){ this.status = status; }
}
