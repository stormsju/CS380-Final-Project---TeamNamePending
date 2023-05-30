package admin.entity;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class FriendID {

    @SerializedName("friendToId")
    private int friendToId;

    @SerializedName("friendOfId")
    private int friendOfId;

    public int getFriendToId() {
        return friendToId;
    }
}
