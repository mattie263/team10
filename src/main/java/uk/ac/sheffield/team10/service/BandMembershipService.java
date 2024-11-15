package uk.ac.sheffield.team10.service;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import uk.ac.sheffield.team10.model.BandMembership;
import uk.ac.sheffield.team10.repository.BandMembershipRepository;

@Service
public class BandMembershipService {
    private final BandMembershipRepository bandMembershipRepository;

    public BandMembershipService(BandMembershipRepository bandMembershipRepository) {
        this.bandMembershipRepository = bandMembershipRepository;
    }

    public List<BandMembership> getAll() {
        return bandMembershipRepository.findAll();
    }

    public Optional<BandMembership> getById(Long id) {
        return bandMembershipRepository.findById(id);
    }

    public BandMembership save(BandMembership bandMembership) {
        return bandMembershipRepository.save(bandMembership);
    }

    public BandMembership update(Long id, BandMembership updatedBandMembership) {
        Optional<BandMembership> bandMembershipOptional = bandMembershipRepository.findById(id);
        if (bandMembershipOptional.isPresent()) {
            BandMembership bandMembership = bandMembershipOptional.get();
            bandMembership.setSeniorBandId(updatedBandMembership.getSeniorBandId());
            bandMembership.setTrainingBandId(updatedBandMembership.getTrainingBandId());
            bandMembership.setMemberId(updatedBandMembership.getMemberId());
            return bandMembershipRepository.save(bandMembership);
        } else {
            throw new RuntimeException("Band Membership Not Found -> id=" + id);
        }
    }

    public void delete(Long id) {
        bandMembershipRepository.deleteById(id);
    }
}
