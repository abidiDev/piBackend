package com.spring.pi.repositories;

import com.spring.pi.entities.HouseBuilding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface MaisonRepository extends JpaRepository<HouseBuilding,Long> , JpaSpecificationExecutor<HouseBuilding> {
    @Query(value = "SELECT m from HouseBuilding as m " +
    "where (:surfaceterrain is null or m.surfaceterrain = :surfaceterrain) "+
    "AND (:HouseSurface is null or m.houseSurface = :HouseSurface or m.houseSurface =:surfaceterrain) " + "AND (:nbrofroom is null or m.nbrofroom = :nbrofroom) " +
            "AND (:piscine is null or m.piscine = :piscine) " +
            "AND (:jardin is null or m.jardin = :jardin) " +
            "AND (:nbreEtage is null or m.nbreEtage = :nbreEtage) " +
            "AND (:lieux is null or m.lieux = :lieux) " +
            "AND (:prix is null or m.prix = :prix) "

    )
    public List<HouseBuilding> finfMaison(@Param("surfaceterrain") Long surfaceterrain,
                                          @Param("HouseSurface") Long HouseSurface,
                                          @Param("nbrofroom") Integer nbrofroom,
                                          @Param("piscine") Boolean piscine,
                                          @Param("jardin") Boolean jardin,
                                          @Param("nbreEtage") Integer nbreEtage,
                                          @Param("lieux") String lieux,
                                          @Param("prix") Long prix);
    List<HouseBuilding> findAllByLieuxAndSurfaceterrainEquals(String z, Long s);
    List<HouseBuilding> findAllByLieuxAndSurfaceterrainEqualsAndHouseSurfaceEquals(String z, Long s, Long t);
    List<HouseBuilding> findAllByLieux(String l);
    List<HouseBuilding> findAllByLieuxAndSurfaceterrainEqualsAndHouseSurfaceEqualsAndNbrofroomAndNbreEtage(String z, Long s, Long t, Integer a, Integer b);


}
