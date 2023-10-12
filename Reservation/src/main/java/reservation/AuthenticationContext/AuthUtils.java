package reservation.AuthenticationContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import reservation.entity.User;
import reservation.enums.StateOfTravel;
import reservation.exceptions.NoUsersFoundException;
import reservation.repository.UserRepository;

@Component
public class AuthUtils {

    @Autowired
    UserRepository userRepository;

    public User getUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NoUsersFoundException("User not found"));
        return user;
    }

}
