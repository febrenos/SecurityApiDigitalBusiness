package br.com.fiap.apitask.services;

import br.com.fiap.apitask.dto.UserCreatedDto;
import br.com.fiap.apitask.models.UserData;
import br.com.fiap.apitask.repositories.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdatedUserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository infoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserData> userData = infoRepository.findByUsername(username);
        return userData.orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public void addUser(UserData userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        infoRepository.save(userInfo);
    }

    public Page<UserCreatedDto> listAllUsers(Pageable pageable) {
        return infoRepository.findAll(pageable).map(UserCreatedDto::new);
    }
}
