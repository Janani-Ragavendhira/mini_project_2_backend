package com.hmsbe.repository;

import com.hmsbe.dto.AppointmentDto;
import com.hmsbe.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query(value = "select a.id, d.name as doctorName, p.name as patientName, p.mobile as patientMobile, a.appointment_at as appointmentAt, a.appointment_reason as appointmentReason from appointment a join doctor d on d.id = a.doctor_id join patient p on p.id = a.patient_id", nativeQuery = true)
    List<Object[]> findAppointments();

}
