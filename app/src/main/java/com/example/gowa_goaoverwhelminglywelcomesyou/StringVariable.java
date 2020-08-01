package com.example.gowa_goaoverwhelminglywelcomesyou;

import java.util.ArrayList;

public class StringVariable {

    ArrayList<MonumentsModel> monumentsList;
    public static final MonumentsModel AGUADA_FORT = new MonumentsModel("Aguada Fort",15.493225,73.773113," A Fortress of Tranquility.Fort Aguada is an epitome of Portuguese architecture built in the 17th century.");
    public static final MonumentsModel AZAD_MAIDAN = new MonumentsModel("Azad Maidan",15.500362,73.826406,"Azad Maidan is dedicated to Dr. Tristao de Braganza Cunha who was a Goan Freedom Fighter.");
    public static final MonumentsModel BASILLICA_OF_BOM_JESUS = new MonumentsModel("Basillica of Bom Jesus",15.501080,73.911745,"The Basilica of Bom Jesus church is one of a kind in India and is known for its exemplary baroque architecture. ");
    public static final MonumentsModel CHAPORA_FORT = new MonumentsModel("Chapora Fort",15.606257,73.736519,"A bastion of history and beauty - Chapora Fort is in North Goa and is located 10 km away from Mapusa. The Fort is undeniably one of the most famous forts in Goa. ");
    public static final MonumentsModel CHURCH_OF_ST_CAJETAN = new MonumentsModel("Church of St. Cajetan",15.505897,73.915160,"This church has a significant resemblance with the St. Peters Basilica in Rome. On the left, there are three altars dedicated to the Holy Family, Our Lady of Piety and St.Clare and the right-side altars are dedicated to St. Agnes, St. Cajetan and St. John. ");
    public static final MonumentsModel CHURCH_OF_ST_FRANCIS_OF_ASSISI = new MonumentsModel("Church of St. Francis of Assisi",15.649367,73.830579,"The Church of St. Francis of Assisi was built in 1661 by the Portuguese in the Portuguese Viceroyalty of India. The Church of St. Francis of Assisi, together with a convent, was established by eight Portuguese Franciscan friars who landed in Goa in 1517");
    public static final MonumentsModel FORT_TIRACOL = new MonumentsModel("Tiracol Fort",15.721810, 73.686638,"Across the Terekhol river from the Querim beach in North Goa lies the majestic Tiracol Fort. Also known as the Terekhol Fort, this magnificent structure was once a crucial part of the maritime defence of the Portuguese colonists.");
    public static final MonumentsModel REIS_MAGOS_FORT = new MonumentsModel("Reis Magos Fort",15.497266,73.809561,"Located on the banks of the mesmerizing River Mandovi, Reis Magos Fort served as a turret to guard the mouth of the estuary and as accommodation for the Portuguese officials since 1551. ");
    public static final MonumentsModel SE_CATHEDRAL = new MonumentsModel("Se Cathedral",15.504022,73.912203,"The majestic white beauty of Se Cathedral in Goa is a shrine dedicated to Saint Catherine. The cathedral is also known as the Sé Catedral de Santa Catarina and is an important centre of the Latin Rite Roman Catholic Archdiocese of Goa and Daman. ");

    public static MonumentsModel getAguadaFort() {
        return AGUADA_FORT;
    }

    public static MonumentsModel getAzadMaidan() {
        return AZAD_MAIDAN;
    }

    public static MonumentsModel getBasillicaOfBomJesus() {
        return BASILLICA_OF_BOM_JESUS;
    }

    public static MonumentsModel getChaporaFort() {
        return CHAPORA_FORT;
    }

    public static MonumentsModel getChurchOfStCajetan() {
        return CHURCH_OF_ST_CAJETAN;
    }

    public static MonumentsModel getChurchOfStFrancisOfAssisi() {
        return CHURCH_OF_ST_FRANCIS_OF_ASSISI;
    }

    public static MonumentsModel getFortTiracol() {
        return FORT_TIRACOL;
    }

    public static MonumentsModel getReisMagosFort() {
        return REIS_MAGOS_FORT;
    }

    public static MonumentsModel getSeCathedral() {
        return SE_CATHEDRAL;
    }

    public ArrayList<MonumentsModel> getMonumentsList(){
        monumentsList = new ArrayList<>();
        monumentsList.add(StringVariable.AGUADA_FORT);
        monumentsList.add(StringVariable.AZAD_MAIDAN);
        monumentsList.add(StringVariable.BASILLICA_OF_BOM_JESUS);
        monumentsList.add(StringVariable.CHAPORA_FORT);
        monumentsList.add(StringVariable.CHURCH_OF_ST_CAJETAN);
        monumentsList.add(StringVariable.CHURCH_OF_ST_FRANCIS_OF_ASSISI);
        monumentsList.add(StringVariable.SE_CATHEDRAL);
        monumentsList.add(StringVariable.REIS_MAGOS_FORT);

        return monumentsList;
    }
    public static final String BLOGS = "blogs";




    public static final String SLIDER_DETAILS = "slider-details";
    public static final String SLIDER_DETAILS_CHURCHES = "churches";
    public static final String SLIDER_DETAILS_NAME = "name";
    public static final String SLIDER_DETAILS_LIKES = "likes";
    public static final String SLIDER_DETAILS_VIEWS = "views";
    public static final String SLIDER_DETAILS_COMMENTS = "comments";
    public static final String SLIDER_DETAILS_DESCRIPTION = "description";



    //FOR PLACE DETAILS
    public static final String PLACE_DETAILS = "place-details";
    public static final String PLACE_DETAILS_NAME = "name";
    public static final String PLACE_DETAILS_DESC = "description";
    public static final String PLACE_DETAILS_DESC_HINDI = "description-hindi";
    public static final String PLACE_DETAILS_LOCATION = "location";

    //COMMENT
    public static final String COMMENTS = "comments";
    public static final String COMMENT_POSTED_BY = "postedBy";
    public static final String COMMENT_TIME = "time";
    public static final String COMMENT_IMAGE_URI = "userURI";
    public static final String COMMENT_CONTENT = "content";


}
