package Springlog.Springlog.dto;

import lombok.Data;

@Data
public class AuthResponseDto {
    //to put token into variable
    public AuthResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }

    private String accessToken;


}
