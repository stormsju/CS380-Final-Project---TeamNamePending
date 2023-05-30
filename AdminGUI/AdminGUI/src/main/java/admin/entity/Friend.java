package admin.entity;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Friend {

    @SerializedName("id")
    private FriendID id;

    public FriendID getId() {
        return id;
    }
}
