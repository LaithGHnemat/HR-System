package com.laith.hrsystem.laith.configuration;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {


    @Override
    public Optional<String> getCurrentAuditor() {
        // we should have the user from spring security.
        return Optional.of(" Layth Ghnemat");
    }
}
