package com.therapy.therapy.finance.invoice;

import com.therapy.therapy.finance.INVOICE_FOR_CATEGORY;
import com.therapy.therapy.finance.item.Item;
import com.therapy.therapy.finance.itemCategory.ItemCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
    List<Invoice> findByCustomerId(Long entitledId);

    Page<Invoice> findByInvoiceCategory(INVOICE_FOR_CATEGORY category, Pageable pageable);

    @Query("FROM Invoice i where i.item.category=:itemCategory")
    Page<Invoice> findByItemCategory(ItemCategory itemCategory, Pageable pageable);

    Page<Invoice> findByItem(Item item, Pageable pageable);

    Page<Invoice> findByCustomerTin(String tin, Pageable pageable);

    @Query("FROM Invoice i where i.dateCreated=:date")
    Page<Invoice> getByGeneratedDate(Date date, Pageable pageable);
}
