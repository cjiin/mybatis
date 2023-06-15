package com.kh.mybatis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    private CompanyMapper companyMapper;

    @PostMapping("")
    public int post(@RequestBody Company company) {
        return companyMapper.insert(company);
    }
    @GetMapping("")
    public List<Company> getAll() {
        return companyMapper.getAll();
    }
}
