package com.ae_diary.domain.use_case.certificate

import com.ae_diary.domain.model.CertificateDomain
import com.ae_diary.domain.repository.CertificateRepository
import javax.inject.Inject

class AddCertificateUseCase @Inject constructor(
    private val certificateRepository: CertificateRepository
) {

    suspend operator fun invoke(certificate: CertificateDomain) =
        certificateRepository.addCertificate(certificate)
}