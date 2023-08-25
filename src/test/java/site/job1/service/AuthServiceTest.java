package site.job1.service;

import site.job1.crypto.PasswordEncoder;
import site.job1.domain.User;
import site.job1.exception.AlreadyExistsEmailException;
import site.job1.exception.InvalidSigninInformation;
import site.job1.repository.UserRepository;
import site.job1.request.Login;
import site.job1.request.Signup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @AfterEach
    void clean() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("회원가입 성공")
    void test1() {
        // given
        Signup signup = Signup.builder()
                .email("hodolman88@gmail.com")
                .password("1234")
                .name("허혜인")
                .build();

        // when
        authService.signup(signup);

        // then
        assertEquals(1, userRepository.count());

        User user = userRepository.findAll().iterator().next();
        assertEquals("hodolman88@gmail.com", user.getEmail());
        assertNotNull(user.getPassword());
        assertNotEquals("1234", user.getPassword());
        assertEquals("허혜인", user.getName());
    }

    @Test
    @DisplayName("회원가입시 중복된 이메일")
    void test2() {
        // given
        User user = User.builder()
                .email("hodolman88@gmail.com")
                .password("1234")
                .name("짱돌맨")
                .build();
        userRepository.save(user);

        Signup signup = Signup.builder()
                .email("hodolman88@gmail.com")
                .password("1234")
                .name("허혜인")
                .build();

        // expected
        assertThrows(AlreadyExistsEmailException.class, () -> authService.signup(signup));
    }

    @Test
    @DisplayName("로그인 성공")
    void test3() {
        // given
        PasswordEncoder encoder = new PasswordEncoder();
        String ecnryptedPassword = encoder.encrpyt("1234");

        User user = User.builder()
                .email("hodolman88@gmail.com")
                .password(ecnryptedPassword)
                .name("짱돌맨")
                .build();
        userRepository.save(user);

        Login login = Login.builder()
                .email("hodolman88@gmail.com")
                .password("1234")
                .build();

        // when
        Long userId = authService.signin(login);

        // then
        assertNotNull(userId);
    }

    @Test
    @DisplayName("로그인시 비밀번호 틀림")
    void test4() {
        // given
        PasswordEncoder encoder = new PasswordEncoder();
        String ecnryptedPassword = encoder.encrpyt("1234");

        User user = User.builder()
                .email("hodolman88@gmail.com")
                .password(ecnryptedPassword)
                .name("짱돌맨")
                .build();
        userRepository.save(user);

        Login login = Login.builder()
                .email("hodolman88@gmail.com")
                .password("5678")
                .build();

        // expected
        assertThrows(InvalidSigninInformation.class,
                () -> authService.signin(login));
    }
}