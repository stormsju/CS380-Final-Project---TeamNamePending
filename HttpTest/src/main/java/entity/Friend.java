package entity;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Friend {

    @SerializedName("id")
    private FriendId id;

}
