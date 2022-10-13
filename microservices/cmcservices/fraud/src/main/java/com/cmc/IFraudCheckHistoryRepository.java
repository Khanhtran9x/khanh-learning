package com.cmc;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IFraudCheckHistoryRepository extends JpaRepository<FraudCheckHistory, Integer> {
}
