package lesson_10.repository;

import lesson_10.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    @Query("select f from Flight f where upper(f.airportName) = upper(?1) and f.ticketsNum != 0 order by f.id")
    List<Flight> findAllByAirportName(String airportName);

    @Modifying
    @Transactional
    @Query("update Flight f set f.ticketsNum = f.ticketsNum - 1 where f.id = ?1")
    void updateTicketsNum(Integer id);
}