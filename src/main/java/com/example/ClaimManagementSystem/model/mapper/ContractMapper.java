package com.example.ClaimManagementSystem.model.mapper;

import com.example.ClaimManagementSystem.model.Contract;
import com.example.ClaimManagementSystem.model.dto.request.ContractCreateDTO;
import com.example.ClaimManagementSystem.model.dto.request.ContractUpdateDTO;
import com.example.ClaimManagementSystem.model.dto.response.ContractDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContractMapper {

    public ContractDTO ToDtoMapper(Contract contract) {
        return new ContractDTO(contract.getUuid(), contract.getContractNumber(), contract.getStartDate(), contract.getEndDate(), contract.getInsuredName(), contract.getVehiclePlate());
    }

    public List<ContractDTO> ToDtoMapper(List<Contract> contracts) {
        List<ContractDTO> contractDTOList = new ArrayList<>();
        for (Contract contract : contracts) {
            contractDTOList.add(ToDtoMapper(contract));
        }
        return contractDTOList;
    }

    public Contract ToEntityMapper(ContractCreateDTO contractDTO) {
        Contract contract = new Contract();
        contract.setContractNumber(contractDTO.contractNumber());
        contract.setStartDate(contractDTO.startDate());
        contract.setEndDate(contractDTO.endDate());
        contract.setInsuredName(contractDTO.insuredName());
        contract.setVehiclePlate(contractDTO.vehiclePlate());
        return contract;
    }

    public void ToEntityMapper(ContractUpdateDTO contractUpdateDTO, Contract contract) {
        if(contractUpdateDTO.contractNumber().isPresent()){
            contract.setContractNumber(contractUpdateDTO.contractNumber().get());
        }
        if(contractUpdateDTO.startDate().isPresent()){
            contract.setStartDate(contractUpdateDTO.startDate().get());
        }
        if(contractUpdateDTO.endDate().isPresent()){
            contract.setEndDate(contractUpdateDTO.endDate().get());
        }
        if(contractUpdateDTO.insuredName().isPresent()){
            contract.setInsuredName(contractUpdateDTO.insuredName().get());
        }
        if(contractUpdateDTO.vehiclePlate().isPresent()){
            contract.setVehiclePlate(contractUpdateDTO.vehiclePlate().get());
        }
    }
}
