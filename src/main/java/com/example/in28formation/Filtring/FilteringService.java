//package com.example.in28formation.Filtring;
//
//import com.fasterxml.jackson.databind.ser.FilterProvider;
//import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
//import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
//import org.springframework.http.converter.json.MappingJacksonValue;
//import org.springframework.stereotype.Component;
//
//
//@Component
//
//public class FilteringService {
//
//    public static MappingJacksonValue Filtering(SomeBean someBean){
//        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
//        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFiltre",filter);
//        MappingJacksonValue mapping = new MappingJacksonValue(someBean);
//        mapping.setFilters(filters);
//        return mapping;
//    }
//}
