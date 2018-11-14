package ua.nure.koshova.finalProject.service.Impl;

import ua.nure.koshova.finalProject.db.dao.BrandDao;
import ua.nure.koshova.finalProject.db.dao.impl.BrandDaoImpl;
import ua.nure.koshova.finalProject.db.entity.Brand;
import ua.nure.koshova.finalProject.service.BrandService;

import java.util.List;

public class BrandServiceImpl implements BrandService{

    private BrandDao brandDao = BrandDaoImpl.getInstance();

    public List<Brand> getBrandList() {
        return brandDao.findAllBrands();
    }

    public Brand getBrandById(Long id){
        return brandDao.getBrandById(id);
    }
    public Long getBrandByName(String name){
        return brandDao.getBrandByName(name);
    }
}
