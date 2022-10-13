package com.cmckayling.user.service;

import com.cmckayling.user.VO.Department;
import com.cmckayling.user.VO.ResponseTemplateVO;
import com.cmckayling.user.entity.User;
import com.cmckayling.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        log.info("Inside saveUser method of service");
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        ResponseTemplateVO vo  = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);

        Department department = restTemplate.getForObject(
                "http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId(),
                    Department.class);
        vo.setUser(user);
        vo.setDepartment(department);
        return vo;
    }
}
