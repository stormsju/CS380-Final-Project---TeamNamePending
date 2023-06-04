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
    private int id;
    @SerializedName("image")
    private String image;
    @SerializedName("file")
    private String file;
    @SerializedName("person_id")
    private Person person;

    public String toString(){
        return "id:\t" + id +
                "\ndata\t" + image +
                "\nfile\t" + file;
    }
}
