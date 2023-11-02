package org.flower.models.user;

import jakarta.transaction.Transactional;
        import org.flower.entities.User;
        import org.flower.repositories.UserRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

@Service
public class UserEditService {

    @Autowired
    private UserRepository userRepository;


    /*
     *  Update user's nickname
     * */
    @Transactional
    public UserEditInfo editUserNickname(UserEditInfo userEditInfo) throws Exception {
        User user = userRepository.findById(userEditInfo.getUserNo())
                .orElseThrow(() -> new Exception("User with ID " + userEditInfo.getUserNo() + " not found"));

        // Update user's nickname based on UserEditInfo
        user.setUserNickNm(userEditInfo.getUserNickNm());
        user = userRepository.save(user);

        // Convert updated user to UserEditInfo and return
        UserEditInfo updatedUserEditInfo = new UserEditInfo(
                user.getUserNo(),
                user.getUserNickNm()
        );

        return updatedUserEditInfo;
    }


}