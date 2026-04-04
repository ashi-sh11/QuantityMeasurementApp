package Com.apps.QuantityMeasurementApp.UseCase15.repo;
import Com.apps.QuantityMeasurementApp.UseCase15.entity.QuantityMeasurementEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class QuantityMeasurementRepositoryUC15 {

    private final List<QuantityMeasurementEntity> db = new ArrayList<>();

    public void save(QuantityMeasurementEntity entity) {
        db.add(entity);
    }

    public List<QuantityMeasurementEntity> findAll() {
        return db;
    }
}