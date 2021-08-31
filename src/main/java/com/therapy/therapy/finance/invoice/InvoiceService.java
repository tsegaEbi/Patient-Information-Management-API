package com.therapy.therapy.finance.invoice;

import com.therapy.therapy.common.CRUDService;
import com.therapy.therapy.finance.INVOICE_FOR_CATEGORY;
import com.therapy.therapy.finance.item.Item;
import com.therapy.therapy.finance.itemCategory.ItemCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface InvoiceService extends CRUDService<Invoice> {

    List<Invoice> getByCustomerId(Long entitledId);
    List<Invoice>getByReferenceId(Long entitledId);

    Page<Invoice> getByInvoiceForCategory(INVOICE_FOR_CATEGORY category, Pageable pageable);

    Page<Invoice> getByItemCategory(ItemCategory itemCategory, Pageable pageable);

    Page<Invoice> getByItem(Item item, Pageable pageable);

    Page<Invoice> getByInvoiceByTin(INVOICE_FOR_CATEGORY category, Pageable pageable);

    Page<Invoice> getByGeneratedDate(Date date, Pageable pageable);
}
