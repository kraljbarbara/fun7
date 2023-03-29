package si.barbarak.fun7.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MultiplayerServiceTest {

    @InjectMocks
    private MultiplayerService multiplayerService;

     @Mock
     private AuditLogService mockAuditLogService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testIsEnabled_UnsupportedCountry(){
        assert !multiplayerService.isEnabled("", "si");
        assert !multiplayerService.isEnabled("", "it");
        assert !multiplayerService.isEnabled("", "au");
    }

    @Test
    public void testIsEnabled_CountCheckFail(){
        when(mockAuditLogService.countChecksByUser("")).thenReturn(3l);

        assert !multiplayerService.isEnabled("", "us");
    }

    @Test
    public void testIsEnabled_CountCheckSuccess(){
        when(mockAuditLogService.countChecksByUser("")).thenReturn(6l);

        assert multiplayerService.isEnabled("", "us");
    }
}