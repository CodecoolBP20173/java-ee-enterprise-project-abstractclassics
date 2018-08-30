package com.codecool.labourent.repository.serviceRepository;

import com.codecool.labourent.model.Service;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingServiceRepository extends ServiceRepository {
    
    @Query(value = "SELECT *, AVG(sumofrating/nullif(numofratings, 0)) as averageRating\n" +
            "FROM service\n" +
            "group by id\n" +
            "order by averageRating asc", nativeQuery = true)
    List<Service> findAllByOrderByAverageRatingAsc();

    @Query(value = "SELECT *, AVG(sumofrating/nullif(numofratings, 0)) as averageRating\n" +
            "FROM service\n" +
            "group by id\n" +
            "order by averageRating desc", nativeQuery = true)
    List<Service> findAllByOrderByAverageRatingDesc();

    @Query(value = "select *, AVG(sumofrating/nullif(numofratings, 0)) as averageRating\n" +
            "from service\n" +
            "  where servicecategory_id=:servicecategoryid\n" +
            "group by id\n" +
            "order by averageRating asc", nativeQuery = true)
    List<Service> findByServiceCategoryIdOrderByAverageRatingAsc(@Param("servicecategoryid") Integer serviceCategoryId);
    
    @Query(value = "select *, AVG(sumofrating/nullif(numofratings, 0)) as averageRating\n" +
            "from service\n" +
            "  where servicecategory_id=:servicecategoryid\n" +
            "group by id\n" +
            "order by averageRating desc ", nativeQuery = true)
    List<Service> findByServiceCategoryIdOrderByAverageRatingDesc(@Param("servicecategoryid") Integer serviceCategoryId);



}
