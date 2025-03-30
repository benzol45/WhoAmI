package identifier;

import identifier.model.EmployeeModel;
import identifier.repository.EmployeeRepository;
import identifier.service.IdentifyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IdentifyServiceTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private IdentifyService identifyService;

    @Test
    void getName_EmployeeIdExistInDb_OptionalWithEmployeeName() {
        //Arrange
        int TEST_ID = 1;
        String TEST_NAME = "NAME";
        when(repository.getNameById(TEST_ID)).thenReturn(Optional.of(TEST_NAME));

        //Act
        Optional<?> result = identifyService.getName(TEST_ID);

        //Assert
        assertTrue(result.isPresent());
        assertEquals(TEST_NAME, ((EmployeeModel)result.get()).getName());
        verify(repository, times(1)).getNameById(1);
    }

    @ParameterizedTest
    @ValueSource(ints = {100,101,1000})
    void testGetName1(int employeeId) {
        //Arrange

        //Act
        Optional<?> result = identifyService.getName(employeeId);

        //Assert
        assertTrue(result.isPresent());
        assertEquals("External consultant", ((EmployeeModel)result.get()).getName());
        verify(repository, never()).getNameById(anyInt());

    }

    @Test
    void testGetName2() {
        //Arrange
        int TEST_ID = 42;
        when(repository.getNameById(TEST_ID)).thenReturn(Optional.empty());

        //Act
        Optional<?> result = identifyService.getName(TEST_ID);

        //Assert
        assertFalse(result.isPresent());
        verify(repository, times(1)).getNameById(TEST_ID);
    }
}