package com.aritra.healthbridge.graphql;


import com.aritra.healthbridge.dto.DoctorRequestDTO;
import com.aritra.healthbridge.dto.DoctorResponseDTO;
import com.aritra.healthbridge.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DoctorGraphQLController {

    private final DoctorService doctorService;

    @QueryMapping
    public List<DoctorResponseDTO> allDoctors(){
        return doctorService.getAllDoctors();
    }
    @QueryMapping
    public DoctorResponseDTO getDoctorById(@Argument Long id){
        return doctorService.getDoctorById(id);
    }
    @MutationMapping
    public DoctorResponseDTO addDoctor(
            @Argument String name,
            @Argument String degree,
            @Argument String department,
            @Argument String yearOfPassing,
            @Argument String specialization,
            @Argument String email,
            @Argument String phone,
            @Argument BigDecimal consultationFee) {

        DoctorRequestDTO request = new DoctorRequestDTO(
                name, degree, department,
                LocalDate.parse(yearOfPassing),   // String → LocalDate
                specialization, email, phone, consultationFee
        );
        return doctorService.addNewDoctor(request);
    }

}
