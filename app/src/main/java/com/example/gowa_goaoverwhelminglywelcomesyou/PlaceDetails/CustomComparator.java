package com.example.gowa_goaoverwhelminglywelcomesyou.PlaceDetails;

import java.util.Comparator;

public class CustomComparator implements Comparator<CommentModel> {
    @Override
    public int compare(CommentModel o1, CommentModel o2) {
        return -((int)(o1.getTimestamp())-(int)(o2.getTimestamp()));
    }
}
