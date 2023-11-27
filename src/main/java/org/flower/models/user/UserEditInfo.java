package org.flower.models.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEditInfo {
    private Long userNo;        // User Number
    private String userNickNm;  // Nickname
    //private String cellPhone; // cellphone
}
