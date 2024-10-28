package com.example.healthcare_system.controller;

import com.example.healthcare_system.models.Medicine;
import com.example.healthcare_system.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicines")
public class MedicineController {
    @Autowired
    private MedicineService medicineService;

    @GetMapping
    public List<Medicine> getAllMedicines() {
        return medicineService.getAllMedicines();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable int id) {
        Medicine medicine = medicineService.getMedicineById(id);
        return (medicine != null) ? ResponseEntity.ok(medicine) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Medicine addMedicine(@RequestBody Medicine medicine) {
        return medicineService.addMedicine(medicine);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medicine> updateMedicine(@PathVariable int id, @RequestBody Medicine medicine) {
        medicine.setMedicineId(id);
        Medicine updatedMedicine = medicineService.updateMedicine(medicine);
        return ResponseEntity.ok(updatedMedicine);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicine(@PathVariable int id) {
        medicineService.deleteMedicine(id);
        return ResponseEntity.noContent().build();
    }
}
