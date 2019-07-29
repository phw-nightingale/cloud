package xyz.frt.serveruser.entity;

import lombok.Data;

@Data
public class UserLoginDTO {

    private User user;

    private JWT jwt;

}
