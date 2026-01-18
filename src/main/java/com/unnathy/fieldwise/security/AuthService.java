package com.unnathy.fieldwise.security;

import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.user.User;
import com.unnathy.fieldwise.user.UserRepository;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthResponse login(AuthRequest request) throws UnnathyError {
        if (request == null || request.getUsernameOrEmail() == null || request.getPassword() == null) {
            throw new UnnathyError("INVALID_REQUEST", "Username/email and password are required", null);
        }

        String identifier = request.getUsernameOrEmail().trim();
        User user = userRepository.findByUsernameIgnoreCaseOrEmailIgnoreCase(identifier, identifier)
                .orElseThrow(() -> new UnnathyError("UNAUTHORIZED", "Invalid credentials", null));

        if (Boolean.FALSE.equals(user.getIsActive())) {
            throw new UnnathyError("UNAUTHORIZED", "User is inactive", null);
        }

        if (!matchesPassword(request.getPassword(), user.getPasswordHash())) {
            throw new UnnathyError("UNAUTHORIZED", "Invalid credentials", null);
        }

        user.setLastLoginAt(Instant.now());
        userRepository.save(user);

        String token = jwtUtil.generateToken(user);
        return new AuthResponse(
                token,
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmployeeCode(),
                user.getProfilePhotoUrl(),
                user.getRoleId()
        );
    }

    private boolean matchesPassword(String raw, String stored) {
        if (raw == null || stored == null) {
            return false;
        }
        if (isBcryptHash(stored)) {
            return passwordEncoder.matches(raw, stored);
        }
        return MessageDigest.isEqual(
                raw.getBytes(StandardCharsets.UTF_8),
                stored.getBytes(StandardCharsets.UTF_8)
        );
    }

    private boolean isBcryptHash(String value) {
        return value.startsWith("$2a$") || value.startsWith("$2b$") || value.startsWith("$2y$");
    }
}
