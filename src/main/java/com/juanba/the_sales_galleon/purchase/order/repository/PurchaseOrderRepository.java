package com.juanba.the_sales_galleon.purchase.order.repository;

import com.juanba.the_sales_galleon.purchase.details.entity.PurchaseDetails;
import com.juanba.the_sales_galleon.purchase.order.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
}
