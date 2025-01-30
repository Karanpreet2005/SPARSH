package com.example.doa;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final List<Scheme> schemeList = new ArrayList<>();
    private final List<Scheme> filteredList = new ArrayList<>();
    private SchemeAdapter adapter;
    private String selectedCategory = "Financial"; // Default category
    private String searchQuery = ""; // Store the search input

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the app theme to follow the system settings
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView
        RecyclerView recyclerView = findViewById(R.id.schemes_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Populate scheme list with sample data
        populateSchemeList();

        // Set up the adapter for RecyclerView
        adapter = new SchemeAdapter(this, filteredList);
        recyclerView.setAdapter(adapter);

        // Initialize Spinner for category selection
        Spinner categorySpinner = findViewById(R.id.category_spinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(
                this, R.array.scheme_categories, R.layout.spinner_item); // Use custom spinner item layout
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item); // Custom dropdown style
        categorySpinner.setAdapter(spinnerAdapter);

        // Set default category to "Financial"
        categorySpinner.setSelection(0);

        // Handle category selection events
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCategory = parent.getItemAtPosition(position).toString();
                filterSchemes(); // Filter schemes based on category and search query
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Default to Financial if nothing is selected
                selectedCategory = "Financial";
                filterSchemes();
            }
        });

        // Set up search input listener
        EditText searchInput = findViewById(R.id.search_input);
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchQuery = s.toString();
                filterSchemes(); // Filter schemes based on search query and category
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Filter schemes initially for the default category
        filterSchemes();
    }

    private void populateSchemeList() {
        schemeList.add(new Scheme("HIM UNNATI YOJANA", R.drawable.him_unati_yojana,
                "https://youtube.com/embed/_6viRHCjl9Y",
                "HIM UNNATI YOJANA is an initiative by the Himachal Pradesh government aimed\n" +
                        "at boosting the agricultural and horticultural sectors. The scheme is focused on\n" +
                        "promoting sustainable development, increasing productivity, and improving the\n" +
                        "livelihood of farmers in the state. It also emphasizes the use of modern technologies,\n" +
                        "resources, and practices to enhance agricultural output and reduce post-harvest\n" +
                        "losses.",
                "● Who can apply: The scheme is primarily targeted at farmers, horticulturists,\n" +
                        "and individuals involved in agriculture in Himachal Pradesh. Beneficiaries\n" +
                        "need to have land in Himachal Pradesh for agriculture or horticulture\n" +
                        "activities.\n" +
                        "● Income group: There are no specific income group restrictions, but priority\n" +
                        "may be given to small and marginal farmers.",
                "1. Online Application:\n" +
                        "○ Visit the Himachal Pradesh Department of Horticulture website or the\n" +
                        "state government's agriculture portal.\n" +
                        "○ Navigate to the section for schemes and select \"HIM UNNATI\n" +
                        "YOJANA.\"\n" +
                        "○ Fill in the necessary details such as personal information, landholding\n" +
                        "details, and the specific activities for which you are applying.\n" +
                        "○ Upload required documents like proof of land ownership, identity proof\n" +
                        "(Aadhaar card), and income certificates (if needed).\n" +
                        "○ Submit the application form online and save the acknowledgment\n" +
                        "number for future reference.\n" +
                        "2. Offline Application:\n" +
                        "○ Farmers can also visit their nearest Agriculture/Horticulture\n" +
                        "Department Office in Himachal Pradesh.\n" +
                        "○ Collect the application form for HIM UNNATI YOJANA and fill it out.\n" +
                        "○ Submit the form along with the required documents to the concerned\n" +
                        "officials.\n" +
                        "○ After submission, applicants will receive a receipt, which can be used\n" +
                        "for follow-up.",
                "Educational",
                R.drawable.logo));

        schemeList.add(new Scheme("Prakritik Kheti Khushhal Kisan Yojana", R.drawable.prakritik,
                "https://youtube.com/embed/_6viRHCjl9Y",
                "Prakritik Kheti Khushhal Kisan Yojana is an initiative by the Government of\n" +
                        "Himachal Pradesh that promotes natural or traditional farming methods to reduce\n" +
                        "farmers' dependence on chemical fertilizers and pesticides. The scheme aims to\n" +
                        "revitalize soil health, reduce production costs, and increase farmers' incomes\n" +
                        "through sustainable agricultural practices. It focuses on promoting zero budget\n" +
                        "natural farming (ZBNF), which minimizes external inputs by using natural\n" +
                        "resources available on farms.\n" +
                        "Here’s a detailed description of the Prakritik Kheti Khushhal Kisan Yojana:\n" +
                        "Key Features:\n" +
                        "1. Promotion of Natural Farming: The scheme promotes zero budget natural\n" +
                        "farming (ZBNF), which advocates using farm-made inputs like cow dung, cow\n" +
                        "urine, and plant extracts instead of chemical fertilizers and pesticides. It\n" +
                        "focuses on low-cost and eco-friendly farming practices.\n" +
                        "2. Soil Health Improvement: Natural farming helps restore the fertility of the\n" +
                        "soil by reducing the chemical load and encouraging the use of organic inputs.\n" +
                        "It also improves the biodiversity of the soil.\n" +
                        "3. Zero Budget Farming: ZBNF encourages farmers to reduce their cost of\n" +
                        "cultivation to nearly zero by producing their own organic inputs. This helps\n" +
                        "small and marginal farmers become less dependent on external credit or\n" +
                        "market-purchased inputs.\n" +
                        "4. Subsidies and Financial Assistance: The government provides financial\n" +
                        "assistance to farmers for adopting ZBNF practices, purchasing livestock, and\n" +
                        "setting up necessary infrastructure like compost pits.\n" +
                        "5. Training and Capacity Building: Farmers are trained in ZBNF techniques\n" +
                        "through workshops, demonstrations, and farmer-to-farmer\n" +
                        "knowledge-sharing programs. The government engages agricultural experts\n" +
                        "and ZBNF practitioners to provide technical guidance.\n" +
                        "6. Focus on Indigenous Resources: The scheme emphasizes the use of indigenous\n" +
                        "seeds, local breeds of livestock (especially cows), and traditional farming\n" +
                        "knowledge.\n" +
                        "7. Climate Resilience: Natural farming is more resilient to climate change as it\n" +
                        "reduces water usage, enhances soil moisture retention, and improves the\n" +
                        "overall adaptability of crops to extreme weather conditions.\n" +
                        "Benefits:\n" +
                        "● Low Production Costs: ZBNF reduces farmers’ dependence on expensive\n" +
                        "chemical inputs and significantly lowers the cost of production, which is\n" +
                        "especially beneficial for small and marginal farmers.\n" +
                        "● Improved Soil Health: Natural inputs help improve the health and fertility of\n" +
                        "the soil, leading to better yields over time without harming the environment.\n" +
                        "● Increased Farm Income: By reducing input costs and increasing the quality of\n" +
                        "produce, farmers can fetch higher prices in the market, thus improving their\n" +
                        "income.\n" +
                        "● Eco-Friendly Farming: The scheme encourages sustainable farming that\n" +
                        "reduces environmental degradation and promotes biodiversity.\n" +
                        "● Market Linkages: Organic and naturally grown produce often fetches\n" +
                        "premium prices, helping farmers tap into niche organic markets.",
                "● Who can apply: All farmers in Himachal Pradesh, including small and\n" +
                        "marginal farmers, are eligible to apply. Special focus is given to farmers\n" +
                        "practicing chemical-intensive farming and those in ecologically sensitive zones.\n" +
                        "● Target Farmers: Priority is often given to small, marginal, and women\n" +
                        "farmers who stand to benefit the most from reducing their input costs.",
                "1. Online Application:\n" +
                        "○ Visit the Himachal Pradesh Agriculture Department website or the\n" +
                        "Prakritik Kheti Khushhal Kisan Yojana portal.\n" +
                        "○ Register as a farmer on the portal and complete the online application\n" +
                        "form with details about your farm, landholding, and current\n" +
                        "agricultural practices.\n" +
                        "○ Provide information about the ZBNF practices you intend to adopt.\n" +
                        "○ Upload required documents such as proof of identity, land records, and\n" +
                        "any other necessary information.\n" +
                        "○ Submit the form and keep a copy of the application number for future\n" +
                        "reference.\n" +
                        "2. Offline Application:\n" +
                        "○ Visit the nearest Agriculture Department Office, Krishi Vigyan Kendra\n" +
                        "(KVK), or the Zonal Agriculture Office to collect the application form.\n" +
                        "○ Fill out the form with personal details, landholding information, and\n" +
                        "the nature of ZBNF practices you wish to adopt.\n" +
                        "○ Submit the form along with the required documents.\n" +
                        "○ Upon submission, an acknowledgment receipt will be provided for\n" +
                        "tracking the status of the application.",
                "Educational",
                R.drawable.logo));

        schemeList.add(new Scheme("Rajya Krishi Yantrikaran Programme", R.drawable.rajya_krishi,
                "https://youtube.com/embed/_6viRHCjl9Y",
                "Rajya Krishi Yantrikaran Programme is a scheme initiated by various state\n" +
                        "governments in India, including Himachal Pradesh, to promote farm mechanization.\n" +
                        "The primary objective of the scheme is to help farmers access modern agricultural\n" +
                        "equipment at subsidized rates, thereby reducing their labor burden, improving\n" +
                        "productivity, and increasing income.\n" +
                        "Subsidy Structure:\n" +
                        "● The amount of subsidy typically ranges from 40% to 80% of the total cost of\n" +
                        "the equipment.\n" +
                        "● Women farmers, SC/ST farmers, and small and marginal farmers are usually\n" +
                        "eligible for higher subsidies.",
                "● Who can apply: Farmers involved in agriculture within the state, particularly\n" +
                        "small and marginal farmers, are eligible for this scheme.\n" +
                        "● Categories: Priority is often given to small and marginal farmers, as well as\n" +
                        "women and farmers from disadvantaged communities (SC/ST).",
                "1. Online Application:\n" +
                        "○ Visit the Agriculture Department website of the respective state.\n" +
                        "○ Look for the \"Farm Mechanization\" or \"Rajya Krishi Yantrikaran\n" +
                        "Programme\" section.\n" +
                        "○ Register yourself as a farmer on the portal (if not already registered).\n" +
                        "○ Fill in the application form with your personal details, landholding\n" +
                        "information, and the type of machinery you wish to apply for.\n" +
                        "○ Upload necessary documents such as identity proof, land ownership\n" +
                        "records, and bank details.\n" +
                        "○ Submit the form and save the application number for future reference.\n" +
                        "2. Offline Application:\n" +
                        "○ Farmers can visit their nearest Agriculture Department Office or\n" +
                        "Krishi Vigyan Kendra (KVK) and obtain the application form.\n" +
                        "○ Fill out the form with required details and submit it along with\n" +
                        "supporting documents such as land ownership proof and identification.\n" +
                        "○ Upon successful submission, you will receive an acknowledgment\n" +
                        "receipt.",
                "Financial",
                R.drawable.logo));

        schemeList.add(new Scheme("The Flow Irrigation Scheme", R.drawable.flow_irrigation,
                "https://youtube.com/embed/_6viRHCjl9Y",
                "The Flow Irrigation Scheme is an initiative by various state governments in India,\n" +
                        "including Himachal Pradesh, to improve irrigation facilities by harnessing natural\n" +
                        "water sources, such as rivers, streams, and other water bodies, to provide efficient\n" +
                        "and sustainable irrigation to farmlands. The scheme primarily focuses on reducing\n" +
                        "farmers' dependence on rainfall, ensuring a regular water supply to agricultural\n" +
                        "fields, and enhancing crop productivity.",
                "● Who can apply: Farmers or groups of farmers who own or lease land in areas\n" +
                        "where natural water sources are available for flow irrigation are eligible to\n" +
                        "apply.\n" +
                        "● Income Groups: There are generally no income restrictions, but priority may\n" +
                        "be given to small and marginal farmers, as well as those in drought-prone\n" +
                        "regions.",
                "1. Online Application:\n" +
                        "○ Visit the official Agriculture Department or Irrigation Department\n" +
                        "website of your state.\n" +
                        "○ Navigate to the \"Irrigation Schemes\" or \"Flow Irrigation Scheme\"\n" +
                        "section.\n" +
                        "○ Register yourself on the portal if required and fill in the application\n" +
                        "form with relevant details like personal information, landholding\n" +
                        "details, and specific irrigation requirements.\n" +
                        "○ Upload necessary documents such as proof of land ownership, identity\n" +
                        "documents, and a water source verification certificate.\n" +
                        "○ Submit the application form online and keep the acknowledgment\n" +
                        "number for future reference.\n" +
                        "2. Offline Application:\n" +
                        "○ Visit the nearest Agriculture Department, Irrigation Department\n" +
                        "Office, or Krishi Vigyan Kendra (KVK) in your area to collect the\n" +
                        "application form.\n" +
                        "○ Fill in the form with details about the water source, landholding, and\n" +
                        "irrigation needs.\n" +
                        "○ Submit the filled application form along with required documents to the\n" +
                        "concerned office.\n" +
                        "○ Upon submission, you will receive an acknowledgment receipt, which\n" +
                        "can be used for follow-up.",
                "Financial",
                R.drawable.logo));

        schemeList.add(new Scheme("Scheme 5",
                R.drawable.logo,
                "https://youtube.com/embed/_6viRHCjl9Y",
                "Scheme Description",
                "Eligibility Criteria",
                "Application Process",
                "Financial",
                R.drawable.logo));

        schemeList.add(new Scheme("Scheme 6",
                R.drawable.logo,
                "https://youtube.com/embed/_6viRHCjl9Y",
                "Scheme Description",
                "Eligibility Criteria",
                "Application Process",
                "Financial",
                R.drawable.logo));

        schemeList.add(new Scheme("Scheme 7",
                R.drawable.logo,
                "https://youtube.com/embed/_6viRHCjl9Y",
                "Scheme Description",
                "Eligibility Criteria",
                "Application Process",
                "Financial",
                R.drawable.logo));

        schemeList.add(new Scheme("Scheme 8",
                R.drawable.logo,
                "https://youtube.com/embed/_6viRHCjl9Y",
                "Scheme Description",
                "Eligibility Criteria",
                "Application Process",
                "Educational",
                R.drawable.logo));

        schemeList.add(new Scheme("Scheme 9",
                R.drawable.logo,
                "https://youtube.com/embed/_6viRHCjl9Y",
                "Scheme Description",
                "Eligibility Criteria",
                "Application Process",
                "Educational",
                R.drawable.logo));

        schemeList.add(new Scheme("Scheme 10",
                R.drawable.logo,
                "https://youtube.com/embed/_6viRHCjl9Y",
                "Scheme Description",
                "Eligibility Criteria",
                "Application Process",
                "Educational",
                R.drawable.logo));

    }

    @SuppressLint("NotifyDataSetChanged")
    private void filterSchemes() {
        filteredList.clear();
        for (Scheme scheme : schemeList) {
            boolean matchesCategory = scheme.getCategory().equalsIgnoreCase(selectedCategory);
            boolean matchesQuery = scheme.getName().toLowerCase().contains(searchQuery.toLowerCase());

            if (matchesCategory && matchesQuery) {
                filteredList.add(scheme);
            }
        }
        adapter.notifyDataSetChanged();
    }

    public void openSchemeDetail(Scheme scheme) {
        Intent intent = new Intent(MainActivity.this, SchemeDetailActivity.class);
        intent.putExtra("scheme_name", scheme.getName());
        intent.putExtra("scheme_video", scheme.getVideoUrl());
        intent.putExtra("scheme_content", scheme.getDescription());
        intent.putExtra("eligibility_content", scheme.getEligibilityContent());
        intent.putExtra("how_to_apply_content", scheme.getHowToApplyContent());
        intent.putExtra("scheme_logo", scheme.getLogoResource()); // Pass logo resource
        startActivity(intent);
    }
}
