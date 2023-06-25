package com.example.in28formation.Versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersionController {
    @GetMapping(value = "/person/parm", params = "version=1")
    public PersonV1 paramv1(){
        return new PersonV1("yousfi issame");
    }
    @GetMapping(value = "/person/parm", params = "version=2")
    public PersonV2 paramV2(){
        return new PersonV2(new Name("issame", "YOUSFI"));
    }




//    @GetMapping(path = "/person/v1")
//    public PersonV1 personv1(){
//        return new PersonV1("yousfi issame");
//    }
//    @GetMapping(path = "/person/v2")
//    public PersonV2 personV2(){
//        return new PersonV2(new Name("issame", "YOUSFI"));
//    }
}
