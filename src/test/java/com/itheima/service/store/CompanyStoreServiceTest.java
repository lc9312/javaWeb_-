package com.itheima.service.store;

import com.itheima.domain.store.Company;
import com.itheima.service.store.Impl.CompanyServiceImpl;
import org.junit.Test;

import java.util.List;

public class CompanyStoreServiceTest {
    @Test
    public void findAll(){
        CompanyServiceImpl companyService = new CompanyServiceImpl();
        List<Company> all = companyService.findAll();
        System.out.println(all.size());
        System.out.println("------------------");
        System.out.println(companyService.findAll(1, 5).getSize());
    }
    @Test
    public void findById(){
        CompanyServiceImpl companyService = new CompanyServiceImpl();
        Company company = companyService.findById("1");
        System.out.println(company);
    }
}
