package com.example.ClaimManagementSystem.model.mapper;

import com.example.ClaimManagementSystem.model.Claim;
import com.example.ClaimManagementSystem.repository.ContractRepository;
import com.example.ClaimManagementSystem.model.dto.request.ClaimCreateDTO;
import com.example.ClaimManagementSystem.model.dto.request.ClaimUpdateDTO;
import com.example.ClaimManagementSystem.model.dto.response.ClaimDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClaimMapper {

    private final ContractRepository contractRepository;

    public ClaimMapper(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public ClaimDTO ToDtoMapper(Claim claim) {
        return new ClaimDTO(claim.getUuid(), claim.getClaimNumber(), claim.getCreationDate(), claim.getAccidentDate(), claim.getStatus(), contractRepository.findById(claim.getContractId()).orElseThrow(() -> new RuntimeException("Contract not found with id: " + claim.getContractId()))
                .getUuid());
    }

    public List<ClaimDTO> ToDtoMapper(List<Claim> claims) {
        List<ClaimDTO> claimDTOList = new ArrayList<>();
        for(Claim claim: claims){
            claimDTOList.add(ToDtoMapper(claim));
        }
        return claimDTOList;
    }

    public Claim ToEntityMapper(ClaimCreateDTO claimDTO) {
        Claim claim = new Claim();
        claim.setClaimNumber(claimDTO.claimNumber());
        claim.setAccidentDate(claimDTO.accidentDate());
        claim.setStatus(claimDTO.status());
        claim.setContractId(claimDTO.contractId());
        return claim;
    }

    public Claim ToEntityMapper(ClaimUpdateDTO claimDTO, Claim claim) {

        if(claimDTO.claimNumber().isPresent()){
            claim.setClaimNumber(claimDTO.claimNumber().get());
        }
        if(claimDTO.accidentDate().isPresent()){
            claim.setAccidentDate(claimDTO.accidentDate().get());
        }
        if(claimDTO.contractId().isPresent()){
            claim.setContractId(claimDTO.contractId().get());
        }
        if(claimDTO.status().isPresent()){
            claim.setStatus(claimDTO.status().get());
        }
        return claim;
    }
}
