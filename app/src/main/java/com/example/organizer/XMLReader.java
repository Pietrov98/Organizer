package com.example.organizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public abstract class XMLReader {
    /*
    id
    time
    title
    content
     */

    public static ArrayList<Memento> parseXML(FileInputStream fileInputStream) {
        System.out.println("dziala_odczyt");
        ArrayList<Memento> activities = new ArrayList<>();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader((inputStreamReader));
            String lines;
            while((lines = bufferedReader.readLine()) != null)
            {
                Memento memento = new Memento();
                memento.setId(lines);
                for(int i = 0; i < 3; i++)
                {
                    lines = bufferedReader.readLine();
                    if(i == 0)
                        memento.setTime(lines);
                    else if(i == 1)
                        memento.setTitle(lines);
                    else
                        memento.setContent(lines);
                }
                activities.add(memento);
            }
            return activities;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    private static ArrayList<Memento> processParsing(XmlPullParser parser) throws IOException, XmlPullParserException {
//        ArrayList<Memento> activities = new ArrayList<>();
//        int eventType = parser.getEventType();
//        Memento currentActivity = null;
//
//        int i = 0;
//        while(eventType != XmlPullParser.END_DOCUMENT)
//        {
//            String eltname = null;
//            switch (eventType) {
//                case XmlPullParser.START_TAG:
//                    eltname = parser.getName();
//                    if("memento".equals(eltname))
//                    {
//                        currentActivity = new Memento();
//                        activities.add(currentActivity);
//                        currentActivity.setId(String.valueOf(i));
//                    }
//                    else if(currentActivity != null)
//                    {
//                        if("time".equals(eltname))
//                        {
//                            currentActivity.setTime(parser.nextText());
//                        }
//                        else if("title".equals(eltname))
//                        {
//                            currentActivity.setTitle(parser.nextText());
//                        }
//                        else if("content".equals(eltname))
//                        {
//                            currentActivity.setContent(parser.nextText());
//                        }
//                    }
//                    break;
//            }
//            eventType = parser.next();
//            i++;
//        }
//        return activities;
//
//    }

}
