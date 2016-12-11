package cz.project.c3.repository.offer;

import cz.project.c3.domain.offer.Offer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends PagingAndSortingRepository<Offer, Long> {
}
