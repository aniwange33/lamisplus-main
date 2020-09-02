package org.lamisplus.modules.base.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lamisplus.modules.base.controller.apierror.EntityNotFoundException;
import org.lamisplus.modules.base.controller.apierror.RecordExistException;
import org.lamisplus.modules.base.domain.dto.IcdDTO;
import org.lamisplus.modules.base.domain.entity.Icd;
import org.lamisplus.modules.base.domain.mapper.IcdMapper;
import org.lamisplus.modules.base.repository.IcdRepository;
import org.lamisplus.modules.base.util.GenericSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class IcdService {
    private final IcdRepository icdRepository;
    private final IcdMapper icdMapper;
    private final Integer archived = 1;

    public Icd save(IcdDTO icdDTO) {
        Optional<Icd> icdOptional = icdRepository.findByFullCode(icdDTO.getFullCode());
        if(icdOptional.isPresent())throw new RecordExistException(Icd.class, "Full Code", icdDTO.getFullCode() +"");
        final Icd icd = icdMapper.toIcd(icdDTO);
        return icdRepository.save(icd);
    }

    public Icd update(Long id, IcdDTO icdDTO) {
        Optional<Icd> icdOptional = icdRepository.findById(id);
        if(!icdOptional.isPresent())throw new EntityNotFoundException(Icd.class, "Id", id +"");
        final Icd icd = icdMapper.toIcd(icdDTO);
        icd.setId(id);
        return icdRepository.save(icd);
    }
    public IcdDTO getIcd(Long id){
        Optional<Icd> icdOptional = icdRepository.findById(id);
        if (!icdOptional.isPresent())throw new EntityNotFoundException(Icd.class, "Id", id +"");
        final IcdDTO icdDTO = icdMapper.toIcdDTO(icdOptional.get());
        return icdDTO;
    }

    public List<IcdDTO> getAllIcd() {
        GenericSpecification<Icd> genericSpecification = new GenericSpecification<>();
        Specification<Icd> specification = genericSpecification.findAll();
        List<IcdDTO> icdDTOList = new ArrayList();
        List <Icd> icdList = icdRepository.findAll(specification);

        icdList.forEach(icd -> {
            final IcdDTO icdDTO = icdMapper.toIcdDTO(icd);
            icdDTOList.add(icdDTO);
        });

        return icdDTOList;
    }

    public List<IcdDTO> getDiagnosisByCategory(String categoryCode){
        List<Icd> icdList = icdRepository.findAllByCategoryCode(categoryCode);
        List<IcdDTO> icdDTOList = new ArrayList();

        icdList.forEach(icd -> {
            final IcdDTO icdDTO = icdMapper.toIcdDTO(icd);
            icdDTOList.add(icdDTO);
        });

        return icdDTOList;
    }

    public List<IcdDTO> getAllCategory(){
        GenericSpecification<Icd> genericSpecification = new GenericSpecification<Icd>();
        Specification<Icd> specification = genericSpecification.findAllDistinct("categoryTitle");

        List<Icd> icdList = icdRepository.findAll(specification);

        List<IcdDTO> icdDTOList = new ArrayList();

        icdList.forEach(icd -> {
            final IcdDTO icdDTO = icdMapper.toIcdDTO(icd);
            icdDTOList.add(icdDTO);
        });

        return icdDTOList;
    }

    public Integer delete(Long id){
        Optional<Icd> standardCodesetOptional = icdRepository.findById(id);
        if (!standardCodesetOptional.isPresent())throw new EntityNotFoundException(Icd.class, "Id", id +"");
        standardCodesetOptional.get().setArchived(archived);
        return standardCodesetOptional.get().getArchived();
    }
}