package com.sportyshoes.services;

import com.sportyshoes.exceptions.ProductNotFoundException;
import com.sportyshoes.models.Purchase;
import com.sportyshoes.repositories.PurchaseRepository;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Transactional
    public Purchase getPurchaseById(long id) {
        Optional<Purchase> optionalPurchase = purchaseRepository.findById(id);
        return optionalPurchase.orElseThrow(() -> new ProductNotFoundException());
    }

    @Transactional
    public List<Purchase> getAllPurchases() {
        return (List<Purchase>) purchaseRepository.findAll();
    }

    @Transactional
    public List<Purchase> getAllPurchasesByUserId(long userId) {
        return purchaseRepository.getAllItemsByUserId(userId);
    }

//    public long  update3Purchase(Purchase purchase) {
//        String sql = "";
//        long newId = 0;
//        if (purchase.getID() == 0) {
//            this.sessionFactory.getCurrentSession().save(purchase);
//            newId = purchase.getID();
//        } else {
//            Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
//            query.setParameter("user_id", purchase.getUserId());
//            query.setParameter("gross_total", purchase.getTotal());
//
//            query.executeUpdate();
//        }
//        return newId;
//    }

    @Transactional
    public long  updatePurchase(Purchase purchase) {
        return purchaseRepository.updatePurchase(purchase);
    }

    @Transactional
    public void deletePurchaseById(long id) {
        purchaseRepository.deleteById(id);
    }

    @Transactional
    public Purchase addPurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }
}
