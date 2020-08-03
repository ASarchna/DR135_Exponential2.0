# DR135_Exponential2.0
SIH 2020 Project

Team Members:
- Archana Bharti
- Aman Raj Sundram
- Shikhar Srivastva
- Ravikant Singh
- Atul Kumar
- Priyanshu Nayan


# REST APIs

The `sih-backend` folder contains the code of all the REST APIs used in the project. The stack used is Node.js as a runtime, mongodb and firebase for databases. The APIs are hosted on heroku free tier.

## Flights
This API is used to get the best flights from the source airport to GOA. 

### Usage

GET https://sih-backend.herokuapp.com/flights?flyFrom=DEL&flyTo=GOI&dateFrom=12/8/2020&dateTo=12/8/2020

RESPONSE:
```json
{
    "data": [
        {
            "price": 5200.14,
            "stoppage": 0,
            "airlines": [
                "UK"
            ],
            "images": "https://images.kiwi.com/airlines/32x32/UK.png",
            "departure_time": 1597210200,
            "arrival_time": 1597219200,
            "deep_link": "https://www.kiwi.com/deep?from=DEL&to=GOI&departure=12-08-2020&flightsId=0711207c483600006a9cdba8_0&price=66&passengers=1&affilid=picky&lang=en&currency=EUR&booking_token=AgSMhov4kS60TUqvMnmbqZWYeQB-W7AVqpco5dLP6JI7_MIRlbDxeupVrDDwFAEsfyxpDZLe9K-JD7oo79Rxq-cAa5AUH34CErmOdbEpMCSVX1w83ZVIZLvbFABsa128CYhMzpTqU2oGr9djlPIg9aZiIdHmVV72r6KjWJYF7K_Y1iNHu2N1R9muBAWKOqgT8Bf2OGa4qJU98K1JpfuBCuG3C2E6nOFr4fyE-eSWiGcSbxEqDT6pv_VBMeXYGKW7-2Lrk3RS9D4Yq-Aq4906EbtbfMhkLP9sa5BaWxfvXPAciCi_Ys-AXRwpwXKZDWSRnqSwUMeZLhpyGLAvmDNslM145HZgspuh5C6sAvpHItN3-afnZ0FlpTe13vJYLpPTM0FjPFxknOeZ-wVk9Osqclu98G33BvlA6O9UrVaNxzdXk7ILyDtTCxJ6NiU6XH-HYqDXD5ocd-2VnUbO6nuQBoNsap0l6eLtOtxgVfftahFW7ZJ8OfiV9Av6b7lPEzDuqXwtkjHGyvSElqoKIlk8fXevBXWeGnM2WarBs3y1JHkNQ4vaC43Y5NdCAMVhFkR_zO0YnDBAYFBVBXCir44auf2BBQ0M1JHrSgiNyuG_AhGyUeAy188NhlFE0AEV6WOtwsa8TNdrHL6EOGjbpVn_40rl1QsdxblbSU0YZWzuLSoo7F6fRrp46u2GC5kTK-6vw",
            "fly_duration": "2h 30m",
            "origin": "DEL"
        },
        {
            "price": 5357.72,
            "stoppage": 0,
            "airlines": [
                "I5"
            ],
            "images": "https://images.kiwi.com/airlines/32x32/I5.png",
            "departure_time": 1597216200,
            "arrival_time": 1597226100,
            "deep_link": "https://www.kiwi.com/deep?from=DEL&to=GOI&departure=12-08-2020&flightsId=0711207c48360000baffe9ed_0&price=68&passengers=1&affilid=picky&lang=en&currency=EUR&booking_token=AB96-VnCilG53qHK-IFgdD0FIDq24SvPsMbwnhsFmg9OpJFULh2-msDGmf3s48-Ym-rh5A6Ncgr6dhCN2MtlDTiZgxG70XjroIHOdjh4rsrejkAB0tVcr7TqURMoHYh3SxX3CrKxiSyD9oJJU0TteS4FevkE1rc9H61T1w2tQgk4vrvE91lVwMyHyZRz5Sm3XuVQFNi6r-xuwbFlu3Z4L3YILr-FFx1oTCMf0YHo-Ld8Abb2vXlNmqXAnULJuPrSneUXmxxlv5g58lBXaNRa568F-OyOCX5d05tTjj50B2NCBiwkWsGh30PKeFxLv7qMpYnNVxvytr1KO_PFgYWMoTOw2FwuTngz5oUVLylmif4CCpM5o3dZaj9KwtSNWdtAXYyjMJ9bat5UD4_0EXn9bc4f15Fyysf7huc71FFLFjTO8MlA0wQ3ofs64cSTU8TzbWLcUFVkaVdRydAY7FXC9GTBLUmWyKrHirWH6_APqmkPCaT6C7M_Ezqt4PP57ENYTNehtOzv_4go3jMRJf8wchS2YSPrRR2Rp-vbA0HRDibgji8nnvSy5DpoCfORaoDiTopHcsDjVOah6fdn1wYoCybL9YrFHf2V2_7o51fC8hRQ43heW6iNvzX7YwCca4c4HJ7YwoxHvmA2E0_ZqQy9BJHBsEVosp3uAeb8uxNStONM=",
            "fly_duration": "2h 45m",
            "origin": "DEL"
        },
        ....
    ]
}
```

## TOURS

This API is used to get all the latest tours and packages provided by the different organisations:

### Usage:
GET : https://sih-backend.herokuapp.com/tours
RES: 
```json
[
    {
        "description": "About the DestinationNestled on the country’s western coast, Goa forms the smallest state of India. This beautiful destination is known popularly for its rich Portuguese heritage, delectable seafood, pristine beaches, and crazy nightlife. The state’s capital city, Panjim lies at the center and is conveniently connected to the remaining parts of the world by international flights, trains, and buses, making tourism nurture and grow in the state of Goa. Millions of national and international tourists visit this enchanting place throughout the year with the best Family holiday packages in Goa.Flaunting a long coastline extending over a length of more than 100 kilometers, the coastal state of Goa is packed with a plethora of propelling beaches that can be explored with the traveler-friendly Goa tour packages for family. The North Goa beaches of Calangute, Baga, Arambol, and Anjuna have received much popularity all over the world and are known for their colorful beach shacks, bustling nightlife culture, and exhilarating water sports. These hyperactive beaches are mostly frequented by the adrenaline junkies and party animals. The South Goa beaches of Palolem and Agonda are calmer and quieter and are popular among the peace and solitude seekers. Once served as a Portuguese colony, the state of Goa is home to a number of impressive architectural wonders from the ancient colonial period, which can be discovered with the Goa packages for families. One can find a myriad of old bungalows and cathedrals that showcase the traditional Portuguese architectural designs. Such engineering designs can be witnessed in structures like the Basilica of Bom Jesus, Se Cathedral, Church of St. Francis of Assisi, and Terekhol Fort. The locals of Goa are very friendly and accomodating, which makes this travel destination quite safe and comfortable for tourists, including solo travelers and families.      About the Goa Family Tour Package:- Explore the unparalleled charm of Goa with the well-curated Family holiday packages in Goa. - The itinerary of this amazing package will let you discover the top tourist destinations of South and North Goa and offer you the chance to indulge in a plethora of fun-filled and exhilarating activities. - As you tour through the streets of the state with our Goa packages for family, you will witness many iconic structures of the Colonial era including the Se Cathedral and the Basilica of Bom Jesus. - You will also be allowed to relish the authentic Goan seafood and experience adrenaline-pumping water activities on the various beaches of the state. - The Goa tour packages for family will also offer you an ample amount of time to enjoy leisure time with your family, shopping and experiencing the enthralling nightlife of the place. - Not to forget, our Family holiday packages in Goa will even offer you the best accommodation facility and hassle-free transfer services.",
        "img": "https://images.thrillophilia.com/image/upload/s--ww02UW-U--/c_fill,f_auto,fl_strip_profile,g_center,h_183,q_auto,w_245/v1/images/photos/000/273/772/original/1590039644_shutterstock_1389663329.jpg.jpg",
        "price": "INR 13,500",
        "no_of_ratings": "28 Ratings",
        "ratings": "5.0",
        "time": "5D/4N",
        "title": "Family Holiday Packages in Goa - Flat 12% off",
        "link": "/tours/family-holiday-packages-in-goa"
    },
...
]
```
### TOUR DETAILS

The API helps get the details of the Tours:
GET: https://sih-backend.herokuapp.com/toursDetails?id=grande-island-trip-water-sports-combo-goa

RES: 
```json
{
    "img": [
        "https://images.thrillophilia.com/image/upload/s--DX6oeFc---/c_fill,f_auto,fl_strip_profile,h_600,q_auto,w_975/v1/images/photos/000/136/961/original/1585372756_2020-03-26.jpg.jpg?1585372756",
    ],
    "title": "Grand Island Goa Water Sports",
    "highlight": "Multiple Watersports Activities Included\nMost Popular Water Sports Combo\nExperience thrilling Scuba Diving\nComplimentary photos and Videos\n\n    Cashback of 1250\n \n",
    "description": "There is an activity briefing session before conducting any water sport which is about (8-10 minutes), followed by a wait time of about 5-7 minutes before the start of the next activity. ",
    "ratings_count": "928 ratings",
    "rating": "5.0",
    "price": "1,599"
}
```
### API FOR DEPLOYED ML MODEL TO IDENTIFY MONUMENTS FROM IMAGES

POST: https://automl.googleapis.com/v1beta1/projects/858959113323/locations/us-central1/models/ICN1895912089029967872:predict

req: 
```json
{
  "payload": {
    "image": {
      "imageBytes": "BASE64 Encoded IMAGE"
    }
  }
}
```

res: 
```json
  {
    "payload": [
      "annotationSPecId" : "1234q24536323wef",
      "classification": {
        "score" : 0.9987635
      },
      "displayName": "1"
    ]
  }
```
 NOTE: This API required OAuth2 authentication. To verify please raise an issue:
 
