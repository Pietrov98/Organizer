package com.example.organizer;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public abstract class XMLReader {

    public static ArrayList<Activity> parseXML(InputStream is)
    {
        try {
            XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();
            //InputStream is = getAssets().open("mementos.xml");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(is, null);

            return processParsing(parser);

        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ArrayList<Activity> processParsing(XmlPullParser parser) throws IOException, XmlPullParserException {
        ArrayList<Activity> activities = new ArrayList<>();
        int eventType = parser.getEventType();
        Activity currentActivity = null;

        int i = 0;
        while(eventType != XmlPullParser.END_DOCUMENT)
        {
            String eltname = null;
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    eltname = parser.getName();
                    if("activity".equals(eltname))
                    {
                        currentActivity = new Activity();
                        activities.add(currentActivity);
                        currentActivity.setId(String.valueOf(i));
                    }
                    else if(currentActivity != null)
                    {
                        if("time".equals(eltname))
                        {
                            currentActivity.setTime(parser.nextText());
                        }
                        else if("title".equals(eltname))
                        {
                            currentActivity.setTitle(parser.nextText());
                        }
                        else if("content".equals(eltname))
                        {
                            currentActivity.setContent(parser.nextText());
                        }
                    }
                    break;
            }
            eventType = parser.next();
            i++;
        }
        return activities;

    }

}
