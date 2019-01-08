package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "/results", method= RequestMethod.POST)
    public String search(@RequestParam String searchTerm,@RequestParam String searchType, Model model  ) {

        if(searchType.equals("all")){
            ArrayList<HashMap<String, String>> searchResults = JobData.findByValue(searchTerm);
            model.addAttribute("columns", ListController.columnChoices);
            model.addAttribute("searchResults",JobData.findByValue(searchTerm));
            int searchResultsSize = searchResults.size();
            model.addAttribute("searchResultsSize",searchResults.size());
        } else {
            ArrayList<HashMap<String, String>> searchResults = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("columns", ListController.columnChoices);
            model.addAttribute("searchResults", JobData.findByColumnAndValue(searchType, searchTerm));
            int searchResultsSize = searchResults.size();
            model.addAttribute("searchResultsSize",searchResults.size());
        }

        return "search";

    }
}
