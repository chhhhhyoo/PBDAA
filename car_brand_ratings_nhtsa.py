import os
import pandas as pd
import requests
from selenium import webdriver
import time

brand_list = ['Hyundai', 'Kia', 'Volvo', 'Volkswagen', 'Toyota', 'Ford', 'Mercedes-Benz', 'BMW', 'Audi', 'Acura']
len_blist = len(brand_list)
driver = webdriver.Chrome('chromedriver.exe')
driver.get("https://www.nhtsa.gov/ratings")
time.sleep(3)
elem_login = driver.find_element_by_id('ratings-search-input')
dfs = {}
for brand in brand_list:
    elem_login.clear()
    elem_login.send_keys(brand)
    xpath = '''//*[@id="vehicle"]/div[2]/div[2]/div[1]/form/div/div/div/button'''
    driver.find_element_by_xpath(xpath).click()
    time.sleep(5)
    a= 1
    # create an empty list to store the extracted data
    data = []

    while a==1:
        table = driver.find_element_by_class_name('table-responsive').find_element_by_tag_name('table')
        thead = table.find_element_by_tag_name('thead')
        header_row = [th.text.strip() for th in thead.find_elements_by_tag_name('th')][:5]

        # loop over all tbody elements
        for tbody in table.find_elements_by_tag_name('tbody'):
            # loop over each row in the tbody
            for row in tbody.find_elements_by_tag_name('tr'):
                # extract the text content and rating values for each column
                cols = row.find_elements_by_tag_name('td')
                row_values = [cols[0].text]
                if(cols[1].text == 'Not Rated'):
                    a=0
                else:
                    for col in cols[1:5]:
                        rating_img = col.find_element_by_tag_name('img')
                        rating_value = rating_img.get_attribute('alt').split()[0]
                        row_values.append(rating_value)

                    # append the row data to the list
                    data.append(row_values)


        # check if there is a next page
        next_button = driver.find_elements_by_xpath('//button[contains(.,"next")]')
        if len(next_button) == 0:
            break

        # navigate to the next page
        next_button[0].click()
        time.sleep(5)

    # create a pandas DataFrame from the extracted data
    df = pd.DataFrame(data, columns=header_row)
    dfs[brand] = df

driver.quit()