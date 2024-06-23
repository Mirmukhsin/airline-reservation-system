package lesson_10.repository;

import lesson_10.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Integer> {

    @Query("select a from Airport a where upper(a.cityName) = upper(?1)")
    List<Airport> findAllByCityName(String cityName);
}