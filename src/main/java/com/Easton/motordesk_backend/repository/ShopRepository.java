package com.Easton.motordesk_backend.repository;

import com.Easton.motordesk_backend.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository <Shop, Long> {
}
