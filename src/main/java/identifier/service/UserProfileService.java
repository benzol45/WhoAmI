package identifier.service;

import identifier.entity.UserProfile;
import identifier.model.UserProfileModel;
import identifier.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserProfileService(UserProfileRepository userProfileRepository, PasswordEncoder passwordEncoder) {
        this.userProfileRepository = userProfileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public int createUser(UserProfileModel userProfileModel) {
        UserProfile userProfile = new UserProfile();
        userProfile.setUsername(userProfileModel.getUsername());
        userProfile.setPassword(passwordEncoder.encode(userProfileModel.getPassword()));
        userProfile.setRole(UserProfile.Role.USER);
        userProfile.setEnabled(true);

        userProfile = userProfileRepository.save(userProfile);
        return userProfile.getId();
    }
}
