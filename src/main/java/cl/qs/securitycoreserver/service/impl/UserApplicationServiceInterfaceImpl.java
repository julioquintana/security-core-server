package cl.qs.securitycoreserver.service.impl;

import cl.qs.securitycoreserver.dto.UserAplicationAccess.UserApplicationAccessRequestDto;
import cl.qs.securitycoreserver.dto.UserAplicationAccess.UserApplicationAccessResponseDto;
import cl.qs.securitycoreserver.entity.Application;
import cl.qs.securitycoreserver.entity.UserApplicationAccess;
import cl.qs.securitycoreserver.exception.SecurityCoreServerException;
import cl.qs.securitycoreserver.repository.UserApplicationAccessRepositoryInterface;
import cl.qs.securitycoreserver.service.interfaces.UserApplicationServiceAccessInterface;
import cl.qs.securitycoreserver.util.Constants;
import cl.qs.securitycoreserver.util.Matcher;
import cl.qs.securitycoreserver.util.UserApplicationAccessMapper;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class UserApplicationServiceInterfaceImpl implements UserApplicationServiceAccessInterface {

  private final UserApplicationAccessRepositoryInterface userApplicationAccessRepositoryInterface;

  @Autowired
  public UserApplicationServiceInterfaceImpl(
      UserApplicationAccessRepositoryInterface applicationRepository) {
    this.userApplicationAccessRepositoryInterface = applicationRepository;
  }

  @Override
  public List<UserApplicationAccessResponseDto> findBy(
      UserApplicationAccessRequestDto userApplicationAccessRequestDto)
      throws SecurityCoreServerException {

    UserApplicationAccess userApplicationAccessCharacter = UserApplicationAccessMapper.buildSearchCharacter(
        userApplicationAccessRequestDto);
    List<UserApplicationAccess> applicationEntities = userApplicationAccessRepositoryInterface.findAll(
        Example.of(userApplicationAccessCharacter, Matcher.getApplicationMatcher()));

    if (!CollectionUtils.isEmpty(applicationEntities)) {
      return UserApplicationAccessMapper.buildUserApplicationAccessResponseList(
          applicationEntities);
    }
    throw new SecurityCoreServerException("9999", Constants.ERROR, Constants.APPLICATION_NOT_FOUND,
        HttpStatus.NOT_FOUND);
  }

  @Override
  public UserApplicationAccessResponseDto save(
      UserApplicationAccessRequestDto applicationRequestDto)
      throws SecurityCoreServerException {

    Optional<UserApplicationAccess> applicationOptional = userApplicationAccessRepositoryInterface.findByDescription(
        applicationRequestDto.getDescription());
    
    if (applicationOptional.isEmpty()) {
      return UserApplicationAccessMapper.buildUserApplicationAccessResponse(
          userApplicationAccessRepositoryInterface.save(
              UserApplicationAccessMapper.build(applicationRequestDto)));
    } else {
      throw new SecurityCoreServerException("9991",
          Constants.ERROR,
          Constants.APPLICATION_FOUND + " [" + applicationRequestDto.getName() + "]",
          HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public UserApplicationAccessResponseDto update(
      UserApplicationAccessRequestDto applicationRequestDto)
      throws SecurityCoreServerException {
    Optional<UserApplicationAccess> applicationOptional = userApplicationAccessRepositoryInterface.findById(
        applicationRequestDto.getId());

    if (applicationOptional.isPresent()) {
      Application application = UserApplicationAccessMapper.buildApplicationUpdate(
          applicationRequestDto,
          applicationOptional.get());

      return UserApplicationAccessMapper.buildUserApplicationAccessResponse(
          userApplicationAccessRepositoryInterface.save(application));
    } else {
      throw new SecurityCoreServerException("9991", Constants.ERROR,
          Constants.APPLICATION_NOT_FOUND_BY_ID, HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public UserApplicationAccessResponseDto deleteById(Long id) throws SecurityCoreServerException {
    Optional<UserApplicationAccess> levelOptional = userApplicationAccessRepositoryInterface.findById(
        id);

    if (levelOptional.isPresent()) {
      userApplicationAccessRepositoryInterface.deleteById(id);
      return UserApplicationAccessMapper.buildUserApplicationAccessResponse(levelOptional.get());
    } else {
      throw new SecurityCoreServerException("9993", Constants.ERROR,
          Constants.APPLICATION_NOT_FOUND_BY_ID, HttpStatus.NOT_FOUND);
    }
  }
}
