package ua.nure.koshova.SummaryTask4.service;

import ua.nure.koshova.SummaryTask4.db.entity.Brand;

import java.util.List;

/**
 * Interface for common brand actions
 */
public interface BrandService {

    /**
     * Get all brand
     *
     * @return {@Link List<Brand}
     */
    List<Brand> getBrandList();

    /**
     * Get brand by id
     *
     * @param id
     * @return {@Link Brand}
     */

    Brand getBrandById(Long id);

    /**
     * Get brand by name
     *
     * @param name
     * @return id brand
     */
    Long getBrandByName(String name);
}
