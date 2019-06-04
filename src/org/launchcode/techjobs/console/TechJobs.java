package org.launchcode.techjobs.console;

import java.util.*;

/**
 * Created by LaunchCode
 */
public class TechJobs {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        // Initialize our field map with key/name pairs
        HashMap<String, String> columnChoices = new HashMap<>();
        columnChoices.put("core competency", "Skill");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("position type", "Position Type");
        columnChoices.put("all", "All");

        // Top-level menu options
        HashMap<String, String> actionChoices = new HashMap<>();
        actionChoices.put("search", "Search");
        actionChoices.put("list", "List");

        System.out.println("Welcome to LaunchCode's TechJobs App!");

        // Allow the user to search until they manually quit
        while (true) {

            String actionChoice = getUserSelection("View jobs by:", actionChoices);

            if (actionChoice.equals("list")) {

                String columnChoice = getUserSelection("List", columnChoices);

                if (columnChoice.equals("all")) {
                    printJobs(JobData.findAll());
                } else {

                    ArrayList<String> results = JobData.findAll(columnChoice);

                    System.out.println("\n*** All " + columnChoices.get(columnChoice) + " Values ***");

                    // Print list of skills, employers, etc
                    for (String item : results) {
                        System.out.println(item);
                    }
                }

            } else { // choice is "search"

                // How does the user want to search (e.g. by skill or employer)
                String searchField = getUserSelection("Search by:", columnChoices);
                ArrayList<String> search_by_jobs = JobData.findAll(searchField);
                ArrayList<HashMap<String, String>> allJobs = JobData.findAll();
                // What is their search term?
                System.out.println("\nSearch term: ");
                String searchTerm = in.nextLine();
                String searchTerm_lower = searchTerm.toLowerCase();

                if (searchField.equals("all")) {

                    ArrayList<HashMap<String, String>> searched_jobs = new ArrayList<HashMap<String, String>>();
                    for (HashMap<String, String> job : allJobs) {
                        Collection<String> job_values = job.values();
                        ArrayList<String> job_values_lower = new ArrayList<>();
                        for (String value : job_values) {
                            String value_lower = value.toLowerCase();
                            job_values_lower.add(value_lower);
                        }
                        for (String value : job_values_lower) {
                            if (value.contains(searchTerm)) {
                                searched_jobs.add(job);
                            }
                            printJobs(searched_jobs);
                        }}
                        // TreeMap<String, String> job_lower = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
                        //for (HashMap<String, String> job : allJobs) {

                        //    job_lower.putAll(job);

                            /*

                            for (String key : job.keySet()) {
                                ArrayList<String> keys_lower = new ArrayList<String>();
                                String key_lower = key.toLowerCase();
                                keys_lower.add(key_lower);
                            }
                            for (String value : job.values()) {
                                ArrayList<String> values_lower = new ArrayList<String>();
                                String value_lower = value.toLowerCase();
                                values_lower.add(value_lower);
                            }
                             */
                    // -- if search, then choose something other than all --
                } else {

                    ArrayList<HashMap<String, String>> searched_jobs = new ArrayList<HashMap<String, String>>();
                    ArrayList<String> search_params = new ArrayList<>();

                    for (String job : search_by_jobs) {
                        String job_lower = job.toLowerCase();
                        search_params.add(job_lower); }

                    if (search_params.contains(searchTerm_lower)) {
                        printJobs(JobData.findByColumnAndValue(searchField, searchTerm));
                    } else {
                        System.out.println("No Results...");
                    }
                }
            }

                            /*
                            for (String key : job.keySet()) {

                                if (key.equalsIgnoreCase(searchTerm)) {
                                    //System.out.println("\n*****");

                                    String value = job.get(key);
                                    HashMap<String, String> solo_job = new HashMap<String, String>();
                                    solo_job.put(key, value);

                                    //System.out.println(key + ": " + value);

                                    //System.out.println("*****");
                                }
                                searched_jobs.put(solo_job);
                                printJobs(searched_jobs);
                            }
                            for (String value : job.values()) {
                                if (value.equalsIgnoreCase(searchTerm)) {
                                    System.out.println("\n*****");

                                    String key = job.get(value);
                                    System.out.println(key + ": " + value);

                                    System.out.println("*****");
                                }
                            }

                        }
                    }
                } */ /*else {
                    for (String item : search_list) {
                        String search_item = searchTerm.toLowerCase();
                        String item_lower = item.toLowerCase();
                        if (search_item.equalsIgnoreCase(item_lower)) {
                            printJobs(JobData.findByColumnAndValue(searchField, searchTerm));
                        } else {
                            System.out.println("No Results");
                        }
                    }
                } */


            }
        }


    // ﻿Returns the key of the selected item from the choices Dictionary;
    private static String getUserSelection(String menuHeader, HashMap<String, String> choices) {

        Integer choiceIdx;
        Boolean validChoice = false;
        String[] choiceKeys = new String[choices.size()];

        // Put the choices in an ordered structure so we can
        // associate an integer with each one
        Integer i = 0;
        for (String choiceKey : choices.keySet()) {
            choiceKeys[i] = choiceKey;
            i++;
        }

        do {

            System.out.println("\n" + menuHeader);

            // Print available choices
            for (Integer j = 0; j < choiceKeys.length; j++) {
                System.out.println("" + j + " - " + choices.get(choiceKeys[j]));
            }

            choiceIdx = in.nextInt();
            in.nextLine();

            // Validate user's input
            if (choiceIdx < 0 || choiceIdx >= choiceKeys.length) {
                System.out.println("Invalid choice. Try again.");
            } else {
                validChoice = true;
            }

        } while (!validChoice);

        return choiceKeys[choiceIdx];
    }

    // Print a list of jobs
    private static void printJobs(ArrayList<HashMap<String, String>> someJobs) {
        ArrayList<HashMap<String,String>> jobs_unique = new ArrayList<>();
        for (HashMap<String, String> job : someJobs) {

            if (!jobs_unique.contains(job)) {
                jobs_unique.add(job);
            }
        for (HashMap<String, String> job_map : jobs_unique){
            System.out.println("\n*****");
            for (String key : job_map.keySet()){
                String value = job_map.get(key);
                System.out.println(key + ": " + value);
            }
            System.out.println("*****");
        }
    }


    }
}

/* public class testBreak {

    public static void main(String [] args) {
        int[] someInts = {1, 10, 2, 3, 5, 8, 10};
        int searchTerm = 10;
        for(int oneInt : someInts) {
            if (oneInt == searchTerm) {
                System.out.println("Found it!");
                break;
            }
        }
    }

 */