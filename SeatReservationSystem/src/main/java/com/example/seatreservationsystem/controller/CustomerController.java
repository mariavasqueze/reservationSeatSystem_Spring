package com.example.seatreservationsystem.controller;

import com.example.seatreservationsystem.entities.Customer;
import com.example.seatreservationsystem.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    public CustomerRepository customerRepository;

    @GetMapping(path = {"/", "index"})
    public String getAllCustomers(Model model) {
        List<Customer> customers = customerRepository.findAll();
        model.addAttribute("customerList", customers);

        return "homePage";
    }

    @GetMapping(path = "/delete")
    public String delete(Long id){
        customerRepository.deleteById(id);
        return "redirect:/index";
    }


    @GetMapping(path = "/add")
    public String addReservation(Model model){
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "addCustomer";
    }

    @PostMapping(path = "/save")
    public String saveNewCustomer(Customer customer, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "addCustomer";
        } else {
            customerRepository.save(customer);
            return "redirect:/index";
        }
    }


    //    @PostMapping(path = "/save")
//    public String saveNewCustomer(Model model, long id, BindingResult bindingResult){
//        Customer customer = customerRepository.findById(id).orElse(null);
//        if(customer == null){
//            customer = new Customer();
//            model.addAttribute("customer", customer);
//            return "redirect:/index";
//        }
//        if (bindingResult.hasErrors()) {
//            return "index";
//        } else {
//            customerRepository.save(customer);
//            return "redirect:/index";
//        }
//    }
    @GetMapping(path = "/editReservation")
    public String updateField(Model model, long id){
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer == null){
            throw new RuntimeException("Record does not exist!");
        }
        model.addAttribute("customer", customer);
        return "editField";
    }





}
