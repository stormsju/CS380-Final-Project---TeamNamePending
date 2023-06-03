package entity;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Person {


    @SerializedName("id")
    private int id;

    @SerializedName("fname")
    private String fname;
    @SerializedName("lname")
    private String lname;

    @SerializedName("username")
    private String username;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

    @SerializedName("date")
    private String date;

}
