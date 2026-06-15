package com.trainingmug.ecommerce.dto.response;
import com.trainingmug.ecommerce.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDto {
    private int id;
    private String name;
    private String email;
    private Status status;
}

