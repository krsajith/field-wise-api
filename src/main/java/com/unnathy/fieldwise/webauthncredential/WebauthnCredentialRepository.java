package com.unnathy.fieldwise.webauthncredential;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface WebauthnCredentialRepository extends JpaRepository<WebauthnCredential, Long> {
}
