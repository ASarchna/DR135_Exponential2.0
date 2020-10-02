# DR135_Exponential2.0

This branch contains the information about the data collection and model performances.

## Data Collection

Selinium is used to download the images of different landmark/monuments. The code can be found in webscrape.py.
The file webscrape.py needs an updated webdriver (here chromedriver) for execution. Make sure that the web driver version is not outdated and is in sync with your browser.

## Performance of different models on the scrapped Data:

Optimizer used: RMS.

Not Applied Adam and momentum etc. You may check if it improves performance in your case.

denseNet121 : train 79.26 cross_val 72.31 20 epochs

mobileNet : train 81.06 cross_val 75.11 40 epochs

vgg16 : train 71.41 cross_val 74.68 20 epochs

resnet50 : train 80.00 cross_val 7.87 20 epochs

mobileNet : train 85.44 cross_val 79.63 20 x3 epochs


