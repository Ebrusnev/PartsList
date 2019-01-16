package com.partsList;

import com.partsList.model.CompPart;
import com.partsList.repository.CompPartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class CompPartsController {

    private static Integer pageNumber;
    private static String requiredOrNot;
    private static String stringFromSearch;
    private Page<CompPart> parts;

    @Autowired
    private CompPartRepository compPartRepository;

    @GetMapping("/")
    public String showPage(Model model, @RequestParam(name = "required", defaultValue = "") String required, @RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "nameForSearch", defaultValue = "") String nameForSearch) {

//        Sort sort = sort = new Sort(Sort.Direction.ASC, "id");
//        //Sorting by Necessity
//        if (!"".equals(required)) {
//            if ("true".equalsIgnoreCase(required)) {
//                sort = new Sort(Sort.Direction.DESC, "isMustHave");
//            }
//            if ("false".equalsIgnoreCase(required)) {
//                sort = new Sort(Sort.Direction.ASC, "isMustHave");
//            }
//        }

        Integer pageToPass = (page > 0) ? page - 1 : 0;
        //PageRequest request = PageRequest.of(pageToPass, 10, sort);
        PageRequest request = PageRequest.of(pageToPass, 10);

        Map<String, Integer> mapOfNecessaryParts = new HashMap<>();
        Iterable<CompPart> compParts = compPartRepository.findAll();
        for (CompPart part : compParts) {
            if (part.isMustHave()) {
                String partName = part.getName().split(" ")[0].toLowerCase();
                if (mapOfNecessaryParts.containsKey(partName)) {
                    int count = mapOfNecessaryParts.get(partName) + part.getQuantity();
                    mapOfNecessaryParts.put(partName, count);
                } else {
                    mapOfNecessaryParts.put(partName, part.getQuantity());
                }
            }
        }
        List<Integer> partsQuantity = new ArrayList<>(mapOfNecessaryParts.values());
        Collections.sort(partsQuantity);
        if (partsQuantity.isEmpty()) {
            model.addAttribute("availableComps", 0);
        } else {
            model.addAttribute("availableComps", partsQuantity.get(0));
        }
        //parts = compPartRepository.findBySearchParams(nameForSearch, request);
        if ("true".equalsIgnoreCase(required) || "false".equalsIgnoreCase(required)) {
            boolean isRequired = Boolean.valueOf(required);
            parts = compPartRepository.findByIsMustHaveAndNameContaining(isRequired, nameForSearch, request);
        } else {
            parts = compPartRepository.findByNameContaining(nameForSearch, request);
        }
        pageNumber = page;
        requiredOrNot = required;
        stringFromSearch = nameForSearch;


        model.addAttribute("parts", parts);
        model.addAttribute("currentPage", pageToPass);
        model.addAttribute("pageNumber", page);
        model.addAttribute("required", required);
        model.addAttribute("nameForSearch", nameForSearch);

        return "index";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute CompPart compPart, @RequestParam(value = "isMustHave", required = false) String checkBoxValue) {

        if (checkBoxValue != null) {
            compPart.setMustHave(true);
        } else {
            compPart.setMustHave(false);
        }
        compPartRepository.save(compPart);
        return redirectionBuilder();
    }

    @GetMapping("/delete")
    public String deleteCompPart(Integer id) {
        if (pageNumber > 0 && (parts.getTotalElements() - 1) % 10 == 0) {
            pageNumber--;
        }
        compPartRepository.deleteById(id);
        return redirectionBuilder();
    }

    @GetMapping("/findOne")
    @ResponseBody
    public Optional<CompPart> findOne(Integer id) {
        return compPartRepository.findById(id);
    }

    private String redirectionBuilder() {
        StringBuilder redirection = new StringBuilder("redirect:/");
        if (!"".equalsIgnoreCase(requiredOrNot)) {
            redirection.append("?required=").append(requiredOrNot);
        }
        String redirect = redirection.toString();
        if (redirect.contains("required")) {
            if (!"".equalsIgnoreCase(stringFromSearch)) {
                redirect += "&nameForSearch=" + stringFromSearch;
            }
        } else {
            redirect += "?nameForSearch=" + stringFromSearch;
        }
        if (redirect.contains("nameForSearch") || redirect.contains("required")) {
            redirect += "&page=" + pageNumber;
        } else {
            redirect += "?page=" + pageNumber;
        }

        return redirect;
    }
}

