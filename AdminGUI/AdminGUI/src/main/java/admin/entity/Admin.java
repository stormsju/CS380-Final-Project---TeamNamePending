package admin.entity;
import com.google.gson.annotations.SerializedName;
import lombok.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Admin {
    @SerializedName("id")
    private int id;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
}