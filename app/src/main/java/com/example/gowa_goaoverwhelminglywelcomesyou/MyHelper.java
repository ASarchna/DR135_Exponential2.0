package com.example.gowa_goaoverwhelminglywelcomesyou;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyHelper {
    public static Map<Integer,String> map = new HashMap<>();

    public static String getMappedMonument(int label){
        switch (label){
            case 1: return "aguada-fort";
            case 2: return "azad-maidan";
            case 3: return "basilica-of-bom-jesus";
            case 4: return "church_of_st._cajetan";
            case 5: return "church-of-st-francis-of-assisi-goa";
            case 6: return "reis-magos-fort";
            case 7:return "se-cathedral";
            case 8: return "church-of-our-lady-of-the-immaculate-conception";
            case 9: return "fort-tiracol";
            case 10: return "viceroys-arch";
        }
        return "se-cathedral";
    }
    public static String getMappedMonumentName(String name){
        switch (name){
            case "aguada-fort": return "Aguada Fort";
            case    "azad-maidan": return "Azad Maidan";
            case    "basilica-of-bom-jesus": return "Basillica of Bom Jesus";
            case    "chapora-fort": return "Chapora Fort";
            case    "church-of-our-lady-of-the-immaculate-conception": return "Church of our lady of the immaculate Conception";
            case    "church-of-st-cajetan": return "Church of St Cajetan";
            case    "church-of-st-francis-of-assisi-goa": return "Church of St. Francis of Assisi goa";
            case    "flea-market": return "Flea Market";
            case    "fort-tiracol": return "Fort Tiracol";
            case    "night-market":return "Night Market";
            case    "our-lady-of-the-immaculate-conception-church":return "Our Lady of the Immaculate Conception Church";
            case    "reis-magos-fort":return "Reis Magos Fort";
            case    "se-cathedral":return "Se Cathedral";
            case    "tiracol-fort":return "Tiracol Fort";
            case    "viceroys-arch":return "Viceroys Arch";
        }
        return "Se Cathedral";
    }

    public static String getMappedMonumentDesc(String id){
        switch (id){
            case "aguada-fort": return "Fort Aguada is a 17th-century Portuguese fort looking out at the confluence of Mandovi River and the Arabian Sea. The crumbling ramparts of the fort stand on the Sinquerim Beach, approximately 18 km from Panjim. The highlight of the fort is a lone four-storey lighthouse (which is one-of-its-kind in Asia )and a stunning view of the sunset.";
            case    "azad-maidan": return "Azad Maidan";
            case    "basilica-of-bom-jesus": return "The Basilica of Bom Jesus Church located in Goa is one of a kind in India and is known for its exemplary baroque architecture. Built in 1594 and consecrated in 1605, the building of this church coincides with the beginning of Christianity in India. The church is located in Old Goa in Bainguinim about 10 km away from Panjim. The oldest church in Goa, it holds the remains of St. Francis Xavier, a special friend of St. Ignatius Loyola with whom he founded the Society of Jesus (Jesuits). Even after 400 years, the remains are in good condition and are taken out once every decade. \n" +
                    "\n" +
                    "A site with rich cultural and religious significance, the Basilica of Bom Jesus has been declared a World Heritage Site by UNESCO. Literally translating to 'Holy Jesus', this is the only church in Old Goa that is not been plastered on the outside. The facade of the church has triangular roofing which is delicately carved with the initials 'IHS', which is an abbreviation for Jesuit emblem meaning 'Jesus, Saviour of Men'.\n" +
                    "\n" +
                    "Inside, the floor of the Basilica is made of marble mosaic with precious stones, giving it a solemn appearance. The interior is embellished with a screen that runs from the floor to the ceiling and has the image of St. Ignatius Loyola protecting an infant Jesus. Above all is the image of the Holy Trinity, which is the most sacred symbol in Christianity. The Basilica of Bom Jesus is over 408 years old and is open to the public every day.";
            case    "chapora-fort": return "Chapora Fort";
            case    "church-of-our-lady-of-the-immaculate-conception": return "Church of our lady of the immaculate Conception";
            case    "church-of-st-cajetan": return "Church of St Cajetan";
            case    "church-of-st-francis-of-assisi-goa": return "Church of St. Francis of Assisi goa";
            case    "flea-market": return "Flea Market";
            case    "fort-tiracol": return "Fort Tiracol";
            case    "night-market":return "Night Market";
            case    "our-lady-of-the-immaculate-conception-church":return "Our Lady of the Immaculate Conception Church";
            case    "reis-magos-fort":return "Reis Magos Fort";
            case    "se-cathedral":return "Se Cathedral";
            case    "tiracol-fort":return "Tiracol Fort";
            case    "viceroys-arch":return "Viceroys Arch";
        }
        return "Se Cathedral";
    }

    public static String getbase64Image(Bitmap img){
        ByteArrayOutputStream baos =new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.JPEG,50,baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes,Base64.DEFAULT);
        return encodedImage;
    }

    public static ArrayList<LocalRecommendationCard> getRecommendations(Context c){
        ArrayList<LocalRecommendationCard> localRecommendationList = new ArrayList<>();
        SharedPreferences pref = c.getSharedPreferences("UserPrefs", 0); // 0 - for private mode
//        editor.putString("name", name);
//        editor.putString("age", age);
//        editor.putString("married", married);
//        editor.putString("gender", gender);
//        editor.putString("religion", religion);
        String age = pref.getString("age","25");
        String gender = pref.getString("age","Male");
        String married = pref.getString("married","Single");
        String religion = pref.getString("married","Christianity");

//        String[] items = new String[]{"Single", "Married", "Others"};
//        String[] items2 = new String[]{"Male", "Female", "Others"};
//        String[] items3 = new String[]{"Christianity", "Hinduism", "Islam", "Buddhism", "others"};

        if(Integer.parseInt(age)<=20){
            localRecommendationList.add(new LocalRecommendationCard("Archaeological Museum of Goa","Located at the back of the Convent and Church of St. Francis of Assisi, this museum contains many important and beautiful artefacts of the Portuguese rule in India. Its treasures are divided amongst eight galleries. The most important of these are considered to be the portraits of the long-dead Viceroys and Governors of Goa.\n" + "\n" + "The museum also has a phenomenal collection of stamps, religious artefacts and other such treasures","230","https://goa-tourism.com/GTDC-holidays/images/banner-museum-archaeological.jpg","archaeological-museum-of-goa"));
            localRecommendationList.add(new LocalRecommendationCard("Museum of Christian Art","Richly embroidered vestments, chalices and other sacred receptacles made out of precious metals and inlaid with precious and semi-precious stones, carved ivory, gorgeous paintings and statuary, all this and more is on display at the museum of Christian Art. The most unique aspect of this wonderful collection is that much of it was in fact created by local artists and artisans, many of whom were traditional Hindu artists.\n" +
                    "\n" +
                    "Located as it is in the old convent of Santa Monica, the museum is in the heart of Old Goa, a place steeped in religious history and crowned with many important religious monuments. The museum’s collection is extensive and well maintained. It is one of the few institutions of its kind in Asia.\n" +
                    "\n" ,"160","https://lh3.googleusercontent.com/proxy/qFskgqP6thTF2vw10iK-6bOZrYwiUiUz_IKjVN8lal7UkZgC3Voeqi16cTMCT_wEe7H9hxkFiXVlNG7k5iRNKeju-DMeFwchOgiOpnfzLNIxbkPjz10g5UtkH1C7PYQEuma2MDA8HvFpDmJbMesBsHWbF0qr0nQLJHwa9XX0","museum-of-christian-art"));
            if(religion.equals("Christianity")){
                localRecommendationList.add(new LocalRecommendationCard("Church of our lady of the immaculate Conception","The Our Lady of the Immaculate Conception Church (Igreja de Nossa Senhora da Imaculada Conceição) is located in Panjim, Goa, India. The Church conducts Mass every day in English, Konkani, and Portuguese.[1]\n" + "\n" + "The colonial Portuguese Baroque style church was first built in 1541 as a chapel on a hill side overlooking the city of Panjim. It was eventually replaced by a larger church in the 1600s as part of Portuguese Goa's religious expansion.[2][3] This church houses the ancient bell that was removed from the Augustinian ruins of the Church of Our Lady of Grace (Nossa Senhora da Graça) in the once famed city of Old Goa. This bell is considered to be the second largest of its kind in Goa, surpassed only by the Golden Bell which resides in the Sé Cathedral in Old Goa.","230","https://upload.wikimedia.org/wikipedia/commons/thumb/1/1e/0438_Panaji_-_Church_of_Our_Lady_of_the_Immaculate_Conception_2006-02-13_13-46-23_%2810543370125%29.jpg/1024px-0438_Panaji_-_Church_of_Our_Lady_of_the_Immaculate_Conception_2006-02-13_13-46-23_%2810543370125%29.jpg","church-of-our-lady-of-the-immaculate-conception"));
                localRecommendationList.add(new LocalRecommendationCard("Church of St Cajetan","The Our Lady of the Immaculate Conception Church (Igreja de Nossa Senhora da Imaculada Conceição) is located in Panjim, Goa, India. The Church conducts Mass every day in English, Konkani, and Portuguese.[1]\n" + "\n" + "The colonial Portuguese Baroque style church was first built in 1541 as a chapel on a hill side overlooking the city of Panjim. It was eventually replaced by a larger church in the 1600s as part of Portuguese Goa's religious expansion.[2][3] This church houses the ancient bell that was removed from the Augustinian ruins of the Church of Our Lady of Grace (Nossa Senhora da Graça) in the once famed city of Old Goa. This bell is considered to be the second largest of its kind in Goa, surpassed only by the Golden Bell which resides in the Sé Cathedral in Old Goa.","230","https://www.makemytrip.com/travel-guide/media/dg_image/goa/14_St-Cajetans_Church.jpg","church-of-st-cajetan"));
                localRecommendationList.add(new LocalRecommendationCard("Church of St. Francis of Assisi goa","The Our Lady of the Immaculate Conception Church (Igreja de Nossa Senhora da Imaculada Conceição) is located in Panjim, Goa, India. The Church conducts Mass every day in English, Konkani, and Portuguese.[1]\n" + "\n" + "The colonial Portuguese Baroque style church was first built in 1541 as a chapel on a hill side overlooking the city of Panjim. It was eventually replaced by a larger church in the 1600s as part of Portuguese Goa's religious expansion.[2][3] This church houses the ancient bell that was removed from the Augustinian ruins of the Church of Our Lady of Grace (Nossa Senhora da Graça) in the once famed city of Old Goa. This bell is considered to be the second largest of its kind in Goa, surpassed only by the Golden Bell which resides in the Sé Cathedral in Old Goa.","230","https://goa-tourism.com/GTDC-holidays/images/st-francis-assissi-small.jpg","church-of-st-francis-of-assisi-goa"));
            }
            else if(religion.equals("Hinduism")){
                localRecommendationList.add(new LocalRecommendationCard("Shree Mangueshi Temple", "Shri Mangesh temple is located at Mangeshi Village in Priol, Ponda taluk, Goa. It is at a distance of 1 km from Mardol close to Nagueshi, 21 km from Panaji the capital of Goa, and 26 km from Margao. H.H.Shrimad Swamiji of Shri Kavale Math is Spiritual head Of Shri Manguesh Saunsthan, Mangeshi.","230","https://upload.wikimedia.org/wikipedia/commons/7/7d/Shri-Mangesh-Temple%2CGoa.JPG","shree-mangueshi-temple"));
                localRecommendationList.add(new LocalRecommendationCard("Shree Shantadurga Mandir", "Shri Mangesh temple is located at Mangeshi Village in Priol, Ponda taluk, Goa. It is at a distance of 1 km from Mardol close to Nagueshi, 21 km from Panaji the capital of Goa, and 26 km from Margao. H.H.Shrimad Swamiji of Shri Kavale Math is Spiritual head Of Shri Manguesh Saunsthan, Mangeshi.","230","https://i.pinimg.com/originals/48/07/f7/4807f77fac39bee325c6f81be99770ad.jpg","shree-mangueshi-temple"));
            }
        }
        else if(Integer.parseInt(age)>50){
            if(religion.equals("Christianity")){
                localRecommendationList.add(new LocalRecommendationCard("Church of our lady of the immaculate Conception","The Our Lady of the Immaculate Conception Church (Igreja de Nossa Senhora da Imaculada Conceição) is located in Panjim, Goa, India. The Church conducts Mass every day in English, Konkani, and Portuguese.[1]\n" + "\n" + "The colonial Portuguese Baroque style church was first built in 1541 as a chapel on a hill side overlooking the city of Panjim. It was eventually replaced by a larger church in the 1600s as part of Portuguese Goa's religious expansion.[2][3] This church houses the ancient bell that was removed from the Augustinian ruins of the Church of Our Lady of Grace (Nossa Senhora da Graça) in the once famed city of Old Goa. This bell is considered to be the second largest of its kind in Goa, surpassed only by the Golden Bell which resides in the Sé Cathedral in Old Goa.","230","https://upload.wikimedia.org/wikipedia/commons/thumb/1/1e/0438_Panaji_-_Church_of_Our_Lady_of_the_Immaculate_Conception_2006-02-13_13-46-23_%2810543370125%29.jpg/1024px-0438_Panaji_-_Church_of_Our_Lady_of_the_Immaculate_Conception_2006-02-13_13-46-23_%2810543370125%29.jpg","church-of-our-lady-of-the-immaculate-conception"));
                localRecommendationList.add(new LocalRecommendationCard("Church of St Cajetan","The Our Lady of the Immaculate Conception Church (Igreja de Nossa Senhora da Imaculada Conceição) is located in Panjim, Goa, India. The Church conducts Mass every day in English, Konkani, and Portuguese.[1]\n" + "\n" + "The colonial Portuguese Baroque style church was first built in 1541 as a chapel on a hill side overlooking the city of Panjim. It was eventually replaced by a larger church in the 1600s as part of Portuguese Goa's religious expansion.[2][3] This church houses the ancient bell that was removed from the Augustinian ruins of the Church of Our Lady of Grace (Nossa Senhora da Graça) in the once famed city of Old Goa. This bell is considered to be the second largest of its kind in Goa, surpassed only by the Golden Bell which resides in the Sé Cathedral in Old Goa.","230","https://www.makemytrip.com/travel-guide/media/dg_image/goa/14_St-Cajetans_Church.jpg","church-of-st-cajetan"));
                localRecommendationList.add(new LocalRecommendationCard("Church of St. Francis of Assisi goa","The Our Lady of the Immaculate Conception Church (Igreja de Nossa Senhora da Imaculada Conceição) is located in Panjim, Goa, India. The Church conducts Mass every day in English, Konkani, and Portuguese.[1]\n" + "\n" + "The colonial Portuguese Baroque style church was first built in 1541 as a chapel on a hill side overlooking the city of Panjim. It was eventually replaced by a larger church in the 1600s as part of Portuguese Goa's religious expansion.[2][3] This church houses the ancient bell that was removed from the Augustinian ruins of the Church of Our Lady of Grace (Nossa Senhora da Graça) in the once famed city of Old Goa. This bell is considered to be the second largest of its kind in Goa, surpassed only by the Golden Bell which resides in the Sé Cathedral in Old Goa.","230","https://goa-tourism.com/GTDC-holidays/images/st-francis-assissi-small.jpg","church-of-st-francis-of-assisi-goa"));
            }
            else if(religion.equals("Hinduism")){
                localRecommendationList.add(new LocalRecommendationCard("Shree Mangueshi Temple", "Shri Mangesh temple is located at Mangeshi Village in Priol, Ponda taluk, Goa. It is at a distance of 1 km from Mardol close to Nagueshi, 21 km from Panaji the capital of Goa, and 26 km from Margao. H.H.Shrimad Swamiji of Shri Kavale Math is Spiritual head Of Shri Manguesh Saunsthan, Mangeshi.","230","https://upload.wikimedia.org/wikipedia/commons/7/7d/Shri-Mangesh-Temple%2CGoa.JPG","shree-mangueshi-temple"));
                localRecommendationList.add(new LocalRecommendationCard("Shree Shantadurga Mandir", "Shri Mangesh temple is located at Mangeshi Village in Priol, Ponda taluk, Goa. It is at a distance of 1 km from Mardol close to Nagueshi, 21 km from Panaji the capital of Goa, and 26 km from Margao. H.H.Shrimad Swamiji of Shri Kavale Math is Spiritual head Of Shri Manguesh Saunsthan, Mangeshi.","230","https://i.pinimg.com/originals/48/07/f7/4807f77fac39bee325c6f81be99770ad.jpg","shree-mangueshi-temple"));
            }
        }
        return localRecommendationList;

    }
}
