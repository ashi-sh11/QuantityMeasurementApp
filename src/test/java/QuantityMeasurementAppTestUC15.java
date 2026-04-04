import Com.apps.QuantityMeasurementApp.UseCase15.controller.QuantityMeasurementRestControllerUC15;
import Com.apps.QuantityMeasurementApp.UseCase15.dto.AddRequestUC15;
import Com.apps.QuantityMeasurementApp.UseCase15.dto.QuantityDTOUC15;
import Com.apps.QuantityMeasurementApp.UseCase15.exception.GlobalExceptionHandlerUC15;
import Com.apps.QuantityMeasurementApp.UseCase15.exception.QuantityMeasurementExceptionUC15;
import Com.apps.QuantityMeasurementApp.UseCase15.repo.QuantityMeasurementRepositoryUC15;
import Com.apps.QuantityMeasurementApp.UseCase15.service.IQuantityMeasurementServiceUC15;
import Com.apps.QuantityMeasurementApp.UseCase15.service.QuantityMeasurementServiceImplUC15;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTestUC15 {

    private final IQuantityMeasurementServiceUC15 service =
            new QuantityMeasurementServiceImplUC15();

    private final GlobalExceptionHandlerUC15 handler =
            new GlobalExceptionHandlerUC15();

    //  DTO TESTS
    @Test
    void testDTOCreation() {
        QuantityDTOUC15 dto = new QuantityDTOUC15(10, "KG", "WEIGHT");

        assertEquals(10, dto.getValue());
        assertEquals("KG", dto.getUnitName());
        assertEquals("WEIGHT", dto.getMeasurementType());
    }



    @Test
    void testAdd_DifferentMeasurementType_ShouldThrow() {
        QuantityDTOUC15 q1 = new QuantityDTOUC15(10, "KG", "WEIGHT");
        QuantityDTOUC15 q2 = new QuantityDTOUC15(5, "FEET", "LENGTH");

        assertThrows(QuantityMeasurementExceptionUC15.class,
                () -> service.add(q1, q2));
    }

    @Test
    void testAdd_NullInput_ShouldThrow() {
        assertThrows(QuantityMeasurementExceptionUC15.class,
                () -> service.add(null, null));
    }


    @Test
    void testConvert_NullInput_ShouldThrow() {
        assertThrows(QuantityMeasurementExceptionUC15.class,
                () -> service.convert(null, "KG"));
    }




    // EXCEPTION HANDLER TESTS

    @Test
    void testHandleDomainException() {
        QuantityMeasurementExceptionUC15 ex =
                new QuantityMeasurementExceptionUC15("Error");

        ResponseEntity<String> response =
                handler.handleDomainException(ex);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Error", response.getBody());
    }

    @Test
    void testHandleGenericException() {
        Exception ex = new Exception();

        ResponseEntity<String> response =
                handler.handleGeneric(ex);

        assertEquals(500, response.getStatusCodeValue());
    }



}