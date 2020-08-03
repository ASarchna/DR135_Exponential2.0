from selenium import webdriver
import os
import time
from PIL import Image
import requests
import io
import hashlib


"""
import pyrebase

config = {
    "apiKey": "AIzaSyDHeSY5fj3aiEcrG7TLVwOqFyO1mO3e4ck",
    "authDomain": "goasih.firebaseapp.com",
    "databaseURL": "https://goasih.firebaseio.com",
    "projectId": "goasih",
    "storageBucket": "goasih.appspot.com",
    "messagingSenderId": "329505041953",
    "appId": "1:329505041953:web:021e674be75ea7c9acfd60",
    "measurementId": "G-0ZK50KZJ6V"

}

firebase = pyrebase.initialize_app(config)

storage = firebase.storage()

"""

DRIVER_PATH = "C:\\Users\\shikh\\Desktop\\Scraping\\chromedriver"


def fetch_image_urls(query:str, max_links_to_fetch:int, wd:webdriver, sleep_between_interactions:int=1):
    def scroll_to_end(wd):
        wd.execute_script("window.scrollTo(0, document.body.scrollHeight);")
        time.sleep(sleep_between_interactions)    
    
    # build the google query
    #https://www.google.com/search?q=aguada+fort&sxsrf=ACYBGNSqL8KltCIVhF6CKBjDIRsvFfYSzw:1578630095174&source=lnms&tbm=isch&sa=X&ved=2ahUKEwi-zbewl_jmAhWdxzgGHdmzDtkQ_AUoAXoECBYQAw&biw=1366&bih=664
    search_url = "https://www.google.com/search?safe=off&site=&tbm=isch&source=hp&q={q}&oq={q}&gs_l=img"



    # load the page
    wd.get(search_url.format(q=query))

    image_urls = set()
    image_count = 0
    results_start = 0
    while image_count < max_links_to_fetch:
        scroll_to_end(wd)

        # get all image thumbnail results
        thumbnail_results = wd.find_elements_by_css_selector("img.rg_ic")
        number_results = len(thumbnail_results)
        
        print(f"Found: {number_results} search results. Extracting links from {results_start}:{number_results}")
        
        for img in thumbnail_results[results_start:number_results]:
            # try to click every thumbnail such that we can get the real image behind it
            try:
                img.click()
                time.sleep(sleep_between_interactions)
            except Exception:
                continue

            # extract image urls    
            actual_images = wd.find_elements_by_css_selector('img.irc_mi')
            for actual_image in actual_images:
                if actual_image.get_attribute('src'):
                    image_urls.add(actual_image.get_attribute('src'))

            image_count = len(image_urls)

            if len(image_urls) >= max_links_to_fetch:
                print(f"Found: {len(image_urls)} image links, done!")
                break
        else:
            print("Found:", len(image_urls), "image links, looking for more ...")
            time.sleep(1)
            load_more_button = wd.find_element_by_css_selector(".ksb")
            if load_more_button:
                wd.execute_script("document.querySelector('.ksb').click();")

        # move the result startpoint further down
        results_start = len(thumbnail_results)

    return image_urls






def persist_image(folder_path:str,url:str):
    try:
        image_content = requests.get(url).content

    except Exception as e:
        print(f"ERROR - Could not download {url} - {e}")

    try:
        image_file = io.BytesIO(image_content)
        image = Image.open(image_file).convert('RGB')
        file_path = os.path.join(folder_path,hashlib.sha1(image_content).hexdigest()[:10] + '.jpg')
        with open(file_path, 'wb') as f:
            image.save(f, "JPEG", quality=85)
        print(f"SUCCESS - saved {url} - as {file_path}")
    except Exception as e:
        print(f"ERROR - Could not save {url} - {e}")




def search_and_download(search_term:str,driver_path:str,target_path='./images',number_images=10):
    target_folder = os.path.join(target_path,'_'.join(search_term.lower().split(' ')))

    if not os.path.exists(target_folder):
        os.makedirs(target_folder)

    with webdriver.Chrome(executable_path=driver_path) as wd:
        res = fetch_image_urls(search_term, number_images, wd=wd, sleep_between_interactions=0.5)
        
    for elem in res:
        persist_image(target_folder,elem)


search_term1 = "beaches"

search_and_download(search_term = search_term1, driver_path=DRIVER_PATH)


#storage.child("aguada_fort_goa").put("C:\\Users\\shikh\\Desktop\\scraping\\images\\aguada_fort_goa")

