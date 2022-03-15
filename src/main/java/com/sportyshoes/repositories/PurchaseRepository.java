package com.sportyshoes.repositories;

import com.sportyshoes.models.Purchase;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
}
