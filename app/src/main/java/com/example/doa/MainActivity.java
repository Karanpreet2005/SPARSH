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
                "https://youtube.com/embed/tmYUPIqUeC8",
                "HIM UNNATI YOJANA is an initiative by the Himachal Pradesh government aimed\n" +
                        "at boosting the agricultural and horticultural sectors. The scheme is focused on\n" +
                        "promoting sustainable development, increasing productivity, and improving the livelihood of farmers in the state. It also emphasizes the use of modern technologies, resources, and practices to enhance agricultural output and reduce post-harvest losses.",
                "● Who can apply: The scheme is primarily targeted at farmers, horticulturists,\n" +
                        "and individuals involved in agriculture in Himachal Pradesh. Beneficiaries need to have land in Himachal Pradesh for agriculture or horticulture activities.\n" +
                        "● Income group: There are no specific income group restrictions, but priority may be given to small and marginal farmers.",
                "1. Online Application:\n" +
                        "○ Visit the Himachal Pradesh Department of Horticulture website or the state government's agriculture portal.\n" +
                        "○ Navigate to the section for schemes and select \"HIM UNNATI YOJANA.\"\n" +
                        "○ Fill in the necessary details such as personal information, landholding details, and the specific activities for which you are applying.\n" +
                        "○ Upload required documents like proof of land ownership, identity proof (Aadhaar card), and income certificates (if needed).\n" +
                        "○ Submit the application form online and save the acknowledgment number for future reference.\n" +
                        "2. Offline Application:\n" +
                        "\n"+
                        "○ Farmers can also visit their nearest Agriculture/Horticulture Department Office in Himachal Pradesh.\n" +
                        "○ Collect the application form for HIM UNNATI YOJANA and fill it out.\n" +
                        "○ Submit the form along with the required documents to the concerned officials.\n" +
                        "○ After submission, applicants will receive a receipt, which can be used for follow-up.",
                "Once the application is submitted, officials from the Agriculture or Horticulture Department will verify the details. After successful verification, eligible applicants will be provided with subsidies or support under the scheme.\n" +
                        "Where to Apply:\n" +
                        "1. Online: Himachal Pradesh Department of Horticulture\n" +
                        "2. Offline: Visit the nearest Agriculture/Horticulture Department Office in\n" +
                        "Himachal Pradesh.",
                "Required Documents:\n" +
                        "● Proof of identity (Aadhaar card or PAN card).\n" +
                        "● Proof of land ownership or tenancy.\n" +
                        "● Bank account details.\n" +
                        "● A copy of the income certificate, if needed.\n" +
                        "● Photographs of the farm or orchard.",
                "Educational",
                R.drawable.logo));

        schemeList.add(new Scheme("Prakritik Kheti Khushhal Kisan Yojana", R.drawable.prakritik,
                "https://youtube.com/embed/YkB3bruu00o",
                "Prakritik Kheti Khushhal Kisan Yojana is an initiative by the Government of Himachal Pradesh that promotes natural or traditional farming methods to reduce farmers' dependence on chemical fertilizers and pesticides. The scheme aims to revitalize soil health, reduce production costs, and increase farmers' incomes through sustainable agricultural practices. It focuses on promoting zero budget natural farming (ZBNF), which minimizes external inputs by using natural resources available on farms.",

                "● Who can apply: All farmers in Himachal Pradesh, including small and marginal farmers, are eligible to apply. Special focus is given to farmers practicing chemical-intensive farming and those in ecologically sensitive zones.\n" +
                        "● Target Farmers: Priority is often given to small, marginal, and women farmers who stand to benefit the most from reducing their input costs.",
                "1. Online Application:\n" +
                        "○ Visit the Himachal Pradesh Agriculture Department website or the\n" +
                        "PRAKRITIK KHETI KHUSHAL KISAN YOJANA portal.\n" +
                        "○ Register as a farmer on the portal and complete the online application form with details about your farm, landholding, and current agricultural practices.\n" +
                        "○ Provide information about the ZBNF practices you intend to adopt.\n" +
                        "○ Upload required documents such as proof of identity, land records, and any other necessary information.\n" +
                        "○ Submit the form and keep a copy of the application number for future reference.\n" +
                        "\n"+
                        "2. Offline Application:\n" +
                        "○ Visit the nearest Agriculture Department Office, Krishi Vigyan Kendra\n" +
                        "(KVK), or the Zonal Agriculture Office to collect the application form.\n" +
                        "○ Fill out the form with personal details, landholding information, and the nature of ZBNF practices you wish to adopt.\n" +
                        "○ Submit the form along with the required documents.\n" +
                        "○ Upon submission, an acknowledgment receipt will be provided for tracking the status of the application.",
                "● After the application is submitted, officials from the Agriculture Department will verify the documents and inspect the farm to ensure it is suitable for ZBNF practices.\n" +
                        "● Selected farmers will receive approval and financial support under the scheme.\n" +
                        "● The department also conducts periodic reviews to assess the implementation of natural farming practices.\n"+"Practices Promoted under the Scheme:\n" +
                        "\n" +
                        "1. Jeevamrit: A microbial culture made from cow dung, cow urine, jaggery, and pulses to enrich the soil.\n" +
                        "2. Bijamrit: A treatment for seeds using cow dung and cow urine to protect crops from pests and diseases.\n" +
                        "3. Mulching: The practice of covering soil with organic materials to retain moisture and improve soil fertility.\n" +
                        "4. Waphasa: The process of managing soil moisture to optimize water use and enhance root growth.",
                "Required Documents:\n" +
                        "● Proof of identity (Aadhaar card, voter ID, or PAN card).\n" +
                        "● Proof of land ownership or tenancy (land records or patta).\n" +
                        "● Bank account details for receiving financial assistance.\n" +
                        "● Passport-size photographs.\n" +
                        "\n"+
                        "Financial Support and Subsidies:\n" +
                        "● Subsidies: Farmers receive subsidies for the purchase of essential equipment,seeds, livestock (especially native cows for cow dung and cow urine), and the construction of farm infrastructure like compost pits and irrigation systems.\n" +
                        "● Financial Assistance: Farmers can also receive financial incentives for adopting ZBNF practices. The amount of assistance varies based on the land size and the specific components of the farming system being implemented.",
                "Educational",
                R.drawable.logo));

        schemeList.add(new Scheme("Rajya Krishi Yantrikaran Programme", R.drawable.rajya_krishi,
                "https://youtube.com/embed/jFLuOkC4lnQ",
                "Rajya Krishi Yantrikaran Programme is a scheme initiated by various state governments in India, including Himachal Pradesh, to promote farm mechanization.The primary objective of the scheme is to help farmers access modern agricultural equipment at subsidized rates, thereby reducing their labor burden, improving productivity, and increasing income.\n" +
                        "\n"+
                        "Subsidy Structure:\n" +
                        "● The amount of subsidy typically ranges from 40% to 80% of the total cost of the equipment.\n" +
                        "● Women farmers, SC/ST farmers, and small and marginal farmers are usually eligible for higher subsidies.\n"+
                        "\n"+
                        "Machinery Available under the Scheme:\n" +
                        "● Tractors\n" +
                        "● Power tillers\n" +
                        "● Seed drills\n" +
                        "● Combine harvesters\n" +
                        "● Sprayers\n" +
                        "● Irrigation pumps\n" +
                        "● Rotavators\n" +
                        "● Threshers",
                "● Who can apply: Farmers involved in agriculture within the state, particularly\n" +
                        "small and marginal farmers, are eligible for this scheme.\n" +
                        "● Categories: Priority is often given to small and marginal farmers, as well as\n" +
                        "women and farmers from disadvantaged communities (SC/ST).",
                "1. Online Application:\n" +
                        "○ Visit the Agriculture Department website of the respective state.\n" +
                        "○ Look for the \"Farm Mechanization\" or \"Rajya Krishi Yantrikaran Programme\" section.\n" +
                        "○ Register yourself as a farmer on the portal (if not already registered).\n" +
                        "○ Fill in the application form with your personal details, landholding information, and the type of machinery you wish to apply for.\n" +
                        "○ Upload necessary documents such as identity proof, land ownership records, and bank details.\n" +
                        "○ Submit the form and save the application number for future reference.\n" +
                        "\n"+
                        "2. Offline Application:\n" +
                        "○ Farmers can visit their nearest Agriculture Department Office or Krishi Vigyan Kendra (KVK) and obtain the application form.\n" +
                        "○ Fill out the form with required details and submit it along with supporting documents such as land ownership proof and identification.\n" +
                        "○ Upon successful submission, you will receive an acknowledgment receipt.",
                "● After submitting the application, the concerned authorities will verify the documents and eligibility of the applicant.\n" +
                        "● Selected farmers will be notified, and the machinery will either be provided directly or made available through custom hiring centers.",
                "Required Documents:\n" +
                        "● Proof of identity (Aadhaar card, voter ID, or PAN card).\n" +
                        "● Land ownership documents or lease records.\n" +
                        "● Bank account details for receiving the subsidy amount.\n" +
                        "● Caste certificate (if applicable for higher subsidy).\n" +
                        "● Passport-size photographs.",
                "Financial",
                R.drawable.logo));

        schemeList.add(new Scheme("The Flow Irrigation Scheme", R.drawable.flow_irrigation,
                "https://youtube.com/embed/rSn8AKv6Sqs",
                "The Flow Irrigation Scheme is an initiative by various state governments in India,including Himachal Pradesh, to improve irrigation facilities by harnessing natural water sources, such as rivers, streams, and other water bodies, to provide efficient and sustainable irrigation to farmlands. The scheme primarily focuses on reducing farmers' dependence on rainfall, ensuring a regular water supply to agricultural fields, and enhancing crop productivity.",
                "● Who can apply: Farmers or groups of farmers who own or lease land in areas where natural water sources are available for flow irrigation are eligible to apply.\n" +
                        "● Income Groups: There are generally no income restrictions, but priority may be given to small and marginal farmers, as well as those in drought-prone regions.",
                "1. Online Application:\n" +
                        "○ Visit the official Agriculture Department or Irrigation Department website of your state.\n" +
                        "○ Navigate to the \"Irrigation Schemes\" or \"Flow Irrigation Scheme\"section.\n" +
                        "○ Register yourself on the portal if required and fill in the application form with relevant details like personal information, landholding details, and specific irrigation requirements.\n" +
                        "○ Upload necessary documents such as proof of land ownership, identity documents, and a water source verification certificate.\n" +
                        "○ Submit the application form online and keep the acknowledgment number for future reference.\n" +
                        "\n"+
                        "2. Offline Application:\n" +
                        "○ Visit the nearest Agriculture Department, Irrigation Department Office, or Krishi Vigyan Kendra (KVK) in your area to collect the application form.\n" +
                        "○ Fill in the form with details about the water source, landholding, and irrigation needs.\n" +
                        "○ Submit the filled application form along with required documents to the concerned office.\n" +
                        "○ Upon submission, you will receive an acknowledgment receipt, which can be used for follow-up.",
                "● Once the application is submitted, officials from the Irrigation Department or Agriculture Department will visit the site to verify the availability of the water source and assess the feasibility of constructing the flow irrigation infrastructure.\n" +
                        "● Upon successful verification, eligible applicants will be sanctioned financial support or infrastructural assistance for the implementation of the flow irrigation system.",
                "Required Documents:\n" +
                        "● Proof of identity (Aadhaar card, voter ID, or PAN card).\n" +
                        "● Proof of land ownership or tenancy.\n" +
                        "● Certificate or document verifying the availability of a nearby water source (river, stream, or canal).\n" +
                        "● Bank account details for receiving subsidies or financial support.\n" +
                        "● Passport-size photographs.\n" +
                        "\n"+
                        "Subsidy and Financial Support:\n" +
                        "● Farmers or farmer groups participating in the scheme are eligible for financial support from the government for constructing water channels,diversion weirs, check dams, or any other infrastructure needed for the flow irrigation system.\n" +
                        "● The government typically offers a subsidy ranging from 50% to 90% of the total project cost, depending on the region, type of farmer (small, marginal),and specific project requirements.\n"+
                        "\n"+
                        "Infrastructure Provided:\n" +
                        "● Irrigation Canals: Construction of small- and medium-sized irrigation canals to carry water from natural sources to farmlands.\n" +
                        "● Diversion Weirs and Check Dams: Small barriers or dams to divert water into irrigation channels.\n" +
                        "● Field Channels: Construction of field channels or watercourses within farmlands to ensure even distribution of water across the fields.",
                "Financial",
                R.drawable.logo));

        schemeList.add(new Scheme("Agricultural Marketing",
                R.drawable.logo,
                "",
                "Agriculture Marketing refers to the processes involved in moving agricultural products from farms to consumers, encompassing production, harvesting, grading, packaging, storage,transport, and sales. A well-functioning agricultural marketing system ensures that farmers can sell their produce at fair prices, while consumers get access to high-quality products.In India, various government schemes and policies have been introduced to improve the agricultural marketing infrastructure, enhance farmer incomes, and make the market system more transparent and efficient.",
                "● Who can apply: Farmers involved in agriculture within the state, particularly small and marginal farmers, are eligible for this scheme.\n" +
                        "● Categories: Priority is often given to small and marginal farmers, as well as women and farmers from disadvantaged communities (SC/ST).",
                "1. Online Application:\n" +
                        "○ Farmers can apply for various schemes related to agricultural marketing by visiting the Agriculture Department's website or specific scheme portals like e-NAM or PMKSY.\n" +
                        "○ Registration on the e-NAM platform requires basic details like Aadhaar number, land records, and bank account details.\n" +
                        "○ Once registered, farmers can start listing their produce for sale on the platform.\n" +
                        "\n"+
                        "2. Offline Application:\n" +
                        "○ Farmers can visit their nearest Agriculture Office, Agriculture Produce Market Committee (APMC), or Krishi Vigyan Kendra (KVK) to apply for various schemes.\n" +
                        "○ Application forms are available at these offices, where farmers can fill in their details and submit required documents like identity proof, land records, and bank details.",
                "● After submitting the application, the concerned authorities will verify the farmer’s\n" +
                        "documents and eligibility for the scheme.\n" +
                        "● For platforms like e-NAM, farmers can start selling their produce after successful\n" +
                        "registration.\n" +
                        "● For subsidies related to infrastructure development or post-harvest management, field inspections and assessments will be conducted to ensure proper utilization of funds.",
                "Required Documents:\n" +
                        "● Aadhaar card (for identity proof).\n" +
                        "● Land ownership documents or patta (land records).\n" +
                        "● Bank account details for receiving payments and subsidies.\n" +
                        "● Photographs of the farmer and the farmland.\n" +
                        "● Commodity details for participating in trading platforms like e-NAM.\n"+"\n"+"Financial Assistance and Subsidy:\n" +
                        "1. Subsidies for Market Infrastructure: Grants and subsidies are available for setting up\n" +
                        "market infrastructure, cold storage, and processing facilities under schemes like PMKSY and Operation Greens.\n" +
                        "2. Price Information Systems: Farmers are provided with access to real-time price\n" +
                        "information systems to monitor daily market rates and decide when to sell their produce.\n" +
                        "3. Warehouse Receipt System: Farmers can avail loans against their stored produce\n" +
                        "using the Warehouse Receipt System, reducing the need to sell crops immediately\n" +
                        "after harvest when prices are low.\n"+
                        "● Price information and trading data are regularly updated on platforms like e-NAM to ensure market transparency.",
                "Educational",
                R.drawable.logo));

        schemeList.add(new Scheme("Promotion of Nutri Cereals",
                R.drawable.logo,
                "",
                "Promotion of Nutri Cereals is a significant initiative undertaken by the Government of India to promote the cultivation, consumption, and value addition of nutri-cereals (also known as millets). These cereals are rich in essential nutrients such as protein, fiber, vitamins, and minerals, making them ideal for combating malnutrition, especially in rural areas. The government has introduced several schemes and programs under the broader umbrella of agricultural development to encourage millet production, processing, and market access.",
                "● Who can apply: Farmers involved in agriculture within the state, particularly\n" +
                        "small and marginal farmers, are eligible for this scheme.\n" +
                        "● Categories: Priority is often given to small and marginal farmers, as well as\n" +
                        "women and farmers from disadvantaged communities (SC/ST).",
                "1. Online Application:\n" +
                        "○ Farmers can apply for millet promotion schemes by visiting the National Food\n" +
                        "Security Mission (NFSM) website or the agriculture department’s portal in their\n" +
                        "state.\n" +
                        "○ Fill out the application form with basic details like Aadhaar number, land\n" +
                        "records, and bank details to access benefits like subsidized seeds and inputs.\n" +
                        "\n" +
                        "2. Offline Application:\n" +
                        "○ Farmers can visit their nearest Agriculture Office, Krishi Vigyan Kendra (KVK), or Panchayat Office to obtain application forms and guidance on millet-related schemes.\n" +
                        "○ Submit the filled forms along with necessary documents such as land records and bank account details.",
                "● After applying, the documents are verified by the Agriculture Department or relevant\n" +
                        "authority.\n" +
                        "● Field inspections are carried out to assess eligibility and needs, after which farmers\n" +
                        "receive subsidized inputs or financial assistance for processing and marketing\n" +
                        "infrastructure.",
                "Required Documents:\n" +
                        "● Aadhaar Card (for identity proof).\n" +
                        "● Land ownership documents (patta or land records).\n" +
                        "● Bank account details for receiving subsidies.\n" +
                        "● Seed purchase receipt (if applicable for input subsidies).\n" +
                        "Financial Assistance and Subsidy:\n" +
                        "1. Subsidized Seeds: Farmers can receive subsidized quality seeds for millet cultivation\n" +
                        "under the NFSM-Nutri Cereals program.\n" +
                        "2. Irrigation Support: Assistance for the creation of small irrigation structures like\n" +
                        "check dams, ponds, and drip irrigation systems for nutri-cereal farmers.\n" +
                        "3. Post-Harvest Infrastructure: Subsidies for developing milling, processing, grading,\n" +
                        "and packaging units to improve the value addition of millets.\n" +
                        "4. Processing Units: Financial support for setting up millet-processing units like milling\n" +
                        "machines, de-hulling machines, and flour mills to improve the quality and marketability of millet products.",
                "Educational",
                R.drawable.logo));

        schemeList.add(new Scheme("Jal Se Krishi Ko Bal Yojana",
                R.drawable.logo,
                "https://youtube.com/embed/d4m-S_xii3o",
                "Jal Se Krishi Ko Bal Yojana is an agricultural water management initiative that focuses on improving irrigation facilities for farmers by ensuring the efficient use of water resources. The scheme's objective is to increase agricultural productivity by providing farmers with access to reliable water sources and modern irrigation techniques, reducing dependency on erratic rainfall. By enhancing water availability and promoting sustainable irrigation methods, the scheme ensures that farmers can grow crops year-round, improving their incomes and contributing to rural development.",
                "● Who can apply: All farmers, especially small and marginal farmers, who are in need of irrigation facilities and water conservation systems.\n" +
                        "● Focus Areas: Farmers in water-scarce or drought-prone regions are given priority under the scheme.\n" +
                        "● Landholding Size: The scheme is open to all farmers, but small and marginal farmers are prioritized for receiving subsidies and financial assistance.",
                "1. Online Application:\n" +
                        "○ Visit the official website of the Agriculture Department of the respective state or the dedicated portal for the Jal Se Krishi Ko Bal Yojana.\n" +
                        "○ Register as a farmer by providing your personal details, landholding information, and water requirements.\n" +
                        "○ Fill out the online application form for the specific irrigation facilities or subsidies you wish to apply for (e.g., drip irrigation, sprinklers, farm ponds).\n" +
                        "○ Upload the required documents, such as identity proof, land records,and water availability certificates.\n" +
                        "○ Submit the application and save the acknowledgment for future reference.\n" +
                        "\n"+"2. Offline Application:\n" +
                        "○ Visit the nearest Agriculture Office, Irrigation Department, or Krishi Vigyan Kendra (KVK) to collect the application form.\n" +
                        "○ Fill in the form with details about your land, water source, and the type of irrigation facility you require.\n" +
                        "○ Attach necessary documents like identity proof (Aadhaar card), land ownership documents, and water source details (if applicable).\n" +
                        "○ Submit the form andRequired Documents:\n" +
                        "● Proof of identity (Aadhaar card, voter ID, or PAN card).\n" +
                        "● Proof of land ownership (land records or patta).\n" +
                        "● Water source details (well, borewell, canal, etc.).\n" +
                        "● Bank account details (for receiving subsidies).\n" +
                        "\n" +
                        "● Photographs of the applicant and the farmland.\n" +
                        "Financial Support and Subsidy:\n" +
                        "● The scheme provides financial subsidies to farmers for installing irrigation\n" +
                        "systems like drip irrigation, sprinklers, and water pumps. The subsidy\n" +
                        "typically covers 50% to 90% of the total cost, depending on the region, type of\n" +
                        "farmer, and the equipment being installed.\n" +
                        "● Additional financial assistance is provided for constructing farm ponds, check dams, and rainwater harvesting structures.\n" +
                        "Solar Pumps: Farmers receive subsidies to install solar-powered water pumps, reducing dependence on electricity for operating irrigation systems. collect the acknowledgment receipt for tracking the status of your application.",
                "● After the application is submitted, officials from the Agriculture or Irrigation Department will verify the documents and assess the feasibility of the proposed irrigation system or water conservation project.\n" +
                        "● Upon verification, the application will be approved, and the financial assistance or subsidy will be disbursed to the farmer’s bank account.\n" +
                        "● The construction of irrigation systems and water conservation structures is carried out with technical assistance from the government, ensuring proper implementation.",
                "Additional financial assistance is provided for constructing farm ponds",
                "Financial",
                R.drawable.logo));

        schemeList.add(new Scheme("HP Crop Diversification Promotion Project",
                R.drawable.logo,
                "",
                "The HP Crop Diversification Promotion Project (JICA-EAP) is a collaborative project between the Government of Himachal Pradesh and the Japan International Cooperation Agency (JICA) under the Externally Aided Projects (EAP) initiative. The project aims to diversify cropping patterns by promoting the cultivation of high-value cash crops like fruits,vegetables, and spices, which offer better returns compared to traditional crops. This initiative focuses on improving farmers' incomes, enhancing agricultural productivity, and ensuring sustainable farming practices in Himachal Pradesh. The project also aims to improve irrigation facilities, market access, and post-harvest management for farmers.",
                "● Who can apply: Farmers from Himachal Pradesh, particularly those in the project’s\n" +
                        "target districts, are eligible to participate in the project.\n" +
                        "● Focus Areas: Small and marginal farmers, women farmers, and those in remote and\n" +
                        "hilly regions are prioritized.\n"+"Target Districts: The project is mainly implemented in the lower and mid-hill regions of Himachal Pradesh, covering districts such as Kangra, Mandi, Hamirpur, Una, Bilaspur, and Solan. These regions have high potential for crop diversification due to their climatic conditions.",
                "1. Online Application:\n" +
                        "○ Farmers can visit the Agriculture Department's official website of Himachal Pradesh or the project-specific portal to register for the scheme.\n" +
                        "○ Fill in the online application form with necessary details such as personal information, landholding details, and the type of assistance required (e.g., crop diversification, irrigation facilities).\n" +
                        "○ Submit relevant documents such as identity proof, land records, and bank details,\n" +
                        "if needed.\n" +
                        "\n"+
                        "2. Offline Application:\n" +
                        "○ Visit the nearest Agriculture Office, Krishi Vigyan Kendra (KVK), or Panchayat Office to obtain the application form.\n" +
                        "○ Fill out the form providing details of your land, crops, and the type of support required under the project.\n" +
                        "○ Submit the form along with necessary documents (e.g., land ownership proof, Aadhaar card) at the office.",
                "● After submitting the application, the Agriculture Department officials will review the documents and conduct a field inspection to assess the feasibility of the project for the applicant.\n" +
                        "● Once the verification is completed, farmers will receive the necessary inputs, irrigation infrastructure, or financial assistance to begin the diversification process.",
                "Required Documents:\n" +
                        "\n" +
                        "● Proof of Identity (Aadhaar card, voter ID, or PAN card).\n" +
                        "● Proof of Land Ownership (land records or patta).\n" +
                        "● Bank Account Details for receiving subsidies or financial assistance.\n" +
                        "● Photographs of the farmer and the farmland.Financial Support and Subsidy:\n" +
                        "● Irrigation Systems: Subsidies are provided for installing drip irrigation, sprinkler systems, and constructing farm ponds or check dams to enhance water availability.\n" +
                        "● High-Value Crop Inputs: Farmers receive financial assistance to procure high-quality seeds, organic fertilizers, and other inputs for growing high-value crops.\n" +
                        "● Farm Machinery: Subsidized farm machinery and tools are provided to reduce labor costs and increase farm productivity.\n" +
                        "● Post-Harvest Facilities: Financial support is offered for setting up cold storage,grading, sorting, and packaging units to improve the marketability of the produce.\n"+"Areas of Assistance:\n"+
                        "● Field Inspections: Agriculture Department officials will conduct regular field inspections to monitor the progress of the crop diversification and irrigation projects.\n" +
                        "● Post-Implementation Support: Farmers receive ongoing technical support and guidance to ensure the successful implementation of new farming practices and technologies.\n" +
                        "● Market Linkages: The project facilitates market linkages through Farmer Producer Organizations (FPOs) to ensure that farmers can directly sell their produce to buyers, eliminating middlemen and increasing profits.",
                "Educational",
                R.drawable.logo));

        schemeList.add(new Scheme("Mukhyamantri Kisan Evam Khetihar Mazdoor Jeevan Suraksha Yojana",
                R.drawable.logo,
                "https://youtube.com/embed/0Rn0fn2cZeQ",
                "The Mukhyamantri Kisan Evam Khetihar Mazdoor Jeevan Suraksha Yojana is a welfare initiative launched by various state governments to provide life insurance and accidental coverage to farmers and agricultural laborers. This scheme is designed to ensure the social security of farmers and farm workers by offering financial compensation in the event of death or disability due to accidents, thus protecting the livelihoods of farming families.",
                "● Who can apply: Farmers and agricultural laborers involved in agricultural activities and registered with the state agricultural department or related authorities are eligible.\n" +
                        "● Age Criteria: Generally, beneficiaries between the ages of 18 and 70 years are eligible for coverage under the scheme.\n" +
                        "● Income Group: The scheme is open to all categories of farmers, including small and marginal farmers, as well as landless laborers working in the agricultural sector.",
                "1. Online Application:\n" +
                        "○ Visit the official Agriculture Department or Social Welfare Department website of the respective state.\n" +
                        "○ Look for the section dedicated to welfare schemes or insurance programs and select \"Mukhyamantri Kisan Evam Khetihar Mazdoor Jeevan Suraksha Yojana.\"\n" +
                        "○ Fill in the application form with your personal details, landholding information, or employment as an agricultural laborer.\n" +
                        "○ Upload necessary documents such as identity proof, proof of age, and proof of agricultural activity.\n" +
                        "○ Submit the form and save the acknowledgment number for future reference.\n" +
                        "\n"+"2. Offline Application:\n" +
                        "○ Farmers or agricultural laborers can visit the nearest Agriculture Department Office, Revenue Department Office, or Panchayat Office in their area.\n" +
                        "○ Collect the application form for the Mukhyamantri Kisan Evam Khetihar Mazdoor Jeevan Suraksha Yojana.\n" +
                        "○ Fill out the form with details such as the beneficiary's name, age,occupation, and landholding status or work details.\n" +
                        "○ Submit the form along with the required documents to the concerned office.\n" +
                        "○ Upon submission, an acknowledgment receipt will be provided for future reference.",
                "Claim Process:\n" +
                        "1. Reporting the Incident: In the event of an accidental death or disability, the family members or legal heirs of the beneficiary must report the incident to the nearest Agriculture Office, Panchayat Office, or Revenue Office within a stipulated time.\n" +
                        "2. Submitting the Claim: The family must submit a claim form along with the necessary documents such as the death certificate, FIR (in case of accidental death), medical reports (for disability), and other required documents.\n" +
                        "3. Verification and Approval: Once the claim is submitted, officials will verify the details of the incident, and if the claim is found valid, the compensation amount will be disbursed to the beneficiary’s family.\n" +
                        "4. Disbursement of Compensation: The compensation amount is directly transferred to the beneficiary’s bank account or to the family’s account in case of death.",
                "Required Documents:\n" +
                        "● Proof of identity (Aadhaar card, voter ID, or PAN card).\n" +
                        "● Proof of age (birth certificate, Aadhaar card, or any other age certificate).\n" +
                        "● Proof of being a farmer or agricultural laborer (land records, agricultural labor card, or employer certification).\n" +
                        "● Bank account details of the beneficiary for the disbursement of benefits.\n" +
                        "● Passport-size photographs.\n" +
                        "\n"+
                        "Insurance Coverage:\n" +
                        "● Accidental Death: Coverage is provided in the event of death due to an accident while engaged in farming or related activities.\n" +
                        "● Permanent Total Disability: Full disability, where the beneficiary is unable to continue farming or labor work, will result in full compensation.\n" +
                        "● Partial Disability: For partial disability where the beneficiary can still work in a limited capacity, a portion of the full compensation is provided. Premium Payment:\n" +
                        "● In many cases, the state government bears the premium cost, ensuring that farmers and agricultural laborers can access the benefits of the scheme without any financial burden. In other cases, the premium may be nominal and highly subsidized.",
                "Financial",
                R.drawable.logo));

        schemeList.add(new Scheme("Mukhyamantri Krishi Sanvardhan Yojana",
                R.drawable.logo,
                "https://youtube.com/embed/S9dTGQbwMlI",
                "Mukhyamantri Krishi Sanvardhan Yojana (MKSY) is a state-level agricultural initiative aimed at promoting sustainable farming practices, enhancing agricultural productivity, and improving farmers' income. The scheme provides financial and technical support to farmers for adopting modern farming techniques, enhancing irrigation systems, and encouraging the use of high-quality inputs. It focuses on increasing crop production through effective utilization of resources and supporting small and marginal farmers by providing subsidies for seeds, fertilizers, equipment, and other farming necessities.",
                "● Who can apply: All farmers, including small and marginal farmers, are eligible for the scheme. Special emphasis is placed on supporting small landholders.\n" +
                        "● Landholding Size: Small and marginal farmers with limited landholdings are prioritized for receiving subsidies and assistance.\n" +
                        "● Focus Areas: Farmers engaged in traditional agriculture, horticulture,organic farming, and crop diversification are encouraged to apply.",
                "1. Online Application:\n" +
                        "\n" +
                        "○ Visit the official website of the Agriculture Department of the respective state government or the dedicated portal for Mukhyamantri Krishi Sanvardhan Yojana.\n" +
                        "○ Register as a farmer by filling in personal and landholding details.\n" +
                        "○ Fill out the application form specifying the type of assistance required (e.g., subsidies for seeds, irrigation systems, or machinery).\n" +
                        "○ Upload necessary documents, such as land ownership proof, identity documents, and bank account details.\n" +
                        "○ Submit the form and keep the acknowledgment number for future reference.\n" +
                        "2. Offline Application:\n" +
                        "○ Visit the nearest Agriculture Office, Krishi Vigyan Kendra (KVK), or Panchayat Office to collect the application form.\n" +
                        "○ Fill in details about your land, crops, and the type of assistance you need under the scheme.\n" +
                        "○ Submit the form along with the necessary documents, such as land records, identity proof (Aadhaar card), and bank account details.\n" +
                        "○ After submission, a receipt will be provided for tracking the application status.",
                "● After the application is submitted, the Agriculture Department officials will verify the documents and assess the eligibility of the applicant.\n" +
                        "● Upon successful verification, the approved farmers will receive the required inputs, equipment, or financial assistance.\n" +
                        "● Training programs may be organized to ensure that farmers use the provided resources effectively.",
                "Required Documents:\n" +
                        "● Identity proof (Aadhaar card, voter ID, or PAN card).\n" +
                        "● Proof of land ownership (land records or patta).\n" +
                        "● Bank account details for receiving subsidies.\n" +
                        "● Photographs of the farmer and the farmland.\n" +
                        "● Crop details for which financial assistance is being sought.Financial Support and Subsidy:\n" +
                        "● Subsidy on Seeds and Fertilizers: Farmers receive subsidies ranging from 40% to 80% on high-quality seeds, organic fertilizers, and other essential inputs.\n" +
                        "● Machinery Subsidy: The scheme offers a subsidy of up to 50-75% on farm machinery like tractors, tillers, and seed drills.\n" +
                        "● Irrigation Subsidy: Financial assistance of up to 70-90% is provided for installing irrigation systems like drip irrigation and sprinklers.\n" +
                        "● Organic Farming Support: Special grants and subsidies are available for transitioning to organic farming, covering organic inputs and certification costs.\n"+
                        "\n" +
                        "Areas of Assistance:\n" +
                        "1. Seed and Fertilizer Support: The scheme offers subsidies on high-quality seeds, organic fertilizers, and bio-pesticides to improve crop production.\n" +
                        "2. Farm Machinery: Financial support is provided for purchasing modern\n" +
                        "agricultural machinery like tractors, harvesters, and tillers to reduce manual\n" +
                        "labor.\n" +
                        "3. Irrigation Facilities: Assistance is provided for setting up drip and sprinkler\n" +
                        "irrigation systems and constructing farm ponds for water conservation.\n" +
                        "4. Organic Farming: Subsidies are available for organic inputs and certification,\n" +
                        "encouraging farmers to shift to sustainable farming practices.\n" +
                        "5. Post-Harvest Management:\n" +
                        "6. Financial Assistance for Crop Diversification",
                "Financial",
                R.drawable.logo));

        schemeList.add(new Scheme("Krishi Se Sampanna Yojana",
                R.drawable.logo,
                "https://youtube.com/embed/6TGqeTlCD8A",
                "The Krishi Se Sampanna Yojana (KSY) is an agricultural initiative aimed at improving the economic conditions of farmers by enhancing agricultural productivity, promoting sustainable farming practices, and increasing farmers' incomes. The scheme focuses on providing financial assistance, modern equipment, and technical support to farmers, especially small and marginal farmers. The objective is to empower farmers with better resources and knowledge, leading to improved yields, diversified farming activities, and ultimately a better livelihood.",
                "● Who can apply: All farmers, including small, marginal, and landless farmers, as well as farmer cooperatives and Farmer Producer Organizations (FPOs).\n" +
                        "● Income Group: The scheme is targeted primarily at small and marginal farmers, but all farmers are eligible to apply.\n" +
                        "● Landholding Size: Farmers with small landholdings are prioritized, but the scheme is open to all categories of farmers regardless of landholding size.",
                "1. Online Application:\n" +
                        "○ Visit the official website of the Agriculture Department of your respective state or the Krishi Se Sampanna Yojana section of the National Agriculture Portal.\n" +
                        "○ Register by filling in your personal details and landholding information.\n" +
                        "○ Fill out the application form specifying the kind of assistance you are seeking, such as machinery, inputs, or financial support.\n" +
                        "○ Upload necessary documents like proof of land ownership, identity documents, and any other relevant certificates.\n" +
                        "○ Submit the form and save the acknowledgment for future reference.\n" +
                        "2. Offline Application:\n" +
                        "○ Visit the nearest Agriculture Office, Krishi Vigyan Kendra (KVK), or Panchayat Office to collect the application form.\n" +
                        "○ Complete the form with details about your land, crops, and the type of assistance you need.\n" +
                        "○ Submit the form along with the required documents, such as land records, Aadhaar card, and bank account details.\n" +
                        "○ After submission, you will receive a receipt, and the officials will process your application.",
                "● Upon receiving the application, officials from the Agriculture Department will verify the documents and assess the applicant’s eligibility.\n" +
                        "● After verification, the approved beneficiaries will receive the required inputs, equipment, or financial assistance.\n" +
                        "● Farmers are encouraged to participate in training programs organized under the scheme to ensure they are well-equipped to use the provided resources effectively.",
                "Required Documents:\n" +
                        "● Identity proof (Aadhaar card, voter ID, or PAN card).\n" +
                        "● Proof of land ownership (land records or patta).\n" +
                        "● Bank account details (for receiving subsidies or financial assistance).\n" +
                        "● Crop details and the type of assistance you are seeking.\n" +
                        "● Passport-size photographs.\n" +
                        "\n" +
                        "Financial Support and Subsidy:\n" +
                        "● The scheme offers financial subsidies on various agricultural inputs and machinery. Subsidy rates may vary depending on the type of input or equipment being provided, typically ranging from 40% to 80%.\n" +
                        "● Special grants are available for organic farming and other sustainable agricultural practices.\n" +
                        "● Credit facilities are made available at lower interest rates to ensure that farmers can access the necessary funds to improve their agricultural activities.Areas of Assistance:\n" +
                        "1. Seed and Fertilizer Support: The scheme offers subsidies on high-quality seeds and fertilizers to improve crop production.\n" +
                        "2. Machinery and Equipment: Modern agricultural machinery is made available at subsidized rates to reduce manual labor and increase efficiency.\n" +
                        "3. Financial and Credit Assistance: Farmers can access credit at subsidized\n" +
                        "interest rates through government-backed schemes such as the Kisan Credit\n" +
                        "Card (KCC).",
                "Financial",
                R.drawable.logo));

        schemeList.add(new Scheme("Mukhyamantri Krishi Utpadan Sanrakshan Yojana",
                R.drawable.logo,
                "https://youtube.com/embed/_6viRHCjl9Y",
                "The Mukhyamantri Krishi Utpadan Sanrakshan Yojana (Badhbandi) is a scheme initiated by the Government of Himachal Pradesh to protect agricultural produce from wild animals and stray cattle by constructing protective fencing around farmland. The scheme, also known as the Badhbandi Yojana, aims to reduce crop damage caused by animals like monkeys, wild boars, and stray cattle, which is a significant issue for farmers in many regions of the state. By providing financial assistance for constructing fencing, the scheme ensures increased agricultural productivity, income security, and protection of crops.",
                "● Who can apply: All farmers in Himachal Pradesh, including small and marginal farmers, as well as farmer cooperatives, are eligible to apply for the scheme.\n" +
                        "● Income Group: Priority is given to small and marginal farmers, but all categories of farmers can apply for the scheme.\n" +
                        "● Farmland Size: Farmers with both large and small landholdings are eligible for financial assistance under the scheme.",
                "1. Online Application:\n" +
                        "○ Visit the official website of the Himachal Pradesh Agriculture Department or the relevant district agriculture portal.\n" +
                        "○ Register as a farmer on the portal and navigate to the \"Mukhyamantri Krishi Utpadan Sanrakshan Yojana\" section.\n" +
                        "○ Fill out the online application form with personal details, landholding information, and the type of fencing you wish to install.\n" +
                        "○ Upload necessary documents like identity proof, land records, and estimates for the cost of fencing.\n" +
                        "○ Submit the application form and keep the acknowledgment number for future reference.\n" +
                        "\n"+"2. Offline Application:\n" +
                        "○ Visit the nearest Agriculture Department Office, Panchayat Office, or Krishi Vigyan Kendra (KVK) to collect the application form.\n" +
                        "○ Fill in the form with details about your farmland, the fencing method,and the size of the area to be protected.\n" +
                        "○ Submit the form along with the necessary documents (identity proof,land records, and cost estimates).\n" +
                        "○ After submission, you will receive an acknowledgment receipt, which you can use to follow up on your application status.",
                "● Once the application is submitted, officials from the Agriculture Department will verify the documents and assess the feasibility of constructing the proposed fencing around the farmland.\n" +
                        "● Upon successful verification, the application is approved, and financial support is sanctioned for the construction of the protective fencing.\n" +
                        "● The subsidy amount is disbursed to the beneficiary’s bank account once the construction of fencing is completed and inspected by the concerned authorities.",
                "Required Documents:\n" +
                        "● Proof of identity (Aadhaar card, voter ID, or PAN card).\n" +
                        "● Proof of land ownership (land records, patta, or other legal documents showing ownership of the land).\n" +
                        "● Bank account details for receiving the subsidy amount.\n" +
                        "● Fencing cost estimate from a certified contractor or agency (if required by the scheme guidelines).\n" +
                        "● Passport-size photographs.\n" +
                        "\n"+
                        "Financial Support and Subsidy:\n" +
                        "● The scheme provides financial assistance in the form of subsidies, covering between 60% to 80% of the total fencing cost, depending on the region and type of applicant (small, marginal farmers, or farmer groups).\n" +
                        "● The remaining cost is borne by the farmer or farmer group.\n"+
                        "\n"+
                        "Types of Fencing Approved:\n" +
                        "● Wire Fencing: Barbed wire or chain-link fencing to deter animals from entering the fields.\n" +
                        "● Wall Fencing: Construction of a physical wall, particularly in areas where wild animals frequently damage crops.\n" +
                        "● Solar Fencing: Solar-powered electric fences in areas where traditional fencing methods may not be as effective. This type of fencing deters animals without harming them and is eco-friendly.\n" +
                        "● Trench Fencing: Digging trenches around the fields in regions with large animal populations like wild boars.",
                "Financial",
                R.drawable.logo));

        schemeList.add(new Scheme("Tea and Coffee Cultivation Scheme",
                R.drawable.logo,
                "https://youtube.com/embed/_gSbFDc8nvo",
                "The Tea and Coffee Cultivation Scheme is an initiative launched by the Government of India to promote the cultivation, production, and value addition of tea and coffee, especially in regions suitable for their growth such as the northeastern states, southern states, and hilly areas. These crops are major contributors to India’s economy and provide employment to millions, particularly in rural and tribal regions.",
                "● Who can apply: Farmers involved in agriculture within the state, particularly small and marginal farmers, are eligible for this scheme.\n" +
                        "● Categories: Priority is often given to small and marginal farmers, as well as women and farmers from disadvantaged communities (SC/ST).",
                "1. Online Application:\n" +
                        "○ Farmers can apply for the Tea Development and Promotion Scheme or ICDP via the Tea Board and Coffee Board websites, respectively.\n" +
                        "○ Applications for organic farming promotion schemes like PKVY can be submitted on the respective state agriculture department’s website.\n" +
                        "\n" +
                        "2. Offline Application:\n" +
                        "○ Farmers can visit the nearest Tea Board or Coffee Board office or Krishi Vigyan Kendra (KVK) to get assistance with applying for schemes.\n" +
                        "○ Forms are available at these offices, where farmers can fill in their details and submit required documents.",
                "● After submitting the application (either online or offline), the documents will be verified by the respective authority (Tea Board, Coffee Board, or state agricultural department).\n" +
                        "● Field inspections may be conducted to ensure eligibility and compliance.\n" +
                        "● Eligible farmers receive financial assistance in installments based on the progress of plantation or infrastructure development.",
                "Required Documents:\n" +
                        "● Aadhaar Card (for identity proof).\n" +
                        "● Land ownership documents (patta or land records).\n" +
                        "● Bank account details for receiving subsidies.\n" +
                        "● Photographs of the farmer and land.\n" +
                        "● Receipts of input purchases (if applicable).\n" +"\n"+
                        "Financial Assistance and Subsidy:\n" +
                        "1. Replanting and Rejuvenation Subsidy:\n" +
                        "○ Financial assistance is provided for replanting and rejuvenation of old tea and coffee plantations, typically covering 40-50% of the cost.\n" +
                        "2. New Plantations:\n" +
                        "○ For new plantations, farmers receive subsidies for land preparation, planting material, and inputs like fertilizers and plant protection chemicals.\n" +
                        "3. Organic Farming Subsidy:\n" +
                        "○ Subsidies up to 50% of the cost for organic inputs, certification, and infrastructure development for organic farming.\n" +
                        "4. Processing and Infrastructure Support:\n" +
                        "○ Assistance for setting up small tea factories, processing units, and value-addition infrastructure such as packaging units and storage facilities.",
                "Financial",
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
        intent.putExtra("selection_process", scheme.getSelectionProcess());
        intent.putExtra("additional_info", scheme.getAdditionalInfo());
        intent.putExtra("scheme_logo", scheme.getLogoResource()); // Pass logo resource
        startActivity(intent);
    }
}
