package com.example.healthcare_system.service;

import com.example.healthcare_system.models.Medicine;
import com.example.healthcare_system.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Transactional
    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

    @Transactional
    public Medicine getMedicineById(int id) {
        return medicineRepository.findById(id).orElse(null);
    }

    @Transactional
    public Medicine addMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    @Transactional
    public Medicine updateMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    @Transactional
    public void deleteMedicine(int id) {
        medicineRepository.deleteById(id);
    }

}
