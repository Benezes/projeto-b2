package br.com.gabriel.projetob2.infra;

import br.com.gabriel.projetob2.model.Selic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
class ApiInfraTest {

    @InjectMocks
    private ApiInfra apiInfra;

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldGetApiSelicWithSuccess() {
        List<Selic> result = apiInfra.getApiSelic();
        assertNotNull(result);
    }

}