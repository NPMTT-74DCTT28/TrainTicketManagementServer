package com.nmptt.ticketapi.specification;

import com.nmptt.ticketapi.entity.VeTau;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class VeTauSpecification {
    public static Specification<VeTau> filter(String keyword, String maVe, LocalDateTime ngayDatVe) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (keyword != null && !keyword.trim().isEmpty()) {
                String likeKeyword = "%" + keyword + "%";
                jakarta.persistence.criteria.Predicate keywordPredicate = cb.or(
                        cb.like(root.get("maVe"), likeKeyword),
                        cb.like(root.get("idKhachHang"), likeKeyword),
                        cb.like(root.get("ngayDatVe"), likeKeyword)
                );
                predicates.add(keywordPredicate);
            }

            if (maVe != null && !maVe.trim().isEmpty()){
                predicates.add(cb.equal(root.get("maVe"), maVe));
            }

            if (ngayDatVe != null && !ngayDatVe.isAfter(LocalDateTime.now())){
                predicates.add(cb.equal(root.get("ngayDatVe"), ngayDatVe));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
