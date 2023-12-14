package com.example.backend.promotion.service;


import com.example.backend.exceptions.exception.WrongDataEnteredException;
import com.example.backend.person.model.Person;
import com.example.backend.person.model.Role;
import com.example.backend.person.repository.PersonRepository;
import com.example.backend.promotion.dto.PromotionDTO;
import com.example.backend.promotion.model.Promotion;
import com.example.backend.promotion.repository.PromotionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PromotionService {
    private final PromotionRepository promotionRepository;

    private final PersonRepository personRepository;

    @Autowired
    public PromotionService(PromotionRepository promotionRepository, PersonRepository personRepository) {
        this.promotionRepository = promotionRepository;
        this.personRepository = personRepository;
    }


    @Transactional
    public void requestPromotion(Long userId, Role requestedRole){
        if(this.promotionRepository.existsPromotionByUserId(userId))
            throw new WrongDataEnteredException("There is already a request by that user");
        Promotion promotion = new Promotion(userId, requestedRole);
        this.promotionRepository.save(promotion);
    }


    @Transactional
    public Promotion getAndDeletePromotion(Long userId){
        Promotion promotion = this.promotionRepository.findPromotionByUserId(userId);
        this.promotionRepository.deletePromotionByUserId(userId);
        return promotion;
    }


    @Transactional
    public List<PromotionDTO> getPromotionRequests(){
        List<Promotion> promotionRequests = this.promotionRepository.findAll();
        List<Long> userIds = promotionRequests.stream()
                .map(Promotion::getUserId)
                .toList();
        List<Person> personList = this.personRepository.findAllById(userIds);
        Map<Long, Person> personMap = personList.stream()
                .collect(Collectors.toMap(Person::getId, person -> person));
        List<PromotionDTO> promotionDTOS = new ArrayList<>(promotionRequests.size());
        for (int i = 0; i < promotionRequests.size(); i++) {
            promotionDTOS.get(i).setUserId(promotionRequests.get(i).getUserId());
            promotionDTOS.get(i).setRequestedRole(promotionRequests.get(i).getRole().name());
            promotionDTOS.get(i).setUserName(personMap.get(promotionRequests.get(i).getUserId()).getFirstName() + personMap.get(promotionRequests.get(i).getUserId()).getLastName());
            promotionDTOS.get(i).setUserImage(personMap.get(promotionRequests.get(i).getUserId()).getPhotoLink());
        }
        return promotionDTOS;
    }
}
