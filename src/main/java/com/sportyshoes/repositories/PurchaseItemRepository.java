package com.sportyshoes.repositories;


import com.sportyshoes.models.PurchaseItem;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseItemRepository extends CrudRepository<PurchaseItem, Long> {
}
