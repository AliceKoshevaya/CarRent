package ua.nure.koshova.SummaryTask4.db.dao;

import ua.nure.koshova.SummaryTask4.db.entity.Brand;
import java.util.List;

public interface BrandDao {

    /**
     * Get brand by id
     *
     * @param  id
     *
     * @return  {@link Brand}
     *
     *
     */
    Brand getBrandById(Long id);

    /**
     * Get brand by name
     *
     * @param name
     *
     * @return brand id
     */
    Long getBrandByName(String name);

    /**
     * Select all brands
     *
     * @return {@link List<Brand>}
     */

    List<Brand> findAllBrands();
}
