package com.miniproject.skybook.repository;


import com.miniproject.skybook.model.entity.BookingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingDetailRepository extends JpaRepository<BookingDetail,String> {

}
