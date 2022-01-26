package cl.qs.securitycoreserver.service.impl;

import cl.qs.securitycoreserver.dto.application.ApplicationRequestDto;
import cl.qs.securitycoreserver.dto.application.ApplicationResponseDto;
import cl.qs.securitycoreserver.entity.Application;
import cl.qs.securitycoreserver.exception.SecurityCoreServerException;
import cl.qs.securitycoreserver.repository.ApplicationRepositoryInterface;
import cl.qs.securitycoreserver.service.ApplicationServiceInterface;
import cl.qs.securitycoreserver.util.ApplicationMapper;
import cl.qs.securitycoreserver.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationServiceInterfaceImpl implements ApplicationServiceInterface {

    private final ApplicationRepositoryInterface applicationRepository;

    @Autowired
    public ApplicationServiceInterfaceImpl(ApplicationRepositoryInterface applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public List<ApplicationResponseDto> findAll() throws SecurityCoreServerException {
        List<Application> applicationEntities = applicationRepository.findAll();
        if (!CollectionUtils.isEmpty(applicationEntities)) {
            return ApplicationMapper.buildApplicationResponseList(applicationEntities);
        }
        throw new SecurityCoreServerException("9999", Constants.ERROR, Constants.APPLICATION_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @Override
    public ApplicationResponseDto save(ApplicationRequestDto applicationRequestDto) throws SecurityCoreServerException {
        Optional<Application> applicationOptional = applicationRepository.findByName(applicationRequestDto.getName());
        if (applicationOptional.isEmpty()) {
            return ApplicationMapper.buildApplicationResponse(applicationRepository.save(ApplicationMapper.buildApplication(applicationRequestDto)));
        } else {
            throw new SecurityCoreServerException("9991",
                    Constants.ERROR, Constants.APPLICATION_FOUND + " [" + applicationRequestDto.getName() + "]",
                    HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ApplicationResponseDto update(ApplicationRequestDto applicationRequestDto) throws SecurityCoreServerException {
        Optional<Application> applicationOptional = applicationRepository.findById(applicationRequestDto.getId());

        if (applicationOptional.isPresent()) {
            Application application = ApplicationMapper.buildApplicationUpdate(applicationRequestDto, applicationOptional.get());

            return ApplicationMapper.buildApplicationResponse(applicationRepository.save(application));
        } else {
            throw new SecurityCoreServerException("9991", Constants.ERROR, Constants.APPLICATION_NOT_FOUND_BY_ID, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ApplicationResponseDto findById(Long id) throws SecurityCoreServerException {
        Optional<Application> levelOptional = applicationRepository.findById(id);

        if (levelOptional.isPresent()) {
            return ApplicationMapper.buildApplicationResponse(levelOptional.get());
        } else {
            throw new SecurityCoreServerException("9992", Constants.ERROR, Constants.APPLICATION_NOT_FOUND_BY_ID, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ApplicationResponseDto deleteById(Long id) throws SecurityCoreServerException {
        Optional<Application> levelOptional = applicationRepository.findById(id);

        if (levelOptional.isPresent()) {
            applicationRepository.deleteById(id);
            return ApplicationMapper.buildApplicationResponse(levelOptional.get());
        } else {
            throw new SecurityCoreServerException("9993", Constants.ERROR, Constants.APPLICATION_NOT_FOUND_BY_ID, HttpStatus.NOT_FOUND);
        }
    }
}
