# Predictive Big Data Analytics Application: NYC Vehicle Collision Analysis

## Overview
The **PBDAA (Predictive Big Data Analytics Application)** project investigates vehicle collisions in New York City based on the point of impact and different vehicle brands. The study aims to determine which car brand is the most successful at reducing injuries in collisions by combining insights from NYC car crash reports with vehicle safety ratings from the National Highway Traffic Safety Administration (NHTSA). The project utilizes **Hadoop MapReduce, HiveQL, and web scraping** to analyze and compare datasets.

## Key Features
- **Big Data Processing with Hadoop**: Utilizes **MapReduce** for data cleaning, transformation, and aggregation of large NYC vehicle collision datasets.
- **SQL-Based Analysis with Hive**: Uses **HiveQL** to join datasets, extract insights, and perform complex queries on vehicle crash data.
- **Web Scraping for Safety Ratings**: Automates data collection from **NHTSA's vehicle safety ratings** using Selenium in Python.
- **Comparative Analysis**: Compares real-world injury data with official safety ratings to assess brand performance in reducing injuries.
- **Data Visualization**: Creates visual insights using **Tableau and Excel** to interpret trends in vehicle collision safety.

## Installation & Setup
### Prerequisites
- **Hadoop & HDFS** (for MapReduce jobs)
- **Apache Hive** (for SQL queries on structured data)
- **Python** (for web scraping and preprocessing)
- **Java** (for implementing MapReduce)
- **Selenium** (for extracting NHTSA safety ratings)
- **Tableau** (for data visualization, optional)

### Steps
1. **Set Up Hadoop & Hive**
   - Install and configure **Hadoop** (including HDFS, YARN, and MapReduce).
   - Install and configure **Apache Hive**.
   - Upload cleaned datasets to HDFS.

2. **Run MapReduce Jobs**
   - Compile and run Java MapReduce programs:
     ```sh
     hadoop jar your-jar-file.jar Clean input_path output_path
     hadoop jar your-jar-file.jar RegiCount input_path output_path
     ```

3. **Import Data into Hive**

4. **Run SQL Queries in Hive**

5. **Scrape NHTSA Safety Ratings**
   - Run the Python script to scrape safety ratings:
     ```sh
     python car_brand_ratings_nhtsa.py
     ```

6. **Compare Results & Visualize Data**
   - Load aggregated results into Tableau for visualization.

## Project Structure
```
PBDAA/
|-- data/
|   |-- raw/  
|   |-- processed/  
|
|-- scripts/
|   |-- car_brand_ratings_nhtsa.py  # Web scraper for NHTSA ratings
|
|-- src/
|   |-- Clean.java  # MapReduce job for cleaning data
|   |-- CleanMapper.java  
|   |-- CleanReducer.java  
|   |-- RegiCount.java  # Registration count MapReduce job
|   |-- RegiCountMapper.java  
|   |-- RegiCountReducer.java  
|   |-- CountRecs.java  # Record counting MapReduce job
|   |-- CountRecsMapper.java  
|   |-- CountRecsReducer.java  
|
|
|-- README.md  # Project documentation
```

## Results & Findings
- **Most Common Points of Impact:**
  - Center front and center back were the most frequent points of impact in NYC collisions.
- **Best Car Brand for Injury Prevention:**
  - Volvo had the lowest injury rate among all brands in real NYC crash data.
  - Volvo also had the highest safety ratings from NHTSA, confirming consistency between real-world data and crash test results.
- **Data Consistency & Validation:**
  - The results from the real-world dataset aligned with the safety ratings, supporting the reliability of NHTSA rankings.

## Future Improvements
- **Granular Model-Level Analysis:** Investigate **injury rates per specific car models** instead of overall brand averages.
- **Extended Data Collection:** Include additional years of NYC collision data to observe **trends over time**.
- **Machine Learning Integration:** Develop a predictive model to estimate **injury risk based on car make, model, and collision conditions**.
- **Cloud Processing:** Deploy Hadoop processing on **AWS EMR or Google Cloud Dataproc** for better scalability.

## Technologies Used
- **Big Data Processing:** Hadoop, MapReduce, HiveQL
- **Programming Languages:** Java, Python
- **Web Scraping:** Selenium
- **Data Visualization:** Tableau, Excel
- **Databases:** Apache Hive, HDFS

## References
- [NYC Open Data - Motor Vehicle Collisions](https://data.cityofnewyork.us/Public-Safety/Motor-Vehicle-Collisions-Vehicles/bm4k-52h4)
- [NHTSA Vehicle Safety Ratings](https://www.nhtsa.gov/ratings)
- [Hadoop & MapReduce](https://hadoop.apache.org/)
- [Apache Hive](https://hive.apache.org/)

## License
This project is open-source and available under the MIT License.

