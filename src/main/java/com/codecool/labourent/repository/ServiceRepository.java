package com.codecool.labourent.repository;

import com.codecool.labourent.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
    List<Service> findAllByOrderByIdAsc();
    List<Service> findAllByOrderByIdDesc();


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


    List<Service> findAllByOrderByNameAsc();
    List<Service> findAllByOrderByNameDesc();

    List<Service> findAllByOrderByPriceAsc();
    List<Service> findAllByOrderByPriceDesc();



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




    List<Service> findByServiceCategoryIdOrderByNameAsc(Integer serviceCategoryId);
    List<Service> findByServiceCategoryIdOrderByNameDesc(Integer serviceCategoryId);
    List<Service> findByServiceCategoryIdOrderByPriceAsc(Integer serviceCategoryId);
    List<Service> findByServiceCategoryIdOrderByPriceDesc(Integer serviceCategoryId);

    List<Service> findByServiceCategoryIdOrderByIdAsc(Integer serviceCategoryId);


    Service findById(Integer serviceId);



}