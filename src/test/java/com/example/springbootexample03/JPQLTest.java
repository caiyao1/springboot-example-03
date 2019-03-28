package com.example.springbootexample03;

import com.example.springbootexample03.entity.Address;
import com.example.springbootexample03.entity.User;
import com.example.springbootexample03.entity.UserAddress;
import com.example.springbootexample03.repository.AddressRepository;
import com.example.springbootexample03.repository.UserAddressRepository;
import com.example.springbootexample03.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class JPQLTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserAddressRepository userAddressRepository;
    @Test
    public void init() {
        User user=new User("张三");
        userRepository.save(user);
        User user1=new User("李四");
        userRepository.save(user1);

        Address address=new Address("901");
        addressRepository.save(address);
        Address address1=new Address("913");
        addressRepository.save(address1);
        Address address2=new Address("910");
        addressRepository.save(address2);

        UserAddress userAddress=new UserAddress(user,address);
        userAddressRepository.save(userAddress);
        UserAddress userAddress1=new UserAddress(user,address1);
        userAddressRepository.save(userAddress1);
        UserAddress userAddress2=new UserAddress(user1,address2);
        userAddressRepository.save(userAddress2);
    }
    @Test
    public void userRepTest(){
        User u=userRepository.find(1);
        log.debug(u.getName());
    }
    @Test
    public void addressRepTest(){
        addressRepository.list("910")
                .forEach(address -> log.debug("{}", address.getId()));
    }
    @Test
    public void userAddressRepTest(){
        UserAddress userAddress=userAddressRepository.find("张三","913");
        log.debug("插入时间：{}", userAddress.getInsertTime());
    }
}
