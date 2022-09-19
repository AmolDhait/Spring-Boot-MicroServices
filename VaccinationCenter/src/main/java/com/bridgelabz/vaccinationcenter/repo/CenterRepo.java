package com.bridgelabz.vaccinationcenter.repo;

import com.bridgelabz.vaccinationcenter.entity.VaccinationCenter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CenterRepo extends JpaRepository<VaccinationCenter, Integer> {
}
