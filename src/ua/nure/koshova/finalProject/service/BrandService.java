package ua.nure.koshova.finalProject.service;

import ua.nure.koshova.finalProject.db.dao.BrandDao;
import ua.nure.koshova.finalProject.db.entity.Brand;

import java.util.List;

public class BrandService {

    private BrandDao brandDao  = BrandDao.getInstance();

    public List<Brand> getBrandList() {
        return brandDao.findAllBrands();
    }
}
