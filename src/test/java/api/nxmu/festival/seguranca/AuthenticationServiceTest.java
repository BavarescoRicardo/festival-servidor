package api.nxmu.festival.seguranca;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-teste.properties")
class AuthenticationServiceTest {

    @Autowired
    private AuthenticationService authenticationService;


    @Test
    void registerAdminUser() {
        authenticationService.register(RegisterRequest.builder().email("teste@teste").senha("123").build());
    }

}
