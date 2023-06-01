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
public class Post {
    @SerializedName("id")
    private int id;
    @SerializedName("text")
    private String text;
    @SerializedName("person")
    private Person person;
}
