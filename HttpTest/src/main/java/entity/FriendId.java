package entity;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class FriendId {

    @SerializedName("friendToId")
    private int friendToId;

    @SerializedName("friendOfId")
    private int friendOfId;
}
